package com.sample.shopping.cart.app;

import com.sample.shopping.cart.app.constants.CustomerType;
import com.sample.shopping.cart.app.model.Cart;
import com.sample.shopping.cart.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import java.util.Scanner;

@SpringBootApplication
public class MyShoppingCartAppApplication implements CommandLineRunner {

	@Autowired
	private Cart cart;

	@Autowired
	private CartService cartService;

	public static void main(String[] args) {
		SpringApplication.run(MyShoppingCartAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.println("*********** Please input the Total Cart Amount **********");
			double totalAmount=0d;
			String customerType = null;
			double finalAmount = 0d;
			while (scanner.hasNext()) {
				try{
					totalAmount = Double.parseDouble(scanner.next());
					break;
				} catch (NumberFormatException e){
					System.out.println("Please enter a valid amount!!");
				}
			}
			System.out.println("*********** Please input the Customer Type (Enter R for Regular, P for Premium)**********");
			scanner = new Scanner(System.in);
			while (scanner.hasNext()){
				customerType = scanner.next();
				if (StringUtils.isEmpty(customerType) || !("R".equalsIgnoreCase(customerType) || "P".equalsIgnoreCase(customerType))){
					System.out.println("Please enter a valid Customer Type!!");
					continue;
				} else {
					customerType = "R".equalsIgnoreCase(customerType) ? CustomerType.REGULAR.name() : "P".equalsIgnoreCase(customerType) ? CustomerType.PREMIUM.name() : null;
					break;
				}
			}
			cart.setTotalPrice(totalAmount);

			finalAmount = cartService.getFinalPrice(cart, customerType);
			System.out.println("*********************************************************");
			System.out.format("* Total Amount is  		: %.2f \n", totalAmount);
			System.out.format("* Final Amount after Discount is : %.2f \n", finalAmount);
			System.out.println("*********************************************************");
			System.out.print("Do you want to Rerun? : ");
			scanner = new Scanner(System.in);
			if (scanner.hasNext()){
				String input = scanner.next();
				if (!"Y".equalsIgnoreCase(input)) break;
			}
		}
	}

	@Configuration
	@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CommandLineRunner.class))
	@EnableAutoConfiguration
	public static class TestApplicationConfiguration {
	}
}
