package co.ntier.spring.boot.archetype.web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="company_seq_gen")
	@SequenceGenerator(name="company_seq_gen", sequenceName="COMPANY_SEQ")
	@Setter(AccessLevel.NONE)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Person> employees;

}
