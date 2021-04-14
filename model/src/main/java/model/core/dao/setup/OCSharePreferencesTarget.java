package model.core.dao.setup;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository(value = "OCSharePreferencesTarget")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/setup/OCSharePreferencesTarget.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class OCSharePreferencesTarget extends OntimizeJdbcDaoSupport {

	public static final String ATTR_ID = "ID_SHARE_TARGET";
	public static final String ATTR_ID_SHARE = "ID_SHARE";
	public static final String ATTR_USER_TARGET = "USER_TARGET";

	public OCSharePreferencesTarget() {
		super();
	}

}
