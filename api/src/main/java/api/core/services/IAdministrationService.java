package api.core.services;

import java.util.List;
import java.util.Map;

import com.ontimize.db.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

import api.core.constants.entities.Test;

public interface IAdministrationService {

	String getServerPermissionsSql();

	Test test(String who);
	
	public EntityResult userRoleQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;
	
}
