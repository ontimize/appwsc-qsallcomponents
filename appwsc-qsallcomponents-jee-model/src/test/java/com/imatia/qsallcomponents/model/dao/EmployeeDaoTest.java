package com.imatia.qsallcomponents.model.dao;

import com.ontimize.jee.common.db.AdvancedEntityResult;
import com.ontimize.jee.common.db.AdvancedEntityResultMapImpl;
import com.ontimize.jee.common.db.handler.DefaultSQLStatementHandler;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.dao.jdbc.AdvancedEntityResultResultSetExtractor;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import com.ontimize.jee.server.dao.jdbc.OntimizeTableMetaDataContext;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {

    @InjectMocks
    EmployeeDao employeeDao;



    @Nested
    class PaginationQuery {

        @Test
        void when_receive_keysValues_and_attributes_and_sort_and_queryId_and_queryAdapter_expect_paginationQuery() {

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("EMPLOYEEID",1);
            List<String> attributes = new ArrayList<>(Arrays.asList("column1"));
            int recordNumber = 5;
            int startIndex = 3;
            List<String> orderBy = new ArrayList<>();
            String queryId = "default";

            OntimizeJdbcDaoSupport ontimizeJdbcDaoSupport = new OntimizeJdbcDaoSupport();

            //ArgumentCaptor<OntimizeJdbcDaoSupport.SimpleScrollablePreparedStatementCreator> ssPSC = ArgumentCaptor.forClass(OntimizeJdbcDaoSupport.SimpleScrollablePreparedStatementCreator.class);
            ArgumentCaptor<ArgumentPreparedStatementSetter> pss = ArgumentCaptor.forClass(ArgumentPreparedStatementSetter.class);
            String checkSQLQuery = " SELECT column1 FROM  [my-table]   WHERE column3 = ?  AND column2 = ? ";


            JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
            Mockito.doReturn(new AdvancedEntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT)).when(jdbcTemplate).query(Mockito.any(PreparedStatementCreator.class), Mockito.any(), Mockito.any());
            Mockito.doReturn(new EntityResultMapImpl()).when(jdbcTemplate).query(Mockito.any(String.class), (Object[]) Mockito.any(), Mockito.any(ResultSetExtractor.class));
            ontimizeJdbcDaoSupport.setJdbcTemplate(jdbcTemplate);
            ontimizeJdbcDaoSupport.setStatementHandler(new DefaultSQLStatementHandler());
            ReflectionTestUtils.setField(ontimizeJdbcDaoSupport, "compiled", true);
            OntimizeTableMetaDataContext tableMetaDataContext = ontimizeJdbcDaoSupport.getTableMetaDataContext();
            ReflectionTestUtils.setField(tableMetaDataContext, "tableName", "myTable");

            AdvancedEntityResult eR = ontimizeJdbcDaoSupport.paginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy, queryId, null);
            //Mockito.verify(jdbcTemplate).query(ssPSC.capture(), pss.capture(), Mockito.any(AdvancedEntityResultResultSetExtractor.class));

            AdvancedEntityResult advancedEntityResult = employeeDao.paginationQuery(keysValues, attributes, recordNumber, startIndex, orderBy, queryId);


        }
    }

}