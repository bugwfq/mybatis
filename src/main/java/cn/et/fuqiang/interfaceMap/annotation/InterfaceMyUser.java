package cn.et.fuqiang.interfaceMap.annotation;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface InterfaceMyUser {
	@Select("select * from myuser where userid=#{0}")
	public Map<String,Object> queryMyUserById(Integer userid);
	/**
	 * 从sql语句中 获取参数 有多中方式
	 * 不能根据索引取值因为el表达式会把索引当运算的数字     
	 * el表达式  ${} 只能用在形参中指定的名称 来获取  和  param1 名字  		（获取值得方法相当于字符串的拼接    如果是字符串的话要用引号）
	 * ognl表达式  #{} 可以根据传入的变量名来获取值，也可以根据上面两种方式  	 （获取值得方法是防注入的，？会用问号代替）
	 * 默认是根据   形参的索引   从0开始
	 * 
	 * 如果要使用形参的参数名作为键  则需要在该形参前指定    @param("对应的名字")
	 * 
	 * @param userid
	 * @param username
	 * @param userage
	 */
	@Update("update myuser set username='${username}',userage=#{userage} where userid=#{0}")
	public void updateUserByMap(Integer userid,@Param("username")String username,@Param("userage") Integer userage);
	@SelectKey(before=true,keyProperty="userid",resultType=int.class,statement="select nvl(max(userid),0)+1 from myuser")
	@Insert("insert into myuser(userid,username,userage) values(#{userid},#{username},#{userage})")
	public void saveUserByMap(Map<String,Object> map);
	@Delete("delete from myuser where userid=#{param1}")
	public void deleteUserById(Integer userid);

}
