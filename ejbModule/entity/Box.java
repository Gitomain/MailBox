package entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;

@Entity
@Table(name = "Boxes")
public class Box implements Serializable
{

	private static final long serialVersionUID = 1L;
	protected int boxId;
	protected int type;
	protected String boxName;
	protected FinalUser user;
	protected Collection<Message> messages;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "boxId")
	public int getBoxId() {
		return boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
	
	@Column(name = "boxType")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "boxName")
	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	
	@OneToOne()
	@JoinColumn(name = "userId")
	public FinalUser getUser() {
		return user;
	}

	public void setUser(FinalUser user) {
		this.user = user;
	}

	public void addMessage(Message message)
	{
		message.setBox(this);
		messages.add(message);
	}
	
	public Message readAMessage(int messageId)
	{
		for (Iterator<Message> iter = messages.iterator(); iter.hasNext();)
		{
			Message message = (Message) iter.next();
			if (message.getMessageId() == messageId)
			{
				return message;
			}
		}
		return null;
	}
	
	@OneToMany(cascade = ALL, mappedBy = "box")
	public Collection<Message> getAllMessages()
	{
		return messages;
	}
	
	public void setAllMessages(Collection<Message> messages)
	{
		this.messages = messages;
	}
}