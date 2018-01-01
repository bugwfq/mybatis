package cn.et.fuqiang.interfaceMap.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class InterfaceMapXmlTest {
	private static SqlSession session;
	private static InterfaceMyUser mapper=null;
	static{
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = InterfaceMapXmlTest.class.getResourceAsStream(resource);
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
	@Test
	public void quesrUserById(){
		System.out.println(mapper.queryMyUserById(1));
	}
	
	//@Test
	public void deleteUserByID(){
		mapper.deleteUserById(1);
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
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", 2);
		map.put("username", "wahaha");
		map.put("userage", 18);
		mapper.updateUserByMap(map);
		session.commit();
	}
	
}
