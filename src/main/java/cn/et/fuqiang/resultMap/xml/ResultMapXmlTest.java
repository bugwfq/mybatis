package cn.et.fuqiang.resultMap.xml;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.fuqiang.resultMap.entity.Grade;
import cn.et.fuqiang.resultMap.entity.Student;


public class ResultMapXmlTest {
	private static SqlSession session;
	private static GradeXmlInterface gradeInter=null;
	private static StudentXmlInterface studentInter=null;
	static{
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = GradeXmlInterface.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        session = sessionFactory.openSession();
        gradeInter=session.getMapper(GradeXmlInterface.class);
        studentInter=session.getMapper(StudentXmlInterface.class);
	}
	//@Test
	public void queryGradeTest(){
		Grade grade=gradeInter.queryGradeById(1);
		System.out.println(grade.getId()+"------"+grade.getGname());
	}
	//@Test
	public void queryStudentTest(){
		Student student=studentInter.queryStudentById(1);
		System.out.println(student.getSid()+"------"+student.getSname());
	}
	//@Test
	public void queryGradeInStudent(){
		List<Student> students=studentInter.quserAllStudent();
		for (Student stu : students) {
			System.out.println(stu.getSid()+"------"+stu.getSname()+"-------"+stu.getGrade().getGname());
		}
		
	}
	@Test
	public void queryStudentInGrade(){
		List<Grade> students=gradeInter.queryAllGrade();
		for (Grade grade : students) {
			System.out.println(grade.getGname()+"��"+grade.getStudents().size()+"��");
		}
	}
}
