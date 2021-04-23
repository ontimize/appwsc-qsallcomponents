package com.imatia.qsallcomponents.api.services;

import java.util.List;
import java.util.Map;

import com.ontimize.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

public interface ISharePrefService {

	public static final String JOIN_QUERY = "defaultJoinTable";

	public EntityResult shareQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

	public EntityResult shareInsert(Map<String, Object> attributesValues) throws OntimizeJEERuntimeException;

	public EntityResult shareUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

	public EntityResult shareDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;
}
