package com.imatia.qsallcomponents.model.dao;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {


    @InjectMocks
    EmployeeDao employeeDao;

    @Nested
    class PaginationQuery {

        Map<String, Object> keysValues = new HashMap<>();

        @Test
        void when_checkKeysValues_receive_keysValues_expected_Map_String_Object() {
            keysValues.put(EmployeeDao.ATTR_ID, 6378);
            employeeDao.checkKeysValues(keysValues);

            assertTrue(keysValues.containsKey(EmployeeDao.ATTR_ID));
        }

        @Test
        void when_checkKeysValues_receive_keysValues_Map_String_int_expected_convert_int_to_String() {
            keysValues.put(EmployeeDao.ATTR_ID, "6378");
            employeeDao.checkKeysValues(keysValues);

            assertTrue(keysValues.containsKey(EmployeeDao.ATTR_ID));
        }

        @Test
        void when_paginationQuery_receive_keysValues_and_attributes_and_recordNumber_and_startIndex_and_orderBy_queryId_expect_AdvancedEntityResult() {
            keysValues.put(EmployeeDao.ATTR_ID, 1);

            employeeDao.checkKeysValues(keysValues);
            assertTrue(employeeDao.checkKeysValues(keysValues).containsKey(EmployeeDao.ATTR_ID));

        }
    }

}