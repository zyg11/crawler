package crawler;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // 定义即将访问的链接  
        String url = "https://www.zhihu.com/explore/recommendations";  
        // 访问链接并获取页面内容  
        String content = Spider.sendGet(url);  
        // 获取该页面的所有的知乎对象  
        ArrayList<zhihu> myZhihu = Spider.Getzhihu(content);  
        // 打印结果  
        System.out.println(myZhihu);  
	}

}
