package com.imatia.qsallcomponents.ws.rest;

import java.util.Hashtable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ontimize.dto.EntityResult;
import com.ontimize.dto.EntityResultMapImpl;

@RestController
@RequestMapping("/test")
public class DummyController {

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityResult> test() {
		EntityResult er = new EntityResultMapImpl();
		Hashtable<String, String> result = new Hashtable<>();
		result.put("Ontimize", "works");
		er.addRecord(result);
		return new ResponseEntity<EntityResult>(er, HttpStatus.OK);
	}

	/*
	 * Develop purposes only
	 */
	private static final String DUMMY_PERMISSION = "{\"components\":[{\"attr\":\"customer_table_home\",\"selector\":\"o-table\",\"columns\":[{\"attr\":\"NAME\",\"visible\":true,\"enabled\":true}],\"menu\":{\"visible\":true,\"enabled\":true},\"actions\":[{\"attr\":\"update\",\"visible\":true,\"enabled\":false},{\"attr\":\"delete\",\"visible\":true,\"enabled\":false},{\"attr\":\"insert\",\"visible\":true,\"enabled\":false},{\"attr\":\"refresh\",\"visible\":true,\"enabled\":false},{\"attr\":\"detail\",\"visible\":true,\"enabled\":false},{\"attr\":\"example\",\"visible\":true,\"enabled\":false},{\"attr\":\"example2\",\"visible\":true,\"enabled\":false}]}]}";

	@RequestMapping(value = "/dummyPermission", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityResult> dummyPermission() {
		EntityResult er = new EntityResultMapImpl();
		Hashtable<String, String> result = new Hashtable<>();
		result.put("permission", DUMMY_PERMISSION);
		er.addRecord(result);
		return new ResponseEntity<EntityResult>(er, HttpStatus.OK);
	}

}
