package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IUsersApi {
	/**
	* Deletes an User.
	* This resource represents an user in the system. 
	* @param name Name (required)
	* @param deleteParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}/delete
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(String name, com.ontimize.jee.server.rest.DeleteParameter deleteParameter);

	/**
	* Inserts an User.
	* This resource represents an user in the system. 
	* @param name Name (required)
	* @param insertParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}/insert
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> insert(String name, com.ontimize.jee.server.rest.InsertParameter insertParameter);

	/**
	* .
	* . 
	* @return java.lang.Void
	*
	*/
	@RequestMapping(path = "/login", method = RequestMethod.POST, 
		produces = { "application/json" })
	public ResponseEntity<java.lang.Void> login();

	/**
	* Returns a list of Users.
	* This resource represents a list of users in the system. 
	* @param name Name (required)
	* @param filter Filter (optional)
	* @param columns Columns (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, String filter, String columns);

	/**
	* Searches and returns a list of Users.
	* This resource represents a list of users in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, com.ontimize.jee.server.rest.QueryParameter queryParameter) throws Exception;

	/**
	* Performs an advanced search and returns a list of Users.
	* This resource represents a list of users in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.db.AdvancedEntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}/advancedsearch
	*/
	
	public ResponseEntity<com.ontimize.jee.common.db.AdvancedEntityResult> query(String name, com.ontimize.jee.server.rest.AdvancedQueryParameter queryParameter) throws Exception;

	/**
	* Updates an User.
	* This resource represents an user in the system. 
	* @param name Name (required)
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /users/{name}/update
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> update(String name, com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
