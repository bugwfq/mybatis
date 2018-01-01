package cn.et.fuqiang.dynamicSql.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import cn.et.fuqiang.dynamicSql.entity.Student;
/**
 * ���ڱ�д��̬sql����
 * ���з������뷵�� String ������Ϊ���ص���sql���
 * 
 * ��̬sql�ı�д�����ַ���
  ��һ��
 	ֱ�ӱ�дString���͵�sql���
 �ڶ���
  	ʹ��SQL��������дsql��䣨����,�ô���
  	
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
	 * WHERE���Զ��ж�����оͼ�û�оͱ�Ϊand  ���ں����OR()��Ĭ�ϵ�and�ᱻ�滻
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
	 * SQL�е�SET()���Զ�����  "," ��
	 * ÿ���޸���䶼����SET�в���Ҫ�κη��ţ�Ҳ����ҪдSET
	 * @param param
	 * @return
	 */
	public String updateStudentBySetSql(Map<String,Object> param){
		Student student = (Student) param.get("student");
		SQL sql = new SQL();
		sql.UPDATE("student");
		String sb = "";
		/**
		 * ��Provider����ʹ��  ognl ���ʽ�� el���ʽ ����Ƕ�����Ҫ    ������.������
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
