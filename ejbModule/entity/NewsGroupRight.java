package entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "Rights")
public class NewsGroupRight implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int rightId; 
	protected FinalUser finalUser;
	protected boolean readNewsGroup;  
	protected boolean writeNewsGroup;
	
	@Id
	@Column(name = "rightId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getRightId(){
		return rightId;
	}
	
	public void setRightId(int rightId){
		this.rightId = rightId;
	}
	
	@OneToOne(cascade=ALL)
	@JoinColumn(name = "userId")
	public FinalUser getFinalUser(){
		return finalUser;
	}
	
	public void setFinalUser(FinalUser finaUser){
		this.finalUser = finaUser;
	}
	
	@Column(name = "readNewsGroup")
	public boolean getReadNewsGroup(){
		return readNewsGroup;
	}
	
	public void setReadNewsGroup(boolean readNewsGroup){
		this.readNewsGroup = readNewsGroup;
	}
	
	@Column(name = "writeNewsGroup")
	public boolean getWriteNewsGroup(){
		return this.writeNewsGroup;
	}
	
	public void setWriteNewsGroup(boolean writeNewsGroup){
		this.writeNewsGroup = writeNewsGroup;
	}
	
}
