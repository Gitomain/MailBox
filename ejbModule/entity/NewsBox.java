package entity;

import javax.persistence.*;

@Entity
@Table(name = "Boxes")
public class NewsBox extends Box{
	public NewsBox() {
		this.type = 2;
	}
}
