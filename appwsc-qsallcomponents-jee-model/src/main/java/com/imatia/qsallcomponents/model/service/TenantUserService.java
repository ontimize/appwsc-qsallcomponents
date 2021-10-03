package com.imatia.qsallcomponents.model.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.imatia.qsallcomponents.api.services.ITenantUserService;
import com.imatia.qsallcomponents.model.dao.TenantUserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

@Lazy
@RequestScope
@Service("TenantUserService")
public class TenantUserService implements ITenantUserService {

    @Autowired
    private TenantUserDao tenantUserDao;

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    public EntityResult tenantUserQuery(final Map<?, ?> keyMap, final List<?> attrList) {
	return this.daoHelper.query(this.tenantUserDao, keyMap, attrList);
    }

    public EntityResult tenantUserInsert(final Map<?, ?> attrMap) {
	return this.daoHelper.insert(this.tenantUserDao, attrMap);
    }

    public EntityResult tenantUserUpdate(final Map<?, ?> attrMap, final Map<?, ?> keyMap) {
	return this.daoHelper.update(this.tenantUserDao, attrMap, keyMap);
    }

    public EntityResult tenantUserDelete(final Map<?, ?> keyMap) {
	final Map<Object, Object> attrMap = new HashMap<>();
	attrMap.put("user_tenant_down_date", new Timestamp(Calendar.getInstance().getTimeInMillis()));
	return this.daoHelper.update(this.tenantUserDao, attrMap, keyMap);
    }

}
