/**
 * 
 */
package com.aurora.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.aurora.service.ReadPropertyFile;

@Service("readPropertyFile")
public class ReadPropertyFileImpl implements ReadPropertyFile {
	Map<String, String> map;
	
	public Map<String, String> getCountryTimeMap() {
		map =  new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;

		try{
			String filename = "timeCountryMapping.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}

			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				map.put(key, value);
				//System.out.println("Key : " + key + ", Value : " + value);
			}
		} catch(IOException ex){
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return map;
	}

}
