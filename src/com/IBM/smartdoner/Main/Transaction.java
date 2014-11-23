package com.IBM.smartdoner.Main;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("Transaction")
public class Transaction extends IBMDataObject {

	public static final String CLASS_NAME = "Transaction";
	
	private String DONATIONTYPE = "DONATIONTYPE";
	private String QUANTITY = "QUANTITY";
	private String VALIDITY = "VALIDITY";
	private String AREA = "AREA";
	private String ADDRESS = "ADDRESS";
	private String NOTE = "NOTE";
	private String NAME = "NAME";
	private String PHONE = "PHONE";
	private String DATE = "DATE";

	public String getNAME() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(NAME);
		// return ADDRESS1;
	}
	public String getPHONE() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(PHONE);
		// return ADDRESS1;
	}
	
	public String getDONATIONTYPE() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(DONATIONTYPE);
		// return ADDRESS1;
	}

	public String getQUANTITY() {
		System.out.println("getter of getADDRESS2 is called");
		return (String) getObject(QUANTITY);
		// return ADDRESS2;
	}

	public String getVALIDITY() {
		System.out.println("getter of getAAREA is called");
		return (String) getObject(VALIDITY);
		// return ADDRESS1;
	}
	
	public String getAREA() {
		System.out.println("getter of getAAREA is called");
		return (String) getObject(AREA);
		// return ADDRESS1;
	}

	public String getADDRESS() {
		System.out.println("getter of getPIN is called");
		return (String) getObject(ADDRESS);
		// return ADDRESS1;
	}

	public String getNOTE() {
		System.out.println("getter of getSTATE is called");
		return (String) getObject(NOTE);
		// return ADDRESS1;
	}
	
	public String getDATE() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(DATE);
		// return ADDRESS1;
	}

	// .........................................................

	
	public void setDONATIONTYPE(String dONATIONTYPE) {
		System.out.println("setter of setNAME is called");
		setObject(DONATIONTYPE, (dONATIONTYPE != null) ? dONATIONTYPE : "");
		DONATIONTYPE = dONATIONTYPE;
	}

	public void setQUANTITY(String qUANTITY) {
		System.out.println("setter of setADDRESS2 is called");
		setObject(QUANTITY, (qUANTITY != null) ? qUANTITY : "");
		QUANTITY = qUANTITY;
	}

	public void setVALIDITY(String vALIDITY) {
		System.out.println("setter of setAREA is called");
		setObject(VALIDITY, (vALIDITY != null) ? vALIDITY : "");
		VALIDITY = vALIDITY;
	}
	
	public void setAREA(String aREA) {
		System.out.println("setter of setAREA is called");
		setObject(AREA, (aREA != null) ? aREA : "");
		AREA = aREA;
	}

	public void setADDRESS(String aDDRESS) {
		System.out.println("setter of setNAME is called");
		setObject(ADDRESS, (aDDRESS != null) ? aDDRESS : "");
		ADDRESS = aDDRESS;
	}

	public void setNAME(String nAME) {
		System.out.println("setter of setNAME is called");
		setObject(NAME, (nAME != null) ? nAME : "");
		NAME = nAME;
	}
	
	public void setPHONE(String pHONE) {
		System.out.println("setter of setNAME is called");
		setObject(PHONE, (pHONE != null) ? pHONE : "");
		PHONE = pHONE;
	}
	
	public void setNOTE(String nOTE) {
		System.out.println("setter of setNAME is called");
		setObject(NOTE, (nOTE != null) ? nOTE : "");
		NOTE = nOTE;
	}
	
	public void setDATE(String dATE) {
		System.out.println("setter of setNAME is called");
		setObject(DATE, (dATE != null) ? dATE : "");
		DATE = dATE;
	}

	public String toString() {
		System.out.println("toString start----------------");
		String theItemName = "";
		theItemName = getDONATIONTYPE() + "   " + getQUANTITY()
				+ "   " + getVALIDITY() + "   " + getADDRESS() + "   "
				+ getNOTE() + "  " + getDATE();
		System.out.println("toString end----------------");

		return theItemName;
	}
}
