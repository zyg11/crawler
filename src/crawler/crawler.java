package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class crawler {
	static String SendGet(String url) {
		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;

		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;

	}

	static String RegexString(String targetStr, String patternStr) {
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		if (matcher.find()) {
			// 打印出结果
			return matcher.group(0);
		}
		return "Nothing";
	}
	public static void splitDemo(String str,String reg)
	{
		String[] arr=str.split(reg);
		System.out.println(arr.length);
		for (String  s: arr) {
			System.out.println(s);
		}
		
	}
	public static void repalceAll(String str,String reg,String newStr)
	{
		str=str.replaceAll(reg,newStr);
		System.out.println(str);
	}
	public static void getDemo()
	{
		//获取
				String str5="ming tian jiu yao fang jia le,da jia.";
//				String str6="123456";
				
//				String reg="[0-9]\\d{4,14}";
				String reg="\\b[a-z]{4}\\b";
				//将规则封装成对象
				Pattern p=Pattern.compile(reg);
				//让正则对象和要作用的字符串相关联,获取匹配器对象 
				Matcher m=p.matcher(str5);
				
				// 打印结果
//				System.out.println(m.matches());//其实String类中的matches方法，用的就是Pattern和Matcher对象来完成时。
				//只不过被String的方法封装后，用起来较为简单。但是功能却单一。
//				boolean b=m.find();//将规则作用于字符串上，并进行符合规则的子串查找。
//				System.out.println(b);
//				System.out.println(m.group());
//				boolean b1=m.find();//将规则作用于字符串上，并进行符合规则的子串查找。
//				System.out.println(b1);
//				System.out.println(m.group());
				while (m.find()) {
					System.out.println(m.group());
					System.out.println(m.start()+"..."+m.end());
				}
	}
	public static void test1() {
		String str="我我...我我...我要..要要...要要...学学学...学学...编编编...编程..程..程程...程...程";
		/*
		 * 将已有字符串变成另一个字符串。使用替换功能。1、先去掉.2、将重复的内容变成单个内容*/
		 str=str.replaceAll("\\.+","");//替换点
		 System.out.println(str);
		 str=str.replaceAll("(.)\\1+","$1");//替换叠词
		 System.out.println(str);
	}
	/*192.68.1.254 102.49.23.013 10.10.10.10 2.2.2.2 8.109.90.30
	 * 将IP地址进行地址段排序
	 * 还按照字符串自然顺序，只要让它们每一段都是3位即可
	 *  1、按照每一段需要最多的0进行补齐那么每一段至少有三位
	 *  2、将每一段只保留3位，这样所有的IP地址都是每一段3位
	 * */
	public static void ipsort() {
		String ip="192.68.1.254 102.49.23.013 10.10.10.10 2.2.2.2 8.109.90.30";
		ip=ip.replaceAll("(\\d+)","00$1");
		System.out.println(ip);
		ip=ip.replaceAll("0*(\\d{3})","$1");
		System.out.println(ip);
		String [] arr=ip.split(".+");
		TreeSet<String> ts=new TreeSet<String>();
		for (String s : arr) {
			ts.add(s); 
		}
		for (String s : ts) {
			System.out.println(s.replaceAll("0*(\\d+)","$1"));
		}
	}
	/*对邮件地址进行校验
	 * 
	 * */
	public static void checkMail(){
		String mail="abc12@sina.com";
		String reg="[a-zA-Z0-9_]+@[a-zA-Z0-9]+[\\.[a-zA-Z]+]+";//较为精确的匹配
		reg="\\w+@\\w+[\\.+\\w+]+";//相对笼统的匹配
//		mail.indexOf("@")!=-1;只找有无@/太粗糙
		System.out.println(mail.matches(reg));
	}
	/*
	 * 获取指定文档中的邮件地址
	 * 使用获取功能，pattern  Matcher
	 * */
	public static void getMails() throws Exception{
		  File dir = new File("F:\\mail.txt");
		BufferedReader burf=new BufferedReader(new FileReader(dir));
		String line=null;
		String mailreg="\\w+@\\w+(\\.\\w+)+";
		Pattern p=Pattern.compile(mailreg);
		while ((line=burf.readLine())!=null) {
			Matcher m=p.matcher(line);
			while (m.find()) {
				System.out.println(m.group());
			}
		}
	}
	/*
	 * 获取网上中的邮件地址
	 * 使用获取功能，pattern  Matcher
	 * */
	public static void getMailsnet() throws Exception{
		URL url=new URL("http://127.0.0.1/mail.html");
		URLConnection conn=url.openConnection();
		BufferedReader butin=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line=null;
		String mailreg="\\w+@\\w+(\\.\\w+)+";
		Pattern p=Pattern.compile(mailreg);
		while ((line=butin.readLine())!=null) {
			Matcher m=p.matcher(line);
			while (m.find()) {
				System.out.println(m.group());
			}
		}
	}
	public static void main(String[] args) throws Exception {

		// 定义即将访问的链接
		String url = "http://www.baidu.com";
//		String url = "http://www.7160.com/";
		String result = SendGet(url);
		// 使用正则匹配图片的src内容
//		String imgSrc = RegexString(result, "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*");
//		String imgSrc = RegexString(result, "<title>.*?</title>");
//		String imgSrc = RegexString(result, "<img.*src=(.*?)>");
//		String imgSrc = RegexString(result, "<img.*src=(.*?)\\s*/>");
//		String imgSrc = RegexString(result, "<img.*src\\s*=\\s*(.*?)[^>]*?>");
//		String imgSrc = RegexString(result, "<img.*src=(.*?)\\s*/>");
		String imgSrc = RegexString(result, "src=\"?(.*?)(\"|>|\\s+)");
		//测试分割
		String str="zhangasan.lisi.wangwu";//用这个切割\.
		String str1="c:\\abc\\a.txt";//用这个切割\\\\
		String str2="erkkkkkktyqquizzo";//用这个切割叠词(.)\\1+;为了可以让规则的结果被重用，可以将规则封装成一个组。用
										//（）完成。组的出现都有编号。从1开始。想要使用已有的组可以通过\n（n是组的编号）的形式来获取					
//		String reg="(.)\\1+";
	
//		splitDemo(str2, reg);
		String str3="das566dsa456465sadasda";//将字符串中的数字替换为#
		repalceAll(str3, "\\d{5,}","#");
		String str4="eerraddddyyijnj";//将字符串中的叠词替换为#//将重叠的字符替换为单个字符，zzzz->z
		repalceAll(str4, "(.)\\1+","$1");
		//获取
//		getDemo();
//		test1();
//		ipsort();
//		checkMail();
//		getMails();
		getMailsnet();
		System.out.println(imgSrc);
	}
}
