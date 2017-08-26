package com.gobravery.wind.ws;

import java.util.List;

import com.gobravery.wind.common.AppConfigPropertyiesUtils;

public class ParseKeyword {
	private static final String reg = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";
	private static RegExp re = new RegExp();

	public static List<String> getKeywords(String p) {
		List<String> list = re.find(reg, p);
		return list;
	}

	public static String getSysConf(String key) {
		AppConfigPropertyiesUtils au=new AppConfigPropertyiesUtils();
		return au.get(key).toString();
	}

	public static String parseValue(String str, String key, String val) {
		str = str.replace("${" + key + "}", val);
		return str;
	}

	public static String parseValue(String paramUrl) {
		List<String> keys = getKeywords(paramUrl);
		for (String key : keys) {
			String val = getSysConf(key);
			paramUrl = parseValue(paramUrl, key, val);
		}
		return paramUrl;

	}

	public static void main(String[] args) {
		System.out.println(ParseKeyword.parseValue("${app.ws.ebs.url}/asdlfelks"));
	}
}
