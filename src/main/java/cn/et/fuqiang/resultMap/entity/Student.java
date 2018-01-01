package cn.et.fuqiang.resultMap.entity;

public class Student {
	private Integer sid;//学生id
	private String sname;//学生姓名
	private Integer gid;//班级id
	private Grade grade;//所属班级的所有信息
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Integer sid, String sname, Integer gid, Grade grade) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gid = gid;
		this.grade = grade;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}
