package com.xtt.common.assay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xtt.common.assay.service.IGenerationDictService;
import com.xtt.common.dao.mapper.DictHospitalLabMapper;
import com.xtt.common.dao.mapper.PatientAssayRecordMapper;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateUtil;

@Service("generationDictServiceImpl")
@Transactional
public class GenerationDictServiceImpl implements IGenerationDictService {

    @Resource
    private PatientAssayRecordMapper patientAssayRecordMapper;

    // 检验项目字典
    @Resource
    private DictHospitalLabMapper dictHospitalLabMapper;

    /* 
     * 检验字典同步
     */
    @Override
    public String lisDictService(CmQueryPO po) {
        return autoLisInfoToDict(po);
    }

    /**
     * 检验数据同步到字典
     * 
     * @Title: autoLisInfoToDict
     * @return
     * 
     */
    private String autoLisInfoToDict(CmQueryPO queryPO) {
        // =====================================================================================================
        List<PatientAssayRecordPO> lisDict = patientAssayRecordMapper.getLisDict(queryPO.getStartDate());
        int i = 0, j = 0;
        // String fk_tenant_id = (String) PropertiesUtil.getContextProperty("fk_tenant_id");
        int parseInt = UserUtil.getTenantId();

        for (PatientAssayRecordPO po : lisDict) {
            DictHospitalLabPO dicPo = new DictHospitalLabPO();

            String itemName = po.getItemName();
            String itemCode = po.getItemCode();
            String reference = po.getReference();

            dicPo.setGroupId(po.getGroupId());
            dicPo.setGroupName(po.getGroupName());
            dicPo.setItemCode(itemCode);
            dicPo.setItemName(itemName);
            dicPo.setReference(reference);

            BigDecimal valueMax = po.getValueMax();
            BigDecimal valueMin = po.getValueMin();
            if (valueMax != null) {
                dicPo.setMaxValue(valueMax);
                dicPo.setPersonalMaxValue(valueMax);
            }
            if (valueMin != null) {
                dicPo.setMinValue(valueMin);
                dicPo.setPersonalMinValue(valueMin);
            }

            String result = po.getResult();
            if (StringUtil.isNumber(result)) {
                dicPo.setValueType(1);
                dicPo.setDateType("n");
            } else {
                dicPo.setValueType(2);
                dicPo.setDateType("s");
            }

            dicPo.setUnit(po.getValueUnit());

            // 固定输出数据
            Date currDate = DateUtil.getCurrDate();
            dicPo.setFkTenantId(parseInt);
            dicPo.setCreateTime(currDate);
            dicPo.setCreateUserId(1L);
            dicPo.setUpdateTime(currDate);
            dicPo.setUpdateUserId(1L);

            List<DictHospitalLabPO> selectAllByItemCode = dictHospitalLabMapper.selectAllByItemCode(itemCode);
            if (selectAllByItemCode == null || selectAllByItemCode.size() == 0) {
                String groupId = dicPo.getGroupId();
                String groupName = dicPo.getGroupName();
                if (StringUtil.isNotEmpty(groupId) && StringUtil.isNotEmpty(groupName)) {
                    dictHospitalLabMapper.insert(dicPo);
                    ++i;
                } else {
                    ++j;
                }
            } else {
                ++j;
            }
        }
        String msg = "检验结果表,查询到：" + lisDict.size() + "条,成功插入：" + i + "条, 已存在：" + j + "条.";
        return msg;
    }

    @Override
    public String ordersDictService(CmQueryPO po) {
        return null;
    }

    @Override
    public void buildZkLabItems(CmQueryPO po) {

    }

}
