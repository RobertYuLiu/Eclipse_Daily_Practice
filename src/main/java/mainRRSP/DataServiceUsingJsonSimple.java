package mainRRSP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//@Default
@Alternative
public class DataServiceUsingJsonSimple implements DataService {

	// see readMe for learning note ...
	public void createJSONFile() {
		InputParam input = createInputObject();

		// Method 1: JSONValue.toJSONString(taxInput). It takes map object and change it
		// to the Json style string
		Map<String, Double> taxInput = new HashMap<>();
		taxInput.put("canadianIncome", 2.0);
		taxInput.put("canadianTaxPaid", 2.0);
		taxInput.put("usIncome", 2.0);
		taxInput.put("usTaxPaid", 2.0);
		taxInput.put("yearlyAverageExchangeRate", 2.0);
		taxInput.put("amountRRSP", 2.0);
		String json1 = JSONValue.toJSONString(taxInput);
        
        
        JSONObject employeeDetails = new JSONObject();
//        employeeDetails.put("firstName", "Lokesh");
         
        JSONObject employeeObject = new JSONObject(); 
//        employeeObject.put("employee", employeeDetails);
         
        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
         
        JSONObject employeeObject2 = new JSONObject(); 
//        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(input);
        
//        employeeList.add(employeeObject);
//        employeeList.add(employeeObject2);
        
         
        //Write JSON file
        try (FileWriter file = new FileWriter("fileTaxReturnData.json")) {
 
            file.write(jsonArray.toJSONString());
//            file.write(employeeDetails2.toJSONString());
            file.write(json1);
//            file.write(json2);
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@SuppressWarnings("unchecked")
	public InputParam readDataFromJSONFile() {
		InputParam input = new InputParam();
		
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("fileTaxReturnData.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return input;
    }
 
    private void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");    
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");  
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");    
        System.out.println(website);
    }
}
