/**   
 * @Title: CmFormItemsVO.java 
 * @Package com.xtt.common.form.vo
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月30日 上午9:32:33 
 *
 */
package com.xtt.common.dynamicForm.vo;

import com.xtt.common.dao.po.CmFormItemsPO;

public class CmFormItemsVO {
    private CmFormItemsPO[] records;
    private String formName;
    private Long formId;

    public CmFormItemsPO[] getRecords() {
        return records;
    }

    public void setRecords(CmFormItemsPO[] records) {
        this.records = records;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

}
