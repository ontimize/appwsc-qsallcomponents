package com.imatia.qsallcomponents.model.dao.dms;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.dms.dao.IDMSDocumentFileVersionDao;

@Repository(value = "DMSDocumentFileVersionDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/dms/DMSDocumentFileVersionDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class DMSDocumentFileVersionDao extends OntimizeJdbcDaoSupport implements IDMSDocumentFileVersionDao {

    public static final String ATT_ID_DMS_DOC_FILE_VERSION = "ID_DMS_DOC_FILE_VERSION";
    public static final String ATT_FILE_PATH = "FILE_PATH";
    public static final String ATT_VERSION = "VERSION";
    public static final String ATT_FILE_DESCRIPTION = "FILE_DESCRIPTION";
    public static final String ATT_IS_ACTIVE = "IS_ACTIVE";
    public static final String ATT_FILE_ADDED_DATE = "FILE_ADDED_DATE";
    public static final String ATT_FILE_ADDED_USER_ID = "FILE_ADDED_USER_ID";
    public static final String ATT_ID_DMS_DOC_FILE = "ID_DMS_DOC_FILE";
    public static final String ATT_THUMBNAIL = "THUMBNAIL";
    public static final String ATT_FILE_SIZE = "FILE_SIZE";

}
