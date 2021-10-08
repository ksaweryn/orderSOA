package commerce.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import commerce.util.PaymentType;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	public Order() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderNumber;

	@Column
	private LocalDate orderDate;

	@Column
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Column
	private BigDecimal orderTotalValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Customer customer;

	@OneToOne
	private Address shippingAddress;

	@ManyToMany
	private List<Product> productsPurchases;

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
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

	public List<Product> getProductsPurchases() {
		return productsPurchases;
	}

	public void setProductsPurchases(List<Product> productsPurchases) {
		this.productsPurchases = productsPurchases;
	}

}