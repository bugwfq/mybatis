package cn.et.fuqiang.cache.entity;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stuid;//ѧ��id
	private String stuname;//ѧ������
	private Integer gid;//�༶id
	public Student() {}
	public Student(Integer stuid, String stuname, Integer gid) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.gid = gid;
	}
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
}
