package util;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonServiceChartsPreferencesDtoConversor {

	public static ObjectNode toObjectNode(ChartsPreferencesDto preferences) {

		ObjectNode preferencesObject = JsonNodeFactory.instance.objectNode();

		preferencesObject.put("title", preferences.getTitle()).put("subtitle", preferences.getSubtitle())
				.put("comboXAxis", preferences.getComboXAxis()).put("dataTypeXAxis", preferences.getDataTypeXAxis())
				.put("comboYAxis", preferences.getComboYAxis())
				.put("dataTypeYAxis", preferences.getDataTypeYAxis().toString())
				.put("chartType", preferences.getChartType());

		return preferencesObject;
	}
}
