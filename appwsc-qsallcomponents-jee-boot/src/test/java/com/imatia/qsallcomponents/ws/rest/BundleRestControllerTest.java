package com.imatia.qsallcomponents.ws.rest;

import com.ontimize.jee.common.dto.EntityResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BundleRestControllerTest {

    @InjectMocks
    BundleRestController bundleRestController;

    @Nested
    class GetBundle {
        @ParameterizedTest(name= "{index} - Testing String: {0}")
        @ValueSource(strings = {"en","es","language"})
        void when_getBundle_receive_string_expected_ResponseEntity(String lang) {
            ResponseEntity<EntityResult> bundle = bundleRestController.getBundle(lang);
            Assertions.assertEquals(HttpStatus.OK, bundle.getStatusCode());
        }


    }


}