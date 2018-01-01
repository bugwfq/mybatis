package cn.et.fuqiang.dynamicSql.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.et.fuqiang.dynamicSql.entity.Student;
/**
 * 用于编写动态sql的类
 * 所有方法必须返回 String 类型因为返回的是sql语句
 * 
 * 动态sql的编写有两种方法
  第一种
 	直接编写String类型的sql语句
 第二种
  	使用SQL对象来编写sql语句（方便,好处理）
  	
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
public class StudentProvider {
	public String queryStudentByParamsAndIFSql(final Student student){
		StringBuilder sql = new StringBuilder("select * from student where 1=1 ");
		if(student.getStuname()!=null){
			sql.append(" and stuname like '%"+student.getStuname()+"%'");
		}
		if(student.getGid()!=null){
			sql.append(" and gid ="+student.getGid());
		}
		return sql.toString();
	}
	public String queryStudentsByStunameSql(String stuname){
		if(stuname==null){
			stuname="";
		}
		SQL sql = new SQL();
		sql.SELECT("*").FROM("student").WHERE("stuname like '%"+stuname+"%'");
		return sql.toString();
	}
	/**
	 * WHERE会自动判断如果有就加没有就变为and  若在后面加OR()则默认的and会被替换
	 * 
	 *  
	 * @param param
	 * @return   SELECT * FROM student WHERE (stuid=) OR (stuid=) OR (stuid=) 
	 */
	public String queryStudentsBySidsSql(Map<String,Object> param){
		List<Integer> sids =  (List<Integer>)param.get("sids");
		SQL sql = new SQL();
		sql.SELECT("*").FROM("student");
		for (int i = 0; i<sids.size();i++) {
			sql.WHERE("stuid="+sids.get(i));
			if(i != sids.size()-1){
				sql.OR();
			}
			
		}
		return sql.toString();
	}
	/**
	 * SQL中的SET()会自动加上  "," 号
	 * 每个修改语句都放在SET中不需要任何符号，也不需要写SET
	 * @param param
	 * @return
	 */
	public String updateStudentBySetSql(Map<String,Object> param){
		Student student = (Student) param.get("student");
		SQL sql = new SQL();
		sql.UPDATE("student");
		String sb = "";
		/**
		 * 在Provider类中使用  ognl 表达式与 el表达式 如果是对象则要    对象名.变量名
		 */
		if(student.getStuname()!=null){
			sql.SET("stuname=#{student.stuname}");
		}
		
		if(student.getGid()!=null){
			sql.SET("gid=#{student.gid}");
		}
		sql.WHERE("stuid="+student.getStuid());
		
		return sql.toString();
	}

}
