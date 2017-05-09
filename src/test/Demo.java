package test;

import java.util.List;

import util.ReadHtml;
import util.ToJsonUtil;
import util.WriteUtil;
import var.Var;
import bean.PostBean;

public class Demo {
	/* 读取网站，获取标题、内容、日期等内容，并按Json格式写入TXT */
	public static void run(String url, String path) {// path是写入的TXT的路径
		// 根据网址，整理成PostBean对象
		PostBean postBean = ReadHtml.getPostFromHtml(url);
		if (postBean != null) {
			// 把PostBean整理成Json字符串
			String json = ToJsonUtil.toJsonString(postBean);
			// 把Json字符串写入TXT文档
			WriteUtil.writeLineData(json, path);
		}
	}

	/* 对run函数进行批量操作 */
	public static void main(String[] args) {
		 // 从TXT中读取网址列表
		 List<String> urls = WriteUtil.getUrls();
		 // 对每个网址进行操作
		 for (String url : urls) {
			 run(url, Var.outTxt);
		 }

//		String url = "http://bbs.house.163.com/bbs/yzjlb/620002274.html";
//		run(url, Var.outTxt);

//		 Document doc;
//		 try {
//		 doc = Jsoup.connect(url).get();
//		 Elements elements = doc.select("div");
//		 for (int i = 0; i < elements.size(); i++) {
//			 System.out.println(elements.get(i));
//		 }
//		 }catch(Exception e){
//		
//		 }
	}
}
