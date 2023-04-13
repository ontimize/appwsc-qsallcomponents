package com.imatia.qsallcomponents;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedHandler;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LoggingConfigurationTest {

    @InjectMocks
    LoggingConfiguration loggingConfiguration;

    @Nested
    class AllowUrlSemicolonHttpFirewallNestedClass {
        @Test
        void when_allowUrlSemicolonHttpFirewall_expected_HttpFirewall() {
            HttpFirewall httpFirewall = loggingConfiguration.allowUrlSemicolonHttpFirewall();
            assertNotNull(httpFirewall);

        }
    }

    @Nested
    class RequestRejectedHandlerNestedClass {

        @Test
        void when_requestRejectedHandler_expected_RequestRejectedHandler() {
            RequestRejectedHandler requestRejectedHandler = loggingConfiguration.requestRejectedHandler();
            assertNotNull(requestRejectedHandler);
        }

    }
}