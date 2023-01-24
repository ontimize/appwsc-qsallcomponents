package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.AccountDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @InjectMocks
    BranchService branchService;
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    AccountDao accountDao;

    @Nested
    class Branch {


        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);


        @Test
        void when_branchQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("field1", "value1");
            branchService.branchQuery(keysValues, attributes);
            Mockito.verify(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture());

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );
        }

        @Test
        void when_branchPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put("BRANCHID", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            ArgumentCaptor<Integer> rNumber = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> sIndex = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<String>> oBy = ArgumentCaptor.forClass(List.class);

            branchService.branchPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            Mockito.verify(daoHelper).paginationQuery(Mockito.any(), ksValues.capture(), attrs.capture(), rNumber.capture(), sIndex.capture(), oBy.capture());

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(recordNumber, rNumber.getValue());
                    },
                    () -> {
                        assertEquals(startIndex, sIndex.getValue());
                    },
                    () -> {
                        assertEquals(orderBy, oBy.getValue());
                    }
            );
        }

        @Test
        void when_branchInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            branchService.branchInsert(attributes);
            Mockito.verify(daoHelper).insert(Mockito.any(), attrs.capture());

            assertEquals(attributes, attrs.getValue());

        }

        @Test
        void when_branchUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            branchService.branchUpdate(attributes, keysValues);
            Mockito.verify(daoHelper).update(Mockito.any(), attrs.capture(), ksValues.capture());

            assertAll(() -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(keysValues, ksValues.getValue());
                    }
            );
        }

        @Test
        void when_branchDelete_receive_keyValues_expected_EntityResult() {
            keysValues.put("field1", "value1");

            branchService.branchDelete(keysValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keysValues, ksValues.getValue());
        }

    }

    @Nested
    class Accounts {
        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

        @Test
        void when_accountQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("VACCOUNTBALANCE", "value1");
            branchService.accountQuery(keysValues, attributes);
            Mockito.verify(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture(), Mockito.any(String.class));

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );
        }

        @Test
        void when_accountPaginationQuery_receive_keysValues_and_attributes_and_recordNumber_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put("VACCOUNTBALANCE", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            ArgumentCaptor<Integer> rNumber = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> sIndex = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<String>> oBy = ArgumentCaptor.forClass(List.class);

            branchService.accountPaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);
            Mockito.verify(daoHelper).paginationQuery(Mockito.any(), ksValues.capture(), attrs.capture(), rNumber.capture(), sIndex.capture(), oBy.capture(), Mockito.any());

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(recordNumber, rNumber.getValue());
                    },
                    () -> {
                        assertEquals(startIndex, sIndex.getValue());
                    },
                    () -> {
                        assertEquals(orderBy, oBy.getValue());
                    }
            );
        }

    }

    @Nested
    class AccountsConcepts {

        @Test
        void when_accountConceptsQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("VACCOUNTCONCEPTS", "value1");
            List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

            branchService.accountQuery(keysValues, attributes);
            Mockito.verify(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture(), Mockito.any(String.class));

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );
        }


    }

    @Nested
    class AccountsMovementTypes {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

        @Test
        void when_accountMovementTypesQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("VACCOUNTMOVEMENTTYPES", "value1");
            branchService.accountMovementTypesQuery(keysValues, attributes);
            Mockito.verify(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture(), Mockito.any(String.class));

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );
        }

        @Disabled
        @Test
        void when_accountInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(AccountDao.ATTR_ENTITYID, 2095);
            attributes.remove(AccountDao.ATTR_ANID);
            attributes.remove(AccountDao.ATTR_CDID);

            Map<String, Object> mapAccountData = new HashMap<String, Object>();
            mapAccountData.put(AccountDao.ATTR_CDID, 34);
            mapAccountData.put(AccountDao.ATTR_ANID, 0000000001);

            Map<String, Object> mapAccountKey = new HashMap<String, Object>();
            mapAccountKey.put(AccountDao.ATTR_ID, 1);

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            EntityResult toRet = branchService.accountInsert(attributes);

            Mockito.verify(daoHelper).insert(accountDao, attributes);
            Mockito.verify(daoHelper).update(accountDao, mapAccountData, mapAccountKey);
            /*
            doreturn un er q tenga los valores que yo quiero
            el id de lo q va a enocntrar es diferente
            se necesita el id q ha generado la bd para generar el account/
             */


        }

        @Test
        void when_accountUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            branchService.accountUpdate(attributes, keysValues);
            Mockito.verify(daoHelper).update(Mockito.any(), attrs.capture(), ksValues.capture());

            assertAll(() -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(keysValues, ksValues.getValue());
                    }
            );
        }

        @Test
        void when_accountDelete_receive_keyValues_expected_EntityResult() {
            keysValues.put("field1", "value1");

            branchService.accountDelete(keysValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keysValues, ksValues.getValue());
        }

        @Test
        void when_accountTypeAggregateQuery_receive_attributes_and_keysValues_expected_EntityResult() {
            attributes.add("attribute1");
            keysValues.put("field1", "value1");

            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

            branchService.accountTypeAggregateQuery(keysValues, attributes);
            Mockito.verify(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture(), Mockito.any(String.class));

            assertAll(() -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(keysValues, ksValues.getValue());
                    }
            );
        }


    }

}