package com.sample.github.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HTTPUtils {
	private final String DEBUG_TAG = getClass().getSimpleName().toString();
	private final static String ACCESS_TOKEN = "58749e3006eb6f5b5e2ccd1bede67a305199f0c8";
	
	public static String downloadUrl(String myurl) throws IOException {
		InputStream is = null;

		try {
			
			myurl += "?access_token=" + ACCESS_TOKEN;
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			conn.connect();
			int response = conn.getResponseCode();
			is = conn.getInputStream();

			String content = readInputStream(is);

			return content;
			// Makes sure that the InputStream is closed after the app is
			// finished using it.
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static String readInputStream(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		String read = null;
		try {
			read = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (read != null) {
			sb.append(read);
			try {
				read = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
}
