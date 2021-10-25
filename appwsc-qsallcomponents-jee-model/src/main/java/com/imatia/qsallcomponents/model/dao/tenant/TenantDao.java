package com.imatia.qsallcomponents.model.dao.tenant;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.imatia.qsallcomponents.tenant.store.TenantStoreDao;
import com.ontimize.jee.server.dao.common.ConfigurationFile;

@Lazy
@Repository(value = "TenantDao")
@ConfigurationFile(configurationFile = "base-dao/tenant/TenantDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class TenantDao extends TenantStoreDao {

}
