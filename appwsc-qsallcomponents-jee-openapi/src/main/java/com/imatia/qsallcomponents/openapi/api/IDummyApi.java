package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IDummyApi {
	/**
	* .
	* . 
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	*/
	@RequestMapping(path = "/dummy/dummypermission", method = RequestMethod.GET, 
		produces = { "application/json" })
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> dummyPermission();

	/**
	* .
	* . 
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	*/
	@RequestMapping(path = "/dummy", method = RequestMethod.GET, 
		produces = { "application/json" })
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> dummyTest();
}
