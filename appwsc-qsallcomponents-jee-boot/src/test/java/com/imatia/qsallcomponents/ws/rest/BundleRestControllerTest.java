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

@ExtendWith(MockitoExtension.class)
class BundleRestControllerTest {

    @InjectMocks
    BundleRestController bundleRestController;

    @Nested
    class GetBundle {
        @Test
        void when_getBundle_receive_string_en_expected_ResponseEntity() {
            String lang = "en";
            ResponseEntity<EntityResult> bundle = bundleRestController.getBundle(lang);
            Assertions.assertEquals(HttpStatus.OK, bundle.getStatusCode());
        }

        @Test
        void when_getBundle_receive_string_es_expected_ResponseEntity() {
            String lang = "es";
            ResponseEntity<EntityResult> bundle = bundleRestController.getBundle(lang);
            Assertions.assertEquals(HttpStatus.OK, bundle.getStatusCode());
        }

        @Test
        void when_getBundle_receive_string_language_expected_ResponseEntity() {
            String lang = "language";
            ResponseEntity<EntityResult> bundle = bundleRestController.getBundle(lang);
            Assertions.assertEquals(HttpStatus.OK, bundle.getStatusCode());
        }

    }


}