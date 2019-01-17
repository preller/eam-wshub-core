package ch.cern.eam.wshub.core.services.equipment.impl;

import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.equipment.EquipmentHierarchyService;
import ch.cern.eam.wshub.core.services.equipment.entities.EquipmentStructure;
import ch.cern.eam.wshub.core.tools.ApplicationData;
import ch.cern.eam.wshub.core.tools.InforException;
import ch.cern.eam.wshub.core.tools.Tools;
import net.datastream.schemas.mp_fields.EQUIPMENTID_Type;
import net.datastream.schemas.mp_fields.EQUIPMENTSTRUCTUREID_Type;
import net.datastream.schemas.mp_functions.SessionType;
import net.datastream.schemas.mp_functions.mp0347_001.MP0347_UpdateEquipmentStructureProperties_001;
import net.datastream.schemas.mp_functions.mp0356_001.MP0356_RemoveEquipmentFromStructure_001;
import net.datastream.schemas.mp_functions.mp0356_001.ParentEquipment;
import net.datastream.schemas.mp_functions.mp3057_001.MP3057_AddEquipmentStructure_001;
import net.datastream.schemas.mp_functions.mp3058_001.MP3058_SyncEquipmentStructure_001;
import net.datastream.schemas.mp_functions.mp3058_001.NewParentEquipment;
import net.datastream.wsdls.inforws.InforWebServicesPT;
import javax.xml.ws.Holder;

public class EquipmentHierarchyServiceImpl implements EquipmentHierarchyService {

	private Tools tools;
	private InforWebServicesPT inforws;
	private ApplicationData applicationData;

	public EquipmentHierarchyServiceImpl(ApplicationData applicationData, Tools tools, InforWebServicesPT inforWebServicesToolkitClient) {
		this.applicationData = applicationData;
		this.tools = tools;
		this.inforws = inforWebServicesToolkitClient;
	}

	public String addEquipmentToStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {

		MP3057_AddEquipmentStructure_001 addEqStr = new MP3057_AddEquipmentStructure_001();

		addEqStr.setEquipmentStructure(
				new net.datastream.schemas.mp_entities.equipmentstructure_001.EquipmentStructure());
		EQUIPMENTSTRUCTUREID_Type strID = new EQUIPMENTSTRUCTUREID_Type();
		addEqStr.getEquipmentStructure().setEQUIPMENTSTRUCTUREID(strID);

		if (equipmentStructure.getChildCode() != null) {
			strID.setEQUIPMENTID(new EQUIPMENTID_Type());
			strID.getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
			strID.getEQUIPMENTID().setEQUIPMENTCODE(equipmentStructure.getChildCode().trim().toUpperCase());
		}

		if (equipmentStructure.getNewParentCode() != null) {
			strID.setPARENTEQUIPMENTID(new EQUIPMENTID_Type());
			strID.getPARENTEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
			strID.getPARENTEQUIPMENTID().setEQUIPMENTCODE(equipmentStructure.getNewParentCode().trim().toUpperCase());
		}

		if (equipmentStructure.getCostRollUp() != null && !equipmentStructure.getCostRollUp().trim().equals("")) {
			addEqStr.getEquipmentStructure().setCOSTROLLUP(equipmentStructure.getCostRollUp());
		} else {
			addEqStr.getEquipmentStructure().setCOSTROLLUP("false");
		}

		if (equipmentStructure.getDependent() != null && !equipmentStructure.getDependent().trim().equals("")) {
			addEqStr.getEquipmentStructure().setDEPENDENTON(equipmentStructure.getDependent().toUpperCase());
		}

		if (equipmentStructure.getSequenceNumber() != null
				&& !equipmentStructure.getSequenceNumber().trim().equals("")) {
			addEqStr.getEquipmentStructure()
					.setSEQUENCENUMBER(tools.getDataTypeTools().encodeLong(equipmentStructure.getSequenceNumber(), "Sequence Number"));
		}

		if (context.getCredentials() != null) {
			inforws.addEquipmentStructureOp(addEqStr, applicationData.getOrganization(),
					tools.createSecurityHeader(context), "TERMINATE", null,
					null, applicationData.getTenant());
		} else {
			inforws.addEquipmentStructureOp(addEqStr, applicationData.getOrganization(), null, null,
					new Holder<SessionType>(tools.createInforSession(context)), null, applicationData.getTenant());
		}

		return "OK";
	}

	public String removeEquipmentFromStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {
		MP0356_RemoveEquipmentFromStructure_001 removeeq = new MP0356_RemoveEquipmentFromStructure_001();

		if (equipmentStructure.getChildCode() != null) {
			removeeq.setEQUIPMENTID(new EQUIPMENTID_Type());
			removeeq.getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
			removeeq.getEQUIPMENTID().setEQUIPMENTCODE(equipmentStructure.getChildCode().trim().toUpperCase());
		}

		if (equipmentStructure.getParentCode() != null) {
			removeeq.setParentEquipment(new ParentEquipment());
			removeeq.getParentEquipment().setEQUIPMENTID(new EQUIPMENTID_Type());
			removeeq.getParentEquipment().getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
			removeeq.getParentEquipment().getEQUIPMENTID()
					.setEQUIPMENTCODE(equipmentStructure.getParentCode().trim().toUpperCase());
		}

		if (context.getCredentials() != null) {
			inforws.removeEquipmentFromStructureOp(removeeq, applicationData.getOrganization(),
					tools.createSecurityHeader(context), "TERMINATE", null,
					null, applicationData.getTenant());
		} else {
			inforws.removeEquipmentFromStructureOp(removeeq, applicationData.getOrganization(), null, null,
					new Holder<SessionType>(tools.createInforSession(context)), null, applicationData.getTenant());
		}

		return "OK";
	}

	public String updateEquipmentStructure(InforContext context, EquipmentStructure equipmentStructure) throws InforException {

		//
		// check if existing parent hierarchy will be updates
		//
		if (equipmentStructure.getNewParentCode() == null || equipmentStructure.getNewParentCode().trim().equals("")) {

			MP0347_UpdateEquipmentStructureProperties_001 updateEqStr = new MP0347_UpdateEquipmentStructureProperties_001();

			if (equipmentStructure.getChildCode() != null) {
				updateEqStr.setChildEquipment(new EQUIPMENTID_Type());
				updateEqStr.getChildEquipment().setORGANIZATIONID(tools.getOrganization(context));
				updateEqStr.getChildEquipment()
						.setEQUIPMENTCODE(equipmentStructure.getChildCode().trim().toUpperCase());
			}

			if (equipmentStructure.getParentCode() != null) {
				updateEqStr.setParentEquipment(new EQUIPMENTID_Type());
				updateEqStr.getParentEquipment().setORGANIZATIONID(tools.getOrganization(context));
				updateEqStr.getParentEquipment()
						.setEQUIPMENTCODE(equipmentStructure.getParentCode().trim().toUpperCase());
			}

			if (equipmentStructure.getCostRollUp() != null) {
				updateEqStr.setCOSTROLLUP(equipmentStructure.getCostRollUp().toUpperCase());
			}

			if (equipmentStructure.getDependent() != null) {
				updateEqStr.setDEPENDENTON(equipmentStructure.getDependent().toUpperCase());
			}

			if (context.getCredentials() != null) {
				inforws.updateEquipmentStructurePropertiesOp(updateEqStr, applicationData.getOrganization(),
						tools.createSecurityHeader(context), "TERMINATE",
						null, null, applicationData.getTenant());
			} else {
				inforws.updateEquipmentStructurePropertiesOp(updateEqStr, applicationData.getOrganization(), null, null,
						new Holder<SessionType>(tools.createInforSession(context)), null,
						applicationData.getTenant());
			}

		} else {

			MP3058_SyncEquipmentStructure_001 synceqpstr = new MP3058_SyncEquipmentStructure_001();

			synceqpstr.setEquipmentStructure(
					new net.datastream.schemas.mp_entities.equipmentstructure_001.EquipmentStructure());

			if (equipmentStructure.getChildCode() != null) {
				synceqpstr.getEquipmentStructure().setEQUIPMENTSTRUCTUREID(new EQUIPMENTSTRUCTUREID_Type());
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID().setEQUIPMENTID(new EQUIPMENTID_Type());
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID().getEQUIPMENTID()
						.setORGANIZATIONID(tools.getOrganization(context));
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID().getEQUIPMENTID()
						.setEQUIPMENTCODE(equipmentStructure.getChildCode().trim().toUpperCase());
			}

			if (equipmentStructure.getParentCode() != null) {
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID()
						.setPARENTEQUIPMENTID(new EQUIPMENTID_Type());
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID().getPARENTEQUIPMENTID()
						.setORGANIZATIONID(tools.getOrganization(context));
				synceqpstr.getEquipmentStructure().getEQUIPMENTSTRUCTUREID().getPARENTEQUIPMENTID()
						.setEQUIPMENTCODE(equipmentStructure.getParentCode().trim().toUpperCase());
			}

			if (equipmentStructure.getCostRollUp() != null && !equipmentStructure.getCostRollUp().trim().equals("")) {
				synceqpstr.getEquipmentStructure().setCOSTROLLUP(equipmentStructure.getCostRollUp());
			} else {
				synceqpstr.getEquipmentStructure().setCOSTROLLUP("FALSE");
			}

			if (equipmentStructure.getDependent() != null && !equipmentStructure.getDependent().trim().equals("")) {
				synceqpstr.getEquipmentStructure().setDEPENDENTON(equipmentStructure.getDependent().toUpperCase());
			} else {
				synceqpstr.getEquipmentStructure().setDEPENDENTON("FALSE");
			}

			if (equipmentStructure.getNewParentCode() != null) {
				synceqpstr.setNewParentEquipment(new NewParentEquipment());
				synceqpstr.getNewParentEquipment().setEQUIPMENTID(new EQUIPMENTID_Type());
				synceqpstr.getNewParentEquipment().getEQUIPMENTID().setORGANIZATIONID(tools.getOrganization(context));
				synceqpstr.getNewParentEquipment().getEQUIPMENTID()
						.setEQUIPMENTCODE(equipmentStructure.getNewParentCode().trim().toUpperCase());
			}

			if (context.getCredentials() != null) {
				inforws.syncEquipmentStructureOp(synceqpstr, applicationData.getOrganization(),
						tools.createSecurityHeader(context), "TERMINATE",
						null, null, applicationData.getTenant());
			} else {
				inforws.syncEquipmentStructureOp(synceqpstr, applicationData.getOrganization(), null, null,
						new Holder<SessionType>(tools.createInforSession(context)), null,
						applicationData.getTenant());
			}
		}
		return "OK";
	}


}
