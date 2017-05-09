package util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import var.Var;
import bean.PostBean;
import bean.Reply;
import data.GetData;

public class ReadHtml {
	/* 读取html，获取其中的信息，并且生成PostBean对象 */
	public static PostBean getPostFromHtml(String url) {

		Document doc = null;
		try {
			String title = null;
			try {
				doc = Jsoup.connect(url).get();
				title = doc.title();// 获取标题，这个是每个网站都一样的
			} catch (Exception e) {
				System.out.println(url + "无法打开");
				return null;
			}

			// 主贴和回帖的内容，其中第一个是主贴内容，后面是回帖内容
			List<String> msgs = new ArrayList<String>();
			// 主贴和回帖的日期，其中第一个是主贴日期，后面是回帖日期
			List<String> dates = new ArrayList<String>();

			// 从数据库取出的字符串数组，由于每个网站的class命名不一样，所以需要进行依次对应
			List<String> msgKeys = GetData.getKeyList(Var.msgKeyDB);
			List<String> dateKeys = GetData.getKeyList(Var.dateKeyDB);

			// 从网站中获取内容的字符串list
			msgs = docSelect(doc, msgKeys);
			if (msgs.isEmpty()) { // 若为空，则读取失败，退出函数并且提示出错信息
				System.out.println(url + " 错误，帖子内容信息读取失败");
				return null;
			}

			// 从网站中获取日期的字符串list
			dates = docSelect(doc, dateKeys);
			// 根据正则表达式去除不需要的中文，如“发表于”等
			for (String string : dateKeys) {
				string = string.replaceAll("[\u4e00-\u9fa5]+ ", "");
			}
			if (dateKeys.isEmpty()) { // 若为空，则读取失败，退出函数并且提示出错信息
				System.out.println(url + " 错误，日期读取失败");
				return null;
			}

			// 组装成PostBean
			List<Reply> replys = new ArrayList<>();// 回帖list
			// 防止读取的内容list和日期list不一样造成异常，对比长度并取小的
			int length = msgs.size();
			if (length > dates.size()) {
				length = dates.size();
			}
			if (length > 1) {// 如果有回帖
				for (int i = 1; i < length; i++) {// 由于第一个是主贴的内容和日期，所以从第二个开始
					replys.add(Reply.CreatReply(msgs.get(i), dates.get(i)));// 组装回帖列表
				}
			}
			// 创建帖子对象
			PostBean postBean = PostBean.createPost(url, msgs.get(0), title,
					dates.get(0), replys);
			System.out.println(url + "获取信息成功");
			return postBean;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* 读取网站，获取对应的list并且去除标签 */
	public static List<String> docSelect(Document doc, List<String> keyWord) {
		List<String> msgs = new ArrayList<String>();
		Elements msgsAndTag = null;
		// 根据传入的字符串list（数据库中获取的）进行搜索
		for (int i = 0; i < keyWord.size(); i++) {
			msgsAndTag = doc.select(keyWord.get(i));
			if (!msgsAndTag.isEmpty()) {// 若不为空，说明搜索成功，退出循环
				break;
			}
		}
		String msg = null;
		// 去除html的标签
		for (Element element : msgsAndTag) {
			msg = HtmlUtil.htmlToString(element.toString());
			msgs.add(msg);
		}
		return msgs;
	}

}