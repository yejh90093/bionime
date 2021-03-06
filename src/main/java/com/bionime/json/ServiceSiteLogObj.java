package com.bionime.json;

public class ServiceSiteLogObj {

	private long date;
	private String name;
	private boolean assigned;

	public ServiceSiteLogObj(String name, boolean assigned) {
		this.name = name;
		this.assigned = assigned;
	}

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

	public boolean getAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	@Override
	public String toString() {
		return "SiteLog: [date=" + date + ", name=" + name + ", assigned=" + assigned + "]";
	}
}
