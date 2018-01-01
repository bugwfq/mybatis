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
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = HelloMyBatis.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        return session;
	}
	public static SqlSession getSessionByResources() throws IOException {
		//mybatis的配置文件
        String resource = "mybatis.xml";
          //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
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
		//创建需要插入的值，key 是列名，value 是值
        Map<String,Object> insertMap = new HashMap<String,Object>();
        //新插入信息只需要写入用户名和年龄，id使用selectKey标签添加
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
         * 映射sql的标识字符串，
         * myuser.selectUser是myUserMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        //执行查询返回一个唯一user对象的sql
        Map<String,Object> map= session.selectOne("myuser.selectUserById", 1);
        System.out.println(map);
	}
	
	@Test
	public void selectUserGetUser() {
		SqlSession session = getSession();
        //执行查询返回一个唯一user对象的sql
        User user= session.selectOne("myuser.selectUserGetUser", 1);
        System.out.println(user);
	}

}
