package com.imatia.qsallcomponents.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IConfigApi {
	/**
	* Inserts a Configuration.
	* This resource represents a configuration in the system. 
	* @param requestBody Values (optional
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on remoteconfigurationrestcontroller based classes.
	* Path: /config
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> createUserConfiguration(java.util.Map<?, ?> requestBody);

	/**
	* Deletes a Configuration.
	* This resource represents a configuration in the system. 
	* @param requestBody Values (optional
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on remoteconfigurationrestcontroller based classes.
	* Path: /config
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> delete(java.util.Map<?, ?> requestBody);

	/**
	* Searchs and returns a list of Configurations.
	* This resource represents a list of configuration in the system. 
	* @param requestBody Values (optional
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on remoteconfigurationrestcontroller based classes.
	* Path: /config/search
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> getUserConfiguration(java.util.Map<?, ?> requestBody);

	/**
	* Updates a Configuration.
	* This resource represents a configuration in the system. 
	* @param updateParameter (required)
	* @return com.ontimize.jee.common.dto.EntityResult
	*
	* This interface must be implemented on remoteconfigurationrestcontroller based classes.
	* Path: /config
	*/
	
	public ResponseEntity<com.ontimize.jee.common.dto.EntityResult> updateUserConfiguration(com.ontimize.jee.server.rest.UpdateParameter updateParameter);
}
