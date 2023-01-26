package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.EmployeeDao;
import com.imatia.qsallcomponents.model.dao.EmployeeTypeDao;
import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
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
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    EmployeeDao employeeDao;


    @Nested
    class Employees {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

        @Test
        void when_employeeQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            keysValues.put("EMPLOYEEPHOTO", 1);

            EntityResult toRet = Mockito.mock(EntityResultMapImpl.class);

            Mockito.doReturn(toRet).when(daoHelper).query(Mockito.any(), ksValues.capture(), attrs.capture());
            employeeService.employeeQuery(keysValues, attributes);
            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );


        }

        @Test
        void when_employeePaginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_expected_AdvancedEntityResult() {
            keysValues.put("EMPLOYEEPHOTO", 1);
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();

            ArgumentCaptor<Integer> rNumber = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> sIndex = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<String>> oBy = ArgumentCaptor.forClass(List.class);

            AdvancedEntityResult advancedEResult = Mockito.mock(AdvancedEntityResultMapImpl.class);

            Mockito.doReturn(advancedEResult).when(daoHelper).paginationQuery(Mockito.any(), ksValues.capture(), attrs.capture(), rNumber.capture(), sIndex.capture(), oBy.capture(), Mockito.any());
            employeeService.employeePaginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy);

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
        void when_employeeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, 1);

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeInsert(attributes);
            Mockito.verify(daoHelper).insert(Mockito.any(), attrs.capture());

            assertEquals(attributes, attrs.getValue());

        }

        @Test
        void when_employeeUpdate_receive_attributes_and_keyValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put(EmployeeDao.ATTR_EMPLOYEEPHOTO, 1);
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("keyvalues1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);
            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeUpdate(attributes, keyValues);
            Mockito.verify(daoHelper).update(Mockito.any(), attrs.capture(), ksValues.capture());

            assertAll(() -> {
                        assertEquals(attributes, attrs.getValue());
                    },
                    () -> {
                        assertEquals(keyValues, ksValues.getValue());
                    }
            );

        }

        @Test
        void when_employeeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            employeeService.employeeDelete(keyValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keyValues, ksValues.getValue());
        }

    }

    @Nested
    class EmployeesType {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);


        @Test
        void when_employeeTypeQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            attributes.add("employeeTypeQuery");
            keysValues.put("keysvalues1", "value1");

            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);
            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeTypeQuery(keysValues, attributes);
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
        void when_employeeTypeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeTypeInsert(attributes);
            Mockito.verify(daoHelper).insert(Mockito.any(), attrs.capture());

            assertEquals(attributes, attrs.getValue());

        }

        @Test
        void when_employeeTypeUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            keysValues.put("keysvalues1", "value1");
            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeTypeUpdate(attributes, keysValues);
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
        void when_employeeTypeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeTypeDelete(keyValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keyValues, ksValues.getValue());

        }

        @Test
        void when_employeeTypeAggregateQuery_receive_keysValues_and_attributes_expected_EntityResult() {
            attributes.add(EmployeeTypeDao.AGGREGATE_QUERY_KEY);
            keysValues.put("keysvalues1", "value1");

            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);
            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);

            employeeService.employeeTypeAggregateQuery(keysValues, attributes);
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
}


