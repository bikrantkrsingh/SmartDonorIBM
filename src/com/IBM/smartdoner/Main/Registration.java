package com.IBM.smartdoner.Main;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("Registration")
public class Registration extends IBMDataObject {

	public static final String CLASS_NAME = "Registration";

	private String NAME = "NAME";
	private String ADDRESS1 = "ADDRESS1";
	private String ADDRESS2 = "ADDRESS2";
	private String AREA = "AREA";
	private String PIN = "PIN";
	private String STATE = "STATE";
	private String COUNTRY = "COUNTRY";
	private String EMAIL = "EMAIL";
	private String PHONE = "PHONE";
	private String PAN = "PAN";
	private String USERTYPE = "USERTYPE";

	public String getNAME() {
		System.out.println("getter of getNAME is called");
		return (String) getObject(NAME);
		// return NAME;
	}

	public String getADDRESS1() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(ADDRESS1);
		// return ADDRESS1;
	}

	public String getADDRESS2() {
		System.out.println("getter of getADDRESS2 is called");
		return (String) getObject(ADDRESS2);
		// return ADDRESS2;
	}

	public String getAREA() {
		System.out.println("getter of getAAREA is called");
		return (String) getObject(AREA);
		// return ADDRESS1;
	}

	public String getPIN() {
		System.out.println("getter of getPIN is called");
		return (String) getObject(PIN);
		// return ADDRESS1;
	}

	public String getSTATE() {
		System.out.println("getter of getSTATE is called");
		return (String) getObject(STATE);
		// return ADDRESS1;
	}

	public String getCOUNTRY() {
		System.out.println("getter of getCOUNTRY is called");
		return (String) getObject(COUNTRY);
		// return ADDRESS1;
	}

	public String getEMAIL() {
		System.out.println("getter of getEMAIL is called");
		return (String) getObject(EMAIL);
		// return ADDRESS1;
	}

	public String getPHONE() {
		System.out.println("getter of getPHONE is called");
		return (String) getObject(PHONE);
		// return ADDRESS1;
	}

	public String getPAN() {
		System.out.println("getter of getPAN is called");
		return (String) getObject(PAN);
		// return ADDRESS1;
	}

	public String getUSERTYPE() {
		System.out.println("getter of getADDRESS1 is called");
		return (String) getObject(USERTYPE);
		// return ADDRESS1;
	}
	
	//.........................................................

	public void setNAME(String nAME) {
		System.out.println("setter of setNAME is called");
		setObject(NAME, (nAME != null) ? nAME : "");
		NAME = nAME;
	}

	public void setADDRESS1(String aDDRESS1) {
		System.out.println("setter of setNAME is called");
		setObject(ADDRESS1, (aDDRESS1 != null) ? aDDRESS1 : "");
		ADDRESS1 = aDDRESS1;
	}

	public void setADDRESS2(String aDDRESS2) {
		System.out.println("setter of setADDRESS2 is called");
		setObject(ADDRESS2, (aDDRESS2 != null) ? aDDRESS2 : "");
		ADDRESS2 = aDDRESS2;
	}

	public void setAREA(String aREA) {
		System.out.println("setter of setAREA is called");
		setObject(AREA, (aREA != null) ? aREA : "");
		AREA = aREA;
	}

	public void setPIN(String pIN) {
		System.out.println("setter of setNAME is called");
		setObject(PIN, (pIN != null) ? pIN : "");
		PIN = pIN;
	}

	public void setSTATE(String sTATE) {
		System.out.println("setter of setNAME is called");
		setObject(STATE, (sTATE != null) ? sTATE : "");
		STATE = sTATE;
	}

	public void setCOUNTRY(String cOUNTRY) {
		System.out.println("setter of setNAME is called");
		setObject(COUNTRY, (cOUNTRY != null) ? cOUNTRY : "");
		COUNTRY = cOUNTRY;
	}

	public void setEMAIL(String eMAIL) {
		System.out.println("setter of setNAME is called");
		setObject(EMAIL, (eMAIL != null) ? eMAIL : "");
		EMAIL = eMAIL;
	}

	public void setPHONE(String pHONE) {
		System.out.println("setter of setNAME is called");
		setObject(PHONE, (pHONE != null) ? pHONE : "");
		PHONE = pHONE;
	}

	public void setPAN(String pAN) {
		System.out.println("setter of setNAME is called");
		setObject(PAN, (pAN != null) ? pAN : "");
		PAN = pAN;
	}

	public void setUSERTYPE(String uSERTYPE) {
		System.out.println("setter of setNAME is called");
		setObject(USERTYPE, (uSERTYPE != null) ? uSERTYPE : "");
		USERTYPE = uSERTYPE;
	}

	public String toString() {
		System.out.println("toString start----------------");
		String theItemName = "";
		theItemName = getNAME() + "   " + getADDRESS1() + "   " + getADDRESS2()
				+ "   " + getAREA() + "   " + getPIN() + "   "
				+ getSTATE() + "   " + getCOUNTRY() + "   " + getEMAIL()
				+ "   " + getPHONE() + "   " + getPAN() + "   "
				+ getUSERTYPE();
		System.out.println("toString end----------------");

		return theItemName;
	}

}
