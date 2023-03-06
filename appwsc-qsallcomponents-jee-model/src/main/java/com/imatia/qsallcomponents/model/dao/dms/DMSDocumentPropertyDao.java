package com.imatia.qsallcomponents.model.dao.dms;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.dms.dao.IDMSDocumentPropertyDao;

@Repository(value = "DMSDocumentPropertyDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/dms/DMSDocumentPropertyDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class DMSDocumentPropertyDao extends OntimizeJdbcDaoSupport implements IDMSDocumentPropertyDao {

    public static final String ATTR_ID_DMS_DOC_PROPERTY = "ID_DMS_DOC_PROPERTY";
    public static final String ATTR_DOC_PROPERTY_KEY = "DOC_PROPERTY_KEY";
    public static final String ATTR_DOC_PROPERTY_VALUE = "DOC_PROPERTY_VALUE";
    public static final String ATTR_ID_DMS_DOC = "ID_DMS_DOC";
}
