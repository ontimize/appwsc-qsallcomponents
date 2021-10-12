package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.webclient.remoteconfiguration.IRemoteConfigurationDao;

@Repository(value = "WebClientConfigDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/WebClientConfigDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class WebClientConfigDao extends OntimizeJdbcDaoSupport implements IRemoteConfigurationDao {

}
