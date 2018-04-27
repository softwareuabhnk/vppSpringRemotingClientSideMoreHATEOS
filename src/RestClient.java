import java.io.IOException;
import java.net.URI;

import org.springframework.hateoas.Link;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	public static void main(String[] args) throws IOException {
	

		
		 RestTemplate template = new RestTemplate();
		 //template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		 template.setErrorHandler(new CustomExceptionHandler(template));
		 
		 CustomerCollectionRepresentation allCustomers = template.getForObject("Http://localhost:8080/mywebapp/customers?first=1&last=2", CustomerCollectionRepresentation.class);
		 
		 Link link = allCustomers.getLink("next");
		 System.out.println("The next page will be " + link);
		 
		 System.out.println("The next page (href) will be " + link.getHref());
		 
		 System.out.println(allCustomers);
	}
}
