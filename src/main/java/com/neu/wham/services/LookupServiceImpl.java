package com.neu.wham.services;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class LookupServiceImpl implements LookupService {
	
	Map<String, String> abbvSource;
	
	LookupServiceImpl(){
		abbvSource = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		
		abbvSource.put("Behrakis Health Sciences Center", "30 Leon St");
		abbvSource.put("BK", "30 Leon St");
		
		abbvSource.put("Cargill Hall", "45 Forsyth St");
		abbvSource.put("CG", "45 Forsyth St");
		
		abbvSource.put("Dockser Hall", "65 Forsyth St");
		abbvSource.put("DK", "65 Forsyth St");
		
		abbvSource.put("Holmes Hall", "39-41 Leon St");
		abbvSource.put("HO", "39-41 Leon St");
		
		abbvSource.put("John D. O'Bryant African-American Institute", "40 Leon St");
		abbvSource.put("AF", "40 Leon St");
		
		abbvSource.put("Kariotis Hall", "55 Forsyth St");
		abbvSource.put("KA", "55 Forsyth St");
		
		abbvSource.put("Knowles Center", "416 Huntington Ave");
		abbvSource.put("KN", "416 Huntington Ave");
		
		abbvSource.put("Lake Hall", "43 Leon St");
		abbvSource.put("LA", "43 Leon St");
		
		abbvSource.put("Latino/a Student Cultural Center", "104 Forsyth St");
		abbvSource.put("LC", "104 Forsyth St");
		
		abbvSource.put("Meserve Hall", "35-37 Leon St");
		abbvSource.put("ME", "35-37 Leon St");
		
		abbvSource.put("Nightingale Hall", "105-107 Forsyth St");
		abbvSource.put("NI", "105-107 Forsyth St");
		
		abbvSource.put("Shillman Hall", "115 Forsyth St");
		abbvSource.put("SH", "115 Forsyth St");
		
		abbvSource.put("Stearns Center", "420 Huntington Ave");
		abbvSource.put("ST", "420 Huntington Ave");
		
		abbvSource.put("West Village F", "40A Leon St");
		abbvSource.put("WVF", "40A Leon St");
		
		abbvSource.put("West Village G", "450 Park St");
		abbvSource.put("WVG", "450 Park St");
		
		abbvSource.put("West Village H", "440 Huntington Ave");
		abbvSource.put("WVH", "440 Huntington Ave");
		
		abbvSource.put("Burstein Hall", "458 Huntington Ave");
		abbvSource.put("BU", "458 Huntington Ave");
		
		abbvSource.put("Rubenstein Hall", "464 Huntington Ave");
		abbvSource.put("464", "464 Huntington Ave");
		
		abbvSource.put("West Village Residence Complex A", "500 Parker St");
		abbvSource.put("WVA", "500 Parker St");
		
		abbvSource.put("West Village Residence Complex B", "460 Parker St");
		abbvSource.put("WVB", "460 Parker St");
		
		abbvSource.put("West Village Residence Complex C", "480 Parker St");
		abbvSource.put("WVC", "480 Parker St");
		
		abbvSource.put("West Village Residence Complex E", "10 Leon St");
		abbvSource.put("WVE", "10 Leon St");
		
		abbvSource.put("White Hall", "21 Forsyth St");
		abbvSource.put("WH", "21 Forsyth St");
		
		abbvSource.put("Willis Hall", "50 Leon St");
		abbvSource.put("WI", "50 Leon St");
		
		abbvSource.put("407", "407 Huntington Ave");
		
		abbvSource.put("Ryder Hall", "11 Leon St");
		abbvSource.put("RY", "11 Leon St");
		
		abbvSource.put("Snell Engineering Center", "110 Forsyth St");
		abbvSource.put("SN", "110 Forsyth St");
		
		abbvSource.put("Snell Library", "376 Huntington Ave");
		
		abbvSource.put("YMCA ", "316 Huntington Ave");
		
		abbvSource.put("Marino Recreation Center", "359-369 Huntington Ave");
		abbvSource.put("MC", "359-369 Huntington Ave");
		
		abbvSource.put("Curry Student Center", "346 Huntington Ave");
		abbvSource.put("CSC", "346 Huntington Ave");
		
		abbvSource.put("Egan Engineering/Science Research Center", "120 Forsyth St");
		abbvSource.put("EC", "120 Forsyth St");
		
		abbvSource.put("Raytheon Amphitheater", "120 Forsyth St");
		
		abbvSource.put("Fenway Center", "77 St. Stephens St");
		abbvSource.put("FC", "77 St. Stephens St");
		
		abbvSource.put("Stearns Center", "420 Huntington Ave");
		abbvSource.put("ST", "420 Huntington Ave");
	}

	@Override
	public String lookup(String location) {
		// TODO Auto-generated method stub
		if(abbvSource.containsKey(location)){
			return (abbvSource.get(location) + ", Boston, MA");
		}
		
		return "error";
	}
	
}
