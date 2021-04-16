package model.core.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontimize.db.AdvancedEntityResult;
import com.ontimize.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.util.remote.BytesBlock;

import api.core.services.IEmployeeService;
import model.core.dao.EmployeeDao;
import model.core.dao.EmployeeTypeDao;

@Service("EmployeeService")
public class EmployeeService implements IEmployeeService {

	private static final Logger			logger	= LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeDao					employeeDao;

	@Autowired
	private EmployeeTypeDao				employeeTypeDao;

	@Autowired
	private DefaultOntimizeDaoHelper	daoHelper;

	// ---- EMPLOYEES ----

	@Override
	public EntityResult employeeQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		EntityResult toRet = this.daoHelper.query(this.employeeDao, keysValues, attributes);

		if (toRet.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO)) {
			List<Object> photoEmployee = (List<Object>) toRet.get(EmployeeDao.ATTR_EMPLOYEEPHOTO);
			for (int i = 0; i < photoEmployee.size(); i++) {
				Object o = photoEmployee.get(i);
				if (o instanceof BytesBlock) {
					photoEmployee.set(i, ((BytesBlock) o).getBytes());
				}
			}
		}

		return toRet;
	}

	@Override
	public EntityResult employeeInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {
		if (attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO)) {
			if (attributes.get(EmployeeDao.ATTR_EMPLOYEEPHOTO) instanceof String) {
				String objectPhoto = (String) attributes.remove(EmployeeDao.ATTR_EMPLOYEEPHOTO);
				Map<String, Object> mapAttr = new HashMap<String, Object>();
				mapAttr.putAll(attributes);
				mapAttr.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, Base64.getDecoder().decode(objectPhoto));
				return this.daoHelper.insert(this.employeeDao, mapAttr);
			}
		}
		return this.daoHelper.insert(this.employeeDao, attributes);
	}

	@Override
	public EntityResult employeeUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {

		if (attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO)) {
			if (attributes.get(EmployeeDao.ATTR_EMPLOYEEPHOTO) instanceof String) {
				String objectPhoto = (String) attributes.remove(EmployeeDao.ATTR_EMPLOYEEPHOTO);
				Map<String, Object> mapAttr = new HashMap<String, Object>();
				mapAttr.putAll(attributes);
				mapAttr.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, Base64.getDecoder().decode(objectPhoto));
				return this.daoHelper.update(this.employeeDao, mapAttr, keyValues);
			}
		}

		return this.daoHelper.update(this.employeeDao, attributes, keyValues);
	}

	@Override
	public EntityResult employeeDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.employeeDao, keyValues);
	}

	@Override
	public AdvancedEntityResult employeePaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
			throws OntimizeJEERuntimeException {
		return this.daoHelper.paginationQuery(this.employeeDao, keysValues, attributes, recordNumber, startIndex, orderBy, EmployeeDao.EMPLOYEE_OFFICE_QUERY_KEY);
	}

	// ---- EMPLOYEESTYPE ----

	@Override
	public EntityResult employeeTypeQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.employeeTypeDao, keysValues, attributes);
	}

	@Override
	public EntityResult employeeTypeInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.insert(this.employeeTypeDao, attributes);
	}

	@Override
	public EntityResult employeeTypeUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.employeeTypeDao, attributes, keyValues);
	}

	@Override
	public EntityResult employeeTypeDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.employeeTypeDao, keyValues);
	}

	@Override
	public EntityResult employeeTypeAggregateQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.employeeTypeDao, keysValues, attributes, EmployeeTypeDao.AGGREGATE_QUERY_KEY);
	}

}
