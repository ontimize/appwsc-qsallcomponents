package com.imatia.qsallcomponents.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Repository(value = "EmployeeDao")
@Lazy
@ConfigurationFile(configurationFile = "base-dao/EmployeeDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class EmployeeDao extends OntimizeJdbcDaoSupport {

	public static final String	EMPLOYEE_OFFICE_QUERY_KEY	= "EMPLOYEE_OFFICE";

	public static final String	ATTR_ID						= "EMPLOYEEID";
	public static final String	ATTR_EMPLOYEETYPEID			= "EMPLOYEETYPEID";
	public static final String	ATTR_EMPLOYEENAME			= "EMPLOYEENAME";
	public static final String	ATTR_EMPLOYEESURNAME		= "EMPLOYEESURNAME";
	public static final String	ATTR_EMPLOYEEADDRESS		= "EMPLOYEEADDRESS";
	public static final String	ATTR_EMPLOYEECOMMENTS		= "EMPLOYEECOMMENTS";
	public static final String	ATTR_EMPLOYEESTARTDATE		= "EMPLOYEESTARTDATE";
	public static final String	ATTR_EMPLOYEEPHOTO			= "EMPLOYEEPHOTO";
	public static final String	ATTR_EMPLOYEEEMAIL			= "EMPLOYEEEMAIL";
	public static final String	ATTR_OFFICEID				= "OFFICEID";
	public static final String	ATTR_EMPLOYEEPHONE			= "EMPLOYEEPHONE";
	public static final String	ATTR_LONGITUDE				= "LONGITUDE";
	public static final String	ATTR_LATITUDE				= "LATITUDE";
	public static final String	ATTR_AGENDAXML				= "AGENDAXML";

	public EmployeeDao() {
		super();
	}

	@Override
	public AdvancedEntityResult paginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy, String queryId) {

		checkKeysValues(keysValues);

		return super.paginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy, queryId);
	}

	public Map<?,?> checkKeysValues(Map<?, ?> keysValues) {
		if (keysValues.containsKey(EmployeeDao.ATTR_ID)) {
			Object value = keysValues.get(EmployeeDao.ATTR_ID);
			if (value instanceof String) {
				try {
					((Map<Object, Object>) keysValues).put(EmployeeDao.ATTR_ID, Integer.parseInt(value.toString()));
				} catch (Exception ex) {}
			}
		}
		return keysValues;
	}

}
