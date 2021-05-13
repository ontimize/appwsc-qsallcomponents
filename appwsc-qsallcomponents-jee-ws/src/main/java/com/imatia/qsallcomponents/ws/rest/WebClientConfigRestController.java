package com.imatia.qsallcomponents.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.openapi.api.IConfigApi;
import com.ontimize.jee.webclient.remoteconfiguration.IRemoteConfigurationNameConverter;
import com.ontimize.jee.webclient.remoteconfiguration.IRemoteConfigurationService;
import com.ontimize.jee.webclient.remoteconfiguration.RemoteConfigurationRestController;

@RestController
@RequestMapping("/config")
public class WebClientConfigRestController extends RemoteConfigurationRestController<IRemoteConfigurationService, IRemoteConfigurationNameConverter> implements IConfigApi {

	@Autowired
	private IRemoteConfigurationService remoteConfigService;

	@Override
	public IRemoteConfigurationService getService() {
		return this.remoteConfigService;
	}

}
