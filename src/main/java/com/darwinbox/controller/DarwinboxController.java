package com.darwinbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darwinbox.dto.OfficeLocationReqDto;
import com.darwinbox.dto.Response;
import com.darwinbox.service.OfficeLocationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "api")
public class DarwinboxController {
	
	@Autowired
	OfficeLocationService officeLocationService;

    @RequestMapping(value = "/getAndSaveOfficeLocation", method = RequestMethod.POST)
    public ResponseEntity<?> getAndSaveOfficeLocation(@RequestBody OfficeLocationReqDto req) {
        log.info("officeLocation param is:{} ", req);
        Response response = new Response("Ok");
        try {
			officeLocationService.getOfficeLocationAndSaveToDb(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e.toString());
		}
        ResponseEntity.ok("Inserting to Database");
        return ResponseEntity.ok(response);
    }
}
