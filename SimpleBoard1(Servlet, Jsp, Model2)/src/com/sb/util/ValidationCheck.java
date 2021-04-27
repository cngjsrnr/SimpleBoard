package com.sb.util;

public class ValidationCheck {
	
	public static int NotNumberToOne(String str) {
		if(isNumber(str)) {
			return Integer.parseInt(str);
		}
		return 1;
	}

	public static boolean isNumber(String str) { // 1a3
		boolean flag = true;
		if(str != null && !str.trim().isEmpty()) {
			for(int i=0;i<str.length();i++) {
				int x = str.charAt(i);
				if(x < 48 || x > 57) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
	
}
