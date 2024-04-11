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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        @Test
        void when_movementQuery_receive_keysValues_and_attributes_expected_entityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(MovementDao.MOVEMENTS_QUERY_KEY, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(movementDao, keysValues, attributes, MovementDao.MOVEMENTS_QUERY_KEY);
            EntityResult entityResult = movementService.movementQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }


        @Test
        void when_movementPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
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
            assertNotNull(advancedEntityResult);



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
            assertNotNull(entityResult);



        }

        @Test
        void when_movementUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("keysvalues1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(movementDao, attributes, keysValues);
            EntityResult entityResult = movementService.movementUpdate(attributes, keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_movementDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("field1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(movementDao, keyValues);
            EntityResult entityResult = movementService.movementDelete(keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }


    }


    @Nested
    class MovementType {

        @Test
        void when_movementTypeQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            attributes.add("employeeTypeQuery");
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("keysvalues1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(movementTypeDao, keysValues, attributes);
            EntityResult entityResult = movementService.movementTypeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_movementTypeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(movementTypeDao, attributes);
            EntityResult entityResult = movementService.movementTypeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }


        @Test
        void when_movementTypeUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("keysvalues1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(movementTypeDao, attributes, keysValues);
            EntityResult entityResult = movementService.movementTypeUpdate(attributes, keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_movementTypeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("field1", "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(movementTypeDao, keyValues);
            EntityResult entityResult = movementService.movementTypeDelete(keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }


    }


}

