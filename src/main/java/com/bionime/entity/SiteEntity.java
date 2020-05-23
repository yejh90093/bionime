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
@Table(name="bionime_Site")
public class SiteEntity {
 
    @Id
    @GeneratedValue
    private int id;
     
    @Column(name="NAME")
    private String name;
     
    @Column(name="STAFF_COUNT")
    private int staffCount;
     
     
    @Column(name="LAST_UPDATE")
    private Timestamp lastUpdate;

    @CreationTimestamp
    @Column(name="CREATE_DATE")
    private Timestamp createDate;

    public SiteEntity() {
     
		this.lastUpdate = new Timestamp(System.currentTimeMillis());

    }

    public SiteEntity(String name) {
		this.name = name;
		this.name = name;
    }

    //Setters and getters
 
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(int staffCount) {
		this.staffCount = staffCount;
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
        return "SiteEntity [id=" + id + ", name=" + name + 
                ", staffCount=" + staffCount + ", lastUpdate=" + lastUpdate   + "]";
    }
}