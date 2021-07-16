package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.reportstore.dao.IReportDao;

@Lazy
@Repository(value = "ReportDao")
@ConfigurationFile(configurationFile = "base-dao/ReportDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class ReportDao extends OntimizeJdbcDaoSupport implements IReportDao {
	
	public static final String	ATTR_ID						= "ID";
	public static final String	ATTR_NAME					= "NAME";
	public static final String	ATTR_DESCRIPTION			= "DESCRIPTION";
	public static final String	ATTR_REPORT_TYPE			= "TYPE";
	public static final String	ATTR_MAIN_REPORT_FILENAME	= "MAIN_REPORT_FILENAME";
	public static final String	ATTR_ZIP					= "ZIP";
	public static final String	ATTR_COMPILED				= "COMPILED";
	
	public ReportDao() {
		super();
	}
	
}
