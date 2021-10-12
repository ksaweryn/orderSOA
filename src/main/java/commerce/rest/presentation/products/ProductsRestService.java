package commerce.rest.presentation.products;

import java.net.URI;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import commerce.persistence.entities.Product;
import commerce.persistence.remote.products.ProductInterface;
import commerce.rest.vo.ProductVO;

@Path("/products")
@RequestScoped
public class ProductsRestService {

	@EJB
	private static ProductInterface productInterface;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response customers() {
		return Response.ok(productInterface.getProducts()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		return Response.ok(productInterface.getProduct(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(ProductVO data) {
		Product product;
		try {
			product = productInterface.createProduct(data.getDescription(), data.getPrice(), data.getWeigth());
			URI location = UriBuilder.fromResource(ProductsRestService.class).path("/{id}")
					.resolveTemplate("id", product.getId()).build();
			return Response.created(location).build();
		} catch (SQLException e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}

	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, ProductVO product) {
		try {
			productInterface.updateProduct(id, product.getDescription(), product.getPrice(), product.getWeigth());
			return Response.ok().build();
		} catch (SQLException e) {
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		productInterface.deleteProduct(id);
		return Response.ok().build();
	}

}
