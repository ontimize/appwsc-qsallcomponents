package com.imatia.qsallcomponents.model.dao;

import com.ontimize.jee.report.server.dao.IReportParameterDao;
import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "ReportParameterDao")
@ConfigurationFile(configurationFile = "base-dao/ReportParameterDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class ReportParameterDao extends OntimizeJdbcDaoSupport implements IReportParameterDao {
	
	public static final String	ATTR_ID						= "ID";
	public static final String	ATTR_REPORT_ID				= "REPORT_ID";
	public static final String	ATTR_NAME					= "NAME";
	public static final String	ATTR_DESCRIPTION			= "DESCRIPTION";
	public static final String	ATTR_NESTED_TYPE			= "NESTED_TYPE";
	public static final String	ATTR_VALUE_CLASS			= "VALUE_CLASS";

}
