package com.xtt.platform.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

/**
 * @Title: MachineParameter.java
 * @Package Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年6月30日 上午9:57:11
 *
 */

public class HardwareUtil {

	public static String getProcessorId() throws IOException {
		Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		String property = sc.next();
		String serial = sc.next();
		String para = property + ": " + serial;
		sc.close();
		return para;
	}

	public static String getDiskSerialNumber(String drive) {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n"
							+ "Set objDrive = colDrives.item(\"" + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber"; // see note
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	private static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b).toUpperCase();
		return s.substring(s.length() - 2);
	}

	private static String[] getMac() throws SocketException {
		Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
		List<String> rs = new ArrayList<String>();
		while (el.hasMoreElements()) {
			byte[] mac = el.nextElement().getHardwareAddress();
			if (mac == null || mac.length == 0)
				continue;
			StringBuilder builder = new StringBuilder();
			for (byte b : mac) {
				builder.append(hexByte(b));
				builder.append('-');
			}
			rs.add(builder.deleteCharAt(builder.length() - 1).toString());
		}
		return rs.toArray(new String[] {});
	}

	public static String getMotherboardSN() {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);
			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n" + "Set colItems = objWMIService.ExecQuery _ \n"
							+ "   (\"Select * from Win32_BaseBoard\") \n" + "For Each objItem in colItems \n"
							+ "    Wscript.Echo objItem.SerialNumber \n" + "    exit for  ' do the first cpu only! \n" + "Next \n";
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result += line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

	public static void main(String[] args) throws IOException {

		System.out.println("CPU SN:\t" + getProcessorId());

		System.out.println("硬盘 SN:\t" + getDiskSerialNumber("C"));

		System.out.println("主板 SN:\t" + getMotherboardSN());

		for (String macAddress : getMac())
			System.out.printf("Mac:\t%s\r\n", macAddress);
	}

}
