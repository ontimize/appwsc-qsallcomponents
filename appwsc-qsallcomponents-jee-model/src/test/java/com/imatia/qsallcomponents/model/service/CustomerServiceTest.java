package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.CustomerDao;
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
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Nested
    class Customer {


        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);


        @Disabled
        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");

            customerService.customerQuery(keysValues, attributes);
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
        void when_customerUpdate_receive_attributes_and_keysValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            customerService.customerUpdate(attributes, keysValues);
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
        void when_customerDelete_receive_keyValues_expected_EntityResult() {
            keysValues.put("field1", "value1");

            customerService.customerDelete(keysValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keysValues, ksValues.getValue());
        }

    }


    @Nested
    class CustomerType{

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

        @Test
        void when_customerTypeQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("field1", "value1");
            customerService.customerTypeQuery(keysValues, attributes);
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
        void when_customerTypeInsert_receive_attributes_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            customerService.customerTypeInsert(attributes);
            Mockito.verify(daoHelper).insert(Mockito.any(), attrs.capture());

            assertEquals(attributes, attrs.getValue());

        }


    }



    }

