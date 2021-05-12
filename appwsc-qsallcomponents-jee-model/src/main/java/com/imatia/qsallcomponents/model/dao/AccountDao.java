package com.imatia.qsallcomponents.model.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;

@Lazy
@Repository(value = "AccountDao")
@ConfigurationFile(configurationFile = "base-dao/AccountDao.xml", configurationFilePlaceholder = "base-dao/placeholders.properties")
public class AccountDao extends OntimizeJdbcDaoSupport {

	public static final String	ACCOUNT_BALANCE_QUERY_KEY		= "VACCOUNTBALANCE";
	public static final String	ACCOUNT_CONCEPTS_QUERY_KEY		= "VACCOUNTCONCEPTS";
	public static final String	ACCOUNT_MOVEMENTTYPES_QUERY_KEY	= "VACCOUNTMOVEMENTTYPES";

	public static final String	ATTR_ID							= "ACCOUNTID";
	public static final String	ATTR_ENTITYID					= "ENTITYID";
	public static final String	ATTR_OFFICEID					= "OFFICEID";
	public static final String	ATTR_CDID						= "CDID";
	public static final String	ATTR_ANID						= "ANID";
	public static final String	ATTR_STARTDATE					= "STARTDATE";
	public static final String	ATTR_ENDDATE					= "ENDDATE";
	public static final String	ATTR_INTERESRATE				= "INTERESRATE";
	public static final String	ATTR_ACCOUNTTYP					= "ACCOUNTTYP";

	public AccountDao() {
		super();
	}

	public int calculateCDID(String first, int innerAccount) {
		int firstDC = this.calculateDCUniqueDigit(first);
		int secondDC = this.calculateDCUniqueDigit(String.valueOf(innerAccount));

		StringBuilder builder = new StringBuilder();
		builder.append(firstDC);
		builder.append(secondDC);

		return Integer.valueOf(builder.toString());
	}

	public int calculateDCUniqueDigit(String numberString) {

		int[] v = new int[] { 1, 2, 4, 8, 5, 10, 9, 7, 3, 6 };
		String[] nums = numberString.split("");
		int[] n = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			n[i] = Integer.parseInt(nums[i]);
		}

		int sum = 0;

		for (int i = 0; i < numberString.length(); i++) {
			sum = (v[i] * n[i]) + sum;
		}

		int toRetMod = sum % 11;

		if ((toRetMod == 0) || (toRetMod == 1)) {
			return toRetMod;
		}

		return 11 - toRetMod;
	}

	public int createAccountNumber(int accountID) {
		return 1000000000 + accountID;
	}

}
