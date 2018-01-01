package cn.et.fuqiang.dynamicSql.entity;

public class Student {
	private Integer stuid;//学生id
	private String stuname;//学生姓名
	private Integer gid;//班级id
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
