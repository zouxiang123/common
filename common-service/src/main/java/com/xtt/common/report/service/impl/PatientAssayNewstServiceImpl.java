/**   
 * @Title: PatientAssayNewstServiceImpl.java 
 * @Package com.xtt.txgl.report.service.impl
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月1日 下午7:02:32 
 *
 */
package com.xtt.common.report.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.constants.AssayConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientAssayNewstMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.model.PatientAssayNewst;
import com.xtt.common.dao.po.PatientAssayNewstPO;
import com.xtt.common.dao.po.PatientNewstPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.report.service.IPatientAssayNewstService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateUtil;

/**
 * 患者化验最新一次化验结果
 * 
 * @ClassName: PatientAssayNewstServiceImpl
 * @date: 2016年12月1日 下午7:02:54
 * @version: V1.0
 */
@Service
public class PatientAssayNewstServiceImpl implements IPatientAssayNewstService {

    @Autowired
    private PatientAssayNewstMapper patientAssayNewstMapper;
    @Autowired
    private IPatientAssayClassService patientAssayClassService;
    @Autowired
    private IPatientService patientService;

    @Override
    public Map<String, String> insertAuto(Integer tenantId) {
        patientAssayNewstMapper.deleteNewstAuto(tenantId);
        List<PatientPO> patientList = patientService.getPatientByTenantId(tenantId, false);
        if (CollectionUtils.isEmpty(patientList)) {
            return null;
        }
        // 获取所有化验单提醒列表信息
        List<PatientAssayClass> patientAssayClassArrays = patientAssayClassService.listByTenantIdForAssayNews(tenantId);
        if (CollectionUtils.isNotEmpty(patientAssayClassArrays)) {

            Set<String> itemCodes = new HashSet<String>(patientAssayClassArrays.size());
            for (PatientAssayClass pac : patientAssayClassArrays) {
                itemCodes.add(pac.getItemCode());
            }
            List<PatientAssayNewst> assayList = patientAssayNewstMapper.listLatestAssayRecord(tenantId);
            Set<String> existsItems = new HashSet<>();
            Date now = new Date();
            Long userId = CommonConstants.SYSTEM_USER_ID;
            if (CollectionUtils.isNotEmpty(assayList)) {
                assayList.forEach(assay -> {
                    existsItems.add(assay.getFkPatientId() + "_" + assay.getItemCode());
                    assay.setId(0l);
                    assay.setCreateTime(now);
                    assay.setCreateUserId(userId);
                    assay.setUpdateTime(now);
                    assay.setUpdateUserId(userId);
                    assay.setFkTenantId(tenantId);
                });
            }
            for (Patient patient : patientList) {
                patientAssayClassArrays.forEach(item -> {
                    if (!existsItems.contains(patient.getId() + "_" + item.getItemCode())) {// 当前项目没有化验数据，默认插入一条空的
                        PatientAssayNewst assay = new PatientAssayNewst();
                        assay.setId(0l);
                        assay.setItemCode(item.getItemCode());
                        assay.setItemName(item.getItemName());
                        assay.setFkPatientId(patient.getId());
                        assay.setCreateTime(now);
                        assay.setCreateUserId(userId);
                        assay.setUpdateTime(now);
                        assay.setUpdateUserId(userId);
                        assay.setFkTenantId(tenantId);
                        assayList.add(assay);
                    }
                });
            }
            if (CollectionUtils.isNotEmpty(assayList)) {// 一次插入500条数据
                int batchNo = 500;// 每次插入条数
                int size = assayList.size();
                int page = size / batchNo;
                if (size % batchNo != 0) {
                    page = page + 1;
                }
                for (int i = 0; i < page; i++) {
                    int fromIndex = i * batchNo;
                    int toIndex = fromIndex + batchNo;
                    toIndex = toIndex > size ? size : toIndex;
                    patientAssayNewstMapper.insertList(assayList.subList(fromIndex, toIndex));
                }
            }
        }
        return null;
    }

    @Override
    public Map<Long, List<PatientAssayNewstPO>> findByPatientAssayNewst(List<Long> patientIds) {
        Map<Long, List<PatientAssayNewstPO>> resultMap = new HashMap<Long, List<PatientAssayNewstPO>>();

        // 获取所有化验单提醒列表
        List<PatientAssayClass> patientAssayClassArrays = patientAssayClassService.listByTenantIdForAssayNews(UserUtil.getTenantId());
        if (CollectionUtils.isNotEmpty(patientAssayClassArrays) && CollectionUtils.isNotEmpty(patientIds)) {
            Map<String, PatientAssayClass> classMap = new HashMap<>(patientAssayClassArrays.size());
            patientAssayClassArrays.forEach(item -> {
                classMap.put(item.getItemCode(), item);
            });
            // 获取患者已化验过的项目列表
            List<PatientAssayNewstPO> patientAssayNewstArrays = patientAssayNewstMapper.selectByPatientArray(patientIds, UserUtil.getTenantId());
            if (CollectionUtils.isEmpty(patientAssayNewstArrays)) {
                return resultMap;
            }
            // 计算天数是否超过提醒天数
            Date currentDate = new Date();
            for (PatientAssayNewstPO record : patientAssayNewstArrays) {
                List<PatientAssayNewstPO> resultValues = resultMap.get(record.getFkPatientId());
                if (resultValues == null) {
                    resultValues = new ArrayList<>();
                    resultMap.put(record.getFkPatientId(), resultValues);
                }
                resultValues = resultMap.get(record.getFkPatientId());
                PatientAssayClass assayClass = classMap.get(record.getItemCode());
                if (assayClass == null || assayClass.getAssayDay() == null) {
                    continue;
                }
                // 因为同类项分组的itemCode为对应的分组id，需要显示为分组下所有的itemCode
                record.setItemCode(assayClass.getItemCode());
                // 显示用itemCode,如果是血透唯一标识的item_code，去除其后缀
                record.setItemCodeShow(assayClass.getItemCode().replace(AssayConsts.DICT_UK_SUFFIX, ""));
                if (record.getAssayTime() == null) {// 当前项目未曾化验过
                    record.setState(-1);
                } else {
                    // 化验时间
                    int differDay = DateUtil.getDays(currentDate, record.getAssayTime());
                    int assayRemindDay = assayClass.getAssayDay().intValue();
                    if (differDay > assayRemindDay) {
                        record.setState(1);
                        record.setAssayDay((differDay - assayRemindDay) + "");
                    } else {
                        record.setState(0);
                    }
                }
                resultValues.add(record);
            }
        }
        return resultMap;
    }

    @Override
    public PatientNewstPO listOverduePatients(PatientNewstPO patientNewst) {
        // 处理患者名称简称字段存在多个以“,”号隔开的，输入“,”号搜索时特殊处理掉
        if (StringUtil.isNotEmpty(patientNewst.getName())) {
            patientNewst.setName(patientNewst.getName().replaceAll(",", ",,"));
        }
        if (StringUtil.isNotEmpty(patientNewst.getInitial())) {
            patientNewst.setInitial(patientNewst.getInitial().replaceAll(",", ",,"));
        }
        patientNewst.setResults(patientAssayNewstMapper.listOverduePatients(patientNewst));
        return patientNewst;
    }
}
