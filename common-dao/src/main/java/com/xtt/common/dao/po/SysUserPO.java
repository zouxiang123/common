package com.xtt.common.dao.po;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.dao.model.SysUser;
import com.xtt.platform.util.Constants;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class SysUserPO extends SysUser {

    private String roleName;
    private String birthdayShow;
    private String roleId;
    private String sexShow;

    private String position;
    private String positionShow;
    /** 座机号 */
    private String telephone;

    private String mobileShow;
    /** 查询条件，是否是集团用户 */
    private Boolean groupFlag;

    /** 集团用户名称 */
    private String multiTenantName;

    /**
     * 角色类别
     */
    private String roleType;
    /**
     * 所属系统
     */
    private String sysOwner;
    /** 用户关联的租户（多个） */
    private String multiTenant;

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

    public String getPositionShow() {
        return positionShow;
    }

    public void setPositionShow(String positionShow) {
        this.positionShow = positionShow;
    }

    public String getMobileShow() {
        if (StringUtil.isNotBlank(super.getMobile())) {
            mobileShow = StringUtil.formatMobile(super.getMobile());
        }
        return mobileShow;
    }

    public void setMobileShow(String mobileShow) {
        this.mobileShow = mobileShow;
    }

    public Boolean getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(Boolean groupFlag) {
        this.groupFlag = groupFlag;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public String getMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        this.multiTenant = multiTenant;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMultiTenantName() {
        return multiTenantName;
    }

    public void setMultiTenantName(String multiTenantName) {
        this.multiTenantName = multiTenantName;
    }

}
