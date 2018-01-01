package cn.et.fuqiang.resultMap.xml;

import java.util.List;

import cn.et.fuqiang.resultMap.entity.Student;

public interface StudentXmlInterface {
	public Student queryStudentById(Integer sid);
	public List<Student> quserAllStudent();
	public List<Student> quserUserByGid();
}
