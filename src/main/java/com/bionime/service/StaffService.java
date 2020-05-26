package com.bionime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.entity.SiteEntity;
import com.bionime.entity.StaffEntity;
import com.bionime.exception.RecordNotFoundException;
import com.bionime.json.ServiceSite;
import com.bionime.json.StaffList;
import com.bionime.repository.SiteRepository;
import com.bionime.repository.StaffRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Service
public class StaffService {

	@Autowired
	StaffRepository staffRepository;
	@Autowired
	SiteRepository siteRepository;

	public List<StaffEntity> getAllStaff() {
		List<StaffEntity> staffList = staffRepository.findAll();

		System.out.println("@@@ Andy Deubg:  getAllStaff: " + staffList.size());

		if (staffList.size() > 0) {
			return staffList;
		} else {
			return new ArrayList<StaffEntity>();
		}
	}

	public Map<String, Object> createStaff(StaffEntity entity) throws RecordNotFoundException {
		Map<String, Object> data = new HashMap<>();
		Optional<StaffEntity> staff = staffRepository.findByName(entity.getName());

		System.out.println("@@@ Andy Deubg: createStaff: " + staff.isPresent());

		if (staff.isPresent()) {
			data.put("Result", "StaffEntity already exist");

			StaffEntity newEntity = staff.get();
			newEntity.setId(entity.getId());
			newEntity.setName(entity.getName());
			newEntity.setServiceSite(entity.getServiceSite());
			newEntity.setLastUpdate(entity.getLastUpdate());
			newEntity = staffRepository.save(newEntity);

			data.put("StaffEntity", newEntity);
			return data;
		} else {
			data.put("Result", "Sucess to Create Staff");
			
			entity = staffRepository.save(entity);

			
			updateSiteStaff(entity);
			
			
			data.put("StaffEntity", entity);
			return data;
		}
	}

	
	
	public void updateSiteStaff(StaffEntity entity) throws RecordNotFoundException {
		
		
		System.out.println("@@@ Andy Debug updateSiteStaff: " + entity);
		System.out.println("@@@ Andy Debug updateSiteStaff: " + entity.getServiceSite());

		String jsonString = entity.getServiceSite() ; 



		Gson gson = new Gson(); 
		ServiceSite[] siteArray = gson.fromJson(jsonString, ServiceSite[].class);  
		 
		for(ServiceSite site : siteArray) {
		    System.out.println("@@@@@ siteArray: " + site.getName());

			
			
			Optional<SiteEntity> siteEntity = siteRepository.findByName(site.getName());
		    System.out.println("@@@@@ siteArray: " + siteEntity.get().getId());
		    siteEntity.get().setStaffCount(siteEntity.get().getStaffCount()+1);
		    
			// StaffList[] staffArray = gson.fromJson(siteEntity.get().getStaffList(), StaffList[].class);  

			List<StaffList> staffList = gson.fromJson(siteEntity.get().getStaffList(), new TypeToken<List<StaffList>>() {
			}.getType());

			StaffList newSatffList = new StaffList(entity.getName(), site.getDate());
			staffList.add(newSatffList);
			String strStaffList = gson.toJson(staffList);

			
			
		    siteEntity.get().setStaffList(strStaffList);
		    siteRepository.save(siteEntity.get());
		}
		
		
		
		
		Map<String, Object> data = new HashMap<>();
		Optional<StaffEntity> staff = staffRepository.findByName(entity.getName());

		System.out.println("@@@ Andy Deubg: createStaff: " + staff.isPresent());

		if (staff.isPresent()) {
			data.put("Result", "StaffEntity already exist");

			StaffEntity newEntity = staff.get();
			newEntity.setId(entity.getId());
			newEntity.setName(entity.getName());
			newEntity.setServiceSite(entity.getServiceSite());
			newEntity.setLastUpdate(entity.getLastUpdate());
			newEntity = staffRepository.save(newEntity);

			data.put("StaffEntity", newEntity);
		} else {
			data.put("Result", "Sucess to Create Staff");
			
			entity = staffRepository.save(entity);

			data.put("StaffEntity", entity);
		}
	}

	
	
	
	
	
	public Boolean deleteStaffById(int id) throws RecordNotFoundException {
		Optional<StaffEntity> staff = staffRepository.findById(id);

		if (staff.isPresent()) {
			staffRepository.deleteById(id);
			return true;
		} else {
			throw new RecordNotFoundException("No staff record exist for given id");
		}
	}

	
	
	
	public Optional<StaffEntity> getStaffByName(String name) throws RecordNotFoundException {
		Optional<StaffEntity> staff = staffRepository.findByName(name);

		if (staff.isPresent()) {
			return staff;
		} else {
			throw new RecordNotFoundException("No site record exist for given id");
		}
	}
}