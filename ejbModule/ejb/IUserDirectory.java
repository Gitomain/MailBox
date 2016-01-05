package ejb;

import java.util.Collection;

import javax.ejb.Remote;

import entity.FinalUser;
import entity.NewsGroupRight;

@Remote
public interface IUserDirectory {
	public boolean addUser(String userName, String password);
	public boolean removeUser(String userName);
	public Collection<FinalUser> lookupAllUsers();
	public NewsGroupRight lookupAUserRights(String userName);
	public FinalUser getUserByName(String userName);
	public boolean updateAUserRights(String userName, boolean readRight, boolean writeRight);
	public boolean checkPassword(String userName, String password);
	
}
