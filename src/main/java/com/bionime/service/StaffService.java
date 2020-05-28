package com.bionime.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.entity.SiteEntity;
import com.bionime.entity.StaffEntity;
import com.bionime.exception.RecordNotFoundException;
import com.bionime.json.ServiceSiteLogObj;
import com.bionime.json.StaffLogObj;
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

		if (staffList.size() > 0) {
			return staffList;
		} else {
			return new ArrayList<StaffEntity>();
		}
	}

	public Map<String, Object> createStaff(StaffEntity entity) throws RecordNotFoundException {
		Map<String, Object> data = new HashMap<>();
		Optional<StaffEntity> staff = staffRepository.findByName(entity.getName());

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
			addStaffToSite(entity);
			data.put("StaffEntity", entity);
			return data;
		}
	}

	public Map<String, Object> updateStaff(StaffEntity entity) throws RecordNotFoundException {
		Optional<StaffEntity> staff = staffRepository.findByName(entity.getName());

		Gson gson = new Gson();
		List<ServiceSiteLogObj> oldServiceSite = gson.fromJson(staff.get().getServiceSite(),
				new TypeToken<List<ServiceSiteLogObj>>() {
				}.getType());

		List<ServiceSiteLogObj> newServiceSite = gson.fromJson(entity.getServiceSite(),
				new TypeToken<List<ServiceSiteLogObj>>() {
				}.getType());

		for (ServiceSiteLogObj newSite : newServiceSite) {
			ServiceSiteLogObj existSite = oldServiceSite.stream()
					.filter(oldSite -> newSite.getName().equals(oldSite.getName())).findAny().orElse(null);

			if (existSite != null) {
				newSite.setDate(existSite.getDate());
			}
		}

		delsteStaffFromSite(staff.get());
		String newSiteListStr = gson.toJson(newServiceSite);
		entity.setServiceSite(newSiteListStr);
		addStaffToSite(entity);

		staff.get().setServiceSite(newSiteListStr);
		staff.get().setLastUpdate(new Timestamp(System.currentTimeMillis()));

		staffRepository.save(staff.get());
		Map<String, Object> data = new HashMap<>();
		data.put("Result", "Sucess to updateStaff Staff");
		return data;
	}

	public void addStaffToSite(StaffEntity entity) throws RecordNotFoundException {

		String jsonString = entity.getServiceSite();
		Gson gson = new Gson();
		ServiceSiteLogObj[] siteArray = gson.fromJson(jsonString, ServiceSiteLogObj[].class);

		for (ServiceSiteLogObj site : siteArray) {
			Optional<SiteEntity> siteEntity = siteRepository.findByName(site.getName());
			siteEntity.get().setStaffCount(siteEntity.get().getStaffCount() + 1);
			List<StaffLogObj> staffList = gson.fromJson(siteEntity.get().getStaffList(),
					new TypeToken<List<StaffLogObj>>() {
					}.getType());
			StaffLogObj newSatffList = new StaffLogObj(entity.getName(), site.getDate());
			staffList.add(newSatffList);
			String strStaffList = gson.toJson(staffList);
			siteEntity.get().setStaffList(strStaffList);
			siteRepository.save(siteEntity.get());
		}
	}

	public void delsteStaffFromSite(StaffEntity entity) throws RecordNotFoundException {
		String jsonString = entity.getServiceSite();
		Gson gson = new Gson();
		ServiceSiteLogObj[] siteArray = gson.fromJson(jsonString, ServiceSiteLogObj[].class);

		for (ServiceSiteLogObj site : siteArray) {
			Optional<SiteEntity> siteEntity = siteRepository.findByName(site.getName());
			siteEntity.get().setStaffCount(siteEntity.get().getStaffCount() - 1);
			List<StaffLogObj> oldStaffList = gson.fromJson(siteEntity.get().getStaffList(),
					new TypeToken<List<StaffLogObj>>() {
					}.getType());
			StaffLogObj newSatffList = new StaffLogObj(entity.getName(), site.getDate());
			Iterator<StaffLogObj> it = oldStaffList.iterator();
			while (it.hasNext()) {
				if (it.next().getName().equals(entity.getName())) {
					it.remove();
				}
			}
			String strStaffList = gson.toJson(oldStaffList);
			siteEntity.get().setStaffList(strStaffList);
			siteRepository.save(siteEntity.get());
		}
	}

	public Boolean deleteStaffById(String id) throws RecordNotFoundException {
		Optional<StaffEntity> staff = staffRepository.findById(id);

		if (staff.isPresent()) {
			staffRepository.deleteById(id);
			delsteStaffFromSite(staff.get());
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
			throw new RecordNotFoundException("No site record exist for given name");
		}
	}

	public Optional<StaffEntity> getStaffById(String id) throws RecordNotFoundException {

		Optional<StaffEntity> staff = staffRepository.findById(id);

		if (staff.isPresent()) {
			return staff;
		} else {
			throw new RecordNotFoundException("No site record exist for given id");
		}
	}
}