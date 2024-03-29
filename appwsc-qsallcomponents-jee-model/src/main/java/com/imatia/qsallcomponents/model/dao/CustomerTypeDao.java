package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "CustomerTypeDao")
@ConfigurationFile(configurationFile = "base-dao/CustomerTypeDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class CustomerTypeDao extends OntimizeJdbcDaoSupport {

	public static final String	AGGREGATE_QUERY_KEY	= "AGGREGATE";

	public static final String	ATTR_ID				= "CUSTOMERTYPEID";
	public static final String	ATTR_DESCRIPTION	= "DESCRIPTION";

}
