package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.CustomerDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import com.ontimize.jee.server.dao.IOntimizeDaoSupport;
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

        EntityResult toRet;

        @Disabled
        @Test
        void when_customerQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            /* public EntityResult customerQuery(Map<?, ?> keysValues, List<?> attributes)

            EntityResult toRet = this.daoHelper.query(this.customerDao, keysValues, attributes);
		if (toRet.containsKey(CustomerDao.ATTR_PHOTO)) {
			List<Object> photoCustomer = (List<Object>) toRet.get(CustomerDao.ATTR_PHOTO);
			for (int i = 0; i < photoCustomer.size(); i++) {
				Object o = photoCustomer.get(i);
				if (o instanceof BytesBlock) {
					photoCustomer.set(i, ((BytesBlock) o).getBytes());
				}
			}
		}
		return toRet;/
             */
            /*
            la tabla customers tiene datos q son los q va a devolver customerdao
            /q el entityresult tenga attr-photo na mas
             */
            keysValues.put(CustomerDao.ATTR_PHOTO, "value1");
            CustomerDao customerDao = Mockito.mock(CustomerDao.class);
            Mockito.doReturn(toRet).when(daoHelper).query(customerDao, keysValues, attributes);
            customerService.customerQuery(keysValues, attributes);

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
    class CustomerType {

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


        @Test
        void when_customerTypeUpdate_receive_keysValues_and_attributes_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            keysValues.put("keysvalues1", "value1");
            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            customerService.customerTypeUpdate(keysValues, attributes);
            Mockito.verify(daoHelper).update(Mockito.any(), ksValues.capture(), attrs.capture());

            assertAll(() -> {
                        assertEquals(keysValues, ksValues.getValue());
                    },
                    () -> {
                        assertEquals(attributes, attrs.getValue());
                    }
            );
        }

        @Test
        void when_customerTypeDelete_receive_keyValues_expected_EntityResult() {
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);

            customerService.customerTypeDelete(keyValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(keyValues, ksValues.getValue());

        }


        @Test
        void when_customerTypeAggregateQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("field1", "value1");
            customerService.customerTypeAggregateQuery(keysValues, attributes);
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
    class CustomerAccounts {

        Map<String, Object> keysValues = new HashMap<>();
        List<String> attributes = new ArrayList<>(Arrays.asList("attribute1"));
        ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);


        @Test
        void when_customerAccountQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {

            keysValues.put("field1", "value1");
            customerService.customerAccountQuery(keysValues, attributes);
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
        void when_customerAccountInsert_receive_keyValues_expected_EntityResult() {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            customerService.customerAccountInsert(attributes);
            Mockito.verify(daoHelper).insert(Mockito.any(), attrs.capture());

            assertEquals(attributes, attrs.getValue());

        }

        @Test
        void when_customerAccountUpdate_receive_attributes_and_keyValues_and_expected_EntityResult() {

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("attributes1", "value1");
            Map<String, Object> KeyValues = new HashMap<>();
            KeyValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> attrs = ArgumentCaptor.forClass(Map.class);

            customerService.customerAccountUpdate(attributes, KeyValues);
            Mockito.verify(daoHelper).update(Mockito.any(), attrs.capture(), attrs.capture());

            assertEquals(attributes, attrs.getValue());
        }

        @Test
        void when_customerAccountDelete_receive_keyValues_expected_EntityResult() {

            Map<String, Object> KeyValues = new HashMap<>();
            KeyValues.put("field1", "value1");

            customerService.customerAccountDelete(KeyValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), ksValues.capture());

            assertEquals(KeyValues, ksValues.getValue());

        }

    }

}

