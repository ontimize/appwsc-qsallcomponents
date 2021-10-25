package com.imatia.qsallcomponents.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String currentTenant = TenantContextHolder.getTenant();

        return currentTenant;
    }


}
