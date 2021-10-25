package com.imatia.qsallcomponents.tenant.store;

import java.util.List;

public interface ITenantStore {

    List<TenantConfiguration> getAll();

    void addTenant(TenantConfiguration configuration);

    void removeTenant(String tenantId);
}
