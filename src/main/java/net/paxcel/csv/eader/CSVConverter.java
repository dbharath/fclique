package net.paxcel.csv.eader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class CSVConverter {

	public static List<Map<String, String>> readCSVToMap(FileReader reader){
		CsvMapReader beanReader = new CsvMapReader(reader, CsvPreference.STANDARD_PREFERENCE);	
		List<Map<String, String>> records = new ArrayList<Map<String, String>>();
		try {
			String []  header = beanReader.getHeader(true);
			Map<String, String> beanObject = null;			
			while((beanObject = beanReader.read(header)) != null){
				records.add(beanObject);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				beanReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return records;
		
	}
	public static List<Map<String, String>> readCSVToMap(InputStream stream){
		InputStreamReader reader = new InputStreamReader(stream);
		CsvMapReader beanReader = new CsvMapReader(reader, CsvPreference.STANDARD_PREFERENCE);	
		List<Map<String, String>> records = new ArrayList<Map<String, String>>();
		try {
			String []  header = beanReader.getHeader(true);
			Map<String, String> beanObject = null;			
			while((beanObject = beanReader.read(header)) != null){
				records.add(beanObject);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				beanReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return records;
		
		
	}
}
