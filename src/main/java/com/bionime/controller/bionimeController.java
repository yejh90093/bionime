package com.bionime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bionime.service.SiteService;
import com.bionime.repository.SiteRepository;
import com.bionime.entity.SiteEntity;

@Controller
public class bionimeController {

	@RequestMapping("/bionimeAddSite")
	public String bionimeAddSite() {
		return "bionimeAddSite";
	}

	@RequestMapping("/")
	public String bionimeIndex() {
		return "bionimeAddSite";
	}
	
    @Autowired
    SiteService service;
 
    @GetMapping("/allSite")
    public ResponseEntity<List<SiteEntity>> getAllSite() {
        List<SiteEntity> list = service.getAllSite();
 
        System.out.println("bionimeController: " + list.size());
        System.out.println("bionimeController: " + list.get(0).toString());

        
        return new ResponseEntity<List<SiteEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
