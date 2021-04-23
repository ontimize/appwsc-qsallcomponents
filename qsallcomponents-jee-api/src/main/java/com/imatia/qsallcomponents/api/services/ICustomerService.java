package com.imatia.qsallcomponents.api.services;

import java.util.List;
import java.util.Map;

import com.ontimize.db.AdvancedEntityResult;
import com.ontimize.dto.EntityResult;
import com.ontimize.jee.common.exceptions.DmsException;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;

public interface ICustomerService {

	/**
	 * Users query.
	 *
	 * @param keysValues
	 *            the keys values
	 * @param attributes
	 *            the attributes
	 * @return the entity result
	 * @throws OntimizeJEERuntimeException
	 *             the ontimize jee exception
	 */

	// ---- CUSTOMER ----

	public EntityResult customerQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

	// Pagination query
	public AdvancedEntityResult customerPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
			throws OntimizeJEERuntimeException;

	public EntityResult customerInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException, DmsException;

	public EntityResult customerUpdate(Map<?, ?> attributes, Map<?, ?> KeyValues) throws OntimizeJEERuntimeException;

	public EntityResult customerDelete(Map<?, ?> keyValues) throws OntimizeJEERuntimeException;

	// ---- CUSTOMER TYPE ----

	public EntityResult customerTypeQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeUpdate(Map<?, ?> attributes, Map<?, ?> KeyValues) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeDelete(Map<?, ?> keyValues) throws OntimizeJEERuntimeException;

	public EntityResult customerTypeAggregateQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException;

	// ---- CUSTOMER ACCOUNT ----

	public EntityResult customerAccountQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerAccountInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException;

	public EntityResult customerAccountUpdate(Map<String, Object> attributes, Map<String, Object> KeyValues) throws OntimizeJEERuntimeException;

	public EntityResult customerAccountDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException;

}
