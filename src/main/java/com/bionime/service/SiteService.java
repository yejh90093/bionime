package com.bionime.service;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionime.entity.SiteEntity;
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
     
}