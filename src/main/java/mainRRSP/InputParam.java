package mainRRSP;

public class InputParam {

	private double canadianIncome;
	private double canadianTaxPaid;
	private double usIncome;
	private double usTaxPaid;
	private double yearlyAverageExchangeRate;
	private double amountRRSP;

	public double getCanadianIncome() {
		return canadianIncome;
	}

	public InputParam(double canadianIncome, double canadianTaxPaid, double usIncome, double usTaxPaid,
			double yearlyAverageExchangeRate) {
		super();
		this.canadianIncome = canadianIncome;
		this.canadianTaxPaid = canadianTaxPaid;
		this.usIncome = usIncome;
		this.usTaxPaid = usTaxPaid;
		this.yearlyAverageExchangeRate = yearlyAverageExchangeRate;
	}

	public InputParam() {
		// TODO Auto-generated constructor stub
	}

	public void setCanadianIncome(double canadianIncome) {
		this.canadianIncome = canadianIncome;
	}

	public double getCanadianTaxPaid() {
		return canadianTaxPaid;
	}

	public void setCanadianTaxPaid(double canadianTaxPaid) {
		this.canadianTaxPaid = canadianTaxPaid;
	}

	public double getUsIncome() {
		return usIncome;
	}

	public void setUsIncome(double usIncome) {
		this.usIncome = usIncome;
	}

	public double getUsTaxPaid() {
		return usTaxPaid;
	}

	public void setUsTaxPaid(double usTaxPaid) {
		this.usTaxPaid = usTaxPaid;
	}

	public double getYearlyAverageExchangeRate() {
		return yearlyAverageExchangeRate;
	}

	public void setYearlyAverageExchangeRate(double yearlyAverageExchangeRate) {
		this.yearlyAverageExchangeRate = yearlyAverageExchangeRate;
	}

	public double getAmountRRSP() {
		return amountRRSP;
	}

	public void setAmountRRSP(double amountRRSP) {
		this.amountRRSP = amountRRSP;
	}

}
