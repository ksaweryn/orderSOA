package commerce.rest.presentation;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class CommerceApplication extends Application {
	public CommerceApplication() {
		
	}
	
	public CommerceApplication(@Context ServletConfig servletConfig) {
		super();

		BeanConfig beanConfig = new BeanConfig();

		beanConfig.setVersion("1.0.0");
		beanConfig.setTitle("Commerce API");
		beanConfig.setBasePath("/commerce/api");
		beanConfig.setResourcePackage("commerce.rest.presentation");
		beanConfig.setScan(true);
		beanConfig.setHost("localhost:8080");
		beanConfig.setSchemes(new String[] { "https", "http" });
	}

}
