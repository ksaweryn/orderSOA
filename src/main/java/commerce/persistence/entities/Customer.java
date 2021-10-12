package commerce.persistence.entities;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "CUSTOMER", schema = "commerce", catalog = "commerce", uniqueConstraints = {
		@UniqueConstraint(name = "uniquePhone", columnNames = { "phone" }),
		@UniqueConstraint(name = "uniqueEmail", columnNames = { "email" }) })
@Access(AccessType.FIELD)
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	public Customer() {
	}

	public Customer(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	@Id
	@Access(AccessType.PROPERTY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
	@SequenceGenerator(name = "customerSequence", sequenceName = "customer_id_seq", schema = "customer")
	private Long id;

	@Column
	private String name;

	@Column(length = 20)
	private String phone;

	@Email
	@Column(length = 100)
	private String email;

	@OneToOne
	private Address shippingAddress;

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
