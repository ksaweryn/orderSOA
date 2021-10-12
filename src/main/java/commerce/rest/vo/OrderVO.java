package commerce.rest.vo;

import java.io.Serializable;

import commerce.util.PaymentType;

public class OrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private PaymentType paymentType;

	private Long customerId;

	public OrderVO() {
		super();
	}

	// Getters & Setters
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
