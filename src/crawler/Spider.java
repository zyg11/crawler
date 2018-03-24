package crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {

	static String sendGet(String  url) {
		
		//定义一个字符串来存储网页内容
		String result="";
		//定义一个缓冲字符输入流
		BufferedReader in=null;
		try {
			//将string转成url对象
			URL realURL=new URL(url);
			//String cityname=URLEncoder.encode(SearchContent, "utf-8");//关键在这里可以将URL转成string以"utf-8"的格式
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
	static ArrayList<zhihu>Getzhihu(String content){
		ArrayList<zhihu>results=new ArrayList<zhihu>();
//		Pattern questionPattern=Pattern.compile("question_link.+?>(.+?)<");
//		Matcher questionMatcher=questionPattern.matcher(content);
//		Pattern urlPattern=Pattern.compile("question_link.+?href=\"(.+?)\"");
		Pattern urlPattern=Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlMatcher=urlPattern.matcher(content);
//		boolean isFind=questionMatcher.find()&&urlMatcher.find();
		Boolean isFind=urlMatcher.find();
		while(isFind)
		{			
			zhihu zhihutemp=new zhihu(urlMatcher.group(1));
//			zhihutemp.question=questionMatcher.group(1);
//			zhihutemp.zhihuUrl="https://www.zhihu.com"+urlMatcher.group(1);
			results.add(zhihutemp);
//			isFind=questionMatcher.find()&&urlMatcher.find();
			isFind=urlMatcher.find();
		}
		return results;
		
	}
}	
