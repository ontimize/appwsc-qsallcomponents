package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository("ChartsPreferencesDao")
@ConfigurationFile(configurationFile = "base-dao/ChartsPreferencesDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class ChartsPreferencesDao extends OntimizeJdbcDaoSupport {

	public static final String ATTR_ID = "ID";
	public static final String ATTR_NAME = "NAME";
	public static final String ATTR_DESCRIPTION = "DESCRIPTION";
	public static final String ATTR_PREFERENCES = "PREFERENCES";

}