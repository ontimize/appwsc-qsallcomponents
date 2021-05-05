package com.imatia.qsallcomponents.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imatia.qsallcomponents.api.services.IBranchService;
import com.imatia.qsallcomponents.model.dao.AccountDao;
import com.imatia.qsallcomponents.model.dao.AccountTypeDao;
import com.imatia.qsallcomponents.model.dao.BranchDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

@Service("BranchService")
public class BranchService implements IBranchService {

	private static final Logger			logger	= LoggerFactory.getLogger(BranchService.class);

	@Autowired
	private BranchDao					branchDao;

	@Autowired
	private AccountDao					accountDao;

	@Autowired
	private AccountTypeDao				accountTypeDao;

	@Autowired
	private DefaultOntimizeDaoHelper	daoHelper;

//	@Autowired
//	private IMailService				mailService;

	@Override
	public EntityResult branchQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.branchDao, keysValues, attributes);
	}

	@Override
	public EntityResult branchInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {

		EntityResult toRet = this.daoHelper.insert(this.branchDao, attributes);
		if ((toRet.getCode() != EntityResult.OPERATION_WRONG) && ((String) attributes.get(BranchDao.ATTR_ID)).equalsIgnoreCase("9999")) {

			List<String> receiverList = new ArrayList<String>();
			receiverList.add("pablo.martinez@imatia.com");

			StringBuilder builder = new StringBuilder();
			builder.append("Created new branch office:\n");

			builder.append("Office: ");
			builder.append(((String) attributes.get(BranchDao.ATTR_ID)));
			builder.append("\n");

			builder.append("Branch name: ");
			builder.append(((String) attributes.get(BranchDao.ATTR_NAME)));
			builder.append("\n");

			builder.append("Address: ");
			builder.append(((String) attributes.get(BranchDao.ATTR_ADDRESS)));
			builder.append("\n");

			builder.append("Phone: ");
			builder.append(((String) attributes.get(BranchDao.ATTR_PHONE)));
			builder.append("\n");

			builder.append("Details: ");
			builder.append(((String) attributes.get(BranchDao.ATTR_DETAILS)));
			builder.append("\n");

//			try {
//				this.mailService.sendMailWithoutAttach("platino.apps@gmail.com", receiverList, "New branch", builder.toString());
//			} catch (OntimizeJEEException e) {
//				BranchService.logger.error("Unable to send mail");
//			}

		}

		return toRet;
	}

	@Override
	public EntityResult branchUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.branchDao, attributes, keyValues);
	}

	@Override
	public EntityResult branchDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.branchDao, keyValues);
	}

	// ---- ACCOUNTS ----

	@Override
	public EntityResult accountQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.accountDao, keysValues, attributes, AccountDao.ACCOUNT_BALANCE_QUERY_KEY);
	}

	@Override
	public AdvancedEntityResult accountPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
			throws OntimizeJEERuntimeException {
		return this.daoHelper.paginationQuery(this.accountDao, keysValues, attributes, recordNumber, startIndex, orderBy, AccountDao.ACCOUNT_BALANCE_QUERY_KEY);
	}

	// ---- ACCOUNTS CONCEPTS ----

	@Override
	public EntityResult accountConceptsQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.accountDao, keysValues, attributes, AccountDao.ACCOUNT_CONCEPTS_QUERY_KEY);
	}

	// ---- ACCOUNTS MOVEMENTTYPES ----

	@Override
	public EntityResult accountMovementTypesQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.accountDao, keysValues, attributes, AccountDao.ACCOUNT_MOVEMENTTYPES_QUERY_KEY);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult accountInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {

		attributes.put(AccountDao.ATTR_ENTITYID, 2095);
		attributes.remove(AccountDao.ATTR_ANID);
		attributes.remove(AccountDao.ATTR_CDID);
		EntityResult toRet = this.daoHelper.insert(this.accountDao, attributes);

		if (toRet.getCode() == EntityResult.OPERATION_WRONG) {
			throw new OntimizeJEERuntimeException(toRet.getMessage());
		}

		StringBuilder builderfDC = new StringBuilder();
		builderfDC.append("00");
		builderfDC.append(attributes.get(AccountDao.ATTR_ENTITYID));
		builderfDC.append(attributes.get(AccountDao.ATTR_OFFICEID));

		Object accountKey = toRet.get(AccountDao.ATTR_ID);
		int accountNumber = this.accountDao.createAccountNumber((int) accountKey);
		int accountDC = this.accountDao.calculateCDID(builderfDC.toString(), accountNumber);

		Map<String, Object> mapAccountData = new HashMap<String, Object>();
		mapAccountData.put(AccountDao.ATTR_CDID, accountDC);
		mapAccountData.put(AccountDao.ATTR_ANID, accountNumber);

		Map<String, Object> mapAccountKey = new HashMap<String, Object>();
		mapAccountKey.put(AccountDao.ATTR_ID, accountKey);

		EntityResult accoutUpdate = this.daoHelper.update(this.accountDao, mapAccountData, mapAccountKey);

		if (accoutUpdate.getCode() == EntityResult.OPERATION_WRONG) {
			throw new OntimizeJEERuntimeException(accoutUpdate.getMessage());
		}

		return toRet;
	}

	@Override
	public EntityResult accountUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		attributes.remove(AccountDao.ATTR_ENTITYID);
		attributes.remove(AccountDao.ATTR_OFFICEID);
		attributes.remove(AccountDao.ATTR_CDID);
		attributes.remove(AccountDao.ATTR_ANID);
		return this.daoHelper.update(this.accountDao, attributes, keyValues);
	}

	@Override
	public EntityResult accountDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.accountDao, keyValues);
	}

	@Override
	public EntityResult accountTypeAggregateQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.accountTypeDao, keysValues, attributes, AccountTypeDao.AGGREGATE_QUERY_KEY);
	}

}
