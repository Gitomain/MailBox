package ejb;

import java.util.Collection;

import javax.ejb.Remote;

import entity.Message;

import entity.Box;

@Remote
public interface IMailBoxManager {
	public Message readAUserNewMessages(String userName);
	public Collection<Message> readAUserAllMessages(String receiverName);
	public boolean deleteAUserReadMessages(String userName);
	public boolean sendAMessageToABox(String subject, String body, String receiverName, String senderName);
	public Message getMessageById(int messageId);
	public Box getBoxByUserId(int userId);
}
