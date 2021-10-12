package commerce.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT", schema = "customer", catalog = "commerce")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	public Product() {

	}

	public Product(String description, BigDecimal price, Double weigth) {
		this.description = description;
		this.price = price;
		this.weigth = weigth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
	@SequenceGenerator(name = "productSequence", sequenceName = "product_id_seq", schema = "customer")
	private Long id;

	@Column
	@JsonbProperty
	private String description;

	@Column
	@JsonbProperty
	private BigDecimal price;

	@Column
	@JsonbProperty
	private Double weigth;

	@ManyToMany
	@JsonbProperty
	private List<Order> orders;

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
