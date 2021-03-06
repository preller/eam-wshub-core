package ch.cern.eam.wshub.core.services.material;

import ch.cern.eam.wshub.core.annotations.Operation;
import ch.cern.eam.wshub.core.client.InforContext;
import ch.cern.eam.wshub.core.services.INFOR_OPERATION;
import ch.cern.eam.wshub.core.services.material.entities.Lot;
import ch.cern.eam.wshub.core.tools.InforException;

public interface PartLotService {

    @Operation(logOperation = INFOR_OPERATION.PARTLOT_C)
    String createLot(InforContext context, Lot lot) throws InforException;

}

