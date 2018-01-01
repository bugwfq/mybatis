package cn.et.fuqiang.resultMap.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.et.fuqiang.resultMap.entity.Grade;
import cn.et.fuqiang.resultMap.entity.Student;

public interface GradeAnnotationInterface {
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
	@Results(@Result(property="id",column="gid"))
	@Select("select * from grade where gid=#{0}")
	public Grade queryGradeById(Integer id);
	
	
	@Results(@Result(property="id",column="gid"))
	@Select("select * from grade where gid=#{0}")
	public Grade gradeInStudent();
	
	/**
	 * ʹ��resultMapʵ�ֶ���ѯ
	 * ��@Resultע���п���ʹ�ö���ѯ     
	 * 
	 * ������  ����  many��һ�Զࣩ ��  one��һ��һ��
	 * 
	 * һ�Զ��ʹ��
	 * @Result(property="students",column="gid",many=@Many(select="cn.et.fuqiang.resultMap.annotation.StudentAnnotationInterface.queryStudentByGid"))
		property=ʵ�����е�������  �� 
		column=��ͨ���Ǹ��й���  
		many=@Many(select=namespace.������   ָ����ѯ�ķ���  ������������ӿ��б���ָ��   ȫ����.������)

	          ��Ӧ��ѯ�ĵķ���
		����������ֶ����������������������ͬ���Բ�����
		@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname")})
		��ѯ����൱�ڹ�����ѯ  �������ֵ��ָ���Ĺ������д����  column ���Է����в��ô�
		@Select("select * from student where gid=#{0}")
		public List<Student> queryStudentByGid();
	 * @return һ�Զ෵�صļ�������
	 */
	@Results({@Result(property="id",column="gid"),@Result(property="students",column="gid",many=@Many(select="cn.et.fuqiang.resultMap.annotation.StudentAnnotationInterface.queryStudentByGid"))})
	@Select("select * from grade")
	public List<Grade> queryAllGrade();
}
