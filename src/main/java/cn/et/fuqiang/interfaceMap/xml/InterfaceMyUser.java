package cn.et.fuqiang.interfaceMap.xml;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * �ӿ�ӳ��   mapper ��sql��ǩ��id������ӿڵķ�����һ��
 * @author Administrator
 *
 */
public interface InterfaceMyUser {
	public Map<String,Object> queryMyUserById(@Param("userid") Integer userid);
	public void updateUserByMap(Map<String,Object> map);
	public void saveUserByMap(Map<String,Object> map);
	public void deleteUserById(Integer userid);

}
