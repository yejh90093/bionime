package com.bionime.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "bionime_Staff")
public class StaffEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SERVICE_SITE")
	private String serviceSite;

	@Column(name = "LAST_UPDATE")
	private Timestamp lastUpdate;

	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	public StaffEntity() {

		this.lastUpdate = new Timestamp(System.currentTimeMillis());

	}

	public StaffEntity(String name, String id, String serviceSite) {
		this.id = id;
		this.name = name;
		this.serviceSite = serviceSite;
	}

	// Setters and getters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceSite() {
		return serviceSite;
	}

	public void setServiceSite(String serviceSite) {
		this.serviceSite = serviceSite;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "StaffEntity [id=" + id + ", name=" + name + ", serviceSite=" + serviceSite + ", lastUpdate="
				+ lastUpdate + "]";
	}
}