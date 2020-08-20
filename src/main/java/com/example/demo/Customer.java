package com.example.demo;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement(name = "Customer")
@Document
public class Customer {

    @Id
    private Long id;

    @Field
    private String firstName;
    
	@Field
    private String lastName;

    @Field
    private LocalDate birthdate;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

}