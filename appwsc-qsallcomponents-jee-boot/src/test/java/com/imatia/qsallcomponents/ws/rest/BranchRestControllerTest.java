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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        void when_plan_receive_names_and_files_and_data_expected_ResponseEntity_without_data_without_files() throws IOException {
            List<String> names = new ArrayList<>();
            names.add("names");

            List<MultipartFile> files = new ArrayList<>();

            String data =null;

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("string1", "object1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);

            ResponseEntity<EntityResult> data1 = branchRestController.plan(names, files, data);
            assertEquals(1, entityResult.OPERATION_WRONG);
            assertEquals("<200 OK OK,EntityResult:  ERROR CODE RETURN: ERROR_NO_FILES_INSERTED : {},[]>", data1.toString());

        }

        @Test
        void when_plan_receive_names_and_files_and_data_expected_ResponseEntity_with_data_without_files() throws IOException {
            List<String> names = new ArrayList<>();
            names.add("names");

            List<MultipartFile> files = new ArrayList<>();

            String data ="{\"string1\":\"object1\"}";

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("string1", "object1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);

            ResponseEntity<EntityResult> data1 = branchRestController.plan(names, files, data);
            assertNotNull(data1);
            assertEquals(1, entityResult.OPERATION_WRONG);
            assertEquals("<200 OK OK,EntityResult:  ERROR CODE RETURN: ERROR_NO_FILES_INSERTED : {},[]>", data1.toString());

        }

        @Test
        void when_plan_receive_names_and_files_and_data_expected_ResponseEntity_with_data_with_files() throws IOException {
            List<String> names = new ArrayList<>();
            names.add("names");

            MultipartFile file = Mockito.mock(MultipartFile.class);
            List<MultipartFile> files = new ArrayList<>();
            files.add(file);

            String data ="{\"string1\":\"object1\"}";

            Map<String, Object> keysValues = new HashMap<>();
            keysValues.put("string1", "object1");

            EntityResult entityResult = new EntityResultMapImpl(EntityResult.OPERATION_SUCCESSFUL, EntityResult.DATA_RESULT);
            HashMap record = new HashMap<>();
            record.put("attributes1", "value1");
            record.put("field1", "value1");
            entityResult.addRecord(record);

            ResponseEntity<EntityResult> data1 = branchRestController.plan(names, files, data);
            assertNotNull(data1);
            assertEquals(1, entityResult.OPERATION_WRONG);
            assertEquals("<200 OK OK,[]>", data1.toString());

        }
    }


}