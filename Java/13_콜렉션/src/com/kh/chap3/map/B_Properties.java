package com.kh.chap3.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class B_Properties {
	
	public void method1() {
		Properties prop = new Properties();
		
		// Key, Value 쌍으로 저장할 때 순서 유지는 안되고 키 값은 중복되지 않음
		prop.setProperty("List", "ArrayList");
		prop.setProperty("Set", "HashSet");
		prop.setProperty("Map", "HashMap");
		
		System.out.println(prop);
		
		prop.setProperty("Map", "Properties");
		
		System.out.println(prop);
		System.err.println();
		
		// Value값 읽어오기
		System.out.println(prop.getProperty("Set"));
		System.out.println();
		
		Set<Entry<Object, Object>> entrySet = prop.entrySet();
		Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
	
		while(iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			
			System.out.println("key : " + key);
			System.out.println("value : " + value);
		}
	
		try {
			// 파일들이 자바 프로젝트 밑에 생기게됩니다
//			prop.store(new FileOutputStream("test1.properties"), "Properties1"); // 바이트 기반 
//			prop.store(new FileWriter("test2.properties"), "Properties2"); // 문자 기반
			prop.storeToXML(new FileOutputStream("test.xml"), "xml");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void method2() {
		Properties prop = new Properties();
		
		try {
//			prop.load(new FileInputStream("test1.properties")); // 바이트 기반
//			prop.load(new FileReader("test1.properties")); // 문자 기반
			prop.loadFromXML(new FileInputStream("test.xml"));
			
			System.out.println(prop);
			System.out.println(prop.getProperty("Set"));
			System.out.println(prop.getProperty("List"));
			System.out.println(prop.getProperty("Map"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
