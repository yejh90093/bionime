package com.bionime.json;

public class ServiceSite {
	
    private long date;
    private String name;
     
    public long getDate() {
        return date;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public String toString() {
        return "User [date=" + date + ", name=" + name + "]";
    }
}
