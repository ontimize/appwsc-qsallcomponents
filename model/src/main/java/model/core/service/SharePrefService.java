package model.core.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ontimize.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;

import api.core.services.ISharePrefService;
import model.core.dao.setup.OCSharePreferences;
import model.core.dao.setup.OCSharePreferencesTarget;

@Service("SharePrefService")
public class SharePrefService implements ISharePrefService {

	private static final Logger logger = LoggerFactory.getLogger(SharePrefService.class);

	@Autowired private OCSharePreferences sharePreferences;

	@Autowired private OCSharePreferencesTarget shareTargetPreferences;

	@Autowired private DefaultOntimizeDaoHelper daoHelper;


	@Override
	public EntityResult shareQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
		return this.daoHelper.query(this.sharePreferences, keysValues, attributes, ISharePrefService.JOIN_QUERY);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult shareInsert(Map<String, Object> attributesValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.insert(this.sharePreferences, attributesValues);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult shareUpdate(Map<String, Object> attributesValues, Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.update(this.sharePreferences, attributesValues, keysValues);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EntityResult shareDelete(Map<String, Object> keysValues) throws OntimizeJEERuntimeException {
		return this.daoHelper.delete(this.sharePreferences, keysValues);
	}

}
