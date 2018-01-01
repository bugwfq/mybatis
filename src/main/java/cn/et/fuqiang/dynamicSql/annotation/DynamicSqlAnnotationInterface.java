package cn.et.fuqiang.dynamicSql.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.et.fuqiang.dynamicSql.entity.Student;
/**
 * 
 * 注解使用动态sql有两种方法
 * 第一种
 	在标签中直接使用字符串类型的标签必须用   <scrip>  动态sql   </scrip>
 	
 * 第二种
 * 使用注解 @*Provider() 用java代码完成动态sql
 * 需要一个创建一个类，并且指定方法去完成sql语句的动态拼接，最后再以String类型方法返回
 * 
 * 该注解中有两个值   
 * type=   拼接动态sql语句的类.class
 * method= 返回动态sql语句的方法名
 * 
 	在Provider类中使用调用方法时传入的参数值有两种方法
  	
  	第一种
  	在接口的形参前定义@Param("形参名")
  	然后在Provider类中对应的方法形参中使用Map接收,使用是直接使用map.get("param注解中使用的形参名")获取
  	第二种
  	 在接口上定义下面的注解 设置两个参数
  	 useGeneratedKeys=true
  	 keyProperty="形参的名字"
  	@Options(useGeneratedKeys=true,keyProperty="形参的名字")
  	然后在Provider类中对应的方法上写上相同的变量就可以直接使用
  	(注意使用第二种方法是接口映射的接口中不能定义@Param注解)

 * @author Administrator
 *
 */
public interface DynamicSqlAnnotationInterface {
	
	@SelectProvider(type=StudentProvider.class,method="queryStudentByParamsAndIFSql")
	/** 配置该注解指定在Provider类中的方法可以使用传入的形参名  */
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
