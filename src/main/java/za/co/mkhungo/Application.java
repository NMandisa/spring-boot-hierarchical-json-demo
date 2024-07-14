package za.co.mkhungo;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.mkhungo.model.*;
import za.co.mkhungo.model.enums.OrderStatus;
import za.co.mkhungo.repository.*;

@SpringBootApplication
public class Application {
	private final ReviewRepository reviewRepository;
	private final ProductRepository productRepository;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final RatingRepository ratingRepository;

    public Application(ReviewRepository reviewRepository, ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository, RatingRepository ratingRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.ratingRepository = ratingRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init(){
		Customer customer = new Customer();
		Customer customer2 = new Customer();
		Customer customer3 = new Customer();
		Customer customer4 = new Customer();

		Order order = new Order();
		Order order2 = new Order();
		Order order3 = new Order();
		Order order4 = new Order();
		Order order5 = new Order();

		Product product = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		Product product4 = new Product();
		Product product5 = new Product();

		Rating rating = new Rating();
		Rating rating2 = new Rating();
		Rating rating3 = new Rating();
		Rating rating4 = new Rating();
		Rating rating5 = new Rating();

		Review review = new Review();
		Review review2 = new Review();
		Review review3 = new Review();
		Review review4 = new Review();
		Review review5 = new Review();

		customer.setFirstName("Noxolo");
		customer.setSurname("Mkhungo");
		customer2.setFirstName("Mandisa");
		customer2.setSurname("Sodi");
		order.setOrderStatus(OrderStatus.PACKAGED);
		order.addProduct(product);
		order.addProduct(product2);
		order2.setOrderStatus(OrderStatus.SHIPPED);
		order2.addProduct(product3);
		rating.setRating(4);
		review.setComment("I loved the product");
		review.setTagLine("Exceeded my expectations");
		rating.addReview(review);
		product.setName("Chocolate bar");
		product.setPrice(43.99);

		product2.setPrice(75.22);
		product2.setName("Still Water");

		product3.setPrice(15.22);
		product3.setName("Cool Drink");
		product.addRating(rating);
		customer.addOrder(order);
		customer2.addOrder(order2);

		customerRepository.save(customer);
		customerRepository.save(customer2);
	}
}
