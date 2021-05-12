package com.imatia.qsallcomponents.model.dao.setup;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository(value = "OCSharePreferences")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/setup/OCSharePreferences.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class OCSharePreferences extends OntimizeJdbcDaoSupport {

	public static final String ATTR_ID = "ID_SHARE";
	public static final String ATTR_MESSAGE = "MESSAGE";
	public static final String ATTR_SHARE_TYPE = "SHARE_TYPE";
	public static final String ATTR_CONTENT_SHARE = "CONTENT_SHARE";
	public static final String ATTR_USER_SOURCE = "USER_SOURCE";
	public static final String ATTR_NAME = "NAME";

	public static final String QUERY_DEFAULT = "default";

	public OCSharePreferences() {
		super();
	}

}
