package com.imatia.qsallcomponents.ws.rest;

import com.ontimize.jee.common.services.dms.IDMSService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileManagerRestControllerTest {

    @InjectMocks
    FileManagerRestController fileManagerRestController;

    @Mock
    IDMSService dmsService;

    @Nested
    class GetService{
        @Test
        void when_getService_expected_IDMService(){
            IDMSService service = fileManagerRestController.getService();
            assertEquals(dmsService,service);

        }
    }


}