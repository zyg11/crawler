package crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class crawler1 {

	
	static String sendGet(String  url) {
		
		//定义一个字符串来存储网页内容
		String result="";
		String result1 = null;
		//定义一个缓冲字符输入流
		BufferedReader in=null;
		try {
			//将string转成url对象
			URL realURL=new URL(url);
//			String realURL=URLEncoder.encode(realURL, "utf-8");//关键在这里可以将URL转成string以"utf-8"的格式
			//初始化一个链接到url
			URLConnection urlConnection=realURL.openConnection();
			//开始实际的链接
			urlConnection.connect();
			//初始化Bufferreader输入流来读取URL的响应
			in=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			//用来临时存储抓取到的数据
			String line;
			while((line=in.readLine())!=null){
				//遍历抓取到的每一行将其存储到result里面
				result+=line;
			}
			result1=URLEncoder.encode(result , "utf-8");
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finaly来关闭输入流
		finally {
			try {
				if (in!=null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
		
	}
	static ArrayList<String> RegexString(String targetStr,String patternStr){
		ArrayList<String>result=new ArrayList<String>();
		 // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容  
        // 相当于埋好了陷阱匹配的地方就会掉下去  
		Pattern pattern = Pattern.compile(patternStr);  
        // 定义一个matcher用来做匹配  
        Matcher matcher = pattern.matcher(targetStr);  
        boolean isFind=matcher.find();
        // 如果找到了  
        while (isFind){  
            // 打印出结果  
            result.add(matcher.group(1));
            isFind=matcher.find();
        }  
        return result;  
	}
	public static void main(String []args) {
		//定义要访问的链接
//		String url="http://www.baidu.com/";
		String url="http://www.baidu.com/";
//		String url="http://www.163.com";
		//		String url="http://www.bobx.com/idol/hsu-chi/gallery-hsu-chi-0-7-7.html";
			// 访问链接并获取页面内容  
        String result = sendGet(url);  
        
        // 使用正则匹配图片的src内容  
        ArrayList<String> imgSrc = RegexString(result,"src=\"?(.*?)(\"|>|\\s+)");  
//        ArrayList<String> imgSrc = RegexString(result,"src=\"([\\w\\s./:]+?)\"");  
        // 打印结果  
        System.out.println(imgSrc);  
//        System.out.println(result);  
    }  
}
