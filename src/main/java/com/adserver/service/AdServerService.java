package com.adserver.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adserver.dto.CampaignDTO;
import com.adserver.util.ResponseInfo;



@Path(value="/")
public class AdServerService {
	Logger logger = LoggerFactory.getLogger(AdServerService.class);

	
    //keep alive
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Welcome to AdServer";
	}

	

	@POST
	@Path(value="/ad")
	@Consumes(value={"application/json"})
	@Produces(value={"application/json"})
	public Response createAdCampaign(@RequestBody CampaignDTO dtoObject) {

		this.logger.info("Inside storeCoordinates");

		logger.info("dtoObject.adContent()" + dtoObject.getAd_content());
		logger.info("dtoObject.getPartnerID()" + dtoObject.getPartner_id());

		ResponseInfo responseInfo = new ResponseInfo();

		try {

			if(dtoObject != null && dtoObject.getPartner_id() != null){

				if(CampaignCache.containsKey(dtoObject.getPartner_id())){

					responseInfo.setStatus("SUCCESS");
					responseInfo.setData("AdCampaign is already available for this Partner.");
				}else{

					long createDate = (long) dtoObject.getCreateDate();

					logger.info("createDate" + createDate);
					
					if(createDate <= 0){

						long currentTimeInMillis = System.currentTimeMillis();

						dtoObject.setCreateDate(currentTimeInMillis);
					}
					
					CampaignCache.put(dtoObject.getPartner_id(), dtoObject);
				}


				logger.info("Cooridnates Saved Successfully");
			}
			

		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("storeCoordinates :: Exception ::", new Object[]{e.getMessage(), e});
			responseInfo.setErrorCode("500");
			responseInfo.setErrorMessage("Unable to Create to Campaign. Please contact App Support");
		}
		responseInfo.setStatus("SUCCESS");
		responseInfo.setData("AdCampaign Created Successfully");

		GenericEntity<ResponseInfo> response = new GenericEntity<ResponseInfo>(responseInfo){};
		return Response.ok((Object)response).build();
	}

	@GET
	@Path(value="/ad/{parent_id}")
	@Produces(value={"application/json"})
	@ResponseBody
	public Response getCampaigns(@PathParam("parent_id") String partnerID) {

		this.logger.info("Inside getCampaigns");

		ResponseInfo responseInfo = new ResponseInfo();
		List<CampaignDTO> campaignList = new ArrayList<CampaignDTO>();

		try {

			if(partnerID != null){

				logger.info("partnerid" + partnerID);
				
				CampaignCache.print();
				
				if(CampaignCache.containsKey(partnerID)){
					
					CampaignDTO campaignDTO = CampaignCache.get(partnerID);

					long createdTimestamp = campaignDTO.getCreateDate();
					
					logger.info("createdTimestamp" + createdTimestamp);
					
					int duration = campaignDTO.getDuration();

					Calendar campaignCalender = Calendar.getInstance();
					campaignCalender.setTimeInMillis(createdTimestamp);
					campaignCalender.add(Calendar.SECOND, duration);
					
					long campaignTimeInMillis = campaignCalender.getTimeInMillis();
					
					logger.info("campaignTimeInMillis" + campaignTimeInMillis);

					Calendar todayCalender = Calendar.getInstance();
					todayCalender.setTime(new Date());
					long currentTimeInMillis = todayCalender.getTimeInMillis();

					logger.info("currentTimeInMillis" + currentTimeInMillis);

					if(campaignTimeInMillis <= currentTimeInMillis){

						responseInfo.setStatus("SUCCESS");
						responseInfo.setData("No Active Campaign Avaialable");

					}else{

						responseInfo.setStatus("SUCCESS");
						responseInfo.setStatusDetail("Ad Campaign details are:");
						responseInfo.setData(campaignDTO);

					}
				}else{
					
					responseInfo.setStatus("SUCCESS");
					responseInfo.setData("Couldn't find partner ID in our Catalogue");
				}
			}else{

				responseInfo.setStatus("SUCCESS");
				responseInfo.setData("Incorrect partner ID");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception ::", new Object[]{e.getMessage(), e});

			responseInfo.setErrorCode("500");
			responseInfo.setErrorMessage("Unable to Create to Campaign. Please contact App Support");

		}

		GenericEntity<ResponseInfo> response = new GenericEntity<ResponseInfo>(responseInfo){};
		return Response.ok((Object)response).build();


	}

}
