package util;

import bean.PostBean;

import com.google.gson.Gson;

public class ToJsonUtil {
	public static String toJsonString(PostBean postBean) {
		Gson gson = new Gson();
		String outStr = gson.toJson(postBean);
		return outStr;
	}
	
	/*			自己做的转json，但是可扩展性不好，还是用gson轻松愉悦
	//把PostBean转换成字符串
	public static String toJsonString(PostBean postBean) {
		StringBuffer sb = new StringBuffer();
		String title = postBean.getTitle();
		String content = postBean.getContent();
		String publish_date = postBean.getPublish_date();
		List<Reply> replys = postBean.getReplys();

		String url = postBean.getUrl();
		sb.append(url);
		String postPeace = jsonPeace4("post",connectPeace1(content,title,publish_date));
		
		List<String> replyPeace = new ArrayList<>();
		for (Reply reply : replys) {
			replyPeace.add(connectPeace2(reply.getContent(), reply.getPublish_date()));
		}
		
		String reply2 = jsonPeace2Middle(replyPeace);
		String reply3 = jsonPeace4("replys", reply2);
		
		List<String> mainList = new ArrayList<>();
		mainList.add(postPeace);
		mainList.add(reply3);
		
		String mainString = jsonPeace2Big(mainList);
		sb.append(mainString);

		return sb.toString();
	}


	// "a":"b"
	public static String jsonPeace(String key, String value) {
		String outString = null;
		if (value == null) {
			value = "";
		}
		outString = '"' + key + '"' + ':' + '"' + value + '"';
		return outString;
	}

	// {a,b}
	public static String jsonPeace2Big(List<String> inList) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (String jsonPeace : inList) {
			sb.append(jsonPeace + ",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}");
		return sb.toString();
	}

	// [a,b,c]
	public static String jsonPeace2Middle(List<String> inList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (String jsonPeace : inList) {
			sb.append(jsonPeace + ",");
		}
		if (sb.toString().contains(",")) {
			
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("]");
		return sb.toString();
	}

	// "a":b
	public static String jsonPeace4(String key, String value) {
		String outString = null;
		if (value == null) {
			value = "";
		}
		outString = '"' + key + '"' + ':' + value;
		return outString;
	}
	
	//{"content":"11","title":"22","publish_date":"3"}
	public static String connectPeace1(String contentIn,String titleIn,String dateIn) {
		String contenPeace = jsonPeace("content", contentIn);
		String titlePeace = jsonPeace("title", titleIn);
		String datePeace = jsonPeace("publish_date", dateIn);
		
		List<String> three = new ArrayList<>();
		three.add(titlePeace);
		three.add(contenPeace);
		three.add(datePeace);
		
		String outString = jsonPeace2Big(three);
		return outString;
	}
	
	//{"content":"11","publish_date":"2"}
	public static String connectPeace2(String contentIn,String dateIn) {
		String contenPeace = jsonPeace("content", contentIn);
		String datePeace = jsonPeace("publish_date", dateIn);
		
		List<String> three = new ArrayList<>();
		three.add(contenPeace);
		three.add(datePeace);
		
		String outString = jsonPeace2Big(three);
		return outString;
	}*/
}
