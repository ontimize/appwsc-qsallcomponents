package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IMovementsApi {
	/**
	* Deletes a Movement.
	* This resource represents a movement in the system. 
	* @param name Name (required)
	* @param deleteParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}/delete
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(String name, com.ontimize.jee.server.rest.DeleteParameter deleteParameter);

	/**
	* Inserts a Movement.
	* This resource represents a movement in the system. 
	* @param name Name (required)
	* @param insertParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}/insert
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> insert(String name, com.ontimize.jee.server.rest.InsertParameter insertParameter);

	/**
	* Returns a list of Movements.
	* This resource represents a list of movements in the system. 
	* @param name Name (required)
	* @param filter Filter (optional)
	* @param columns Columns (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, String filter, String columns);

	/**
	* Searchs and returns a list of movements.
	* This resource represents a list of movements in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, com.ontimize.jee.server.rest.QueryParameter queryParameter) throws Exception;

	/**
	* Performs an advanced search and returns a list of movements.
	* This resource represents a list of movements in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.db.AdvancedEntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}/advancedsearch
	*/
	
	public ResponseEntity<com.ontimize.jee.common.db.AdvancedEntityResult> query(String name, com.ontimize.jee.server.rest.AdvancedQueryParameter queryParameter) throws Exception;

	/**
	* Updates a Movement.
	* This resource represents a movement in the system. 
	* @param name Name (required)
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /movements/{name}/update
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> update(String name, com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
