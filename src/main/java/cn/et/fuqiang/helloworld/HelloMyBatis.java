package cn.et.fuqiang.helloworld;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class HelloMyBatis {
	public static SqlSession getSession() {
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = HelloMyBatis.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        SqlSession session = sessionFactory.openSession();
        return session;
	}
	public static SqlSession getSessionByResources() throws IOException {
		//mybatis�������ļ�
        String resource = "mybatis.xml";
          //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        SqlSession session = sessionFactory.openSession();
        return session;
	}
	
	//@Test
	public void updateUserByMap() {
		SqlSession session = getSession();
		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("userid", 1);
        updateMap.put("username", "wfq666");
        updateMap.put("userage", 5858518);
        int update = session.update("updateUserByMap",updateMap);
        System.out.println(update);
        session.commit();
        
	}
	//@Test
	public void deleteUserById() {
		SqlSession session = getSession();
		int delete = session.delete("deleteUserById", 1);
        System.out.println(delete);
        session.commit();
	}
	//@Test
	public void insertUserByMap() {
		SqlSession session = getSession();
		//������Ҫ�����ֵ��key ��������value ��ֵ
        Map<String,Object> insertMap = new HashMap<String,Object>();
        //�²�����Ϣֻ��Ҫд���û��������䣬idʹ��selectKey��ǩ���
        insertMap.put("username", "wfq2");
        insertMap.put("userage", 100);
        int insert = session.insert("myuser.insertUserByMap", insertMap);
        System.out.println(insert);
        session.commit();
	}
	//@Test
	public void selectUserById() {
		SqlSession session = getSession();
		/**
         * ӳ��sql�ı�ʶ�ַ�����
         * myuser.selectUser��myUserMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
         * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
         */
        //ִ�в�ѯ����һ��Ψһuser�����sql
        Map<String,Object> map= session.selectOne("myuser.selectUserById", 1);
        System.out.println(map);
	}
	
	@Test
	public void selectUserGetUser() {
		SqlSession session = getSession();
        //ִ�в�ѯ����һ��Ψһuser�����sql
        User user= session.selectOne("myuser.selectUserGetUser", 1);
        System.out.println(user);
	}

}
