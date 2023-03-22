package com.imatia.qsallcomponents.ws.rest;

import com.ontimize.jee.webclient.remoteconfiguration.IRemoteConfigurationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class WebClientConfigRestControllerTest {

    @InjectMocks
    WebClientConfigRestController webClientConfigRestController;

    @Mock
    IRemoteConfigurationService remoteConfigService;

    @Nested
    class GetService{
        @Test
        void when_getService_expected_IRemoteConfigurationService(){
            IRemoteConfigurationService service = webClientConfigRestController.getService();
            assertEquals(remoteConfigService,service);

        }
    }

}