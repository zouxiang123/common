package com.xtt.common.dao.po;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.dao.model.SysUser;
import com.xtt.platform.util.Constants;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class SysUserPO extends SysUser {

    private String roleName;
    private String birthdayShow;
    private Integer lastStep;
    private String nextStepName;
    private String roleId;
    private String sexShow;
    private String parentRoleId;
    private String positionShow;
    @SuppressWarnings("unused")
    private String mobileShow;

    private Integer value;// 统计值

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBirthdayShow() {
        birthdayShow = DateFormatUtil.convertDateToStr(super.getBirthday(), Constants.SYS_YYYY_MM_DD);
        return birthdayShow;
    }

    public void setBirthdayShow(String birthdayShow) {
        if (StringUtils.isNotEmpty(birthdayShow)) {
            super.setBirthday(DateFormatUtil.convertStrToDate(birthdayShow, Constants.SYS_YYYY_MM_DD));
        }
        this.birthdayShow = birthdayShow;
    }

    public Integer getLastStep() {
        return lastStep;
    }

    public void setLastStep(Integer lastStep) {
        this.lastStep = lastStep;
    }

    public String getNextStepName() {
        return nextStepName;
    }

    public void setNextStepName(String nextStepName) {
        this.nextStepName = nextStepName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getPositionShow() {
        return positionShow;
    }

    public void setPositionShow(String positionShow) {
        this.positionShow = positionShow;
    }

    public String getMobileShow() {
        return StringUtil.formatMobile(super.getMobile());
    }

    public void setMobileShow(String mobileShow) {
        this.mobileShow = mobileShow;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
