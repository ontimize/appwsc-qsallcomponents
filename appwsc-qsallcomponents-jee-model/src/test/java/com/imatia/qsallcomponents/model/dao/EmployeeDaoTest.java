package com.imatia.qsallcomponents.model.dao;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.db.handler.DefaultSQLStatementHandler;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.dao.jdbc.OntimizeTableMetaDataContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {


    @InjectMocks
    EmployeeDao employeeDao;

    @Mock
    OntimizeJdbcDaoSupport ontimizeJdbcDaoSupport;

    @Mock
    AdvancedEntityResult advancedEntityResult;

    @Nested
    class PaginationQuery {

        @Disabled
        @Test
        void when_receive_keysValues_and_attributes_and_sort_and_queryId_and_queryAdapter_expect_paginationQuery() {

            //EmployeeDao employeeDao = Mockito.spy(new EmployeeDao());

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put(EmployeeDao.ATTR_ID, 6378);
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
            ArgumentCaptor<String> qId = ArgumentCaptor.forClass(String.class);

            Mockito.when(employeeDao.paginationQuery(keysValues, attributes,recordNumber,startIndex,orderBy,queryId)).thenReturn(advancedEntityResult);


            ReflectionTestUtils.setField(employeeDao, "compiled", true);
            SQLStatementBuilder.SQLStatement stSQL = Mockito.mock(SQLStatementBuilder.SQLStatement.class);
            ontimizeJdbcDaoSupport.setStatementHandler(new DefaultSQLStatementHandler());


            //employeeDao.paginationQuery(keysValues, attributes,recordNumber,startIndex,orderBy,queryId);
            Mockito.doReturn(advancedEntityResult).when(employeeDao.paginationQuery(ksValues.capture(), attrs.capture(), rNumber.capture(), sIndex.capture(), oBy.capture(), qId.capture()));
            //Mockito.verify(ontimizeJdbcDaoSupport).paginationQuery(ksValues.capture(), attrs.capture(), rNumber.capture(),sIndex.capture(),oBy.capture(), qId.capture());

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
                    },
                    () -> {
                        assertEquals(queryId, qId.getValue());
                    }
            );

        }
    }

}