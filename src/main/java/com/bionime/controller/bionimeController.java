package com.bionime.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.bionime.service.StaffService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bionime.repository.SiteRepository;
import com.bionime.entity.SiteEntity;
import com.bionime.entity.StaffEntity;
import com.bionime.exception.RecordNotFoundException;
import com.bionime.json.ServiceSiteLogObj;
import com.bionime.json.StaffLogObj;

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
	SiteService siteService;
	@Autowired
	StaffService staffService;

	@GetMapping("/bionimeListSite")
	public String bionimeListSite(Model model) {
		model.addAttribute("sites", siteService.getAllSite());
		return "bionimeListSite";
	}

	@GetMapping("/bionimeAddStaff")
	public String bionimeAddStaff(Model model) {
		model.addAttribute("staffs", staffService.getAllStaff());
		model.addAttribute("sites", siteService.getAllSite());
		return "bionimeAddStaff";
	}

	@GetMapping("/bionimeListStaff")
	public String bionimeListStaff(Model model) {
		model.addAttribute("staffs", staffService.getAllStaff());
		return "bionimeListStaff";
	}

	@GetMapping("/bionimeViewSite/{id}")
	public String bionimeViewSite(@PathVariable int id, Model model) throws RecordNotFoundException {
		Optional<SiteEntity> site = siteService.getSiteById(id);
		Gson gson = new Gson();
		List<StaffLogObj> staffList = gson.fromJson(site.get().getStaffList(), new TypeToken<List<StaffLogObj>>() {
		}.getType());

		model.addAttribute("siteName", site.get().getName());
		model.addAttribute("staffList", staffList);
		return "bionimeViewSite";
	}

	@GetMapping("/bionimeViewStaff/{id}")
	public String bionimeViewStaff(@PathVariable String id, Model model) throws RecordNotFoundException {

		Gson gson = new Gson();
		Optional<StaffEntity> staff = staffService.getStaffById(id);

		List<ServiceSiteLogObj> resultServiceSite = new ArrayList<>();
		List<SiteEntity> allSite = siteService.getAllSite();
		List<ServiceSiteLogObj> ServiceSite = gson.fromJson(staff.get().getServiceSite(),
				new TypeToken<List<ServiceSiteLogObj>>() {
				}.getType());

		for (SiteEntity site : allSite) {
			boolean assigned = false;
			for (ServiceSiteLogObj temp : ServiceSite) {
				if (temp.getName().equals(site.getName())) {
					assigned = true;
				}

			}
			resultServiceSite.add(new ServiceSiteLogObj(site.getName(), assigned));
		}

		model.addAttribute("staff", staff.get());
		model.addAttribute("serviceSite", resultServiceSite);
		return "bionimeViewStaff";
	}

	@DeleteMapping("/deleteSite/{id}")
	public ResponseEntity<String> deleteSite(@PathVariable int id) throws RecordNotFoundException {
		Boolean isRemoved = siteService.deleteSiteById(id);
		if (!isRemoved) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStaff/{id}")
	public ResponseEntity<String> deleteStaff(@PathVariable String id) throws RecordNotFoundException {

		Boolean isRemoved = staffService.deleteStaffById(id);
		if (!isRemoved) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping("/addSite")
	public ResponseEntity<Map<String, Object>> createSite(@RequestBody SiteEntity site) throws RecordNotFoundException {
		Map<String, Object> updated = siteService.createSite(site);
		return new ResponseEntity<Map<String, Object>>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/addStaff")
	public ResponseEntity<Map<String, Object>> createStaff(@RequestBody StaffEntity staff)
			throws RecordNotFoundException {
		Map<String, Object> updated = staffService.createStaff(staff);
		return new ResponseEntity<Map<String, Object>>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/modifyStaff")
	public ResponseEntity<Map<String, Object>> updateStaff(@RequestBody StaffEntity staff)
			throws RecordNotFoundException {
		Map<String, Object> updated = staffService.updateStaff(staff);
		return new ResponseEntity<Map<String, Object>>(updated, new HttpHeaders(), HttpStatus.OK);
	}
}
