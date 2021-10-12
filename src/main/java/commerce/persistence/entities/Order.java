package commerce.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import commerce.util.PaymentType;

@Entity
@Table(name = "ORDERS", schema = "commerce", catalog = "commerce")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public Order() {
	}

	public Order(PaymentType paymentType, BigDecimal orderTotalValue, Customer customer, Address shippingAddress,
			List<Product> products) {
		super();
		this.paymentType = paymentType;
		this.orderTotalValue = orderTotalValue;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
	@SequenceGenerator(name = "orderSequence", sequenceName = "order_id_seq", schema = "commerce")
	private Long id;

	@Column
	private LocalDate orderDate;

	@Column
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Column
	private BigDecimal orderTotalValue;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne
	private Address shippingAddress;

	@ManyToMany
	private List<Product> products;

	@PrePersist
	public void beforePersist() {
		this.orderDate = LocalDate.now();
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getOrderTotalValue() {
		return orderTotalValue;
	}

	public void setOrderTotalValue(BigDecimal orderTotalValue) {
		this.orderTotalValue = orderTotalValue;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
