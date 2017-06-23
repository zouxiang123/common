package com.xtt.common.dao.model;

/**
 * sync_module
 */
public class SyncModule {
    /**
     * sync_module.id
     */
    private Long id;

    /**
     * 同步组
     * sync_module.fk_sync_group_id
     */
    private Long fkSyncGroupId;

    /**
     * 模块表名（cm_dict：公用字典 drug：药品 supplies：耗材 medical_order_dict：医嘱字典)
     * sync_module.module
     */
    private String module;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 同步组
     */
    public Long getFkSyncGroupId() {
        return fkSyncGroupId;
    }

    /**
     * 同步组
     */
    public void setFkSyncGroupId(Long fkSyncGroupId) {
        this.fkSyncGroupId = fkSyncGroupId;
    }

    /**
     * 模块表名（cm_dict：公用字典 drug：药品 supplies：耗材 medical_order_dict：医嘱字典)
     */
    public String getModule() {
        return module;
    }

    /**
     * 模块表名（cm_dict：公用字典 drug：药品 supplies：耗材 medical_order_dict：医嘱字典)
     */
    public void setModule(String module) {
        this.module = module;
    }
}