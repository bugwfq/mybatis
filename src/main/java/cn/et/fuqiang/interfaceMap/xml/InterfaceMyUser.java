package cn.et.fuqiang.interfaceMap.xml;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 接口映射   mapper 中sql标签的id必须与接口的方法名一样
 * @author Administrator
 *
 */
public interface InterfaceMyUser {
	public Map<String,Object> queryMyUserById(@Param("userid") Integer userid);
	public void updateUserByMap(Map<String,Object> map);
	public void saveUserByMap(Map<String,Object> map);
	public void deleteUserById(Integer userid);

}
