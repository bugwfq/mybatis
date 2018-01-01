package cn.et.fuqiang.cache.xml;

import cn.et.fuqiang.cache.entity.Student;

public interface MybatisCache {
	public Student queryStudentById(Integer stuid);
	public void updateStudent(Student student);
}
