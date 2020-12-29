package mainRRSP;

public interface DataService {

	public void createJSONFile();

	public InputParam readDataFromJSONFile();

	default public InputParam createInputObject() {
		InputParam input = new InputParam();
		input.setCanadianIncome(2);
		input.setUsIncome(0);
		input.setCanadianTaxPaid(0);
		input.setUsTaxPaid(0);
		input.setYearlyAverageExchangeRate(0);
		input.setAmountRRSP(0);
		return input;
	}
}
