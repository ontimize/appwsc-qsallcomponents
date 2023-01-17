package com.imatia.qsallcomponents.model.service;

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
class BranchServiceTest {

    @InjectMocks
    BranchService branchService;
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Nested
    class Branch {

        @Test
        void when_branchQuery_receive_keysValues_and_attributes_and_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>();

            keysValues.put("field1", "value1");
            attributes.add("attribute1");

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);

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
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("BRANCHID", 1);
            List<String> attributes = new ArrayList<>(Arrays.asList("column1"));
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();
            String queryId = "default";

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
            ArgumentCaptor<List<String>> attrs = ArgumentCaptor.forClass(List.class);
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
            Map<String, Object> keysValues = new HashMap<>();
            attributes.put("attribute1", 1);
            keysValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> ksValues = ArgumentCaptor.forClass(Map.class);
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
            Map<String, Object> keyValues = new HashMap<>();
            keyValues.put("field1", "value1");

            ArgumentCaptor<Map<String, Object>> kValues = ArgumentCaptor.forClass(Map.class);

            branchService.branchDelete(keyValues);
            Mockito.verify(daoHelper).delete(Mockito.any(), kValues.capture());

            assertEquals(keyValues, kValues.getValue());


        }

    }
}