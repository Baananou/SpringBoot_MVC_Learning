package category.SchoolProject;

import category.SchoolProject.entities.Category;
import category.SchoolProject.entities.Product;
import category.SchoolProject.repositories.CategoryRepository;
import category.SchoolProject.repositories.ProductRepository;
import category.SchoolProject.security.service.IServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SchoolProjectApplication {
	/*@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(SchoolProjectApplication.class, args);
	}

	//
	// productRepository.searchProdByIdCat(2).forEach(e -> system.out.println(e.getName));
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	//@Bean
	CommandLineRunner commandLineRunner(IServiceAccount accountService) {
		return args -> {
			accountService.addRole("USER");
			accountService.addRole("ADMIN");
			accountService.addUser("user", "123", "user@gmail.com");
			accountService.addUser("admin","123","admin@gmail.com");
			accountService.addRoleToUser("user","USER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin", "USER");

		};
	}
	//@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository,CategoryRepository categoryRepository)
	{
		return args -> {

			 /*categoryRepository.save(new Category(null,"mecatronique",null));
			categoryRepository.save(Category.builder().name("automatique").build());
			productRepository.save(new Product(null,"laptop",3000,2,categoryRepository.findById(1L).get()));
			productRepository.findAll().forEach(e-> System.out.println(e.getName()));
			productRepository.searchProdByIdCat("1").forEach(e-> System.out.println(e.getName()));*/

		};
	}
}
