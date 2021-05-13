package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICustomersApi {
	/**
	* Deletes a Customers.
	* This resource represents a customer in the system. 
	* @param name Name (required)
	* @param deleteParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}/delete
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(String name, com.ontimize.jee.server.rest.DeleteParameter deleteParameter);

	/**
	* Inserts a Customer.
	* This resource represents a customer in the system. 
	* @param name Name (required)
	* @param insertParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}/insert
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> insert(String name, com.ontimize.jee.server.rest.InsertParameter insertParameter);

	/**
	* Returns a list of Customers.
	* This resource represents a list of customers in the system. 
	* @param name Name (required)
	* @param filter Filter (optional)
	* @param columns Columns (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, String filter, String columns);

	/**
	* Searchs and returns a list of Customers.
	* This resource represents a list of customers in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, com.ontimize.jee.server.rest.QueryParameter queryParameter) throws Exception;

	/**
	* Performs an advanced search and returns a list of Customers.
	* This resource represents a list of customers in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.db.AdvancedEntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}/advancedsearch
	*/
	
	public ResponseEntity<com.ontimize.jee.common.db.AdvancedEntityResult> query(String name, com.ontimize.jee.server.rest.AdvancedQueryParameter queryParameter) throws Exception;

	/**
	* Updates a Customer.
	* This resource represents a customer in the system. 
	* @param name Name (required)
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /customers/{name}/update
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> update(String name, com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
