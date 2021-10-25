package com.imatia.qsallcomponents.tenant;

import java.util.Map;

public interface ITenantManager {

    Map<Object, Object> getDataSourceHashMap();

    void setTenantRoutingDataSource(TenantRoutingDataSource tenantRoutingDataSource);
}
