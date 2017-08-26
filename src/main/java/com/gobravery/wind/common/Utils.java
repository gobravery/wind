package com.gobravery.wind.common;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	public String out(){
		System.out.println("my is utils");
		return "my name is utils";
	}
	public static double get2Double(double f){
		BigDecimal   b   =   new   BigDecimal(f);  
		double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		return f1;
	}
}
