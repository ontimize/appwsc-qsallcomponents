package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.CustomerAccountDao;
import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.imatia.qsallcomponents.model.dao.CustomerTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.DmsException;
import com.ontimize.jee.common.naming.DMSNaming;
import com.ontimize.jee.common.services.dms.DocumentIdentifier;
import com.ontimize.jee.common.util.remote.BytesBlock;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.services.dms.DMSCreationHelper;
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
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    CustomerAccountDao customerAccountDao;

    @Mock
    CustomerDao customerDao;

    @Mock
    CustomerTypeDao customerTypeDao;

    @Mock
    DMSCreationHelper dmsHelper;

    @Nested
    class Customer {


        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult_with_PHOTO_with_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, bytesBlock);
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(customerDao, keysValues, attributes);
            EntityResult entityResult = customerService.customerQuery(keysValues, attributes);
            assertNotNull(entityResult);
            assertEquals(toRet, entityResult);
        }

        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult_with_PHOTO_without_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(customerDao, keysValues, attributes);
            EntityResult entityResult = customerService.customerQuery(keysValues, attributes);
            assertNotNull(entityResult);
            assertEquals(toRet, entityResult);
        }

        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult_without_PHOTO() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("CUSTOMERPHOTO", "value1");
            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();

            record.put("attributes1", "ATR1");
            record.put("CUSTOMERPHOTO", "value1");
            toRet.addRecord(record);

            Mockito.doReturn(toRet).when(daoHelper).query(customerDao, keysValues, attributes);
            EntityResult entityResult = customerService.customerQuery(keysValues, attributes);
            assertNotNull(entityResult);
            assertEquals(toRet, entityResult);
        }


        @Test
        void when_customerPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_with_PHOTO_with_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, bytesBlock);
            advancedEResult.addRecord(record);
            advancedEResult.containsKey(CustomerDao.ATTR_PHOTO);
            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(customerDao, keysValues, attributes, recordNumber, startIndex, orderBy);
            AdvancedEntityResult advancedEntityResult = customerService.customerPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);

        }

        @Test
        void when_customerPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_with_PHOTO_without_BytesBlock() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            byte[] bytes = new byte[]{};
            BytesBlock bytesBlock = new BytesBlock(bytes);
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            advancedEResult.addRecord(record);
            advancedEResult.containsKey(CustomerDao.ATTR_PHOTO);
            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(customerDao, keysValues, attributes, recordNumber, startIndex, orderBy);
            AdvancedEntityResult advancedEntityResult = customerService.customerPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);

        }

        @Test
        void when_customerPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult_without_PHOTO() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();
            AdvancedEntityResult advancedEResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();

            record.put("attributes1", "ATR1");
            record.put("CUSTOMERPHOTO", "value1");
            advancedEResult.addRecord(record);

            advancedEResult.addRecord(record);
            advancedEResult.containsKey(CustomerDao.ATTR_PHOTO);
            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(customerDao, keysValues, attributes, recordNumber, startIndex, orderBy);
            AdvancedEntityResult advancedEntityResult = customerService.customerPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertEquals(advancedEResult, advancedEntityResult);
            assertNotNull(advancedEntityResult);

        }

        @Test
        void when_customerInsert_receive_attributes_and_expected_EntityResult() throws DmsException {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            toRet.addRecord(record);

            DocumentIdentifier docId = new DocumentIdentifier();
            attributes.put(DMSNaming.DOCUMENT_ID_DMS_DOCUMENT, docId.getDocumentId());
            Mockito.doReturn(docId).when(dmsHelper).createDocument((String) attributes.get("CUSTOMER_WORKSPACE"));

            Mockito.doReturn(toRet).when(daoHelper).insert(customerDao, attributes);
            EntityResult entityResult = customerService.customerInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

        @Test
        void when_customerUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(customerDao, attributes, keysValues);
            EntityResult entityResult = customerService.customerUpdate(attributes, keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

        @Test
        void when_customerDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(customerDao, keysValues);
            EntityResult entityResult = customerService.customerDelete(keysValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

    }

    @Nested
    class CustomerType {


        @Test
        void when_customerTypeQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(customerTypeDao, keysValues, attributes);
            EntityResult entityResult = customerService.customerTypeQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }


        @Test
        void when_customerTypeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(customerTypeDao, attributes);
            EntityResult entityResult = customerService.customerTypeInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }


        @Test
        void when_customerTypeUpdate_receive_keyValues_and_attributes_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("keysvalues1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).update(customerTypeDao, keysValues, attributes);
            EntityResult entityResult = customerService.customerTypeUpdate(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

        @Test
        void when_customerTypeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(customerTypeDao, keyValues);
            EntityResult entityResult = customerService.customerTypeDelete(keyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }


        @Test
        void when_customerTypeAggregateQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(customerTypeDao, keysValues, attributes, CustomerTypeDao.AGGREGATE_QUERY_KEY);
            EntityResult entityResult = customerService.customerTypeAggregateQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

    }

    @Nested
    class CustomerAccount {


        @Test
        void when_customerAccountQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
            keysValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).query(customerAccountDao, keysValues, attributes, CustomerAccountDao.CUSTOMER_ACCOUNT_BALANCE_QUERY_KEY);
            EntityResult entityResult = customerService.customerAccountQuery(keysValues, attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

        @Test
        void when_customerAccountInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).insert(customerAccountDao, attributes);
            EntityResult entityResult = customerService.customerAccountInsert(attributes);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);


        }

        @Test
        void when_customerAccountUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            Hashtable record = new Hashtable();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).update(customerAccountDao, attributes, keyValues);

            EntityResult entityResult1 = customerService.customerAccountUpdate(attributes, keyValues);
            assertNotNull(entityResult);
            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_customerAccountDelete_receive_keyValues_expected_EntityResult() {

            Map<String, Object> KeyValues = new HashMap<>();
            KeyValues.put("field1", "value1");

            EntityResult toRet = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put(CustomerDao.ATTR_PHOTO, "value1");
            toRet.addRecord(record);
            Mockito.doReturn(toRet).when(daoHelper).delete(customerAccountDao, KeyValues);
            EntityResult entityResult = customerService.customerAccountDelete(KeyValues);
            assertEquals(toRet, entityResult);
            assertNotNull(entityResult);

        }

    }

}

