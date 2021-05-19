package esanchez.devel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import esanchez.devel.app.dto.Coupon;
import esanchez.devel.app.model.Product;
import esanchez.devel.app.repository.ProductRepository;
import esanchez.devel.app.exception.EntityNotFoundException;

@RestController
@RequestMapping("/productapi")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${couponService.url}")
	private String couponServiceUrl;
	
	@PostMapping("/product")
	public Product create(@RequestBody Product product) {
		
		Coupon coupon = restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		
		return productRepository.save(product);
	}
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable long id) throws EntityNotFoundException {
		return productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
	}
}
