package util;

import java.util.List;

public class ChartsPreferencesDto {

	private String name;
	private String description;
	private String entity;
	private String title;
	private String subtitle;
	private String comboXAxis;
	private String DataTypeXAxis;
	private String comboYAxis;
	private List<String> DataTypeYAxis;
	private String chartType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getComboXAxis() {
		return comboXAxis;
	}

	public void setComboXAxis(String comboXAxis) {
		this.comboXAxis = comboXAxis;
	}

	public String getDataTypeXAxis() {
		return DataTypeXAxis;
	}

	public void setDataTypeXAxis(String dataTypeXAxis) {
		DataTypeXAxis = dataTypeXAxis;
	}

	public String getComboYAxis() {
		return comboYAxis;
	}

	public void setComboYAxis(String comboYAxis) {
		this.comboYAxis = comboYAxis;
	}

	public List<String> getDataTypeYAxis() {
		return DataTypeYAxis;
	}

	public void setDataTypeYAxis(List<String> dataTypeYAxis) {
		DataTypeYAxis = dataTypeYAxis;
	}

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	public ChartsPreferencesDto() {
	}

}
