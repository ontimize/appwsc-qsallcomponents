package com.imatia.qsallcomponents.tenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import com.imatia.qsallcomponents.tenant.store.ITenantStore;
import com.imatia.qsallcomponents.tenant.store.TenantConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantManager implements ITenantManager{

    Logger logger = LoggerFactory.getLogger(TenantManager.class);

    private TenantRoutingDataSource tenantRoutingDataSource;

    private Map<Object, Object> dataSourceMap;

    @Autowired
    private ITenantStore tenantStore;

    @Override
    public Map<Object, Object> getDataSourceHashMap() {
        if (dataSourceMap == null) {
            dataSourceMap = new HashMap();

            List<TenantConfiguration> tenantList = this.tenantStore.getAll();

            for (TenantConfiguration configuration : tenantList) {
                try {
//                HikariDataSource dataSource = new HikariDataSource();
//                dataSource.setDriverClassName(configuration.getDriverClass());
//                dataSource.setJdbcUrl(configuration.getJdbcUrl());
//                dataSource.setUsername(configuration.getUsername());
//                dataSource.setPassword(configuration.getPassword());

                    DataSource dataSource = DataSourceBuilder.create()
                        .driverClassName(configuration.getDriverClass())
                        .url(configuration.getJdbcUrl())
                        .username(configuration.getUsername())
                        .password(configuration.getPassword())
                        .build();

                    // Check that new connection is 'live'. If not - throw exception
                    try(Connection c = dataSource.getConnection()) {
                        dataSourceMap.put(configuration.getTenantId(), dataSource);
                    }catch (Exception ex){
                        logger.warn("Error creating connection: {} ", configuration.getTenantId(), ex);
                    }
                } catch (Exception ex) {
                    logger.warn("Error creating datasource", ex);
                }
            }
        }
        return dataSourceMap;
    }

    @Override
    public void setTenantRoutingDataSource(TenantRoutingDataSource tenantRoutingDataSource) {
        this.tenantRoutingDataSource = tenantRoutingDataSource;
    }

    public void removeTenant(String tenantId){
        if (this.dataSourceMap.containsKey(tenantId)){
            this.tenantStore.removeTenant(tenantId);
            this.dataSourceMap.remove(tenantId);
            this.tenantRoutingDataSource.afterPropertiesSet();
        }
    }

    public void addTenant(String tenantId, String driverClassName, String jdbcUrl, String username, String password) throws SQLException {
        if (!dataSourceMap.containsKey(tenantId)){
            DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();

            TenantConfiguration tenantConfiguration = new TenantConfiguration();
            tenantConfiguration.setTenantId(tenantId);
            tenantConfiguration.setDriverClass(driverClassName);
            tenantConfiguration.setJdbcUrl(jdbcUrl);
            tenantConfiguration.setUsername(username);
            tenantConfiguration.setPassword(password);

            this.tenantStore.addTenant(tenantConfiguration);

            // Check that new connection is 'live'. If not - throw exception
            try(Connection c = dataSource.getConnection()) {
                dataSourceMap.put(tenantId, dataSource);
                tenantRoutingDataSource.afterPropertiesSet();
            }
        }




    }
}
