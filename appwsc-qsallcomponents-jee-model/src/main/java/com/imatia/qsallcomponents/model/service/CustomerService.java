package com.imatia.qsallcomponents.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imatia.qsallcomponents.api.services.ICustomerService;
import com.imatia.qsallcomponents.model.dao.CustomerAccountDao;
import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.CustomerTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.DmsException;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.naming.DMSNaming;
import com.ontimize.jee.common.services.dms.DocumentIdentifier;
import com.ontimize.jee.common.util.remote.BytesBlock;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.services.dms.DMSCreationHelper;

@Service("CustomerService")
//@Secured({ PermissionsProviderSecured.SECURED })
public class CustomerService implements ICustomerService {

	/** The user roles dao. */
	@Autowired
	private CustomerDao					customerDao;

	@Autowired
	private CustomerTypeDao				customerTypeDao;

	@Autowired
	private CustomerAccountDao			customerAccountDao;

	/** The DAO helper. */
	@Autowired
	private DefaultOntimizeDaoHelper	daoHelper;
	
	@Autowired
	private DMSCreationHelper dmsHelper;


	// ---- CUSTOMER ----

	@Override
	public EntityResult customerQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {
		EntityResult toRet = this.daoHelper.query(this.customerDao, keysValues, attributes);
		if (toRet.containsKey(CustomerDao.ATTR_PHOTO)) {
			List<Object> photoCustomer = (List<Object>) toRet.get(CustomerDao.ATTR_PHOTO);
			for (int i = 0; i < photoCustomer.size(); i++) {
				Object o = photoCustomer.get(i);
				if (o instanceof BytesBlock) {
					photoCustomer.set(i, ((BytesBlock) o).getBytes());
				}
			}
		}
		return toRet;
	}

	@Override
	public AdvancedEntityResult customerPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
			throws OntimizeJEERuntimeException {
		AdvancedEntityResult advancedEResult = this.daoHelper.paginationQuery(this.customerDao, keysValues, attributes, recordNumber, startIndex, orderBy);

		if (advancedEResult.containsKey(CustomerDao.ATTR_PHOTO)) {
			List<Object> photoCustomer = (List<Object>) advancedEResult.get(CustomerDao.ATTR_PHOTO);
			for (int i = 0; i < photoCustomer.size(); i++) {
				Object o = photoCustomer.get(i);
				if (o instanceof BytesBlock) {
					photoCustomer.set(i, ((BytesBlock) o).getBytes());
				}
			}
		}
		return advancedEResult;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult customerInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException, DmsException {
		DocumentIdentifier docId = this.dmsHelper.createDocument((String) attributes.get("CUSTOMER_WORKSPACE"));
		((Map<String, Object>)attributes).put(DMSNaming.DOCUMENT_ID_DMS_DOCUMENT, docId.getDocumentId());
		return this.daoHelper.insert(this.customerDao, attributes);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult customerUpdate(Map<?, ?> attributes, Map<?, ?> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.customerDao, attributes, keyValues);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult customerDelete(Map<?, ?> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.customerDao, keyValues);
	}

	// ---- CUSTOMER TYPE ----

	@Override
	public EntityResult customerTypeQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.customerTypeDao, keysValues, attributes);
	}

	@Override
	public EntityResult customerTypeInsert(Map<?, ?> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.insert(this.customerTypeDao, attributes);
	}

	@Override
	public EntityResult customerTypeUpdate(Map<?, ?> keyValues, Map<?, ?> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.customerTypeDao, keyValues, attributes);
	}

	@Override
	public EntityResult customerTypeDelete(Map<?, ?> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.customerTypeDao, keyValues);
	}

	@Override
	public EntityResult customerTypeAggregateQuery(Map<?, ?> keysValues, List<?> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.customerTypeDao, keysValues, attributes, CustomerTypeDao.AGGREGATE_QUERY_KEY);
	}

	// ---- CUSTOMER ACCOUNT ----

	@Override
	public EntityResult customerAccountQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.customerAccountDao, keysValues, attributes, CustomerAccountDao.CUSTOMER_ACCOUNT_BALANCE_QUERY_KEY);
	}

	@Override
	public EntityResult customerAccountInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.insert(this.customerAccountDao, attributes);
	}

	@Override
	public EntityResult customerAccountUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.customerAccountDao, attributes, keyValues);
	}

	@Override
	public EntityResult customerAccountDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.customerAccountDao, keyValues);
	}

}
