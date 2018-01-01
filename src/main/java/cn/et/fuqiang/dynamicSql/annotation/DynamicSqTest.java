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
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = DynamicSqTest.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
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
		 * SQL 对象的练习
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
