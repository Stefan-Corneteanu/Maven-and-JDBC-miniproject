public class Share {
	
	private double value;
	private Company company;
	
	public Share(double value, Company company) {
		super();
		this.value = value;
		this.company = company;
	}

	public double getValue() {
		return value;
	}

	public Company getCompany() {
		return company;
	}
	
}