package com.imatia.qsallcomponents.tenant;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantConfiguration {

    @Bean
    ITenantManager tenantManager(){
        return new TenantManager();
    }

    @Bean
    public DataSource tenantDataSource(DataSource mainDataSource, ITenantManager tenantManager){
        TenantRoutingDataSource tenantRoutingDataSource = new TenantRoutingDataSource();
        tenantRoutingDataSource.setTargetDataSources(tenantManager.getDataSourceHashMap());
        tenantRoutingDataSource.setDefaultTargetDataSource(mainDataSource);

        tenantManager.setTenantRoutingDataSource(tenantRoutingDataSource);
        return tenantRoutingDataSource;
    }
}
