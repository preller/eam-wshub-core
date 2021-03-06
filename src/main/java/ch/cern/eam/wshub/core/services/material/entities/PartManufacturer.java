package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.adapters.BooleanAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

public class PartManufacturer implements Serializable {

	private String manufacturerCode;
	private String manufacturerDesc;
	private String partCode;
	private String drawingNumber;
	private Boolean outOfService = false;
	private Boolean primary = false;
	private String manufacturerPartNumber;
	private String manufacturerPartNumberNew;
	
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getDrawingNumber() {
		return drawingNumber;
	}
	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber = drawingNumber;
	}

	@XmlJavaTypeAdapter(BooleanAdapter.class)
	public Boolean getOutOfService() {
		return outOfService;
	}
	public void setOutOfService(Boolean outOfService) {
		this.outOfService = outOfService;
	}
	public String getManufacturerPartNumber() {
		return manufacturerPartNumber;
	}
	
	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		this.manufacturerPartNumber = manufacturerPartNumber;
	}
	
	@Override
	public String toString() {
		return "PartManufacturer ["
				+ (manufacturerCode != null ? "manufacturerCode="
						+ manufacturerCode + ", " : "")
				+ (partCode != null ? "partCode=" + partCode + ", " : "")
				+ (drawingNumber != null ? "drawingNumber=" + drawingNumber
						+ ", " : "")
				+ (outOfService != null ? "outOfService=" + outOfService + ", "
						: "")
				+ (manufacturerPartNumber != null ? "manufacturerPartNumber="
						+ manufacturerPartNumber : "") + "]";
	}
	public String getManufacturerPartNumberNew() {
		return manufacturerPartNumberNew;
	}
	public void setManufacturerPartNumberNew(String manufacturerPartNumberNew) {
		this.manufacturerPartNumberNew = manufacturerPartNumberNew;
	}

	@XmlJavaTypeAdapter(BooleanAdapter.class)
	public Boolean getPrimary() {
		return primary;
	}
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
	public String getManufacturerDesc() {
		return manufacturerDesc;
	}
	public void setManufacturerDesc(String manufacturerDesc) {
		this.manufacturerDesc = manufacturerDesc;
	}

	
}
