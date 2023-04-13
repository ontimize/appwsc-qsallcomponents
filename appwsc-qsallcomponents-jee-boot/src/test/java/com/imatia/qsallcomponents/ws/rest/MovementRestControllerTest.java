package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.api.services.IMovementService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MovementRestControllerTest {

    @InjectMocks
    MovementRestController movementRestController;

    @Mock
    IMovementService movementService;

    @Nested
    class GetService {
        @Test
        void when_getService_expected_IMovementService(){
            IMovementService service = movementRestController.getService();
            assertEquals(movementService,service);

        }
    }

}