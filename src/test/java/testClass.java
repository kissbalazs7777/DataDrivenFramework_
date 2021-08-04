import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import utils.ExcelReader;

public class testClass {
	public static void main(String[] args) throws IOException {
		/*ArrayList<String> list = new ArrayList<String>();
		ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
		for (int i = 0; i < excelReader.getRowNumbers("unsuccessfulLogin"); i++) {
			for (int j = 0; j < excelReader.getColNumbers("unsuccessfulLogin", i); j++) {
				 list.add(excelReader.getCellData("unsuccessfulLogin", i+1, j));
			}
		}
		System.out.println(list.get(list.size()-1));
		System.out.println(excelReader.getCellData(0, 1, 0));*/
		
		URL url = new URL("https://kissbalazstest.000webhostapp.com/blog/asd123456789.html");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();
		System.out.print(code);
		
	}
	
}
