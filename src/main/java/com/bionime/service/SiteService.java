package com.bionime.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.entity.SiteEntity;
import com.bionime.exception.RecordNotFoundException;
import com.bionime.repository.SiteRepository;

@Service
public class SiteService {

	@Autowired
	SiteRepository repository;

	public List<SiteEntity> getAllSite() {
		List<SiteEntity> siteList = repository.findAll();

		if (siteList.size() > 0) {
			return siteList;
		} else {
			return new ArrayList<SiteEntity>();
		}
	}

	public Map<String, Object> createSite(SiteEntity entity) throws RecordNotFoundException {
		Map<String, Object> data = new HashMap<>();
		Optional<SiteEntity> site = repository.findByName(entity.getName());

		if (site.isPresent()) {
			data.put("Result", "Site already exist");
			SiteEntity newEntity = site.get();
			newEntity.setName(entity.getName());
			newEntity.setStaffCount(entity.getStaffCount());
			newEntity.setLastUpdate(entity.getLastUpdate());
			newEntity = repository.save(newEntity);
			data.put("SiteEntity", newEntity);
			return data;
		} else {
			data.put("Result", "Sucess to Create Site");
			entity = repository.save(entity);
			data.put("SiteEntity", entity);
			return data;
		}
	}

	public SiteEntity createOrUpdateSite(SiteEntity entity) throws RecordNotFoundException {
		Optional<SiteEntity> site = repository.findById(entity.getId());

		if (!site.isPresent()) {
			site = repository.findByName(entity.getName());
		}
		if (site.isPresent()) {
			SiteEntity newEntity = site.get();
			newEntity.setName(entity.getName());
			newEntity.setStaffCount(entity.getStaffCount());
			newEntity.setLastUpdate(entity.getLastUpdate());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public Boolean deleteSiteById(int id) throws RecordNotFoundException {
		Optional<SiteEntity> site = repository.findById(id);

		if (site.isPresent()) {
			repository.deleteById(id);
			return true;
		} else {
			throw new RecordNotFoundException("No site record exist for given id");
		}
	}

	public Optional<SiteEntity> getSiteById(int id) throws RecordNotFoundException {
		Optional<SiteEntity> site = repository.findById(id);

		if (site.isPresent()) {
			return site;
		} else {
			throw new RecordNotFoundException("No site record exist for given id");
		}
	}

}