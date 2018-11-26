package com.ms.taojin.monitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.util.StringTokenizer;

import com.ms.taojin.common.utils.StringUtils;
import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("restriction")
public class RuntimeTest {

	public String host;/* 主机监控地址 */

	public String hostName;
	public String hostIP;

	public String name;/* 主机监控名称 */

	public int port;/* 主机监控端口 */
	public int AllHDMeasure;/* 磁盘总空间 */

	private static final int CPUTIME = 30;

	private static final int PERCENT = 100;

	private static final int FAULTLENGTH = 10;

	private static String linuxVersion = null;

	public static void main(String[] args) {
		OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		long totalMemory = osmb.getTotalPhysicalMemorySize();
		long userMemory = osmb.getFreePhysicalMemorySize();
		System.out.println("物理内存总计：" + totalMemory / 1024 / 1024 + "MB");
		System.out.println("可用内存：" + userMemory / 1024 / 1024 + "MB");
		System.out.println("内存使用率：" + userMemory * 100 / totalMemory + "%");

		System.out.println("物理CPU总计：" + osmb.getProcessCpuTime());
		System.out.println("CPU使用率" + getCpuRatio() + "%");

		File[] roots = File.listRoots();

		for (File file : roots) {
			System.out.println(file.getPath() + "信息如下:");
			System.out.println("空闲未使用 = " + file.getFreeSpace() / 1024 / 1024 / 1024 + "G");// 空闲空间
			System.out.println("已经使用 = " + file.getUsableSpace() / 1024 / 1024 / 1024 + "G");// 可用空间
			System.out.println("总容量 = " + file.getTotalSpace() / 1024 / 1024 / 1024 + "G");// 总空间
			System.out.println();
		}

		System.out.println();

	}

	public static String getCpuRatio() {
		// 操作系统
		String osName = System.getProperty("os.name");
		double cpuRatio = 0;
		if (osName.toLowerCase().startsWith("windows")) {
			// 获取windows操作系统图的cpu使用率。
			cpuRatio = getCpuRatioForWindows();

		} else {
			// 获取Linux操作系统图的cpu使用率。
			cpuRatio = getCpuRateForLinux();
		}
		String str = cpuRatio + "";
		if (str.endsWith(".0")) {
			return str.replaceAll(".0", "");
		}

		if (str.endsWith(".00")) {
			return str.replaceAll(".00", "");
		}
		return str;
	}

	/**
	 * 读取Linux下面的cpu信息。
	 * 
	 * @return
	 */
	private static double getCpuRateForLinux() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader brStat = null;
		StringTokenizer tokenStat = null;
		try {
			System.out.println("Get usage rate of CUP , linux version: " + linuxVersion);

			Process process = Runtime.getRuntime().exec("top -b -n 1");
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);

			if (linuxVersion.equals("2.4")) {
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				String user = tokenStat.nextToken();
				tokenStat.nextToken();
				String system = tokenStat.nextToken();
				tokenStat.nextToken();
				String nice = tokenStat.nextToken();

				System.out.println(user + " , " + system + " , " + nice);

				user = user.substring(0, user.indexOf("%"));
				system = system.substring(0, system.indexOf("%"));
				nice = nice.substring(0, nice.indexOf("%"));

				float userUsage = new Float(user).floatValue();
				float systemUsage = new Float(system).floatValue();
				float niceUsage = new Float(nice).floatValue();

				return (userUsage + systemUsage + niceUsage) / 100;
			} else {
				brStat.readLine();
				brStat.readLine();

				tokenStat = new StringTokenizer(brStat.readLine());
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				tokenStat.nextToken();
				String cpuUsage = tokenStat.nextToken();

				System.out.println("CPU idle : " + cpuUsage);
				Float usage = new Float(cpuUsage.substring(0, cpuUsage.indexOf("%")));

				return (1 - usage.floatValue() / 100);
			}

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			freeResource(is, isr, brStat);
			return 1;
		} finally {
			freeResource(is, isr, brStat);
		}

	}

	private static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br) {
		try {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * 获得CPU使用率.
	 * 
	 * @return 返回cpu使用率
	 * @author fengbo 2012-02-27
	 */
	private static double getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"
			        + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// 取进程信息
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(PERCENT * (busytime) / (busytime + idletime)).doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

	/**
	 * 
	 * 读取CPU信息.
	 * 
	 * @param proc
	 * @author fengbo 2012-02-23
	 */
	private static long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < FAULTLENGTH) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				String caption = StringUtils.subString(line, capidx, cmdidx - 1).trim();
				String cmd = StringUtils.subString(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				if (caption.equals("System Idle Process") || caption.equals("System")) {
					idletime += Long.valueOf(StringUtils.subString(line, kmtidx, rocidx - 1).trim()).longValue();
					idletime += Long.valueOf(StringUtils.subString(line, umtidx, wocidx - 1).trim()).longValue();
					continue;
				}

				kneltime += Long.valueOf(StringUtils.subString(line, kmtidx, rocidx - 1).trim()).longValue();
				usertime += Long.valueOf(StringUtils.subString(line, umtidx, wocidx - 1).trim()).longValue();
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}