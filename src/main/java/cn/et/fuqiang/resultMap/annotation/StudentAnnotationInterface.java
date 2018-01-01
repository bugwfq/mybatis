package cn.et.fuqiang.resultMap.annotation;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.fuqiang.resultMap.entity.Grade;
import cn.et.fuqiang.resultMap.entity.Student;
/**
 * ʹ��ע�� Ӧ�� resultMap
 * @author Administrator
 *
 */
public interface StudentAnnotationInterface {
	
	/**
	 * �򵥵�ʹ��resultMap  
	 *     @Resultsע���൱��  mapper.xml�ļ���     resultMap��ǩ
	 *     ���п��԰������@Resultע��  
	 *     ���ֻ��һ���Ļ���ֱ��д
	 *     ��� �Ļ�������{@Result(),@Result()}  �м��� ","�Ÿ���
	 *     ע�⣺@Select ע�ⲻ������  ������ǻᱨ��
	 *     org.apache.ibatis.binding.BindingException: Invalid bound statement (not found):�ӿ�ȫ����.������
	 *     
	 * @param id
	 * @return
	 */
	@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname")})
	@Select("select * from student where stuid=#{0}")
	public Student queryStudentById(Integer sid);
	
	/**
	 * ʹ��resultMapʵ�ֶ���ѯ
	 * ��@Resultע���п���ʹ�ö���ѯ     
	 * 
	 * ������  ����  many��һ�Զࣩ ��  one��һ��һ��
	 * 
	 * һ��һ��ʹ��
	 *@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname"),@Result(column="gid",property="grade",one=@One(select="cn.et.fuqiang.resultMap.annotation.GradeAnnotationInterface.gradeInStudent"))})
		property=ʵ�����е�������  �� 
		column=��ͨ���Ǹ��й���  
		one=@one(select=namespace.������   ָ����ѯ�ķ���  ������������ӿ��б���ָ��   ȫ����.������)


	          ��Ӧ��ѯ�ĵķ���
		����������ֶ����������������������ͬ���Բ�����
		@Results(@Result(property="id",column="gid"))
		��ѯ����൱�ڹ�����ѯ  �������ֵ��ָ���Ĺ������д����  column ���Է����в��ô�
		@Select("select * from grade where gid=#{0}")
		public Grade gradeInStudent();
		
	 * @return һ�Զ෵��ָ���Ķ�������
	 */
	@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname"),@Result(column="gid",property="grade",one=@One(select="cn.et.fuqiang.resultMap.annotation.GradeAnnotationInterface.gradeInStudent"))})
	@Select("select * from student")
	public List<Student> queryAllStudent();
	
	@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname")})
	@Select("select * from student where gid=#{0}")
	public List<Student> queryStudentByGid();
}
