package com.bionime.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bionime.service.SiteService;
import com.bionime.repository.SiteRepository;
import com.bionime.entity.SiteEntity;
import com.bionime.exception.RecordNotFoundException;

@Controller
public class bionimeController {

	@RequestMapping("/bionimeAddSite")
	public String bionimeAddSite() {
		return "bionimeAddSite";
	}

	@GetMapping("/bionimeAddStaff")
	public String bionimeAddStaff(Model model) {
		model.addAttribute("sites", service.getAllSite());
		return "bionimeAddStaff";
	}

	@RequestMapping("/")
	public String bionimeIndex() {
		return "bionimeAddSite";
	}

	@Autowired
	SiteService service;

	@GetMapping("/bionimeListSite")
	public String bionimeListSite(Model model) {
		model.addAttribute("sites", service.getAllSite());
		return "bionimeListSite";
	}

	@DeleteMapping("/deleteSite/{id}")
	public ResponseEntity<String> deleteSite(@PathVariable int id) throws RecordNotFoundException {

		System.out.println("@@@ Andy Debug deletePost: " + id);

		Boolean isRemoved = service.deleteSiteById(id);
		System.out.println("@@@ Andy Debug deletePost: " + isRemoved);

		if (!isRemoved) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@PostMapping("/addSite")
	public ResponseEntity<Map<String, Object>> createOrUpdateSite(@RequestBody SiteEntity site)
			throws RecordNotFoundException {

		System.out.println("bionimeController: " + site);
		System.out.println("createOrUpdateEmployee: " + site);

		Map<String, Object> updated = service.createSite(site);
		return new ResponseEntity<Map<String, Object>>(updated, new HttpHeaders(), HttpStatus.OK);
	}

}
