package commerce.rest.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;

	private BigDecimal price;

	private Double weigth;

	public ProductVO() {

	}

	public ProductVO(String description, BigDecimal price, Double weigth) {
		this.description = description;
		this.price = price;
		this.weigth = weigth;
	}

	// Getters & Setters
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getWeigth() {
		return weigth;
	}

	public void setWeigth(Double weigth) {
		this.weigth = weigth;
	}

}
