package com.xtt.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
/*
 利用Runtime call操作系统的命令，具体的命令取决于不同的操作系统，注意不要调用Runtime.getRuntime().exec(String)接口，要用Runtime.getRuntime().exec(String[])这个接口，不然复杂命令的执行会有问题。例子如下（拿cpu个数，其他类似）：
 定义命令：
 WindowsCmd ="cmd.exe /c echo %NUMBER_OF_PROCESSORS%";//windows的特殊
 SolarisCmd = {"/bin/sh", "-c", "/usr/sbin/psrinfo | wc -l"};
 AIXCmd = {"/bin/sh", "-c", "/usr/sbin/lsdev -Cc processor | wc -l"};
 HPUXCmd = {"/bin/sh", "-c", "echo /"map/" | /usr/sbin/cstm | grep CPU | wc -l "};
 LinuxCmd = {"/bin/sh", "-c", "cat /proc/cpuinfo | grep ^process | wc -l"};

 然后判断系统：
 os = System.getProperty("os.name").toLowerCase();

 根据不同的操作系统call不同的命令。
 */
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GetMACAddress {
	private static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b).toUpperCase();
		return s.substring(s.length() - 2);
	}

	private static String[] getMac() throws SocketException {
		Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
		List<String> rs = new ArrayList<String>();
		while (el.hasMoreElements()) {
			byte[] mac = el.nextElement().getHardwareAddress();
			if (mac == null)
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

	public static void main(String[] args) throws Exception {
		for (String macAddress : getMac())
			System.out.printf("Mac:%s\r\n", macAddress);
	}

	/**
	 * 获取本机Ip地址
	 * 
	 * @Title: getLocalIp
	 * @return
	 *
	 */
	public static String getLocalIp() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() && !ip.isLinkLocalAddress() && ip.isSiteLocalAddress()) {
						return ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}
}