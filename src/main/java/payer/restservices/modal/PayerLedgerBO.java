package payer.restservices.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "t_payer_ledger")
public class PayerLedgerBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEDGER_ID")
	private long id;

	@Column(name = "SCHEME_NAME", length = 50)
	private String schemeName;

	@Column(name = "PAY_MODE", length = 10)
	private String payMode;

	@Column(name = "INTERSET")
	private Double interset;

	@Column(name = "PRINCIPAL")
	private Double principal;

	@Column(name = "GST_ON_PRINCIPAL")
	private Double gstOnPrincipal;

	@Column(name = "BALANCE")
	private Double balance;

	@Column(name = "PAY_STATUS")
	private String payStatus;

	@Column(name = "PAYBLE_EMI")
	private Double paybleEmi;
	
	@Column(name = "EMI_MONTH")
	private int emiMonth;

	@Column(name = "TOTAL_PAYBLE")
	private Double totalPayble;

	@Column(name = "RATE_OF_INTERSET")
	private Double rateOfInterset;

	@Column(name = "PENAL_INTERSET")
	private Double penalInterset;

	@Column(name = "PENAL_GST")
	private Double penalGst;
	
	@Column(name = "PART_PAYMENT")
	private Double partPayment;
	
	@Column(name = "FULL_PAYMENT")
	private Double fullPayment;
	
	@Column(name = "DEFAULT_PAYMENT")
	private Double defaultPaymentt;

	@Column(name = "GST")
	private Double gst;

	@Column(name = "EMI_DATE")
	private Date emiDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RATE_OF_INTEREST_ID")
	private PayerRateOfInterestBO payerRateOfInterest;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Date getEmiDate() {
		return emiDate;
	}

	public void setEmiDate(Date emiDate) {
		this.emiDate = emiDate;
	}

	public PayerRateOfInterestBO getPayerRateOfInterest() {
		return payerRateOfInterest;
	}

	public void setPayerRateOfInterest(PayerRateOfInterestBO payerRateOfInterest) {
		this.payerRateOfInterest = payerRateOfInterest;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public Double getInterset() {
		return interset;
	}

	public void setInterset(Double interset) {
		this.interset = interset;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Double getPartPayment() {
		return partPayment;
	}

	public void setPartPayment(Double partPayment) {
		this.partPayment = partPayment;
	}

	public Double getFullPayment() {
		return fullPayment;
	}

	public void setFullPayment(Double fullPayment) {
		this.fullPayment = fullPayment;
	}

	public Double getDefaultPaymentt() {
		return defaultPaymentt;
	}

	public void setDefaultPaymentt(Double defaultPaymentt) {
		this.defaultPaymentt = defaultPaymentt;
	}

	public int getEmiMonth() {
		return emiMonth;
	}

	public void setEmiMonth(int emiMonth) {
		this.emiMonth = emiMonth;
	}


}
