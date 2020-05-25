package com.bionime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bionime.entity.SiteEntity;
import com.bionime.entity.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {

	@Query("SELECT s FROM StaffEntity s WHERE LOWER(s.name) = LOWER(:name)")
	public Optional<StaffEntity> findByName(@Param("name") String name);

	@Query("SELECT s FROM StaffEntity s WHERE LOWER(s.id) = LOWER(:id)")
	public Optional<StaffEntity> findById(@Param("id") String id);

}