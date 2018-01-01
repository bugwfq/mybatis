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
	 * 简单的使用resultMap  
	 *     @Results注解相当于  mapper.xml文件中     resultMap标签
	 *     其中可以包括多个@Result注解  
	 *     如果只有一个的话可直接写
	 *     多个 的话必须用{@Result(),@Result()}  中间用 ","号隔开
	 *     注意：@Select 注解不能忘记  如果忘记会报出
	 *     org.apache.ibatis.binding.BindingException: Invalid bound statement (not found):接口全类名.方法名
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
	 * 使用resultMap实现多表查询
	 * 在@Result注解中可以使用多表查询     
	 * 
	 * 有两个  属性  many（一对多） 和  one（一对一）
	 * 
	 * 一对多的使用
	 * @Result(property="students",column="gid",many=@Many(select="cn.et.fuqiang.resultMap.annotation.StudentAnnotationInterface.queryStudentByGid"))
		property=实体类中的属性名  ， 
		column=是通过那个列关联  
		many=@Many(select=namespace.方法名   指定查询的方法  ，如果在其他接口中必须指定   全类名.方法名)

	          对应查询的的方法
		如果表名与字段名不符必须先声明如果相同可以不设置
		@Results({@Result(column="stuid",property="sid"),@Result(column="stuname",property="sname")})
		查询语句相当于关联查询  ，传入的值是指定的关联的列传入的  column 所以方法中不用传
		@Select("select * from student where gid=#{0}")
		public List<Student> queryStudentByGid();
	 * @return 一对多返回的集合类型
	 */
	@Results({@Result(property="id",column="gid"),@Result(property="students",column="gid",many=@Many(select="cn.et.fuqiang.resultMap.annotation.StudentAnnotationInterface.queryStudentByGid"))})
	@Select("select * from grade")
	public List<Grade> queryAllGrade();
}
