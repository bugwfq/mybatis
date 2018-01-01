package cn.et.fuqiang.dynamicSql.annotation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.fuqiang.dynamicSql.entity.Student;


public class DynamicSqTest {
	private static SqlSession session;
	private static  DynamicSqlAnnotationInterface dynamicSql=null;
	static{
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = DynamicSqTest.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        session = sessionFactory.openSession();
        dynamicSql=session.getMapper(DynamicSqlAnnotationInterface.class);
	}
	//@Test
	public void sqlIfTest(){
		Student temp = new Student();
		temp.setStuname("s");
		temp.setGid(1);
		List<Student> students = dynamicSql.queryStudentByParamsAndIFSql(temp);
		for (Student student : students) {
			System.out.println(student.getStuname()+"--"+student.getStuid());
		}
	}
	//@Test
	public void whereAndIfTest(){
		Student temp = new Student();
		temp.setStuname("s");
		temp.setGid(1);
		List<Student> students = dynamicSql.queryStudentsByParamsAndWhereAndIF(temp);
		for (Student student : students) {
			System.out.println(student.getStuname()+"--"+student.getStuid());
		}
	}
	
	//@Test
	public void SQLTest(){
		
		/**
		 * SQL �������ϰ
		 */
		
		List<Student> students = dynamicSql.queryStudentsByStuname(null);
		for (Student student : students) {
			System.out.println(student.getStuname()+"--"+student.getStuid());
		}
	}
	
	//@Test
	public void foreach(){
		List<Integer> sids = new ArrayList<Integer>();
		sids.add(1);
		sids.add(2);
		sids.add(3);
		List<Student> students = dynamicSql.queryStudentsBySids(sids);
		for (Student student : students) {
			System.out.println(student.getStuname()+"--"+student.getStuid());
		}
	}
	@Test
	public void set(){
		Student temp = new Student();
		temp.setStuid(1);
		temp.setStuname("s");
		temp.setGid(1);
		dynamicSql.updateStudentBySet(temp);
	}
	//@Test
	public void trimReplaceSet(){
		Student temp = new Student();
		temp.setStuid(1);
		temp.setStuname("s");
		temp.setGid(1);
		dynamicSql.updateStudentByTrim(temp);
	}
}
