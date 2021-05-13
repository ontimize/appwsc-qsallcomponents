package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IBundleApi {
	/**
	* .
	* . 
	* @param lang the lang (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	*/
	@RequestMapping(path = "/bundle", method = RequestMethod.GET, 
		produces = { "application/json" })
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> getBundle(@RequestParam(name = "lang", required = true) String lang);
}
