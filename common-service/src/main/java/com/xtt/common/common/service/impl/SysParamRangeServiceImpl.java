package com.xtt.common.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysParamRangeService;
import com.xtt.common.dao.mapper.SysParamRangeMapper;
import com.xtt.common.dao.model.SysParamRange;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class SysParamRangeServiceImpl implements ISysParamRangeService {

    @Autowired
    private SysParamRangeMapper sysParamRangeMapper;

    @Override
    public void save(SysParamRange record) {
        if (record.getId() == null) {
            record.setSysOwner(UserUtil.getSysOwner());
            record.setFkTenantId(UserUtil.getTenantId());
            record.setIsEnable(true);
            record.setIsShow(true);
            DataUtil.setSystemFieldValue(record);
            sysParamRangeMapper.insert(record);
        } else {
            DataUtil.setSystemFieldValue(record);
            sysParamRangeMapper.updateByPrimaryKey(record);
        }
    }

    @Override
    public List<SysParamRange> listByCondition(SysParamRange record) {
        return sysParamRangeMapper.listByCondition(record);
    }

    @Override
    public SysParamRange getByItemCode(String itemCode) {
        SysParamRange record = new SysParamRange();
        record.setItemCode(itemCode);
        record.setFkTenantId(UserUtil.getTenantId());
        record.setSysOwner(UserUtil.getSysOwner());
        List<SysParamRange> list = listByCondition(record);
        SysParamRange sysParamRange = list.size() > 0 ? list.get(0) : null;
        return sysParamRange;
    }

}
