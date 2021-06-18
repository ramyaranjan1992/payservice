package payer.restservices.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import payer.restservices.dto.Payer;
import payer.restservices.modal.PayerBO;
import payer.restservices.modal.PayerLedgerBO;
import payer.restservices.modal.PayerRateOfInterestBO;
import payer.restservices.repository.MerchantRepository;
import payer.restservices.repository.PayerLedgerRepository;
import payer.restservices.repository.PayerRateOfInterestRepository;
import payer.restservices.repository.PayerRepository;
import payer.restservices.util.Constants;
import payer.restservices.util.DU;

@RestController
@RequestMapping("/payer")
@Api(value = "Merchant", description = "REST API for Payer", tags = { "Merchant" })
public class PayerCreationController {

	@Autowired
	private PayerRepository payerRepository;

	@Autowired
	private PayerRateOfInterestRepository payerRateOfInterestRepository;

	@Autowired
	private PayerLedgerRepository payerLedgerRepository;

	@Autowired
	private Environment env;

	@Autowired
	private MerchantRepository merchantRepository;

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/getPayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPayer(@RequestParam(required = false) String payerId, Principal principal) {

		Payer payer = new Payer();
		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			PayerBO pay = payerRepository.findByAccountIdAndStatus(payerId, "CURRENT");

			if (pay == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}

			payer.setAadhaar(pay.getAadhaar());
			payer.setAddress(pay.getAddress());
			payer.setAlternativePhoneNumber(pay.getAlternativePhoneNumber());
			payer.setCity(pay.getCity());
			payer.setDob(DU.formatStr(pay.getDob()));
			payer.setEmail(pay.getEmail());
			payer.setFullName(pay.getFullName());
			payer.setGender(pay.getGender());
			payer.setPan(pay.getPan());
			payer.setParentName(pay.getParentName());
			payer.setPhoneNumber(pay.getPhoneNumber());
			payer.setState(pay.getState());
			payer.setSchemeName(pay.getPayerRateOfInterest().getSchemeName());
			payer.setMerchantId(pay.getMerchantId());
			payer.setAccountId(pay.getAccountId());

			if (pay != null && pay.getMerchantId().equalsIgnoreCase(principal.getName())) {

				List<PayerLedgerBO> payerLedgers = pay.getPayerRateOfInterest().getPayerLedgers().stream()
						.filter(c -> c.getPayStatus().equalsIgnoreCase("PENDING")).collect(Collectors.toList());

				for (PayerLedgerBO payerLedgerBO : payerLedgers) {
					payer.setPaymentConfirmation(payerLedgerBO.getId());
					payer.setLoanAmount(pay.getPayerRateOfInterest().getLoanAmount());
					payer.setLoanTenure(pay.getPayerRateOfInterest().getLoanTenure());
					payer.setRateOfInterset(payerLedgerBO.getRateOfInterset());
					payer.setInterset(payerLedgerBO.getInterset());
					payer.setPrincipal(payerLedgerBO.getPrincipal());
					payer.setGst(payerLedgerBO.getGst());
					payer.setGstOnPrincipal(payerLedgerBO.getGstOnPrincipal());
					payer.setTotalPayble(payerLedgerBO.getTotalPayble());
					payer.setPaybleEmi(payerLedgerBO.getPaybleEmi());
					payer.setEmiDate(DU.parse(DU.formatStr(payerLedgerBO.getEmiDate()), "dd-MM-yyyy"));
					payer.setPayStatus(Constants.PaymentStatus.MONTHLY_PAYMENT);
					break;
				}

			}
			return new ResponseEntity<>(payer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/monthlyPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> monthlyPayment(@RequestParam(required = false) String payerId, Principal principal) {

		Payer payer = new Payer();
		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			PayerBO pay = payerRepository.findByAccountIdAndStatus(payerId, "CURRENT");

			if (pay == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}

			payer.setAadhaar(pay.getAadhaar());
			payer.setAddress(pay.getAddress());
			payer.setAlternativePhoneNumber(pay.getAlternativePhoneNumber());
			payer.setCity(pay.getCity());
			payer.setDob(DU.formatStr(pay.getDob()));
			payer.setEmail(pay.getEmail());
			payer.setFullName(pay.getFullName());
			payer.setGender(pay.getGender());
			payer.setPan(pay.getPan());
			payer.setParentName(pay.getParentName());
			payer.setPhoneNumber(pay.getPhoneNumber());
			payer.setState(pay.getState());
			payer.setSchemeName(pay.getPayerRateOfInterest().getSchemeName());

			if (pay != null && pay.getMerchantId().equalsIgnoreCase(principal.getName())) {

				List<PayerLedgerBO> payerLedgers = pay.getPayerRateOfInterest().getPayerLedgers().stream()
						.filter(c -> c.getPayStatus().equalsIgnoreCase("PENDING")).collect(Collectors.toList());

				for (PayerLedgerBO payerLedgerBO : payerLedgers) {
					payer.setPaymentConfirmation(payerLedgerBO.getId());
					payer.setLoanAmount(pay.getPayerRateOfInterest().getLoanAmount());
					payer.setLoanTenure(pay.getPayerRateOfInterest().getLoanTenure());
					payer.setRateOfInterset(payerLedgerBO.getRateOfInterset());
					payer.setInterset(payerLedgerBO.getInterset());
					payer.setPrincipal(payerLedgerBO.getPrincipal());
					payer.setGst(payerLedgerBO.getGst());
					payer.setGstOnPrincipal(payerLedgerBO.getGstOnPrincipal());
					payer.setTotalPayble(payerLedgerBO.getTotalPayble());
					payer.setPaybleEmi(payerLedgerBO.getPaybleEmi());
					payer.setEmiDate(DU.parse(DU.formatStr(payerLedgerBO.getEmiDate()), "dd-MM-yyyy"));
					payer.setPayStatus(Constants.PaymentStatus.MONTHLY_PAYMENT);
					break;
				}

			}
			return new ResponseEntity<>(payer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/paymentConfirmation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> paymentConfirmation(@RequestParam(required = false) String payMode,
			@RequestParam(required = false) String payerId, @RequestParam(required = false) String payStatus,
			@RequestParam(required = false) PayerLedgerBO payerLedger,
			@RequestParam(required = false) Double loanAmount, Principal principal) {

		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			if (payerLedger == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}
			payerLedger.setPayMode(payMode);
			payerLedger.setPayStatus(payStatus);
			payerLedger.setUpdatedOn(new Date());
			PayerBO payerbo = payerLedger.getPayerRateOfInterest().getPayer();
			if (payMode.equalsIgnoreCase(Constants.PaymentStatus.FULL_PAYMENT)
					|| payerLedger.getPayerRateOfInterest().getLoanTenure() == payerLedger.getEmiMonth()) {
				payerbo.setClearance(true);
				payerbo.setUpdatedOn(new Date());
				payerRepository.save(payerbo);
				payerLedger.setFullPayment(loanAmount);
			} else if (payMode.equalsIgnoreCase(Constants.PaymentStatus.PART_PAYMENT)) {
				payerLedger.setPartPayment(loanAmount);
			} else if (payMode.equalsIgnoreCase(Constants.PaymentStatus.DEFAULT_PAYMENT)) {
				payerLedger.setDefaultPaymentt(loanAmount);
			}
			payerLedgerRepository.save(payerLedger);

			return new ResponseEntity<>("Payer Confirmation successfully done. ,Error", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/defaultPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> defaultPayment(@RequestParam(required = false) String payerId, Principal principal) {

		Payer payer = new Payer();
		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			PayerBO pay = payerRepository.findByAccountIdAndStatus(payerId, "CURRENT");

			if (pay == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}

			payer.setAadhaar(pay.getAadhaar());
			payer.setAddress(pay.getAddress());
			payer.setAlternativePhoneNumber(pay.getAlternativePhoneNumber());
			payer.setCity(pay.getCity());
			payer.setDob(DU.formatStr(pay.getDob()));
			payer.setEmail(pay.getEmail());
			payer.setFullName(pay.getFullName());
			payer.setGender(pay.getGender());
			payer.setPan(pay.getPan());
			payer.setParentName(pay.getParentName());
			payer.setPhoneNumber(pay.getPhoneNumber());
			payer.setState(pay.getState());
			payer.setSchemeName(pay.getPayerRateOfInterest().getSchemeName());

			if (pay != null && pay.getMerchantId().equalsIgnoreCase(principal.getName())) {

				List<PayerLedgerBO> payerLedgers = pay.getPayerRateOfInterest().getPayerLedgers().stream()
						.filter(c -> c.getPayStatus().equalsIgnoreCase("PENDING")).collect(Collectors.toList());

				for (PayerLedgerBO payerLedgerBO : payerLedgers) {
					payer.setLoanAmount(pay.getPayerRateOfInterest().getLoanAmount());
					payer.setLoanTenure(pay.getPayerRateOfInterest().getLoanTenure());
					payer.setRateOfInterset(payerLedgerBO.getRateOfInterset());
					payer.setInterset(payerLedgerBO.getInterset());
					payer.setPrincipal(payerLedgerBO.getPrincipal());
					payer.setGst(payerLedgerBO.getGst());
					payer.setGstOnPrincipal(payerLedgerBO.getGstOnPrincipal());
					payer.setTotalPayble(payerLedgerBO.getTotalPayble());
					payer.setPaybleEmi(payerLedgerBO.getPaybleEmi());
					payer.setEmiDate(DU.parse(DU.formatStr(payerLedgerBO.getEmiDate()), "dd-MM-yyyy"));
					payer.setPayStatus(Constants.PaymentStatus.DEFAULT_PAYMENT);
					break;
				}

			}
			return new ResponseEntity<>(payer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/partPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> partPayment(@RequestParam(required = false) String payerId, Principal principal) {

		Payer payer = new Payer();
		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			PayerBO pay = payerRepository.findByAccountIdAndStatus(payerId, "CURRENT");

			if (pay == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}

			payer.setAadhaar(pay.getAadhaar());
			payer.setAddress(pay.getAddress());
			payer.setAlternativePhoneNumber(pay.getAlternativePhoneNumber());
			payer.setCity(pay.getCity());
			payer.setDob(DU.formatStr(pay.getDob()));
			payer.setEmail(pay.getEmail());
			payer.setFullName(pay.getFullName());
			payer.setGender(pay.getGender());
			payer.setPan(pay.getPan());
			payer.setParentName(pay.getParentName());
			payer.setPhoneNumber(pay.getPhoneNumber());
			payer.setState(pay.getState());
			payer.setSchemeName(pay.getPayerRateOfInterest().getSchemeName());

			if (pay != null && pay.getMerchantId().equalsIgnoreCase(principal.getName())) {

				List<PayerLedgerBO> payerLedgers = pay.getPayerRateOfInterest().getPayerLedgers().stream()
						.filter(c -> c.getPayStatus().equalsIgnoreCase("PENDING")).collect(Collectors.toList());

				for (PayerLedgerBO payerLedgerBO : payerLedgers) {
					payer.setLoanAmount(pay.getPayerRateOfInterest().getLoanAmount());
					payer.setLoanTenure(pay.getPayerRateOfInterest().getLoanTenure());
					payer.setRateOfInterset(payerLedgerBO.getRateOfInterset());
					payer.setInterset(payerLedgerBO.getInterset());
					payer.setPrincipal(payerLedgerBO.getPrincipal());
					payer.setGst(payerLedgerBO.getGst());
					payer.setGstOnPrincipal(payerLedgerBO.getGstOnPrincipal());
					payer.setTotalPayble(payerLedgerBO.getTotalPayble());
					payer.setPaybleEmi(payerLedgerBO.getPaybleEmi());
					payer.setEmiDate(DU.parse(DU.formatStr(payerLedgerBO.getEmiDate()), "dd-MM-yyyy"));
					payer.setPayStatus(Constants.PaymentStatus.PART_PAYMENT);
					break;
				}

			}
			return new ResponseEntity<>(payer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/fullPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fullPayment(@RequestParam(required = false) String payerId, Principal principal) {

		Payer payer = new Payer();
		try {
			if (payerId == null) {
				return new ResponseEntity<>("Please provide valid Payer Account id. ,Error", HttpStatus.OK);
			}

			PayerBO pay = payerRepository.findByAccountIdAndStatus(payerId, "CURRENT");

			if (pay == null) {
				return new ResponseEntity<>(
						"Payer Account id has dosen't match, Please provide valid Payer Account id. ,Error",
						HttpStatus.OK);
			}

			payer.setAadhaar(pay.getAadhaar());
			payer.setAddress(pay.getAddress());
			payer.setAlternativePhoneNumber(pay.getAlternativePhoneNumber());
			payer.setCity(pay.getCity());
			payer.setDob(DU.formatStr(pay.getDob()));
			payer.setEmail(pay.getEmail());
			payer.setFullName(pay.getFullName());
			payer.setGender(pay.getGender());
			payer.setPan(pay.getPan());
			payer.setParentName(pay.getParentName());
			payer.setPhoneNumber(pay.getPhoneNumber());
			payer.setState(pay.getState());
			payer.setSchemeName(pay.getPayerRateOfInterest().getSchemeName());

			if (pay != null && pay.getMerchantId().equalsIgnoreCase(principal.getName())) {

				List<PayerLedgerBO> payerLedgers = pay.getPayerRateOfInterest().getPayerLedgers().stream()
						.filter(c -> c.getPayStatus().equalsIgnoreCase("PENDING")).collect(Collectors.toList());

				for (PayerLedgerBO payerLedgerBO : payerLedgers) {
					payer.setLoanAmount(pay.getPayerRateOfInterest().getLoanAmount());
					payer.setLoanTenure(pay.getPayerRateOfInterest().getLoanTenure());
					payer.setRateOfInterset(payerLedgerBO.getRateOfInterset());
					payer.setInterset(payerLedgerBO.getInterset());
					payer.setPrincipal(payerLedgerBO.getPrincipal());
					payer.setGst(payerLedgerBO.getGst());
					payer.setGstOnPrincipal(payerLedgerBO.getGstOnPrincipal());
					payer.setTotalPayble(payerLedgerBO.getTotalPayble());
					payer.setPaybleEmi(payerLedgerBO.getPaybleEmi());
					payer.setEmiDate(DU.parse(DU.formatStr(payerLedgerBO.getEmiDate()), "dd-MM-yyyy"));
					payer.setPayStatus(Constants.PaymentStatus.FULL_PAYMENT);
					break;
				}

			}
			return new ResponseEntity<>(payer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage() + "  ERROR", HttpStatus.OK);
		}

	}

	public static String getMd5(String input) {
		try {
			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown" + " for incorrect algorithm: " + e);
			return null;
		}
	}

}
