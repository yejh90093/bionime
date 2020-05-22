package com.bionime.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
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
    private Date lastUpdate;

    @Column(name="CREATE_DATE")
    private Date createDate;

    //Setters and getters
 
    @Override
    public String toString() {
        return "SiteEntity [id=" + id + ", name=" + name + 
                ", staffCount=" + staffCount + ", lastUpdate=" + lastUpdate   + "]";
    }
}