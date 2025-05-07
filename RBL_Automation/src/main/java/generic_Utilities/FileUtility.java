package generic_Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	/**
	 * used to read the data from propertied file based on key
	 * 
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromPropertiesFile(String key) throws Throwable {

		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String data = pObj.getProperty(key);

		return data;
	}
	
	public void writeToProperties(String key, String value) throws Throwable {

		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		pObj.put(key, value);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("./configAppData/commondata.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			pObj.store(fos, "Updated");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
