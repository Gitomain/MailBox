package ejb;


import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.*;
import java.util.Collection;

import entity.Box;
import entity.FinalUser;
import entity.NewsGroupRight;

@Stateless
public class UserDirectoryBean implements IUserDirectory {
	@PersistenceContext(unitName="pu14")
    private EntityManager em;
	private IMailBoxManager mailBoxManager;
	
	public UserDirectoryBean() {
		try {
			InitialContext ic = new InitialContext();
			mailBoxManager = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean addUser(String userName, String password){
		try {	
			FinalUser user = new FinalUser();
			user.setUserName(userName);
			user.setPassword(password);
			NewsGroupRight newsRight = new NewsGroupRight();
			newsRight.setReadNewsGroup(true);
			newsRight.setWriteNewsGroup(false);
			newsRight.setFinalUser(user);
			em.persist(newsRight);
			user.setUserRight(newsRight);
			Box box = new Box();
			box.setBoxName(user.getUserName() + "Box");
			box.setType(1);
			box.setAllMessages(null);
			em.persist(user);
			em.flush();
			em.refresh(user);
			box.setUser(user);
			em.persist(box);
			em.flush();
			em.refresh(box);
			user.setBox(box);
			em.persist(user);
			em.flush();
			em.refresh(user);
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public boolean removeUser(String userName){
		try {
			FinalUser user = getUserByName(userName);
			Box box = user.getBox();
			box.setUser(null);
			user.setBox(null);
			em.remove(user);
			em.remove(box);
			em.flush();
			return true;
		}
		catch (NoResultException e) {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<FinalUser> lookupAllUsers(){
		try {
			Query q = em.createQuery("select user from FinalUser user", FinalUser.class);
			Collection<FinalUser> results = q.getResultList();
	        return results;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	@Override
	public NewsGroupRight lookupAUserRights(String userName){
		try {
			FinalUser user = getUserByName(userName);
			return user.getUserRight();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public boolean updateAUserRights(String userName, boolean readRight, boolean writeRight){
		try {
			FinalUser user = getUserByName(userName);
			NewsGroupRight newsRight = user.getUserRight();
			newsRight.setReadNewsGroup(readRight);
			newsRight.setWriteNewsGroup(writeRight);
			user.setUserRight(newsRight);	
			em.persist(newsRight);
			em.flush();
			em.refresh(newsRight);
			return true;
		}
		catch (NoResultException e) {
			return false;
		}
	}
	
	@Override
	public FinalUser getUserByName(String userName) {
		try {
			Query q = em.createQuery("select user from FinalUser user where user.userName =:userName", FinalUser.class);
			q.setParameter("userName", userName);
			FinalUser user = (FinalUser) q.getSingleResult();
			return user;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public boolean checkPassword(String userName, String password) {
		FinalUser user = getUserByName(userName);
		if (user != null && password.equals(user.getPassword()))
			return true;
		else
			return false;

	}

}