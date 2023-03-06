package com.imatia.qsallcomponents.model.dao.dms;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.services.dms.dao.IDMSCategoryDao;

@Repository(value = "DMSCategoryDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/dms/DMSCategoryDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class DMSCategoryDao extends OntimizeJdbcDaoSupport implements IDMSCategoryDao {

    public static final String ATTR_ID_DMS_DOC_CATEGORY = "ID_DMS_DOC_CATEGORY";
    public static final String ATTR_ID_DMS_DOC = "ID_DMS_DOC";
    public static final String ATTR_ID_DMS_DOC_CATEGORY_PARENT = "ID_DMS_DOC_CATEGORY_PARENT";
    public static final String ATTR_CATEGORY_NAME = "CATEGORY_NAME";

}
