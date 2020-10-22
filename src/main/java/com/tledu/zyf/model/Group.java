package com.tledu.zyf.model;

public class Group {
	private int id;
	/**
	 * 部门名
	 */
	private String groupname;
	/**
	 * 部门介绍
	 */
	private String introduce;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Group() {
		super();
	}

	public Group(int id) {
		super();
		this.id = id;
	}

	public Group(String groupname, String introduce) {
		super();
		this.groupname = groupname;
		this.introduce = introduce;
	}

	public Group(int id, String groupname, String introduce) {
		super();
		this.id = id;
		this.groupname = groupname;
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", groupname=" + groupname + ", introduce=" + introduce + "]";
	}

}
