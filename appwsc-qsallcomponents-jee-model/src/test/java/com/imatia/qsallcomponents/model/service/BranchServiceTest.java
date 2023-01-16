package com.imatia.qsallcomponents.model.service;

import com.imatia.qsallcomponents.model.dao.BranchDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @InjectMocks
    BranchService branchService;
    @Mock
    DefaultOntimizeDaoHelper daoHelper;

    @Mock
    BranchDao branchDao;


    /*@Nested
    class BranchQuery {*/

        @Test
        void when_receive_keysValues_and_attributes_and_recordNumber_anda_startIndex_and_orderBy_expected_EntityResult() {
            Map<String, Object> keysValues = new HashMap<>();
            List<String> attributes = new ArrayList<>();
            int recordNumber = 0;
            int startIndex = 1;
            List<String> orderBy = new ArrayList<>();

            keysValues.put("field1", "value1");
            attributes.add("attribute1");
            orderBy.add("orderBy");

            EntityResult entityResult = daoHelper.query(branchDao, keysValues, attributes);

            EntityResult actual = branchService.branchQuery(keysValues, attributes);

            String expected = "sqlTemplate";

            assertEquals(expected, actual);

        //}
    }

}