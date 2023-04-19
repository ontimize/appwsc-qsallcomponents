package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.api.services.IMovementService;
import com.imatia.qsallcomponents.model.dao.MovementDao;
import com.imatia.qsallcomponents.model.dao.MovementTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("MovementService")
public class MovementService implements IMovementService {


    @Autowired
    private MovementDao movementDao;

    @Autowired
    private MovementTypeDao movementTypeDao;

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    // ---- MOVEMENTS ----

    @Override
    public EntityResult movementQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.movementDao, keysValues, attributes, MovementDao.MOVEMENTS_QUERY_KEY);
    }

    @Override
    public AdvancedEntityResult movementPaginationQuery(Map<?, ?> keysValues, List<?> attributes, int recordNumber, int startIndex, List<?> orderBy)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.paginationQuery(this.movementDao, keysValues, attributes, recordNumber, startIndex, orderBy, MovementDao.MOVEMENTS_QUERY_KEY);
    }

    @Override
    public EntityResult movementInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.movementDao, attributes);
    }

    @Override
    public EntityResult movementUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.movementDao, attributes, keyValues);
    }

    @Override
    public EntityResult movementDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.movementDao, keyValues);
    }

    // ---- MOVEMENTTYPE ----

    @Override
    public EntityResult movementTypeQuery(Map<String, Object> keysValues, List<String> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.movementTypeDao, keysValues, attributes);
    }

    @Override
    public EntityResult movementTypeInsert(Map<String, Object> attributes) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.movementTypeDao, attributes);
    }

    @Override
    public EntityResult movementTypeUpdate(Map<String, Object> attributes, Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.movementTypeDao, attributes, keyValues);
    }

    @Override
    public EntityResult movementTypeDelete(Map<String, Object> keyValues) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.movementTypeDao, keyValues);
    }

}
