package cn.et.fuqiang.cache.xml;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.et.fuqiang.cache.entity.Student;


public class MybatisCacheTest {
	public SqlSession getSession(){
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = MybatisCacheTest.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        return sessionFactory.openSession();
	}
	public SqlSessionFactory getSqlSessionFactory(){
		//mybatis�������ļ�
        String resource = "mybatis.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = MybatisCacheTest.class.getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        return sessionFactory;
	}
	//@Test
	public void firstGradeCache(){
		/*
		 һ������ 
		 һ��������SqlSession����Ļ��档�ڲ������ݿ�ʱ��Ҫ����sqlSession����
		 �ڶ������и�(�ڴ�����)���ݽṹ(HashMap)���ڴ洢�������ݡ�
		 ��ͬ��SqlSession֮�仺���������� (HashMap)�ǻ���Ӱ��ġ�
		 
		 ������
		 һ���������������ͬһ��SqlSession��ͬһ��SqlSession������ִ����ͬ��sql���
		 ��һ��ִ����ϻὫ���ݿ��в�ѯ������д������(�ڴ�)���ڶ��λ�ӻ����л�ȡ���ݣ�
		 �����ٴ����ݿ��ѯ���Ӷ���߲�ѯЧ�ʡ���һ��SqlSession�������SqlSession
		 �������SqlSession�е�һ������Ҳ�Ͳ������ˡ�(MybatisĬ�Ͽ���һ������)
		 */
		//��ȡһ��session�Ự
		SqlSession session = getSession();
		//��ȡһ��ִ��sql����ӳ��ӿ�   A 
		MybatisCache firstGradeCacheA = session.getMapper(MybatisCache.class);
		//��ȡһ��ִ��sql����ӳ��ӿ�   B
		MybatisCache firstGradeCacheB = session.getMapper(MybatisCache.class);
		
		//ͨ��A��Bִ��ͬһ��sql
		Student studentA = firstGradeCacheA.queryStudentById(1);
		Student studentB = firstGradeCacheB.queryStudentById(1);
		//ͨ��log4j����sql���ִ����һ��  ����������������ͬ��˵���ǵ�һ�β�ѯ���뵽�ڴ�����Ķ���
		System.out.println(studentA==studentB);
		
	}
	
	//@Test
	public void firstGradeCacheCommit(){
		/*
		 һ������ 
		 һ��������SqlSession����Ļ��档�ڲ������ݿ�ʱ��Ҫ����sqlSession����
		 �ڶ������и�(�ڴ�����)���ݽṹ(HashMap)���ڴ洢�������ݡ�
		 ��ͬ��SqlSession֮�仺���������� (HashMap)�ǻ���Ӱ��ġ�
		 
		 ������
		 һ���������������ͬһ��SqlSession��ͬһ��SqlSession������ִ����ͬ��sql���
		 ��һ��ִ����ϻὫ���ݿ��в�ѯ������д������(�ڴ�)���ڶ��λ�ӻ����л�ȡ����(session���ڼ��sessionû��commit)��
		 �����ٴ����ݿ��ѯ���Ӷ���߲�ѯЧ�ʡ�(MybatisĬ�Ͽ���һ������)
		 �� Session flush �� close ֮�󣬸�Session�е����� Cache �ͽ���ա�
		 
		 ע��:
		 	���SqlSessionȥִ��commit����(ִ�в��롢���¡�ɾ��)��
		 	�����SqlSession�е�һ�����棬��������Ŀ��Ϊ���û����е����������µ���Ϣ���������
		 */
		//��ȡһ��session�Ự
		SqlSession session = getSession();
		//��ȡһ��ִ��sql����ӳ��ӿ�   A 
		MybatisCache firstGradeCacheA = session.getMapper(MybatisCache.class);
		//��ȡһ��ִ��sql����ӳ��ӿ�   update 
		MybatisCache update= session.getMapper(MybatisCache.class);
		//��ȡһ��ִ��sql����ӳ��ӿ�   A 
		MybatisCache firstGradeCacheB = session.getMapper(MybatisCache.class);
		//��A�Ȳ�ѯ�� ��ȡʵ��1
		Student studentA = firstGradeCacheA.queryStudentById(1);
		//����һ���޸ĵ�����
		Student updateStudent = new Student(1,"wahaha",2);
		//Ȼ�����ø��µ�ִ�нӿ��޸�һ������
		update.updateStudent(updateStudent);
		//Ȼ���ύһ�»Ự
		session.commit();
		//�ٲ�һ����ͬ���û�
		Student studentB = firstGradeCacheB.queryStudentById(1);
		//�ᷢ��sql���ִ��������,���ҷ��صĶ���Ҳ����ͬһ����ַ��
		System.out.println(studentA==studentB);
		
	}
	/**
	  
	��������
	 	���������Ƕ��SqlSession����ģ�����������mapper��ͬһ��namespace��
	 ��ͬ��sqlSession���ִ����ͬnamespace�µ�Sql����һ��ִ����ϻὫ���ݿ��в�ѯ������д������(�ڴ�)��
	 �ڶ��λ�ӻ����л�ȡ���ݽ����ٴ����ݿ��ѯ���Ӷ���߲�ѯЧ�ʡ�
	 ����˵���������ǿ�SqlSession��
	MybatisĬ���ǿ�����������ģ�������Ҫ��mapper.xml������cache��ǩ����ʹ�á�
	��������������ݾͲ��ô����ݿ��л�ȡ��������ϵͳ����
	ע�⣺
	Ҫʹ��ǰһ��session��ѯ����ֵ������Ҫ��ǰһ��session  �رպ�ſ��Ի�ȡ��
	�����ֵ����һ�������У���Ϊmybatis��һ��������Ĭ�ϵ��޷�ȡ������Ҳ��Ϊ�����ݰ�ȫ�Կ���
	
	 ͬһ��SqlSessionFactory���Ի�ȡ��ͬ��session�Ự
	 
	 ʹ�ö�������
	 1.�ں��������ļ�Mybatis.xml������<setting>��ǩ
	 ��Ĭ���ǿ����ĵ��Ǳ�������cache��ǩ����ʹ�����Կ��Բ����ø������ļ���
	 <!-- ������������� -->
	<settings>
		<setting name="cacheEnabled" value="true"/>
	</settings>
	 2.��Mapper.xml�п����������棬����namespace�µ� sqlִ����ɻ�洢�����Ļ�������
	 	��mapper�µ�һ������
	 	<mapper namespace="">
	 	<!-- ���ö�������
		eviction ���ղ���  Ĭ��Ϊ LRU
				��Ĭ�ϡ�LRU�����������ʹ�õģ��Ƴ��ʱ�䲻��ʹ�õĶ��� 
				���¡�LFU�������һ��ʱ��ʹ�ô������ٵ�(���ְ汾����ʹ��)
		 		FIFO�����Ƚ��ȳ��ģ���������뻺���˳�����Ƴ����� 
		 		SOFT���������ã��Ƴ���������������״̬�������ù���Ķ��� 
		 		WEAK���������ã����������Ƴ����������ռ���״̬�������ù���Ķ���
		flushInterval (ˢ�¼��)
				���Ա�����Ϊ�������������60*60*1000������ʽ�ǲ�����ģ����������Ǵ���һ������ĺ�����ʽ��ʱ��Ρ�
				Ĭ������ǲ����ã�Ҳ����û��ˢ�¼������������������ʱˢ�¡� 
		size (������Ŀ)
				���Ա�����Ϊ������������Ҫ��ס�㻺��Ķ�����Ŀ�������л����Ŀ����ڴ���Դ��Ŀ��Ĭ��ֵ��1024.
		readOnly (ֻ��)
				���Կ��Ա�����Ϊtrue��false��ֻ���Ļ��������е����߷��ػ���������ͬʵ���������Щ�����ܱ��޸ģ����ṩ�˺���Ҫ���������ơ��ɶ�д�Ļ���᷵�ػ������Ŀ�����ͨ�������л����������һЩ�����ǰ�ȫ�����Ĭ����false��
		type=""  �Զ��建�����
				�Զ��建����Ҫʵ��cache�ӿ�,������д���еķ������Ϳ���ʹ�õ�����������
		 -->
		<cache eviction="FIFO" flushInterval="10800000" size="512" readOnly="true"></cache>
		
		.........
		</Mapper>
	
	 */
	@Test
	public void secondGradeCacheNoCommitAndClose(){
		
		//��ȡһ��session����
		SqlSessionFactory factory = getSqlSessionFactory();
		//��ȡ������ͬ�ĻỰ
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//ִ�нӿ�
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//��ѯidΪ1��ѧ��
		Student studentA=firstGradeCacheA.queryStudentById(1);
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//�Ƚ����������ͬ�������ʹ�������л��ͷ����л�����ͬ��
		System.out.println(studentA==studentB);
		
		//������ύ���ر�������һֱ��һ�������У��ᷢ������sql���
	}
	@Test
	public void secondGradeCacheByCommit(){
		
		//��ȡһ��session����
		SqlSessionFactory factory = getSqlSessionFactory();
		//��ȡ������ͬ�ĻỰ
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//ִ�нӿ�
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//��ѯidΪ1��ѧ��
		Student studentA=firstGradeCacheA.queryStudentById(1);
		//�ر�A�Ự����һ��������գ������������
		sessionA.commit();
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//�Ƚ����������ͬ�������ʹ�������л��ͷ����л�����ͬ��
		System.out.println(studentA==studentB);
		//ͨ��log4j��ʾ�����н�����Կ������ֵ��ѯ��һ��
	}
	@Test
	public void secondGradeCacheClose(){
		
		//��ȡһ��session����
		SqlSessionFactory factory = getSqlSessionFactory();
		//��ȡ������ͬ�ĻỰ
		SqlSession sessionA = factory.openSession();
		SqlSession sessionB = factory.openSession();
		//ִ�нӿ�
		MybatisCache firstGradeCacheA = sessionA.getMapper(MybatisCache.class);
		MybatisCache firstGradeCacheB= sessionB.getMapper(MybatisCache.class);
		
		//��ѯidΪ1��ѧ��
		Student studentA=firstGradeCacheA.queryStudentById(1);
		//�ر�A�Ự����һ��������գ������������
		sessionA.close();
		Student studentB=firstGradeCacheB.queryStudentById(1);
		//�Ƚ����������ͬ�������ʹ�������л��ͷ����л�����ͬ��
		System.out.println(studentA==studentB);
		//ͨ��log4j��ʾ�����н�����Կ������ֵ��ѯ��һ��
	}
	@Test
	public void queryStudentRedis(){
		/**
		 *ʹ�ö�������ʱǰ��ķ�����ѯ�����û���,�÷�����ѯֱ�Ӵ�redis�л�ȡ��������sql���
		 */
		SqlSession session = getSession();
		MybatisCache firstGradeCache = session.getMapper(MybatisCache.class);

		Student student = firstGradeCache.queryStudentById(2);
		session.close();
	}
	/**
	 * �ύһ�����ݿ��Ƿ�Ὣ�����������
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
