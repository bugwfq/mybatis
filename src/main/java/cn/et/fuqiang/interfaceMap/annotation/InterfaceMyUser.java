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
	 * ��sql����� ��ȡ���� �ж��з�ʽ
	 * ���ܸ�������ȡֵ��Ϊel���ʽ������������������     
	 * el���ʽ  ${} ֻ�������β���ָ�������� ����ȡ  ��  param1 ����  		����ȡֵ�÷����൱���ַ�����ƴ��    ������ַ����Ļ�Ҫ�����ţ�
	 * ognl���ʽ  #{} ���Ը��ݴ���ı���������ȡֵ��Ҳ���Ը����������ַ�ʽ  	 ����ȡֵ�÷����Ƿ�ע��ģ��������ʺŴ��棩
	 * Ĭ���Ǹ���   �βε�����   ��0��ʼ
	 * 
	 * ���Ҫʹ���βεĲ�������Ϊ��  ����Ҫ�ڸ��β�ǰָ��    @param("��Ӧ������")
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
