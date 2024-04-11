package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.EmployeeDao;
import com.imatia.qsallcomponents.model.dao.EmployeeTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.util.remote.BytesBlock;
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
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    EmployeeDao employeeDao;

    @Mock
    EmployeeTypeDao employeeTypeDao;


    @Nested
    class Employees {

        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_and_expected_EntityResult_with_EMPLOYEEPHOTO_and_with_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, "value1");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, bytesBlock);
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(employeeDao, keysValues, attributes);
            EntityResult entityResult = employeeService.employeeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }
        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_and_expected_EntityResult_with_EMPLOYEEPHOTO_and_without_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, "EPhoto");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, "EPhoto");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(employeeDao, keysValues, attributes);
            EntityResult entityResult = employeeService.employeeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }

        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_and_expected_EntityResult_without_EMPLOYEEPHOTO_and_without_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("EPHOTO", "EPhoto");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();

            record.put("attributes1", "value1");
            record.put("EPHOTO", "EPhoto");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(employeeDao, keysValues, attributes);
            EntityResult entityResult = employeeService.employeeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_with_EMPLOYEEPHOTO_without_Base64() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("EMPLOYEEPHOTO", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, "value1");
            advancedEResult.addRecord(record);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(employeeDao, keysValues, attributes, recordNumber, startIndex, orderBy, EmployeeDao.EMPLOYEE_OFFICE_QUERY_KEY);
            AdvancedEntityResult advancedEntityResult = employeeService.employeePaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);


        }


        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_with_EMPLOYEEPHOTO_and_with_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("EMPLOYEEPHOTO", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, bytesBlock);
            advancedEResult.addRecord(record);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(employeeDao, keysValues, attributes, recordNumber, startIndex, orderBy, EmployeeDao.EMPLOYEE_OFFICE_QUERY_KEY);
            AdvancedEntityResult advancedEntityResult = employeeService.employeePaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);
        }

        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_without_EMPLOYEEPHOTO_and_with_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("PHOTO", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put("PHOTO", 1);
            advancedEResult.addRecord(record);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(employeeDao, keysValues, attributes, recordNumber, startIndex, orderBy, EmployeeDao.EMPLOYEE_OFFICE_QUERY_KEY);
            AdvancedEntityResult advancedEntityResult = employeeService.employeePaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);
        }

        @Test
        void when_employeeInsert_receive_attributes_expected_EntityResult_without_EMPLOYEEPHOTO_and_without_String() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("PHOTO", 1);

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("PHOTO", "value1");
            toRet.addRecord(record);

            attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO);
            Mockito.doReturn(toRet).when(daoHelper).insert(employeeDao, attributes);
            EntityResult entityResult = employeeService.employeeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }
        @Test
        void when_employeeInsert_receive_attributes_expected_EntityResult_with_EMPLOYEEPHOTO_and_without_String() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("EMPLOYEEPHOTO", 1);

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);

            attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO);
            Mockito.doReturn(toRet).when(daoHelper).insert(employeeDao, attributes);
            EntityResult entityResult = employeeService.employeeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeeInsert_receive_attributes_expected_EntityResult_with_EMPLOYEEPHOTO_and_with_String() {
            String str = "string1";
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, str);
            attributes.put("attributes1", "value1");


            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, Base64.getDecoder().decode(str));
            record.put("attributes1", "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).insert(Mockito.eq(employeeDao), Mockito.any(Map.class));
            EntityResult entityResult = employeeService.employeeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }

        @Test
        void when_employeeUpdate_receive_attributes_and_keyValues_expected_EntityResult_with_PHOTO_without_String() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, 1);
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("keyvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);

            attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO);
            Mockito.doReturn(toRet).when(daoHelper).update(employeeDao, attributes, keyValues);
            EntityResult entityResult = employeeService.employeeUpdate(attributes, keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }

        @Test
        void when_employeeUpdate_receive_attributes_and_keyValues_expected_EntityResult_with_EMPLOYEEPHOTO_with_string() {
            String str = "string1";
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, str);

            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("keyvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, Base64.getDecoder().decode(str));
            toRet.addRecord(record);

            attributes.containsKey(EmployeeDao.ATTR_EMPLOYEEPHOTO);
            Mockito.doReturn(toRet).when(daoHelper).update(Mockito.eq(employeeDao), Mockito.any(Map.class), Mockito.any(Map.class));
            EntityResult entityResult = employeeService.employeeUpdate(attributes, keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }
        @Test
        void when_employeeUpdate_receive_attributes_and_keyValues_expected_EntityResult_without_ATTR_PHOTO() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Foto", 1);
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("keyvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("Foto", "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).update(employeeDao, attributes, keyValues);
            EntityResult entityResult = employeeService.employeeUpdate(attributes, keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }


        @Test
        void when_employeeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(employeeDao, keysValues);
            EntityResult entityResult = employeeService.employeeDelete(keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);
        }

    }

    @Nested
    class EmployeesType {

        @Test
        void when_employeeTypeQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>();
            attributes.add("employeeTypeQuery");
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(employeeTypeDao, keysValues, attributes);
            EntityResult entityResult = employeeService.employeeTypeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeeTypeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(employeeTypeDao, attributes);
            EntityResult entityResult = employeeService.employeeTypeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeeTypeUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(employeeTypeDao, attributes, keysValues);
            EntityResult entityResult = employeeService.employeeTypeUpdate(attributes, keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeeTypeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("field1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(employeeTypeDao, keyValues);
            EntityResult entityResult = employeeService.employeeTypeDelete(keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_employeeTypeAggregateQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            attributes.add(EmployeeTypeDao.AGGREGATE_QUERY_KEY);
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(employeeTypeDao, keysValues, attributes, EmployeeTypeDao.AGGREGATE_QUERY_KEY);
            EntityResult entityResult = employeeService.employeeTypeAggregateQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

    }
}


