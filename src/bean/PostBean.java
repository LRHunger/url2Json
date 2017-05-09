package bean;

import java.util.List;


public class PostBean {
	/*帖子对象，包括url、标题、内容、日期、回帖列表（每个回帖有日期和内容）*/
	private String url;
	private String title;//标题
	private String content;//帖子内容
	private String publish_date;//日期
	List<Reply> replys;

	/*对象的构造函数*/
	public static PostBean createPost(String url,String content ,String title,
			String date,List<Reply> replys){
		PostBean postBean = new PostBean();
		postBean.setContent(content);
		postBean.setTitle(title);
		postBean.setUrl(url);
		postBean.setPublish_date(date);
		postBean.setReplys(replys);
		return postBean;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Reply> getReplys() {
		return replys;
	}
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
}
