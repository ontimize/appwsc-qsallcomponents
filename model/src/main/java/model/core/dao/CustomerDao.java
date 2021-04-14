package model.core.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "CustomerDao")
@ConfigurationFile(configurationFile = "base-dao/CustomerDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class CustomerDao extends OntimizeJdbcDaoSupport {

	public static final String	ATTR_ID				= "CUSTOMERID";
	public static final String	ATTR_CUSTOMERTYPEID	= "CUSTOMERTYPEID";
	public static final String	ATTR_NAME			= "NAME";
	public static final String	ATTR_ADDRESS		= "ADDRESS";
	public static final String	ATTR_COMMENTS		= "COMMENTS";
	public static final String	ATTR_STARTDATE		= "STARTDATE";
	public static final String	ATTR_PHOTO			= "PHOTO";
	public static final String	ATTR_SURNAME		= "SURNAME";
	public static final String	ATTR_PHONE			= "PHONE";
	public static final String	ATTR_EMAIL			= "EMAIL";
	public static final String	ATTR_LONGITUDE		= "LONGITUDE";
	public static final String	ATTR_LATITUDE		= "LATITUDE";

	public CustomerDao() {
		super();
	}

}
