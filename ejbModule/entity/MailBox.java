package entity;

import javax.persistence.*;

@Entity
@Table(name="Boxes")
public class MailBox extends Box{
	public MailBox()
	{
		this.type = 1;
	}
}
