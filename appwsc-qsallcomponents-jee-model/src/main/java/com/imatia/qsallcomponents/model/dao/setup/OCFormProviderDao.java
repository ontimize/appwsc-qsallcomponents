package com.imatia.qsallcomponents.model.dao.setup;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository(value = "OCFormProviderDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/setup/OCFormProviderDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class OCFormProviderDao extends OntimizeJdbcDaoSupport {

	public static final String ATTR_ID = "ID_FORMPROVIDER";
	public static final String ATTR_NAME = "FORMNAME";
	public static final String ATTR_XML = "FORMXML";

	public OCFormProviderDao() {
		super();
	}

}
