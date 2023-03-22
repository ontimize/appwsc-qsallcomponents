package com.imatia.qsallcomponents.ws.rest;

import org.junit.Ignore;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MainRestControllerTest {

    @InjectMocks
    MainRestController mainRestController;

    @Nested
    class MainNested {

        @Test
        void when_main_expected_String(){
            String index = "index";
            String main1 = mainRestController.main();
            assertEquals(index,main1);

        }
    }


}