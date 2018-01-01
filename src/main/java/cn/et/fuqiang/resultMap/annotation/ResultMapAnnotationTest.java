package cn.et.fuqiang.resultMap.annotation;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.fuqiang.resultMap.entity.Grade;
import cn.et.fuqiang.resultMap.entity.Student;

public class ResultMapAnnotationTest {
	private static SqlSession session;
	private static GradeAnnotationInterface gradeInter=null;
	private static StudentAnnotationInterface studentInter=null;
	static{
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = ResultMapAnnotationTest.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        session = sessionFactory.openSession();
        gradeInter=session.getMapper(GradeAnnotationInterface.class);
        studentInter=session.getMapper(StudentAnnotationInterface.class);
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
	@Test
	public void queryGradeInStudent(){
		List<Student> students=studentInter.queryAllStudent();
		for (Student stu : students) {
			System.out.println(stu.getSid()+"------"+stu.getSname()+"-------"+stu.getGrade().getGname());
		}
		
	}
	//@Test
	public void queryStudentInGrade(){
		List<Grade> students=gradeInter.queryAllGrade();
		for (Grade grade : students) {
			System.out.println(grade.getGname()+"班"+grade.getStudents().size()+"人");
		}
	}
}
