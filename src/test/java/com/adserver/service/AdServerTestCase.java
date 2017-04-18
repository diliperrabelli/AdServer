package com.adserver.service;

import javax.ws.rs.core.Response;

import com.adserver.dto.CampaignDTO;

import junit.framework.TestCase;

public class AdServerTestCase extends TestCase {

	
	public static void main(String args[]){
		
		AdServerService service = new AdServerService();
		
		CampaignDTO dto = new CampaignDTO();
		dto.setAd_content("ad company");
		dto.setDuration(200);
		dto.setPartner_id("12");
		
		Response createAdCampaign = service.createAdCampaign(dto);
		
		System.out.println(createAdCampaign.getStatus());
		
		Response campaigns = service.getCampaigns("12");
		
		System.out.println(campaigns.getStatus());
		
	}
	
	
}
