package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CkdStage;

public class CkdStagePO extends CkdStage {

    private String userName;
    private String akiStageRifle;
    private String ckdAki;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAkiStageRifle() {
        return akiStageRifle;
    }

    public void setAkiStageRifle(String akiStageRifle) {
        this.akiStageRifle = akiStageRifle;
    }

    public String getCkdAki() {
        return ckdAki;
    }

    public void setCkdAki(String ckdAki) {
        this.ckdAki = ckdAki;
    }

}
