package cn.et.fuqiang.resultMap.entity;

import java.util.List;

public class Grade {
	private Integer id;//�༶
	private String gname;//�༶����
	private List<Student> students;//�ð༶�����е�ѧ��
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Grade(Integer id, String gname) {
		super();
		this.id = id;
		this.gname = gname;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
