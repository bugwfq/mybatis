package cn.et.fuqiang.resultMap.xml;

import java.util.List;

import cn.et.fuqiang.resultMap.entity.Grade;

public interface GradeXmlInterface {
	public Grade queryGradeById(Integer id);
	public List<Grade> queryAllGrade();
}
