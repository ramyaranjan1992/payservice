package payer.restservices.dto;

public class User {

	private String merchant;
	private String pwd;
	private String token;

	private String errorMsg;
	private String response;

	/*public User(String mchant, String pasword,String response, String msg) {
		this.merchant = mchant;
		this.pwd = pasword;
		this.response = response;
		this.errorMsg = msg;
	
		
	}*/

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
