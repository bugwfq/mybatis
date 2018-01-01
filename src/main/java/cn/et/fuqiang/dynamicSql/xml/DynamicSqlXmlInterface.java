package cn.et.fuqiang.dynamicSql.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.et.fuqiang.dynamicSql.entity.Student;

public interface DynamicSqlXmlInterface {
	public List<Student> queryStudentByParamsAndTrimAndIF(Student student);
	public List<Student> queryStudentsByParamsAndWhereAndIF(Student student);
	public List<Student> queryStudentsByChoose(@Param("stuname")String stuname);
	public List<Student> queryStudentsByForeach(@Param("sids") List<Integer> sids);
	public void updateStudentBySet(Student student);
	public void updateStudentByTrim(Student student);

}
