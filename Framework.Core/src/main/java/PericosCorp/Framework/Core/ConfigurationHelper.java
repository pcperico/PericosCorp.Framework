package PericosCorp.Framework.Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHelper {
	public static String GetLogPaths()
	{		
		return GetConfigsProperties().getProperty("LogsPath");
	}
	
	public static Properties GetConfigsProperties()
	{
		try {
			File file = new File("AppConfigurations.xml");
			System.out.println(file.getAbsolutePath());
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.loadFromXML(fileInput);
			fileInput.close();
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
}
