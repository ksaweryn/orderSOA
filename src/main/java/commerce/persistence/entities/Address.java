package commerce.persistence.entities;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS", schema = "customer", catalog = "commerce")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	public Address() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
	@SequenceGenerator(name = "addressSequence", sequenceName = "commerce.address_id_seq", schema = "customer")
	private Long id;

	@Column
	@JsonbProperty
	private String street;

	@Column
	@JsonbProperty
	private int number;

	@Column
	@JsonbProperty
	private String city;

	@Column
	@JsonbProperty
	private String state;

	@Column
	@JsonbProperty
	private String zipCode;

	@Column
	@JsonbProperty
	private String country;

	@OneToOne
	@JsonbProperty
	private Customer customer;

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
