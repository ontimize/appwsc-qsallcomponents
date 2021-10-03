package com.imatia.qsallcomponents.api.services;

import java.util.List;
import java.util.Map;

import com.ontimize.jee.common.dto.EntityResult;

public interface ITenantUserService {
    public EntityResult tenantUserQuery(Map<?, ?> keyMap, List<?> attrList);

    public EntityResult tenantUserInsert(Map<?, ?> attrMap);

    public EntityResult tenantUserUpdate(Map<?, ?> attrMap, Map<?, ?> keyMap);

    public EntityResult tenantUserDelete(Map<?, ?> keyMap);
}
