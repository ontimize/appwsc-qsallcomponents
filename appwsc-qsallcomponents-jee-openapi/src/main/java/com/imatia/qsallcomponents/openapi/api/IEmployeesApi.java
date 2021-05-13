package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IEmployeesApi {
	/**
	* Deletes an Employee.
	* This resource represents an employee in the system. 
	* @param name Name (required)
	* @param deleteParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}/delete
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(String name, com.ontimize.jee.server.rest.DeleteParameter deleteParameter);

	/**
	* Inserts an Employee.
	* This resource represents an employee in the system. 
	* @param name Name (required)
	* @param insertParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}/insert
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> insert(String name, com.ontimize.jee.server.rest.InsertParameter insertParameter);

	/**
	* Returns a list of employees.
	* This resource represents a list of employees in the system. 
	* @param name Name (required)
	* @param filter Filter (optional)
	* @param columns Columns (optional)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, String filter, String columns);

	/**
	* Searchs and returns a list of employees.
	* This resource represents a list of employees in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> query(String name, com.ontimize.jee.server.rest.QueryParameter queryParameter) throws Exception;

	/**
	* Performs an advanced search and returns a list of employees.
	* This resource represents a list of employees in the system. 
	* @param name Name (required)
	* @param queryParameter (required)
	* @return com.ontimize.jee.common.db.AdvancedEntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}/advancedsearch
	*/
	
	public ResponseEntity<com.ontimize.jee.common.db.AdvancedEntityResult> query(String name, com.ontimize.jee.server.rest.AdvancedQueryParameter queryParameter) throws Exception;

	/**
	* Updates an Employee.
	* This resource represents an employee in the system. 
	* @param name Name (required)
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on orestcontroller based classes.
	* Path: /employees/{name}/update
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> update(String name, com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
