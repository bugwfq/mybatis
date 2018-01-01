package cn.et.fuqiang.interfaceMap.annotation;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class InterfaceMapAnnotationTest {
	private static SqlSession session;
	private static InterfaceMyUser mapper=null;
	static{
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = InterfaceMapAnnotationTest.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
       session = sessionFactory.openSession();
        mapper=session.getMapper(InterfaceMyUser.class);
	}

	//@Test
	public void quesrUserById(){
		System.out.println(mapper.queryMyUserById(2));
	}
	
	//@Test
	public void deleteUserByID(){
		mapper.deleteUserById(3);
		session.commit();
	}
	
	//@Test
	public void insertUserByMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", "wfq");
		map.put("userage", 120);
		mapper.saveUserByMap(map);
		session.commit();
	}
	
	@Test
	public void updateUserByMap(){
		mapper.updateUserByMap(2,"wava",100);
		session.commit();
	}
}
