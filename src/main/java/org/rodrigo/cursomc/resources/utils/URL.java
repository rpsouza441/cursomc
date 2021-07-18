package org.rodrigo.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String string) {
//		String[] s = string.split(",");
//		List<Integer> list = new ArrayList<>();
//		for (int i = 0; i < s.length; i++) {
//			Integer.parseInt(s[i]);
//		}
//		return list;

		return Arrays.asList(string.split(",")).stream().map(obj -> Integer.parseInt(obj)).collect(Collectors.toList());
	}

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
