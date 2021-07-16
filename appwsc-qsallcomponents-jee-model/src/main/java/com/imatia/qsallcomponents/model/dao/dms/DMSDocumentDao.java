package com.imatia.qsallcomponents.model.dao.dms;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.dms.dao.IDMSDocumentDao;

@Repository(value = "DMSDocumentDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/dms/DMSDocumentDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class DMSDocumentDao extends OntimizeJdbcDaoSupport implements IDMSDocumentDao {

}
