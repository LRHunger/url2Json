package bean;

public class Reply {
	private String content;
	private String publish_date;
	
	/*回帖对象的构造函数*/
	public static Reply CreatReply(String content, String date){
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setPublish_date(date);
		return reply;
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
