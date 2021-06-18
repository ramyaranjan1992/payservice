package payer.restservices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class Payer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min = 4, max = 50)
	private String fullName;

	private String email;

	private String organisation;

	private String pan;

	private String parentName;

	private String dob;

	private String gender;

	private String aadhaar;
	
	private String accountId;

	@Size(min = 10, max = 12)
	private Long phoneNumber;

	@Size(min = 10, max = 12)
	private Long alternativePhoneNumber;

	private String payStatus;

	private String city;

	private String state;

	private String address;

	private int pin;

	private String schemeName;

	private Double rateOfInterset;

	private Double penalInterset;

	private Double penalGst;

	private Double gst;

	private int loanTenure;

	private Long loanAmount;

	private String merchantId;

	private Double interset;

	private Double principal;

	private Double gstOnPrincipal;

	private Double balance;

	private Double paybleEmi;

	private Double totalPayble;

	private Date emiDate;

	private Long paymentConfirmation;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getAlternativePhoneNumber() {
		return alternativePhoneNumber;
	}

	public void setAlternativePhoneNumber(Long alternativePhoneNumber) {
		this.alternativePhoneNumber = alternativePhoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Double getRateOfInterset() {
		return rateOfInterset;
	}

	public void setRateOfInterset(Double rateOfInterset) {
		this.rateOfInterset = rateOfInterset;
	}

	public Double getPenalInterset() {
		return penalInterset;
	}

	public void setPenalInterset(Double penalInterset) {
		this.penalInterset = penalInterset;
	}

	public Double getPenalGst() {
		return penalGst;
	}

	public void setPenalGst(Double penalGst) {
		this.penalGst = penalGst;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public int getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getInterset() {
		return interset;
	}

	public void setInterset(Double interset) {
		this.interset = interset;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getGstOnPrincipal() {
		return gstOnPrincipal;
	}

	public void setGstOnPrincipal(Double gstOnPrincipal) {
		this.gstOnPrincipal = gstOnPrincipal;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getPaybleEmi() {
		return paybleEmi;
	}

	public void setPaybleEmi(Double paybleEmi) {
		this.paybleEmi = paybleEmi;
	}

	public Double getTotalPayble() {
		return totalPayble;
	}

	public void setTotalPayble(Double totalPayble) {
		this.totalPayble = totalPayble;
	}

	public Date getEmiDate() {
		return emiDate;
	}

	public void setEmiDate(Date emiDate) {
		this.emiDate = emiDate;
	}

	public Long getPaymentConfirmation() {
		return paymentConfirmation;
	}

	public void setPaymentConfirmation(Long paymentConfirmation) {
		this.paymentConfirmation = paymentConfirmation;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
