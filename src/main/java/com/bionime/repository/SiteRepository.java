package com.bionime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bionime.entity.SiteEntity;

@Repository
public interface SiteRepository extends JpaRepository<SiteEntity, Integer> {

	@Query("SELECT s FROM SiteEntity s WHERE LOWER(s.name) = LOWER(:name)")
	public Optional<SiteEntity> findByName(@Param("name") String name);

}