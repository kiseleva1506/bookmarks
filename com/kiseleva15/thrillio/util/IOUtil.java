package com.kiseleva15.thrillio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOUtil {

	public static void read(String[] data, String filename) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				data[count] = line;
				count++;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(InputStream in) {
		StringBuilder builder = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static void write(String webpage, long id) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("/home/mary/eclipse-workspace/thrillio/files/" + String.valueOf(id) + ".html"),
				"UTF-8"))) {
			writer.write(webpage);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
