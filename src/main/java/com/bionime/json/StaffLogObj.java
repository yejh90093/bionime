package com.bionime.json;

public class StaffLogObj {

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

	public StaffLogObj(String name, long date) {
		this.name = name;
		this.date = date;
	}

	@Override
	public String toString() {
		return "StaffLog: [date=" + date + ", name=" + name + "]";
	}
}
