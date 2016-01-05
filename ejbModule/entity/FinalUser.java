package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "FinalUsers")
public class FinalUser implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int userId;
	protected String userName;
	protected String password;
	protected NewsGroupRight newsRight;
	protected Box box;
	
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToOne(cascade = ALL, mappedBy = "finalUser")
	public NewsGroupRight getUserRight(){
		return newsRight;
	}
	public void setUserRight(NewsGroupRight newsRight){
		this.newsRight = newsRight;
	}
	
	@OneToOne
	@JoinColumn(name = "boxId")
	public Box getBox()
	{
		return box;
	}

	public void setBox(Box box)
	{
		this.box = box;
	}

}
