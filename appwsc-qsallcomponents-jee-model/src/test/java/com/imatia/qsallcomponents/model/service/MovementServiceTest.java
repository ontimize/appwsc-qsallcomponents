package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.MovementDao;
import com.imatia.qsallcomponents.model.dao.MovementTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {

    @InjectMocks
    MovementService movementService;

    @Mock
    MovementDao movementDao;

    @Mock
    MovementTypeDao movementTypeDao;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;


    @Nested
    class Movements {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

        @Test
        void when_movementQuery_receive_keysValues_and_attributes_expected_entityResult() {

            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(MovementDao.MOVEMENTS_QUERY_KEY, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(movementDao, keysValues, attributes, MovementDao.MOVEMENTS_QUERY_KEY);
            EntityResult entityResult = movementService.movementQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);

        }


        @Test
        void when_movementPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put(MovementDao.MOVEMENTS_QUERY_KEY, 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(MovementDao.MOVEMENTS_QUERY_KEY, "value1");
            advancedEResult.addRecord(record);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(movementDao, keysValues, attributes, recordNumber, startIndex, orderBy, MovementDao.MOVEMENTS_QUERY_KEY);
            AdvancedEntityResult advancedEntityResult = movementService.movementPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);


        }

        @Test
        void when_movementInsert_receive_attributes_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("attributes2", "value2");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(movementDao, attributes);
            EntityResult entityResult = movementService.movementInsert(attributes);
            assertEquals(toRet, entityResult);


        }


    }


}

