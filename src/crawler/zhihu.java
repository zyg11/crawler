package crawler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhihu {
	public String question;//问题
	public String questionDescription;
	public String zhihuUrl;//网页链接
	public ArrayList<String>answers;//存储所有回答问题的数组
	
	//构造方法初始化数据
	public zhihu(String url){
		question="";
		zhihuUrl="";
		questionDescription="";
		answers=new ArrayList<String>();
		// 判断url是否合法  
        if (getRealUrl(url)) {  
            System.out.println("正在抓取" + zhihuUrl);  
            // 根据url获取该问答的细节  
            String content = Spider.sendGet(zhihuUrl);  
            Pattern pattern;  
            Matcher matcher;  
            // 匹配标题  
            pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");  
            matcher = pattern.matcher(content);  
            if (matcher.find()) {  
                question = matcher.group(1);  
            }  
            // 匹配描述  
            pattern = Pattern  
                    .compile("zh-question-detail.+?<div.+?>(.*?)</div>");  
            matcher = pattern.matcher(content);  
            if (matcher.find()) {  
                questionDescription = matcher.group(1);  
            }  
            // 匹配答案  
            pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");  
            matcher = pattern.matcher(content);  
            boolean isFind = matcher.find();  
            while (isFind) {  
                answers.add(matcher.group(1));  
                isFind = matcher.find();  
            }
        }
	}
	
	public boolean getAll() {  
		  
        return true;  
    }  
	
	//处理url  
    boolean getRealUrl(String url) {  
        // 将http://www.zhihu.com/question/22355264/answer/21102139  
        // 转化成http://www.zhihu.com/question/22355264  
        // 否则不变  
        Pattern pattern = Pattern.compile("question/(.*?)/");  
        Matcher matcher = pattern.matcher(url);  
        if (matcher.find()) {  
            zhihuUrl = "https://www.zhihu.com/question/" + matcher.group(1);  
        } else {  
            return false;  
        }  
        return true;  
    }  
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "问题：" + question + "\n" + "描述：" + questionDescription + "\n"  
        + "链接：" + zhihuUrl + "\n回答：" + answers.size() + "\n";  
	}
}
