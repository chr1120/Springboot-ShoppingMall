package com.chr.ourhome.util;

public class Script {
	
	public static String back(String msg) {
		StringBuffer sb=new StringBuffer();
		sb.append("<scrpit>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
}
