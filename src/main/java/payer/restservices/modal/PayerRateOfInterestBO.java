package payer.restservices.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "t_payer_rate_of_interest")
public class PayerRateOfInterestBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RATE_OF_INTERSET_ID")
	private long id;

	@Column(name = "SCHEME_NAME", length = 50)
	private String schemeName;

	@Column(name = "RATE_OF_INTERSET")
	private Double rateOfInterset;

	@Column(name = "PENAL_INTERSET")
	private Double penalInterset;

	@Column(name = "PENAL_GST")
	private Double penalGst;

	@Column(name = "GST")
	private Double gst;

	@Column(name = "LOAN_TENURE")
	private int loanTenure;

	@Column(name = "LOAN_AMOUNT")
	private Long loanAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYER_ID")
	private PayerBO payer;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@OneToMany(mappedBy = "payerRateOfInterest",fetch = FetchType.LAZY)
	private List<PayerLedgerBO> payerLedgers;
	

	
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

	public PayerBO getPayer() {
		return payer;
	}

	public void setPayer(PayerBO payer) {
		this.payer = payer;
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

	public List<PayerLedgerBO> getPayerLedgers() {
		return payerLedgers;
	}

	public void setPayerLedgers(List<PayerLedgerBO> payerLedgers) {
		this.payerLedgers = payerLedgers;
	}

}
