package cn.et.fuqiang.resultMap.entity;

public class Student {
	private Integer sid;//ѧ��id
	private String sname;//ѧ������
	private Integer gid;//�༶id
	private Grade grade;//�����༶��������Ϣ
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
