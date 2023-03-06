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

    public static final String ATTR_ID_DMS_DOC = "ID_DMS_DOC";
    public static final String ATTR_UPDATE_DATE = "UPDATE_DATE";
    public static final String ATTR_UPDATE_BY_ID = "UPDATE_BY_ID";
    public static final String ATTR_DOC_NAME = "DOC_NAME";
    public static final String ATTR_OWNER_ID = "OWNER_ID";
    public static final String ATTR_DOC_DESCRIPTION = "DOC_DESCRIPTION";
    public static final String ATTR_DOC_KEYWORDS = "DOC_KEYWORDS";


}
