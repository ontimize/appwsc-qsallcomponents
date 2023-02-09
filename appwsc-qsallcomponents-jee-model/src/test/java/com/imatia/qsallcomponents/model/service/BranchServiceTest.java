package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.AccountDao;
import com.imatia.qsallcomponents.model.dao.AccountTypeDao;
import com.imatia.qsallcomponents.model.dao.BranchDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @InjectMocks
    BranchService branchService;
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    BranchDao branchDao;

    @Mock
    AccountDao accountDao;

    @Mock
    AccountTypeDao accountTypeDao;

    @Nested
    class Branch {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

        @Test
        void when_branchQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).query(branchDao, keysValues, attributes);

            EntityResult entityResult1 = branchService.branchQuery(keysValues, attributes);
            assertNotNull(entityResult1);
            assertEquals(entityResult, entityResult1);

        }

        @Test
        void when_branchPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put("BRANCHID", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEntityResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            advancedEntityResult.addRecord(record);
            Mockito.doReturn(advancedEntityResult).when(daoHelper).paginationQuery(branchDao, keysValues, attributes, recordNumber, startIndex, orderBy);

            AdvancedEntityResult advancedEntityResult1 = branchService.branchPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertNotNull(advancedEntityResult1);
            assertEquals(advancedEntityResult, advancedEntityResult1);


        }

        @Test
        void when_branchInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).insert(branchDao, attributes);
            EntityResult entityResult1 = branchService.branchInsert(attributes);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_branchUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).update(branchDao, attributes, keysValues);
            EntityResult entityResult1 = branchService.branchUpdate(attributes, keysValues);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_branchDelete_receive_keyValues_expected_EntityResult() {
            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).delete(branchDao, keysValues);
            EntityResult entityResult1 = branchService.branchDelete(keysValues);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);

        }

    }

    @Nested
    class Accounts {
        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

        @Test
        void when_accountQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("VACCOUNTBALANCE", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).query(accountDao, keysValues, attributes, AccountDao.ACCOUNT_BALANCE_QUERY_KEY);
            EntityResult entityResult1 = branchService.accountQuery(keysValues, attributes);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_accountPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put("VACCOUNTBALANCE", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            AdvancedEntityResult advancedEntityResult = new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            advancedEntityResult.addRecord(record);
            Mockito.doReturn(advancedEntityResult).when(daoHelper).paginationQuery(accountDao, keysValues, attributes, recordNumber, startIndex, orderBy, AccountDao.ACCOUNT_BALANCE_QUERY_KEY);
            AdvancedEntityResult advancedEntityResult1 = branchService.accountPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            assertNotNull(advancedEntityResult1);

            assertEquals(advancedEntityResult, advancedEntityResult1);
        }

    }

    @Nested
    class AccountsConcepts {

        @Test
        void when_accountConceptsQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("VACCOUNTCONCEPTS", "value1");
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).query(accountDao, keysValues, attributes, AccountDao.ACCOUNT_CONCEPTS_QUERY_KEY);
            EntityResult entityResult1 = branchService.accountConceptsQuery(keysValues, attributes);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }


    }

    @Nested
    class AccountsMovementTypes {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

        @Test
        void when_accountMovementTypesQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("VACCOUNTMOVEMENTTYPES", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).query(accountDao, keysValues, attributes, AccountDao.ACCOUNT_MOVEMENTTYPES_QUERY_KEY);
            EntityResult entityResult1 = branchService.accountMovementTypesQuery(keysValues, attributes);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }


        @Test
        void when_accountInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(AccountDao.ATTR_ENTITYID, 2095);
            attributes.remove(AccountDao.ATTR_ANID);
            attributes.remove(AccountDao.ATTR_CDID);

            EntityResult toRet = new EntityResultMapImpl();
            toRet.put(AccountDao.ATTR_ID, 1);
            Mockito.doReturn(toRet).when(daoHelper).insert(accountDao, attributes);
            Object accountKey = toRet.get(AccountDao.ATTR_ID);

            Mockito.doReturn(1).when(accountDao).createAccountNumber((Integer) accountKey);

            EntityResult accountUpdate = Mockito.mock(EntityResultMapImpl.class);

            Map<String, Object> mapAccountData = new HashMap<String, Object>();
            mapAccountData.put(AccountDao.ATTR_CDID, 0);
            mapAccountData.put(AccountDao.ATTR_ANID, 0000000001);

            Map<String, Object> mapAccountKey = new HashMap<String, Object>();
            mapAccountKey.put(AccountDao.ATTR_ID, 1);

            Mockito.doReturn(accountUpdate).when(daoHelper).update(accountDao, mapAccountData, mapAccountKey);

            EntityResult entityResult1 = branchService.accountInsert(attributes);
            assertEquals(toRet, entityResult1);
            assertNotNull(entityResult1);


        }

        @Test
        void when_accountInsert_receive_attributes_expected_toRet_EntityResult_OPERATION_WRONG() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(AccountDao.ATTR_ENTITYID, 2095);
            attributes.remove(AccountDao.ATTR_ANID);
            attributes.remove(AccountDao.ATTR_CDID);

            EntityResult toRet = new EntityResultMapImpl();
            toRet.put(AccountDao.ATTR_ID, 1);
            Mockito.doReturn(toRet).when(daoHelper).insert(accountDao, attributes);
            toRet.setCode(1);

            assertThrows(OntimizeJEERuntimeException.class, () -> {
                branchService.accountInsert(attributes);
            });


        }

        @Test
        void when_accountInsert_receive_attributes_expected_accountUpdate_EntityResult_OPERATION_WRONG() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(AccountDao.ATTR_ENTITYID, 2095);
            attributes.remove(AccountDao.ATTR_ANID);
            attributes.remove(AccountDao.ATTR_CDID);

            EntityResult toRet = new EntityResultMapImpl();
            toRet.put(AccountDao.ATTR_ID, 1);
            Mockito.doReturn(toRet).when(daoHelper).insert(accountDao, attributes);
            Object accountKey = toRet.get(AccountDao.ATTR_ID);

            Mockito.doReturn(1).when(accountDao).createAccountNumber((Integer) accountKey);

            Map<String, Object> mapAccountData = new HashMap<String, Object>();
            mapAccountData.put(AccountDao.ATTR_CDID, 0);
            mapAccountData.put(AccountDao.ATTR_ANID, 0000000001);

            Map<String, Object> mapAccountKey = new HashMap<String, Object>();
            mapAccountKey.put(AccountDao.ATTR_ID, 1);

            EntityResult accountUpdate = new EntityResultMapImpl();
            accountUpdate.put(AccountDao.ATTR_ID, 1);
            Mockito.doReturn(accountUpdate).when(daoHelper).update(accountDao, mapAccountData, mapAccountKey);
            accountUpdate.setCode(1);

            assertThrows(OntimizeJEERuntimeException.class, () -> {
                branchService.accountInsert(attributes);
            });

        }

        @Test
        void when_accountUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).update(accountDao, attributes, keysValues);
            EntityResult entityResult1 = branchService.accountUpdate(attributes, keysValues);
            assertNotNull(entityResult1);
            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_accountDelete_receive_keyValues_expected_EntityResult() {
            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).delete(accountDao, keysValues);
            EntityResult entityResult1 = branchService.accountDelete(keysValues);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }

        @Test
        void when_accountTypeAggregateQuery_receive_attributes_and_keysValues_expected_EntityResult() {
            attributes.add("attribute1");
            keysValues.put("field1", "value1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);
            Mockito.doReturn(entityResult).when(daoHelper).query(accountTypeDao, keysValues, attributes, AccountTypeDao.AGGREGATE_QUERY_KEY);
            EntityResult entityResult1 = branchService.accountTypeAggregateQuery(keysValues, attributes);
            assertNotNull(entityResult1);

            assertEquals(entityResult, entityResult1);
        }


    }

}
