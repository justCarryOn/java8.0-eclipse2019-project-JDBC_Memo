package vo;

public class UserMessage {

	private String messageseq;
	private String title;
	private String content;
	private String indate;
	private String toid;
	private String fromid;

	public UserMessage(String messageseq, String title, String content, String indate, String toid, String fromid) {
		super();
		this.messageseq = messageseq;
		this.title = title;
		this.content = content;
		this.indate = indate;
		this.toid = toid;
		this.fromid = fromid;
	}

	public UserMessage() {
		super();
	}

	public String getMessageseq() {
		return messageseq;
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

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public String getFromid() {
		return fromid;
	}

	public void setFromid(String fromid) {
		this.fromid = fromid;
	}

	@Override
	public String toString() {
		return "Message Number=" + messageseq + ", Title=" + title + ", Content=" + content + ", Date=" + indate + ", Receiver=" + toid + ", Sender=" + fromid;
	}

}
