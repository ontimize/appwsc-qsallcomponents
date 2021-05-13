package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IBranchesApi {
	/**
	* Deletes a Branch.
	* This resource represents a branch in the system. 
	* @param name Name (required)
	* @param deleteParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}/delete
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(String name, com.ontimize.jee.server.rest.DeleteParameter deleteParameter);

	/**
	* Inserts a Branch.
	* This resource represents a branch in the system. 
	* @param name Name (required)
	* @param insertParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}/insert
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> insert(String name, com.ontimize.jee.server.rest.InsertParameter insertParameter);

	/**
	* .
	* . 
	* @param names the names (required)
	* @param orgSpringframeworkWebMultipartMultipartFile (required)
	* @param data the data (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	*/
	@RequestMapping(path = "/plan", method = RequestMethod.POST,
		consumes = { "multipart/form-data" }, 
		produces = { "application/json" })
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> plan(@RequestParam(name = "names", required = true) java.util.List<String> names, 
			@RequestBody java.util.List<org.springframework.web.multipart.MultipartFile> orgSpringframeworkWebMultipartMultipartFile, 
			@RequestParam(name = "data", required = false) String data) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException, com.fasterxml.jackson.databind.JsonMappingException;;

	/**
	* Returns a list of Branches.
	* This resource represents a list of branches in the system. 
	* @param name Name (required)
	* @param filter Filter (optional)
	* @param columns Columns (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, String filter, String columns);

	/**
	* Searchs and returns a list of Branches.
	* This resource represents a list of branches in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, com.ontimize.jee.server.rest.QueryParameter queryParameter) throws Exception;

	/**
	* Performs an advanced search and returns a list of Branches.
	* This resource represents a list of branches in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.db.AdvancedEntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}/advancedsearch
	*/
	
	public ResponseEntity<com.ontimize.jee.common.db.AdvancedEntityResult> query(String name, com.ontimize.jee.server.rest.AdvancedQueryParameter queryParameter) throws Exception;

	/**
	* Updates a Branch.
	* This resource represents a branch in the system. 
	* @param name Name (required)
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /branches/{name}/update
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> update(String name, com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
