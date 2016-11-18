package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.MarkType;
import com.xtt.common.dao.model.MedicalHistoryRemark;
import com.xtt.platform.util.time.DateFormatUtil;

public class MedicalHistoryRemarkPO extends MedicalHistoryRemark {
	private String mhrStartTimeShow;
	private String mhrEndTimeShow;
	private String updateUserIdShow;
	private String createTimeShow;
	private List<MarkType> mhrMarkType;

	public MedicalHistoryRemarkPO() {
		super();
	}

	public List<MarkType> getMhrMarkType() {
		return mhrMarkType;
	}

	public void setMhrMarkType(List<MarkType> mhrMarkType) {
		this.mhrMarkType = mhrMarkType;
	}

	public String getMhrStartTimeShow() {
		mhrStartTimeShow = DateFormatUtil.convertDateToStr(super.getMhrStartTime(), "yyyy-MM-dd");
		return mhrStartTimeShow;
	}

	public void setMhrStartTimeShow(String mhrStartTimeShow) {
		this.mhrStartTimeShow = mhrStartTimeShow;
		if (mhrStartTimeShow != null) {
			super.setMhrStartTime(DateFormatUtil.convertStrToDate(mhrStartTimeShow, "yyyy-MM-dd"));
		}
	}

	public String getMhrEndTimeShow() {
		mhrEndTimeShow = DateFormatUtil.convertDateToStr(super.getMhrEndTime(), "yyyy-MM-dd");
		return mhrEndTimeShow;
	}

	public void setMhrEndTimeShow(String mhrEndTimeShow) {
		this.mhrEndTimeShow = mhrEndTimeShow;
		if (mhrEndTimeShow != null) {
			super.setMhrEndTime(DateFormatUtil.convertStrToDate(mhrEndTimeShow, "yyyy-MM-dd"));
		}
	}

	public String getUpdateUserIdShow() {
		updateUserIdShow = DateFormatUtil.convertDateToStr(super.getUpdateTime(), "yyyy-MM-dd");
		return updateUserIdShow;
	}

	public void setUpdateUserIdShow(String updateUserIdShow) {
		this.updateUserIdShow = updateUserIdShow;
		if (updateUserIdShow != null) {
			super.setUpdateTime(DateFormatUtil.convertStrToDate(updateUserIdShow, "yyyy-MM-dd"));
		}
	}

	public String getCreateTimeShow() {
		createTimeShow = DateFormatUtil.convertDateToStr(super.getCreateTime(), "yyyy-MM-dd");
		return createTimeShow;
	}

	public void setCreateTimeShow(String createTimeShow) {
		this.createTimeShow = createTimeShow;
		if (createTimeShow != null) {
			super.setCreateTime(DateFormatUtil.convertStrToDate(createTimeShow, "yyyy-MM-dd"));
		}
	}
}
