package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "TenantUserDao")
@ConfigurationFile(configurationFile = "base-dao/TenantUserDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class TenantUserDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "TENANTUSERID";
    public static final String ATTR_USER = "USER_";
    public static final String ATTR_TENANTNAME = "TENANTNAME";
    public static final String ATTR_DRIVERCLASSNAME = "DRIVERCLASSNAME";
    public static final String ATTR_JDBCURL = "JDBCURL";
    public static final String ATTR_USERNAME = "USERNAME";
    public static final String ATTR_DBPASSWORD = "DBPASSWORD";
    public static final String ATTR_USERISADMIN = "USERISADMIN";

    public TenantUserDao() {
	super();
    }

}
