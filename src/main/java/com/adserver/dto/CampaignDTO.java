package com.adserver.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class CampaignDTO
implements Serializable {
	
    private static final long serialVersionUID = 1;
    private String partner_id;
    private String ad_content;
    private int duration;
    private long createDate;
    
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
    
	
     
  
}
