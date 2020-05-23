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
     
    public List<SiteEntity> getAllSite()
    {
        List<SiteEntity> siteList = repository.findAll();
         
        System.out.println("SiteService: " + siteList.size());
        
        if(siteList.size() > 0) {
            return siteList;
        } else {
            return new ArrayList<SiteEntity>();
        }
    }
    
    
    
    

    
    public  Map<String, Object>  createSite(SiteEntity entity) throws RecordNotFoundException 
    {
        Map<String, Object> data = new HashMap<>();


        Optional<SiteEntity> site = repository.findByName(entity.getName());



        
        System.out.println("@@@ Andy Deubg: createSite: " + site.isPresent());

        
         
        if(site.isPresent()) 
        {
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
    
    
    
    public SiteEntity createOrUpdateSite(SiteEntity entity) throws RecordNotFoundException 
    {
    	
        System.out.println("@@@ Andy Deubg: SiteService: " + entity);
        System.out.println("createOrUpdateSite: " + entity);

        Optional<SiteEntity> site = repository.findById(entity.getId());
        System.out.println("@@@ Andy Deubg: SiteService site1q " + site.isPresent());

        
        if(!site.isPresent()) {
        	site = repository.findByName(entity.getName());
        }
        

        
        System.out.println("@@@ Andy Deubg: SiteService site12: " + site.isPresent());

        
         
        if(site.isPresent()) 
        {
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
    
    /*
     
    
    public String createOrUpdateSite(SiteEntity entity) throws RecordNotFoundException 
    {
    	String result ="";

    	Optional<SiteEntity> site = repository.findById(entity.getId());
        if(!site.isPresent()) {
        	site = repository.findByName(entity.getName());
        }
        

         
        if(site.isPresent()) 
        {
        	result = "Update Site Suceess: " + entity.getName();


        	SiteEntity newEntity = site.get();
            newEntity.setName(entity.getName());
            newEntity.setStaffCount(entity.getStaffCount());
            newEntity.setLastUpdate(entity.getLastUpdate());
 
            newEntity = repository.save(newEntity);
             
            System.out.println("@@@ Andy Deubg: SiteService result: " +result);

            return result;
        } else {
        	result = "Create New Site Suceess: " + entity.getName();
            entity = repository.save(entity);
            System.out.println("@@@ Andy Deubg: SiteService result: " +result);

            return result;
        }
    } 
     * 
     * */
    
     
}