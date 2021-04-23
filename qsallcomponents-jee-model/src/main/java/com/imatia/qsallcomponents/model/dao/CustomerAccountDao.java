package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "CustomerAccountDao")
@ConfigurationFile(configurationFile = "base-dao/CustomerAccountDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class CustomerAccountDao extends OntimizeJdbcDaoSupport {

	public static final String CUSTOMER_ACCOUNT_BALANCE_QUERY_KEY = "VCUSTOMERACCOUNTBALANCE";

	public CustomerAccountDao() {
		super();
	}

}
