package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int messageId;
	protected String senderName;
	protected String receiverName;
	protected Date sendingDate;
	protected String subject;
	protected String body;
	protected boolean alreadyRead;
	protected Box box;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "messageId")
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	@Column(name = "senderName")
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	@Column(name = "receiverName")
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Column(name = "sendingDate")
	public Date getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}
	
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "body")
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Column(name = "alreadyRead")
	public boolean getIsRead() {
		return alreadyRead;
	}
	public void setIsRead(boolean alreadyRead) {
		this.alreadyRead = alreadyRead;
	}
	
	@ManyToOne()
	@JoinColumn(name = "boxId")
	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
}

