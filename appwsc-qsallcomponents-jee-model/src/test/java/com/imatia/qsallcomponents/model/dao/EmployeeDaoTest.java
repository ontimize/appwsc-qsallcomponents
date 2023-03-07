package com.imatia.qsallcomponents.model.dao;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {


    @InjectMocks
    EmployeeDao employeeDao;

    @Nested
    class PaginationQuery {


        @Test
        void when_paginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_and_queryId_expect_AdvancedEntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put(EmployeeDao.ATTR_ID, 1);

            employeeDao.checkKeysValues(keysValues);

            assertTrue(employeeDao.checkKeysValues(keysValues).containsKey(EmployeeDao.ATTR_ID));

        }
    }

    @Nested
    class CheckKeysValues {


        @Test
        void when_checkKeysValues_receive_keysValues_expected_keysValues() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("keysValues", 1);
            employeeDao.checkKeysValues(keysValues);

            assertFalse(keysValues.containsKey(EmployeeDao.ATTR_ID));
        }

        @Test
        void when_checkKeysValues_receive_keysValues_expected_Map_String_Object() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put(EmployeeDao.ATTR_ID, 6378);
            employeeDao.checkKeysValues(keysValues);

            assertTrue(keysValues.containsKey(EmployeeDao.ATTR_ID));
        }

        @Test
        void when_checkKeysValues_receive_keysValues_Map_String_int_expected_convert_String_to_int() {
            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put(EmployeeDao.ATTR_ID, "6378");
            employeeDao.checkKeysValues(keysValues);

            assertTrue(keysValues.containsKey(EmployeeDao.ATTR_ID));
        }
    }
}

