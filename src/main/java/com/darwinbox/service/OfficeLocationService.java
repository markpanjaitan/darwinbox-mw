package com.darwinbox.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.darwinbox.dto.OfficeLocationReqDto;
import com.darwinbox.entity.OfficeLocationEntity;
import com.darwinbox.enums.ErrorCodeEnum;
import com.darwinbox.repository.LocationRepository;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OfficeLocationService {
	
	@Value("${darwinbox.url}")
	private String darwinBoxUrl;
	@Value("${darwinbox.username}")
	private String darwinBoxUserName;
	@Value("${darwinbox.password}")
	private String darwinBoxPassword;
	@Value("${darwinbox.api.key}")
	private String darwinBoxApiKey;
	
	@Autowired
	LocationRepository locationRepository;

    public void getOfficeLocationAndSaveToDb(OfficeLocationReqDto reqDto) throws Exception {		
        HttpRequest httpRequest = HttpRequest.post(darwinBoxUrl).basicAuth(darwinBoxUserName, darwinBoxPassword);

        //Map<String, String> params = new HashMap<String, String>();
        
        String responseData = null;
        List<OfficeLocationEntity> listLocation = new ArrayList<OfficeLocationEntity>();

        try {
            httpRequest.body(JSON.toJSONString(reqDto));
            HttpResponse response = httpRequest.execute();
            responseData = response.body();
            log.info("{} response output:{}", "officelocationlist", responseData);
            
            JSONObject result = JSON.parseObject(responseData);
            JSONArray data = result.getJSONArray("data");

            for (int i = 0; i < data.size(); i++) {
            	JSONObject jsonObject = data.getJSONObject(i);
            	OfficeLocationEntity location = jsonObject.toJavaObject(OfficeLocationEntity.class);
            	listLocation.add(location);
            }
            locationRepository.saveAll(listLocation);
            
        } catch (Exception e) {
            log.error("{} has an error:{}", darwinBoxUrl, e);
            throw new Exception(ErrorCodeEnum.DARWINBOX_LOCATION_ERROR.getCode());
        }
    }
	
	//@Scheduled(cron = "0 * * * * *") // Run every 1 minute
	@Scheduled(fixedDelay = 60 * 60 * 1000, initialDelay = 0) // Run every 1 hour and initial delay 0ms
    public void scheduleGetOfficeLocationAndSaveToDb() throws Exception {
		log.info("scheduleGetOfficeLocationAndSaveToDb :{} " + new Date());
		
    	OfficeLocationReqDto offReqDto = new OfficeLocationReqDto();
    	offReqDto.setApi_key(darwinBoxApiKey);
    	
    	LocalDateTime currentDateTime = LocalDateTime.now();
    	LocalDateTime minus30DaysDateTime = currentDateTime.minusDays(30);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = minus30DaysDateTime.format(formatter);
        
        offReqDto.setLast_modified(formattedDateTime);	

        HttpRequest httpRequest = HttpRequest.post(darwinBoxUrl).basicAuth(darwinBoxUserName, darwinBoxPassword);
        
        String responseData = null;
        //List<OfficeLocationEntity> listLocation = new ArrayList<OfficeLocationEntity>();

        try {
            httpRequest.body(JSON.toJSONString(offReqDto));
            HttpResponse response = httpRequest.execute();
            responseData = response.body();
            log.info("{} response output:{}", "officelocationlist", responseData);
            
            JSONObject result = JSON.parseObject(responseData);
            JSONArray data = result.getJSONArray("data");

            for (int i = 0; i < data.size(); i++) {
            	try {
                	JSONObject jsonObject = data.getJSONObject(i);
                	OfficeLocationEntity location = jsonObject.toJavaObject(OfficeLocationEntity.class);
                	//listLocation.add(location);
                	locationRepository.save(location);	
            	}catch(Exception e) {
            		log.error("{} has an error:{}", "Save entity", e);
            		continue;
            	}
            }
            //locationRepository.saveAll(listLocation);
            
        } catch (Exception e) {
            log.error("{} has an error:{}", darwinBoxUrl, e);
            throw new Exception(ErrorCodeEnum.DARWINBOX_LOCATION_ERROR.getCode());
        }
    }
}
