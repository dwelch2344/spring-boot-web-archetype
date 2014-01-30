package co.ntier.spring.boot.archetype.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="person_seq_gen")
	@SequenceGenerator(name="person_seq_gen", sequenceName="PERSON_SEQ")
	@Setter(AccessLevel.NONE)
	private Long id;
	
	private String name;
	private Date birthdate;

}
