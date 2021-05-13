package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IAdministrationApi {
	/**
	* .
	* . 
	* @return String
	*
	*/
	@RequestMapping(path = "/permissions", method = RequestMethod.GET, 
		produces = { "text/plain" })
	public ResponseEntity<String> permissions();

	/**
	* .
	* . 
	* @param who who (optional, default to &quot;unknown&quot;)
	* @return com.imatia.qsallcomponents.api.constants.entities.Test
	*
	*/
	@RequestMapping(path = "/test", method = RequestMethod.GET, 
		produces = { "application/json" })
	public ResponseEntity<com.imatia.qsallcomponents.api.constants.entities.Test> test(@RequestParam(name = "who", required = false, defaultValue="unknown") String who);
}
