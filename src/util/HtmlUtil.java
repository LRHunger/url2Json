package util;

public class HtmlUtil {
	/*对字符串进行修改，依靠正则表达式去除字符串的标签，达到html代码转换成string的效果*/
	public static String htmlToString(String html){
		return html.replaceAll("<[Bb][Rr]>","\r\n").replaceAll("<.*?>","");
		}
}
