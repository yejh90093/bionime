package com.bionime.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bionime.entity.SiteEntity;
 
@Repository
public interface SiteRepository 
        extends JpaRepository<SiteEntity, Long> {
 
}