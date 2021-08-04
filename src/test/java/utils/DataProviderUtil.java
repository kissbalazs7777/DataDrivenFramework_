package utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.TestBase;

public class DataProviderUtil extends TestBase{

	@DataProvider(name="dataProvider")
	public Object[][] getData(Method method) {
		Object[][] data = new Object[excelReader.getRowNumbers(method.getName())][excelReader.getColNumbers(method.getName(),
				1)];
		for (int i = 0; i < excelReader.getRowNumbers(method.getName()); i++) {
			for (int j = 0; j < excelReader.getColNumbers(method.getName(), i); j++) {
				data[i][j] = excelReader.getCellData(method.getName(), i+1, j);
			}
		}
		return data;

	}

}
