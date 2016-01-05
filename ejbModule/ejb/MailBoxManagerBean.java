package ejb;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.*;


import entity.Box;
import entity.FinalUser;
import entity.Message;

@Stateless
public class MailBoxManagerBean implements IMailBoxManager{
	@PersistenceContext(unitName="pu14")
	private EntityManager em;
	private IUserDirectory userDirectory;
	
	@SuppressWarnings("unchecked")
	@Override
	public Message readAUserNewMessages(String userName) {
		try {
			Query q1 = em.createQuery("select msg from Message msg where msg.receiverName = :userName", Message.class);
			q1.setParameter("userName", userName);
			return (Message) q1.getSingleResult();
		} 
		catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Message> readAUserAllMessages(String receiverName) {
		try {
			Query q1 = em.createQuery("select msg from Message msg where msg.receiverName = :receiverName", Message.class);
			q1.setParameter("receiverName", receiverName);
			Collection<Message> results = q1.getResultList();
			return results;
		} 
		catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteAUserReadMessages(String userName) {
		try {
			Query q1 = em.createQuery("select msg from Message msg where msg.receiverName = :userName", Message.class);
			q1.setParameter("userName", userName);
			Collection<Message> results = q1.getResultList();
			if (results.size() != 0) {
				for (Message message : results) {
					em.remove(message);
					em.flush();
				}
				return true;
			}
			else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public boolean sendAMessageToABox(String subject, String body, String receiverName, String senderName) {
		try {
			Message message = new Message();
			message.setSubject(subject);
			message.setBody(body);
			message.setIsRead(false);
			message.setReceiverName(receiverName);
			message.setSenderName(senderName);
			message.setSendingDate(new Date());
			em.persist(message);
			em.flush();
			em.refresh(message);
			return true;
		 } 
		catch (NullPointerException e) {
			return false;
		}
	}

	
	@Override
	public Message getMessageById(int messageId) {
		try {
			Query q = em.createQuery("SELECT msg from Message msg WHERE msg.messageId = :messageId", Message.class);
			q.setParameter("messageId", messageId);
			return (Message) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Box getBoxByUserId(int userId) {
		try {
			Query q = em.createQuery("SELECT b from Box b WHERE b.userId = :userId", Box.class);
			q.setParameter("userId", userId);
			return (Box) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
