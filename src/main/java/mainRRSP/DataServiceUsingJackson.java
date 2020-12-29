package mainRRSP;

import java.io.File;
import java.io.IOException;

import javax.enterprise.inject.Default;

import org.codehaus.jackson.map.ObjectMapper;

//@Alternative
@Default
public class DataServiceUsingJackson implements DataService {

	@Override
	public void createJSONFile() {
		System.out.println("using Jackson to write ... ");
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("fileTaxReturnData.json");
		InputParam input = createInputObject();
		try {
			mapper.writeValue(file, input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputParam readDataFromJSONFile() {
		System.out.println("using Jackson to read ... ");
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("fileTaxReturnData.json");
		InputParam input = new InputParam();
		try {
			input = objectMapper.readValue(file, InputParam.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

}
