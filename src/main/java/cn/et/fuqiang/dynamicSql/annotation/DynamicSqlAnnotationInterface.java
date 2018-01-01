package cn.et.fuqiang.dynamicSql.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.et.fuqiang.dynamicSql.entity.Student;
/**
 * 
 * ע��ʹ�ö�̬sql�����ַ���
 * ��һ��
 	�ڱ�ǩ��ֱ��ʹ���ַ������͵ı�ǩ������   <scrip>  ��̬sql   </scrip>
 	
 * �ڶ���
 * ʹ��ע�� @*Provider() ��java������ɶ�̬sql
 * ��Ҫһ������һ���࣬����ָ������ȥ���sql���Ķ�̬ƴ�ӣ��������String���ͷ�������
 * 
 * ��ע����������ֵ   
 * type=   ƴ�Ӷ�̬sql������.class
 * method= ���ض�̬sql���ķ�����
 * 
 	��Provider����ʹ�õ��÷���ʱ����Ĳ���ֵ�����ַ���
  	
  	��һ��
  	�ڽӿڵ��β�ǰ����@Param("�β���")
  	Ȼ����Provider���ж�Ӧ�ķ����β���ʹ��Map����,ʹ����ֱ��ʹ��map.get("paramע����ʹ�õ��β���")��ȡ
  	�ڶ���
  	 �ڽӿ��϶��������ע�� ������������
  	 useGeneratedKeys=true
  	 keyProperty="�βε�����"
  	@Options(useGeneratedKeys=true,keyProperty="�βε�����")
  	Ȼ����Provider���ж�Ӧ�ķ�����д����ͬ�ı����Ϳ���ֱ��ʹ��
  	(ע��ʹ�õڶ��ַ����ǽӿ�ӳ��Ľӿ��в��ܶ���@Paramע��)

 * @author Administrator
 *
 */
public interface DynamicSqlAnnotationInterface {
	
	@SelectProvider(type=StudentProvider.class,method="queryStudentByParamsAndIFSql")
	/** ���ø�ע��ָ����Provider���еķ�������ʹ�ô�����β���  */
	@Options(useGeneratedKeys=true,keyProperty="student")
	public List<Student> queryStudentByParamsAndIFSql(Student student);
	@Select("<script>"
		+"select * from student " 
		+" <where> "
		+"	<if test=\"stuid != null\"> "
		+" and stuid=#{stuid}"
		+" </if> "
		+" <if test=\"stuname != null\" > "
		+" and stuname like '%${stuname}%'"
		+" </if>"
		+" <if test=\"gid != null\"> "
		+" and gid = #{gid}"
		+" </if> "
		+" </where> "
		+"</script>")
	public List<Student> queryStudentsByParamsAndWhereAndIF(Student student);
	
	@SelectProvider(type=StudentProvider.class,method="queryStudentsByStunameSql")
	@Options(useGeneratedKeys=true,keyProperty="stuname")
	public List<Student> queryStudentsByStuname(String stuname);
	@SelectProvider(type=StudentProvider.class,method="queryStudentsBySidsSql")
	public List<Student> queryStudentsBySids(@Param("sids") List<Integer> sids);
	@SelectProvider(type=StudentProvider.class,method="updateStudentBySetSql")
	public void updateStudentBySet(@Param("student") Student student);
	public void updateStudentByTrim(Student student);
}
