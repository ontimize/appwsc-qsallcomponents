package com.imatia.qsallcomponents.ws.rest;

import com.imatia.qsallcomponents.openapi.IBundleApi;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;

@RestController
public class BundleRestController implements IBundleApi {

    private static final String VALUE = "value";

    @Override
    public ResponseEntity<EntityResult> getBundle(String lang) {
        EntityResult er;
        switch (lang) {
            case "en":
                er = this.getDummyBundleEn();
                break;
            case "es":
                er = this.getDummyBundleEs();
                break;
            default:
                er = this.getDummyBundleEn();
                break;

        }
        return new ResponseEntity<>(er, HttpStatus.OK);
    }

    public EntityResult getDummyBundleEs() {
        EntityResult er = new EntityResultMapImpl(Arrays.asList("key", BundleRestController.VALUE));
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "REMOTE_BUNDLE_TEST");
        map.put(BundleRestController.VALUE, "Bundle remoto {0} {1} {2}");
        er.addRecord(map);
        return er;
    }

    public EntityResult getDummyBundleEn() {
        EntityResult er = new EntityResultMapImpl(Arrays.asList("key", BundleRestController.VALUE));
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "REMOTE_BUNDLE_TEST");
        map.put(BundleRestController.VALUE, "Remote bundle {0} {1} {2}");
        er.addRecord(map);
        return er;
    }

}
