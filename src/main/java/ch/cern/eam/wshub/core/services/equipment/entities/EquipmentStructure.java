package ch.cern.eam.wshub.core.services.equipment.entities;

import ch.cern.eam.wshub.core.adapters.BigDecimalAdapter;
import ch.cern.eam.wshub.core.adapters.BooleanAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

public class EquipmentStructure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String childCode;
	private String parentCode;
	private String newParentCode;
	private Boolean costRollUp = false;
	private Boolean dependent = false;
	private String sequenceNumber;
	
	public String getChildCode() {
		return childCode;
	}
	
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@XmlJavaTypeAdapter(BooleanAdapter.class)
	public Boolean getCostRollUp() {
		return costRollUp;
	}
	public void setCostRollUp(Boolean costRollUp) {
		this.costRollUp = costRollUp;
	}

	@XmlJavaTypeAdapter(BooleanAdapter.class)
	public Boolean getDependent() {
		return dependent;
	}
	public void setDependent(Boolean dependent) {
		this.dependent = dependent;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	@Override
	public String toString() {
		return "EquipmentStructure ["
				+ (childCode != null ? "childCode=" + childCode + ", " : "")
				+ (parentCode != null ? "parentCode=" + parentCode + ", " : "")
				+ (newParentCode != null ? "newParentCode=" + newParentCode
						+ ", " : "")
				+ (costRollUp != null ? "costRollUp=" + costRollUp + ", " : "")
				+ (dependent != null ? "dependent=" + dependent + ", " : "")
				+ (sequenceNumber != null ? "sequenceNumber=" + sequenceNumber
						: "") + "]";
	}
	public String getNewParentCode() {
		return newParentCode;
	}
	public void setNewParentCode(String newParentCode) {
		this.newParentCode = newParentCode;
	}
	
	
}
