package mainRRSP;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

//@RequestScoped
//@Named(value="service")
public class Service {
	
	@Inject
	private DataService dataService;
	
	
	@PostConstruct
	public void init() {
//	    System.out.println("mmm");
//	    dataService.createJSONFile();
	}
	
	public String test_writeJsonToFile() {
		dataService.createJSONFile();
		return "Write Succeed";
	}
	
	public InputParam loadIncomeTaxDataFromJSONFile() {
		return dataService.readDataFromJSONFile();
	}
	
	public double getNetIncome(InputParam input) {
		double canadianIncome = input.getCanadianIncome();
		double usIncome = input.getUsIncome() * input.getYearlyAverageExchangeRate();
		double amountRRSP = input.getAmountRRSP();
		double otherSmallDeduction = 238;
		double netIncome = canadianIncome + usIncome - amountRRSP - otherSmallDeduction;
		return netIncome;
	}
	
	public double calculateFederalNonRefundableTaxCredit() {
		double personalAmount = 11809;
		double spouseAmount = 11809;
		double tuitionFromChild = (double) 3902.86;
		double otherAmount = 0;
		double creditAmount = (double) ((personalAmount + spouseAmount + tuitionFromChild + otherAmount) * 0.15);
		return creditAmount;
	}

	public double calculateOntarioNonRefundableTaxCredit() {
		double personalAmount = 10354;
		double spouseAmount = 8792;
		double creditAmount = (double) ((personalAmount + spouseAmount) * 0.0505);
		return creditAmount;
	}
	
	public double getFederalTax(double taxableIncome) {
		double percentage1 = (double) 0.15;
		double income1 = 48535;
		double tax1 = income1 * percentage1;
		double percentage2 = (double) 0.205;
		double income2 = 97069;
		double tax2 = (income2 - income1) * percentage2 + tax1;
		double percentage3 = (double) 0.26;
		double income3 = 150473;
		double tax3 = (income3 - income2) * percentage3 + tax2;
		double percentage4 = (double) 0.29;
		double income4 = 214368;
		double tax4 = (income4 - income3) * percentage4 + tax3;
		double percentage5 = (double) 0.33;
		double federalTax = 0;
		if (taxableIncome <= income1) {
			federalTax = taxableIncome * percentage1;
		} else if (income1 < taxableIncome && taxableIncome <= income2) {
			federalTax = (taxableIncome - income1) * percentage2 + tax1;
		} else if (income2 < taxableIncome && taxableIncome <= income3) {
			federalTax = (taxableIncome - income2) * percentage3 + tax2;
		} else if (income3 < taxableIncome && taxableIncome <= income4) {
			federalTax = (taxableIncome - income3) * percentage4 + tax3;
		} else {
			federalTax = (taxableIncome - income4) * percentage5 + tax4;
		}
//		System.out.println("taxableIncome: " + taxableIncome + ", federalTax: " + federalTax);
		return federalTax;
	}
	
	public double getProvincialTax(double taxableIncome) {
		double percentage1 = (double) 0.0505;
		double income1 = 44740;
		double tax1 = income1 * percentage1;
		double percentage2 = (double) 0.0915;
		double income2 = 89482;
		double tax2 = (income2 - income1) * percentage2 + tax1;
		double percentage3 = (double) 0.1116;
		double income3 = 150000;
		double tax3 = (income3 - income2) * percentage3 + tax2;
		double percentage4 = (double) 0.1216;
		double income4 = 220000;
		double tax4 = (income4 - income3) * percentage4 + tax3;
		double percentage5 = (double) 0.1316;
		double provincialTax = 0;
		if (taxableIncome < income1) {
			provincialTax = taxableIncome * percentage1;
		} else if (income1 < taxableIncome && taxableIncome < income2) {
			provincialTax = (taxableIncome - income1) * percentage2 + tax1;
		} else if (income2 < taxableIncome && taxableIncome < income3) {
			provincialTax = (taxableIncome - income2) * percentage3 + tax2;
		} else if (income3 < taxableIncome && taxableIncome < income4) {
			provincialTax = (taxableIncome - income3) * percentage4 + tax3;
		} else {
			provincialTax = (taxableIncome - income4) * percentage5 + tax4;
		}
//		System.out.println("taxableIncome: " + taxableIncome + ", provincialTax: " + provincialTax);
		return provincialTax;
	}
	
	public double getFederalForeignTaxCredit_T2209(InputParam input) {
		double usTaxPaid = calculate_T2209_Line1(input);
		double taxRequired = calculate_T2209_Line2(input) ;
		double taxCredit = (usTaxPaid < taxRequired) ? usTaxPaid : taxRequired;
		return taxCredit;
	}

	private double calculate_T2209_Line1(InputParam input) {
		return input.getUsTaxPaid() * input.getYearlyAverageExchangeRate();
	}
	
	public double calculate_T2209_Line2(InputParam input) {
		/*
		 * Note 1: Any amount of tax paid to a foreign government in excess of the
		 * amount required to be paid, according to a tax treaty, is considered a
		 * voluntary contribution and does not qualify as foreign taxes paid.
		 */
		double usIncome = input.getUsIncome() * input.getYearlyAverageExchangeRate();
		double netIncome = this.getNetIncome(input);
		double line2 = getFederalTax(netIncome) - calculateFederalNonRefundableTaxCredit();
		if (usIncome < netIncome) {
			line2 = line2 * usIncome / netIncome;
		}
		return line2;
	}

	public double getOntarioForeignTaxCredit_T2036(InputParam input) {
		double line1_T2036 = calculate_T2209_Line1(input);
		double line2_T2036 = getFederalForeignTaxCredit_T2209(input);
		double line3_T2036 = line1_T2036 - line2_T2036;
		double line4_T2036 = calculate_T2036_Line4(input);
		double line5_T2036 = (line3_T2036 < line4_T2036) ? line3_T2036 : line4_T2036;
		return line5_T2036;
	}
	
	public double calculate_T2036_Line4(InputParam input) {
		/*
		 * Note 1: Any amount of tax paid to a foreign government in excess of the amount
		 * required to be paid, according to a tax treaty, is considered a voluntary
		 * contribution and does not qualify as foreign taxes paid.
		 */
		double usIncome = input.getUsIncome() * input.getYearlyAverageExchangeRate();
		double netIncome = this.getNetIncome(input);
		double line2 = getProvincialTax(netIncome) - calculateOntarioNonRefundableTaxCredit();
		if (usIncome < netIncome) {
			line2 = line2 * usIncome / netIncome;
		}
		return line2;
	}

}










