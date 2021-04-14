package model.core.dao.dms;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.dms.dao.IDMSDocumentPropertyDao;

@Repository(value = "DMSDocumentPropertyDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/dms/DMSDocumentPropertyDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class DMSDocumentPropertyDao extends OntimizeJdbcDaoSupport implements IDMSDocumentPropertyDao {

}
