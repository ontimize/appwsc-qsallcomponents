package com.imatia.qsallcomponents.api.services;

import java.util.List;
import java.util.Map;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.DmsException;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

public interface ICustomerService {

	// ---- CUSTOMER ----

	public EntityResult customerQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

	// Pagination query
	public AdvancedEntityResult customerPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber,
			int startIndex, List<?> orderBy) throws OntimizeJEERuntimeException;

	public EntityResult customerInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException, DmsException;

	public EntityResult customerUpdate(Map<?, ?> attributes, Map<?, ?> keysValues) throws OntimizeJEERuntimeException;

	public EntityResult customerDelete(Map<?, ?> keysValues) throws OntimizeJEERuntimeException;

	// ---- CUSTOMER TYPE ----

	public EntityResult customerTypeQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeUpdate(Map<?, ?> attributes, Map<?, ?> keysValues)
			throws OntimizeJEERuntimeException;

	public EntityResult customerTypeDelete(Map<?, ?> keysValues) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeAggregateQuery(Map<?, ?> keysValues, List<?> attributes)
			throws OntimizeJEERuntimeException;

	// ---- CUSTOMER ACCOUNT ----

	public EntityResult customerAccountQuery(Map<String, Object> keysValues, List<String> attributes)
			throws OntimizeJEERuntimeException;

	public EntityResult customerAccountInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerAccountUpdate(Map<String, Object> attributes, Map<String, Object> keysValues)
			throws OntimizeJEERuntimeException;

	public EntityResult customerAccountDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException;

}
