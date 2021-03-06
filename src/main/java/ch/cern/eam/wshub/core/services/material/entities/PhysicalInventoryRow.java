package ch.cern.eam.wshub.core.services.material.entities;

import ch.cern.eam.wshub.core.annotations.InforField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "R5STOCKLINES")
public class PhysicalInventoryRow {
    @Id
    @Column(name = "STL_TRANS")
    @InforField(xpath = "TRANSACTIONLINEID/TRANSACTIONID/TRANSACTIONCODE")
    String physicalInventoryCode;

    @Id
    @Column(name = "STL_LINE")
    @InforField(xpath = "TRANSACTIONLINEID/TRANSACTIONLINENUM")
    BigInteger lineNumber;

    @Column(name = "STL_PART")
    @InforField(xpath = "PARTID/PARTCODE")
    String part;

    @Column(name = "STL_STORE")
    @InforField(xpath = "STOREID/STORECODE")
    String store;

    @Column(name = "STL_BIN")
    @InforField(xpath = "BIN")
    String bin;

    @Column(name = "STL_LOT")
    @InforField(xpath = "LOT")
    String lot;

    @Column(name = "STL_EXPQTY")
    @InforField(xpath = "EXPECTEDQUANTITY")
    BigDecimal expectedQuantity;

    @Column(name = "STL_PHYQTY")
    @InforField(xpath = "PHYSICALQUANTITY")
    BigDecimal physicalQuantity;

    // Field missing: description

    // Field missing: error message

    public String getPhysicalInventoryCode() {
        return physicalInventoryCode;
    }

    public void setPhysicalInventoryCode(String physicalInventoryCode) {
        this.physicalInventoryCode = physicalInventoryCode;
    }

    public BigInteger getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(BigInteger line) {
        this.lineNumber = line;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public BigDecimal getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(BigDecimal expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public BigDecimal getPhysicalQuantity() {
        return physicalQuantity;
    }

    public void setPhysicalQuantity(BigDecimal physicalQuantity) {
        this.physicalQuantity = physicalQuantity;
    }

    @Override
    public String toString() {
        return "PhysicalInventoryRow ["
            + (physicalInventoryCode != null ? "physicalInventoryCode=" + physicalInventoryCode + ", " : "")
            + (lineNumber != null ? "lineNumber=" + lineNumber + ", " : "")
            + (part != null ? "part=" + part + ", " : "")
            + (store != null ? "store=" + store + ", " : "")
            + (bin != null ? "bin=" + bin + ", " : "")
            + (lot != null ? "lot=" + lot + ", " : "")
            + (expectedQuantity != null ? "expectedQuantity=" + expectedQuantity + ", " : "")
            + (physicalQuantity != null ? "physicalQuantity=" + physicalQuantity : "")
            + "]";
    }
}
