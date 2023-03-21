package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.IBranchService;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith(MockitoExtension.class)
class BranchRestControllerTest {

    @InjectMocks
    BranchRestController branchRestController;

    @Mock
    IBranchService branchService;

    @Nested
    class GetService {
        @Test
        void when_getService_expected_IBranchService() {
            IBranchService service = branchRestController.getService();
            Assertions.assertEquals(branchService, service);
        }
    }


    @Nested
    class Plan {

        @Test
        void when_plan_receive_names_and_files_and_data_expected_ResponseEntity() throws IOException {
            List<String> names = new ArrayList<>();
            names.add("names");

            List<MultipartFile> files = new ArrayList<>();

            String data = "data";

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("string1", "object1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);

            ResponseEntity<EntityResult> data1 = branchRestController.plan(names, files, data);
            assertNotNull(data1);
            assertEquals("ERROR_NO_FILES_INSERTED", entityResult.OPERATION_WRONG);

        }
    }


}