package com.adserver.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adserver.dto.CampaignDTO;

public class CampaignCache {

	
	private static Map<String,CampaignDTO> campaignStorageMap = new HashMap<String,CampaignDTO>();
	
	
	public static void put(String key, CampaignDTO value){
		
		campaignStorageMap.put(key,value);
	}
	
	public static CampaignDTO get(String key){
		
		return campaignStorageMap.get(key);
	}
	
	public static boolean containsKey(String key){
		
		return campaignStorageMap.containsKey(key);
	}
	
	public static void print(){
		
		
		for(Entry entry: campaignStorageMap.entrySet()){
		
			System.out.println("Key :" + entry.getKey());
		
			System.out.println("Value :" + entry.getValue());
			
		}
	}
}
