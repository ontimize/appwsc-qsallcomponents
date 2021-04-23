package com.imatia.qsallcomponents.api.services;

import java.util.List;
import java.util.Map;

import com.imatia.qsallcomponents.api.constants.entities.Test;
import com.ontimize.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

public interface IAdministrationService {

	String getServerPermissionsSql();

	Test test(String who);
	
	public EntityResult userRoleQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;
	
}
