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
import com.bionime.repository.SiteRepository;
import com.bionime.repository.StaffRepository;

@Service
public class StaffService {

	@Autowired
	StaffRepository repository;

	public List<StaffEntity> getAllStaff() {
		List<StaffEntity> staffList = repository.findAll();

		System.out.println("@@@ Andy Deubg:  getAllStaff: " + staffList.size());

		if (staffList.size() > 0) {
			return staffList;
		} else {
			return new ArrayList<StaffEntity>();
		}
	}

	public Map<String, Object> createStaff(StaffEntity entity) throws RecordNotFoundException {
		Map<String, Object> data = new HashMap<>();
		Optional<StaffEntity> staff = repository.findByName(entity.getName());

		System.out.println("@@@ Andy Deubg: createStaff: " + staff.isPresent());

		if (staff.isPresent()) {
			data.put("Result", "StaffEntity already exist");

			StaffEntity newEntity = staff.get();
			newEntity.setId(entity.getId());
			newEntity.setName(entity.getName());
			newEntity.setServiceSite(entity.getServiceSite());
			newEntity.setLastUpdate(entity.getLastUpdate());
			newEntity = repository.save(newEntity);

			data.put("StaffEntity", newEntity);
			return data;
		} else {
			data.put("Result", "Sucess to Create Staff");
			entity = repository.save(entity);

			data.put("StaffEntity", entity);
			return data;
		}
	}

	public Boolean deleteStaffById(int id) throws RecordNotFoundException {
		Optional<StaffEntity> staff = repository.findById(id);

		if (staff.isPresent()) {
			repository.deleteById(id);
			return true;
		} else {
			throw new RecordNotFoundException("No staff record exist for given id");
		}
	}

}