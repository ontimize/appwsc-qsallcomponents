package com.imatia.qsallcomponents.ws.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.qsallcomponents.api.services.IChartsPreferencesService;
import com.imatia.qsallcomponents.openapi.IChartPreferencesApi;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.rest.ORestController;

import util.ChartsPreferencesDto;
import util.JsonServiceChartsPreferencesDtoConversor;

@RestController
@RequestMapping("/chartspreferences")
public class ChartsPreferencesRestController extends ORestController<IChartsPreferencesService>
		implements IChartPreferencesApi {
	@Qualifier("ChartsPreferencesService")
	@Autowired
	private IChartsPreferencesService chartsPreferencesService;

	@Override
	public IChartsPreferencesService getService() {
		return this.chartsPreferencesService;
	}

	@Autowired
	private JsonServiceChartsPreferencesDtoConversor conversor;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String savePreferences(@RequestBody ChartsPreferencesDto param) throws Exception {

		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("NAME", param.getName());
		attrMap.put("DESCRIPTION", param.getDescription());
		attrMap.put("ENTITY", param.getEntity());
		attrMap.put("PREFERENCES", conversor.toObjectNode(param));

		chartsPreferencesService.preferenceInsert(attrMap);

		return param.toString();
	}

	@RequestMapping(value = "/preferences", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityResult getPreferences() {
		List<String> columns = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		List<String> attrList = new ArrayList<>();
		ChartsPreferencesDto preferences = new ChartsPreferencesDto();
		attrList.add("ID");
		attrList.add("NAME");
		attrList.add("DESCRIPTION");
		attrList.add("ENTITY");
		attrList.add("PREFERENCES");
		EntityResult res = chartsPreferencesService.preferenceQuery(map, attrList);
		return res;
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public EntityResult removePreferences(@PathVariable("id") Long id) {
		EntityResult res = new EntityResultMapImpl();
		Map<String, Object> attrMap = new HashMap<>();
		try {
			attrMap.put("ID", id);
			this.chartsPreferencesService.preferenceDelete(attrMap);
			res.setCode(EntityResult.OPERATION_SUCCESSFUL);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(EntityResult.OPERATION_WRONG);
			return res;
		}
		return res;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public EntityResult updatePreferences(@PathVariable("id") Long id, @RequestBody ChartsPreferencesDto param) {
		EntityResult res = new EntityResultMapImpl();
		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("NAME", param.getName());
		attrMap.put("DESCRIPTION", param.getDescription());
		attrMap.put("PREFERENCES", conversor.toObjectNode(param).toString());

		Map<String, Object> attrKey = new HashMap<>();
		try {
			attrKey.put("ID", id);
			this.chartsPreferencesService.preferenceUpdate(attrMap, attrKey);
			res.setCode(EntityResult.OPERATION_SUCCESSFUL);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(EntityResult.OPERATION_WRONG);
			return res;
		}
		return res;
	}
}
