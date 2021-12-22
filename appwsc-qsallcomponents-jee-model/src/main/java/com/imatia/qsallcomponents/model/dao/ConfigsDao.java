package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "ConfigsDao")
@ConfigurationFile(
	configurationFile = "base-dao/ConfigsDao.xml",
	configurationFilePlaceholder = "base-dao/placeholders.properties")
public class ConfigsDao extends OntimizeJdbcDaoSupport {

	public static final String ID   	  = "ID_CONFIG";
	public static final String USER 	  = "USER_CONFIG";
	public static final String TYPE  	  = "TYPE_CONFIG";
	public static final String COMPONENTS = "COMPONENTS";

}