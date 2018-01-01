package cn.et.fuqiang.cache.xml;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.fuqiang.cache.entity.Student;


public class MybatisCacheTest {
	public SqlSession getSession(){
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisCacheTest.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        return sessionFactory.openSession();
	}
	public SqlSessionFactory getSqlSessionFactory(){
		//mybatis的配置文件
        String resource = "mybatis.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisCacheTest.class.getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        return sessionFactory;
	}
	//@Test
	public void firstGradeCache(){
		/*
		 一级缓存 
		 一级缓存是SqlSession级别的缓存。在操作数据库时需要构造sqlSession对象，
		 在对象中有个(内存区域)数据结构(HashMap)用于存储缓存数据。
		 不同的SqlSession之间缓存数据区域 (HashMap)是互不影响的。
		 
		 作用域
		 一级缓存的作用域是同一个SqlSession在同一个SqlSession中两次执行相同的sql语句
		 第一次执行完毕会将数据库中查询的数据写到缓存(内存)，第二次会从缓存中获取数据，
		 将不再从数据库查询，从而提高查询效率。当一个SqlSession结束后该SqlSession
		 结束后该SqlSession中的一级缓存也就不存在了。(Mybatis默认开启一级缓存)
		 */
		//获取一个session会话
		SqlSession session = getSession();
		//获取一个执行sql语句的映射接口   A 
		MybatisCache firstGradeCacheA = session.getMapper(MybatisCache.class);
		//获取一个执行sql语句的映射接口   B
		MybatisCache firstGradeCacheB = session.getMapper(MybatisCache.class);
		
		//通过A和B执行同一条sql
		Student studentA = firstGradeCacheA.queryStudentById(1);
		Student studentB = firstGradeCacheB.queryStudentById(1);
		//通过log4j发现sql语句执行了一次  ，并且两个对象相同。说明是第一次查询存入到内存里面的对象
		System.out.println(studentA==studentB);
		
	}
	
	//@Test
	public void firstGradeCacheCommit(){
		/*
		 一级缓存 
		 一级缓存是SqlSession级别的缓存。在操作数据库时需要构造sqlSession对象，
		 在对象中有个(内存区域)数据结构(HashMap)用于存储缓存数据。
		 不同的SqlSession之间缓存数据区域 (HashMap)是互不影响的。
		 
		 作用域
		 一级缓存的作用域是同一个SqlSession在同一个SqlSession中两次执行相同的sql语句
		 第一次执行完毕会将数据库中查询的数据写到缓存(内存)，第二次会从缓存中获取数据(session在期间该session没有commit)，
		 将不再从数据库查询，从而提高查询效率。(Mybatis默认开启一级缓存)
		 当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
		 
		 注意:
		 	如果SqlSession去执行commit操作(执行插入、更新、删除)，
		 	会清空SqlSession中的一级缓存，这样做的目的为了让缓存中的数据是最新的信息，避免脏读
		 */
		//获取一个session会话
		SqlSession session = getSession();
		//获取一个执行sql语句的映射接口   A 
		MybatisCache firstGradeCacheA = session.getMapper(MybatisCache.class);
		//获取一个执行sql语句的映射接口   update 
		MybatisCache update= session.getMapper(MybatisCache.class);
		//获取一个执行sql语句的映射接口   A 
		MybatisCache firstGradeCacheB = session.getMapper(MybatisCache.class);
		//让A先查询并 获取实体1
		Student studentA = firstGradeCacheA.queryStudentById(1);
		//创建一个修改的数据
		Student updateStudent = new Student(1,"wahaha",2);
		//然后在用更新的执行接口修改一下数据
		update.updateStudent(updateStudent);
		//然后提交一下会话
		session.commit();
		//再查一下相同的用户
		Student studentB = firstGradeCacheB.queryStudentById(1);
		//会发现sql语句执行了两次,而且返回的对象也不是同一个地址了
		System.out.println(studentA==studentB);
		
	}
	/**
	  
	二级缓存
	 	二级缓存是多个SqlSession共享的，其作用域是mapper的同一个namespace，
	 不同的sqlSession多次执行相同namespace下的Sql语句第一次执行完毕会将数据库中查询的数据写到缓存(内存)，
	 第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。
	 所以说二级缓存是跨SqlSession的
	Mybatis默认是开启二级缓存的，但是需要在mapper.xml中配置cache标签才能使用。
	如果缓存中有数据就不用从数据库中获取，大大提高系统性能
	注意：
	要使用前一个session查询过的值，必须要等前一个session  关闭后才可以获取到
	否则该值还在一级缓存中，因为mybatis的一级缓存是默认的无法取消，这也是为了数据安全性考虑
	
	 同一个SqlSessionFactory可以获取不同的session会话
	 
	 使用二级缓存
	 1.在核心配置文件Mybatis.xml中配置<setting>标签
	 （默认是开启的但是必须配置cache标签才能使用所以可以不配置该配置文件）
	 <!-- 二级缓存的配置 -->
	<settings>
		<setting name="cacheEnabled" value="true"/>
	</settings>
	 2.在Mapper.xml中开启二级缓存，当该namespace下的 sql执行完成会存储到它的缓存区域
	 	在mapper下第一行配置
	 	<mapper namespace="">
	 	<!-- 配置二级缓存
		eviction 回收策略  默认为 LRU
				【默认】LRU——最近最少使用的：移除最长时间不被使用的对象 
				【新】LFU——最近一段时间使用次数最少的(部分版本不能使用)
		 		FIFO——先进先出的：按对象进入缓存的顺序来移除他们 
		 		SOFT——软引用：移除基于垃圾回收器状态和软引用规则的对象 
		 		WEAK——弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
		flushInterval (刷新间隔)
				可以被设置为任意的正整数（60*60*1000这种形式是不允许的），而且它们代表一个合理的毫秒形式的时间段。
				默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新。 
		size (引用数目)
				可以被设置为任意正整数，要记住你缓存的对象数目和你运行环境的可用内存资源数目。默认值是1024.
		readOnly (只读)
				属性可以被设置为true或false。只读的缓存会给所有调用者返回缓存对象的相同实例，因此这些对象不能被修改，这提供了很重要的性能优势。可读写的缓存会返回缓存对象的拷贝（通过发序列化）。这会慢一些，但是安全，因此默认是false。
		type=""  自定义缓存的类
				自定义缓存需要实现cache接口,并且重写其中的方法，就可以使用第三方缓存了
		 -->
		<cache eviction="FIFO" flushInterval="10800000" size="512" readOnly="true"></cache>
		
		.........
		</Mapper>
	
	 */
	@Test
	public void secondGradeCacheNoCommitAndClose(){
		
		//获取一个session工厂
		SqlSessionFactory factory = getSqlSessionFactory();
		//获取两个不同的会话
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//执行接口
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//查询id为1的学生
		Student studentA=firstGradeCacheA.queryStudentById(1);
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//比较两个结果相同（如果是使用了序列化和反序列化则不相同）
		System.out.println(studentA==studentB);
		
		//如果不提交不关闭则数据一直在一级缓存中，会发起两个sql语句
	}
	@Test
	public void secondGradeCacheByCommit(){
		
		//获取一个session工厂
		SqlSessionFactory factory = getSqlSessionFactory();
		//获取两个不同的会话
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//执行接口
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//查询id为1的学生
		Student studentA=firstGradeCacheA.queryStudentById(1);
		//关闭A会话，将一级缓存清空，存入二级缓存
		sessionA.commit();
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//比较两个结果相同（如果是使用了序列化和反序列化则不相同）
		System.out.println(studentA==studentB);
		//通过log4j显示的运行结果可以看出语句值查询了一次
	}
	@Test
	public void secondGradeCacheClose(){
		
		//获取一个session工厂
		SqlSessionFactory factory = getSqlSessionFactory();
		//获取两个不同的会话
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//执行接口
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//查询id为1的学生
		Student studentA=firstGradeCacheA.queryStudentById(1);
		//关闭A会话，将一级缓存清空，存入二级缓存
		sessionA.close();
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//比较两个结果相同（如果是使用了序列化和反序列化则不相同）
		System.out.println(studentA==studentB);
		//通过log4j显示的运行结果可以看出语句值查询了一次
	}
	@Test
	public void queryStudentRedis(){
		/**
		 *使用二级缓存时前面的方法查询过该用户后,该方法查询直接从redis中获取，不发起sql语句
		 */
		SqlSession session = getSession();
		MybatisCache firstGradeCache = session.getMapper(MybatisCache.class);

		Student student = firstGradeCache.queryStudentById(2);
		session.close();
	}
	/**
	 * 提交一个数据看是否会将二级缓存清空
	 */
	@Test
	public void commitData(){
		SqlSession session = getSession();
		MybatisCache update= session.getMapper(MybatisCache.class);

		Student updateStudent = new Student(1,"wahaha",2);
		update.updateStudent(updateStudent);
		session.commit();
	}

}
