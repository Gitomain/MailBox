package client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import javax.naming.InitialContext;

import ejb.IUserDirectory;
import ejb.IMailBoxManager;

import entity.FinalUser;
import entity.Message;
import entity.NewsGroupRight;


public class MailboxClient {
	public static void main(String[] args) {
		try {
			InitialContext ic;
			ic = new InitialContext();
			IUserDirectory userDirectory = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
			IMailBoxManager mailBoxManager = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
			Collection<Message> messages = new ArrayList<Message>();
			Iterator <Message> iterator = null;
			Message message;
			String userName;
			String password;
			Collection<FinalUser> finalUsers = new ArrayList<FinalUser>();
			Scanner sc = new Scanner(System.in);
			boolean exit = false;
			do {
				System.out.println("1. Add a user");
				System.out.println("2. Remove a user");
				System.out.println("3. Look a user's rights");
				System.out.println("4. Update a user's rights");
				System.out.println("5. Send a message");
				System.out.println("6. Read new messages");
				System.out.println("7. Read all messages");
				System.out.println("8. Delete messages");
				System.out.println("9. List all users");
				System.out.println("0. Quit");
				switch (Integer.parseInt(sc.nextLine())) {
				case 0: 
					exit = true;
					break;
				case 1:
					System.out.println("Create a user");
					System.out.println("Please enter the User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) != null)
						System.out.println("This User already exists");
					else {
						System.out.println("Please enter the Password :");
						password = sc.nextLine();
						userDirectory.addUser(userName, password);
						System.out.println("User was added successfully");
					}
					break;
				case 2:
					System.out.println("Delete a user");
					System.out.println("Please enter the User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						userDirectory.removeUser(userName);
						System.out.println("User was removed successfully");
					}
					break;
				case 3:
					System.out.println("Please enter the User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						NewsGroupRight newsGroupRight = userDirectory.lookupAUserRights(userName);
						System.out.println("ReadNewsGroup: " + newsGroupRight.getReadNewsGroup());
						System.out.println("WriteNewsGroup: " + newsGroupRight.getWriteNewsGroup());
					}
					break;
				case 4:
					System.out.println("Please enter the User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						NewsGroupRight newsGroupRight1 = userDirectory.lookupAUserRights(userName);
						System.out.println("ReadNewsGroup: " + newsGroupRight1.getReadNewsGroup());
						System.out.println("WriteNewsGroup: " + newsGroupRight1.getWriteNewsGroup());
						System.out.println("Please enter the User's new readRight:");
						Boolean readRight = Boolean.valueOf(sc.nextLine()); 
						System.out.println("Please enter the User's new writeRight:");
						Boolean writeRight = Boolean.valueOf(sc.nextLine()); 
						userDirectory.updateAUserRights(userName, readRight, writeRight);
						System.out.println("User'rights were updated successfully");
						NewsGroupRight newsGroupRight2 = userDirectory.lookupAUserRights(userName);
						System.out.println("ReadNewsGroup: " + newsGroupRight2.getReadNewsGroup());
						System.out.println("WriteNewsGroup: " + newsGroupRight2.getWriteNewsGroup());	
					}
					break;
				case 5:
					System.out.println("Please enter your User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						System.out.println("Please enter your password :");
						password = sc.nextLine();
						if(password.equals(userDirectory.getUserByName(userName).getPassword())) {
							System.out.println("Please enter the receiver's name :");
							String receiverName = sc.nextLine();
							if(userDirectory.getUserByName(receiverName) != null) {
								System.out.println("Subject :");
								String subject = sc.nextLine();
								System.out.println("Body :");
								String body = sc.nextLine();
								mailBoxManager.sendAMessageToABox(subject, body, receiverName, userName);
								System.out.println("Your message was sent successfully");
							}
							else {
								System.out.println("Sorry, this receiver does not exist");
							}
						}
						else {
							System.out.println("Sorry, your password is not correct");
						}
					}
					break;
				case 6: 
					System.out.println("6. read new messages");
					System.out.println("Please enter your User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						System.out.println("Please enter your password :");
						password = sc.nextLine();
						if(password.equals(userDirectory.getUserByName(userName).getPassword())) {
							message = mailBoxManager.readAUserNewMessages(userName);
							if (message != null) {
								System.out.println("SenderName:" + message.getSenderName());
								System.out.println("Subject:" + message.getSubject());
								System.out.println("body:" + message.getBody());
								System.out.println();
							}
							else {
								System.out.println("You have no new messages");
							}
						}
						else {
							System.out.println("Sorry, your password is not correct");
						}
					}
					break;
				case 7:
					System.out.println("7. read all messages");
					System.out.println("Please enter your User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						System.out.println("Please enter your password :");
						password = sc.nextLine();
						if(password.equals(userDirectory.getUserByName(userName).getPassword())) {
							messages = mailBoxManager.readAUserAllMessages(userName);
							if (messages.size() != 0) {
								System.out.println("SenderName:" + iterator.next().getSenderName());
								System.out.println("Subject:" + iterator.next().getSubject());
								System.out.println("body:" + iterator.next().getBody());
								System.out.println();
							}
							else {
								System.out.println("You have no messages");
							}
						}
						else {
							System.out.println("Sorry, your password is not correct");
						}
					}
					break;
				case 8:
					System.out.println("Please enter your User name :");
					userName = sc.nextLine();
					if (userDirectory.getUserByName(userName) == null)
						System.out.println("Sorry, this User does not exist");
					else {
						System.out.println("Please enter your password :");
						password = sc.nextLine();
						if(password.equals(userDirectory.getUserByName(userName).getPassword())) {
							if (mailBoxManager.deleteAUserReadMessages(userName))
								System.out.println("Your read Messages were deleted successfully");
							else
								System.out.println("Sorry, you have no new message");
						}
						else {
							System.out.println("Sorry, your password is not correct");
						}
					}
					break;
				case 9:
					System.out.println("List all Users");
					System.out.println("**************************");
					finalUsers = userDirectory.lookupAllUsers();
					Iterator<FinalUser> e = finalUsers.iterator();
					while(e.hasNext())
						System.out.println(e.next().getUserName());
					}
					break; 
			} while (!exit);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
