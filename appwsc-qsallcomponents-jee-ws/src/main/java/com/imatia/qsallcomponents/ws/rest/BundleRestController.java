package com.imatia.qsallcomponents.ws.rest;

import java.util.Arrays;
import java.util.Hashtable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.openapi.api.IBundleApi;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;

@RestController
public class BundleRestController implements IBundleApi {

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
		return new ResponseEntity<EntityResult>(er, HttpStatus.OK);
	}

	public EntityResult getDummyBundleEs() {
		EntityResult er = new EntityResultMapImpl(Arrays.asList("key", "value"));
		Hashtable<String, String> record = new Hashtable<String, String>();
		record.put("key", "REMOTE_BUNDLE_TEST");
		record.put("value", "Bundle remoto {0} {1} {2}");
		er.addRecord(record);
		return er;
	}

	public EntityResult getDummyBundleEn() {
		EntityResult er = new EntityResultMapImpl(Arrays.asList("key", "value"));
		Hashtable<String, String> record = new Hashtable<String, String>();
		record.put("key", "REMOTE_BUNDLE_TEST");
		record.put("value", "Remote bundle {0} {1} {2}");
		er.addRecord(record);
		return er;
	}

}
