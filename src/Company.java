

public class Company {
	private String cID;
	private String companyName;
	private int abn;
	private String url;
	private String address;

	public Company(String cID, String companyName, int abn, String url, String address) {
		this.cID = cID;
		this.companyName = companyName;
		this.abn = abn;
		this.url = url;
		this.address = address;
	}

	public String getCID() {
		return cID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getAbn() {
		return abn;
	}

	public String getUrl() {
		return url;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return cID + ";" + companyName + ";" + abn + ";" + url + ";" + address;
	}

}
