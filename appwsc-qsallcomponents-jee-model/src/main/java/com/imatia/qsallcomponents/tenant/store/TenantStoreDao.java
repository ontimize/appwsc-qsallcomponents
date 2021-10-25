package com.imatia.qsallcomponents.tenant.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

public class TenantStoreDao extends OntimizeJdbcDaoSupport implements ITenantStore{
    private static final String TENANT_ID_KEY = "${ontimize.multitenant.configuration.store-repository.tenant-id}";
    private static final String DRIVER_CLASS_KEY = "${ontimize.multitenant.configuration.store-repository.driver-class}";
    private static final String JDBC_URL_KEY = "${ontimize.multitenant.configuration.store-repository.jdbc-url}";
    private static final String USERNAME_KEY = "${ontimize.multitenant.configuration.store-repository.username}";
    private static final String PASSWORD_KEY = "${ontimize.multitenant.configuration.store-repository.password}";

    @Value(TENANT_ID_KEY)
    private String tenantIdColumnName;

    @Value(DRIVER_CLASS_KEY)
    private String driverClassColumnName;

    @Value(JDBC_URL_KEY)
    private String jdbcUrlColumnName;

    @Value(USERNAME_KEY)
    private String usernameColumnName;

    @Value(PASSWORD_KEY)
    private String passwordColumnName;

    @Override
    public List<TenantConfiguration> getAll() {
        List<TenantConfiguration> allTenants = new ArrayList<>();
        Map<Object, Object> keys = new HashMap<>();
        List<Object> attributes = List.of(tenantIdColumnName, driverClassColumnName, jdbcUrlColumnName, usernameColumnName, passwordColumnName);
        EntityResult result = query(keys, attributes, (List)null, "default");
        for(int i=0;i<result.calculateRecordNumber();i++){
            Map record = result.getRecordValues(i);
            TenantConfiguration tenantConfiguration = new TenantConfiguration();
            tenantConfiguration.setDriverClass((String)record.get(driverClassColumnName));
            tenantConfiguration.setTenantId((String)record.get(tenantIdColumnName));
            tenantConfiguration.setJdbcUrl((String)record.get(jdbcUrlColumnName));
            tenantConfiguration.setUsername(((String)record.get(usernameColumnName)));
            tenantConfiguration.setPassword(((String)record.get(passwordColumnName)));
            allTenants.add(tenantConfiguration);
        }
        return allTenants;
    }

    @Override
    public void addTenant(TenantConfiguration configuration) {
        Map<Object, Object> data = new HashMap<>();
        data.put(driverClassColumnName, configuration.getDriverClass());
        data.put(tenantIdColumnName, configuration.getTenantId());
        data.put(jdbcUrlColumnName, configuration.getJdbcUrl());
        data.put(usernameColumnName, configuration.getUsername());
        data.put(passwordColumnName, configuration.getPassword());

        EntityResult eRInsert = insert(data);
        return;
    }

    @Override
    public void removeTenant(String tenantId) {
        Map<Object, Object> data = new HashMap<>();
        data.put(tenantIdColumnName, tenantId);
        delete(data);
    }
}
