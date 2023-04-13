package com.imatia.qsallcomponents.ws.rest;

import com.ontimize.jee.common.dto.EntityResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DummyControllerTest {


    @InjectMocks
    DummyController dummyController;


    @Nested
    class DummyTest{
        @Test
        void when_dummyTest_expected_ResponseEntity(){
            ResponseEntity<EntityResult> entityResultResponseEntity = dummyController.dummyTest();
            Assertions.assertEquals(HttpStatus.OK, entityResultResponseEntity.getStatusCode());
        }
    }

    @Nested
    class DummyPermission{
        @Test
        void when_dummyPermission_expected_RespopnseEntity(){
            ResponseEntity<EntityResult> entityResultResponseEntity = dummyController.dummyPermission();
            assertEquals(HttpStatus.OK, entityResultResponseEntity.getStatusCode());
        }

    }
}