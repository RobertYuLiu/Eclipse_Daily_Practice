package mainRRSP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.enterprise.inject.Alternative;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

//@Named
//@ApplicationScoped
@Alternative
//@Default
public class DataServiceUsingGSON implements DataService {

	@Override
	public void createJSONFile() {
		System.out.println("using Gson to write ... ");
		// pretty print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		InputParam input = createInputObject();
		// Java objects to String
		String json = gson.toJson(input);
		System.out.println(json);
		// Java objects to File
		try (FileWriter writer = new FileWriter("fileTaxReturnData.json")) {
			gson.toJson(input, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputParam readDataFromJSONFile() {
		System.out.println("using Gson to read ... ");
		Gson gson = new Gson();
		InputParam input = new InputParam();
		try {
			input = gson.fromJson(new FileReader("fileTaxReturnData.json"), InputParam.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return input;
	}

}
