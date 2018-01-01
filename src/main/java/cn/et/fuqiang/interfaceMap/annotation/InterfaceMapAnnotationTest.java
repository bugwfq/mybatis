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
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = InterfaceMapAnnotationTest.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
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
