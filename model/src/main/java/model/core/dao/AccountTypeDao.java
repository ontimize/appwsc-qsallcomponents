package model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "AccountTypeDao")
@ConfigurationFile(configurationFile = "base-dao/AccountTypeDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class AccountTypeDao extends OntimizeJdbcDaoSupport {

	public static final String	AGGREGATE_QUERY_KEY	= "AGGREGATE";

	public static final String	ATTR_ID				= "ACCOUNTTYPEID";
	public static final String	ATTR_NAME			= "ACCOUNTTYPENAME";

}
