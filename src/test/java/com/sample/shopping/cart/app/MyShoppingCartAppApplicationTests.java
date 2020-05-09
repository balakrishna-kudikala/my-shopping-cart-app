package com.sample.shopping.cart.app;

import com.sample.shopping.cart.app.constants.CustomerType;
import com.sample.shopping.cart.app.constants.Errors;
import com.sample.shopping.cart.app.model.Cart;
import com.sample.shopping.cart.app.service.CartService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MyShoppingCartAppApplication.TestApplicationConfiguration.class)
@RunWith(SpringRunner.class)
class MyShoppingCartAppApplicationTests {

	@Autowired
	private CartService cartService;

	@Autowired
	private Cart cart;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	void regularCustomerValidInputTests() throws Exception {
		double finalPrice = cartService.getFinalPrice(new Cart(5000d), CustomerType.REGULAR.name());
		Assert.assertTrue(finalPrice == 5000d);
		finalPrice = cartService.getFinalPrice(new Cart(6000d), CustomerType.REGULAR.name());
		Assert.assertTrue(finalPrice == 5900d);
		finalPrice = cartService.getFinalPrice(new Cart(10000d), CustomerType.REGULAR.name());
		Assert.assertTrue(finalPrice == 9500d);
		finalPrice = cartService.getFinalPrice(new Cart(15000d), CustomerType.REGULAR.name());
		Assert.assertTrue(finalPrice == 13500d);
		finalPrice = cartService.getFinalPrice(new Cart(25000d), CustomerType.REGULAR.name());
		Assert.assertTrue(finalPrice == 21500d);
	}

	@Test
	void premiumCustomerValidInputTests() throws Exception {
		double finalPrice = cartService.getFinalPrice(new Cart(4000d), CustomerType.PREMIUM.name());
		Assert.assertTrue(finalPrice == 3600d);
		finalPrice = cartService.getFinalPrice(new Cart(8000d), CustomerType.PREMIUM.name());
		Assert.assertTrue(finalPrice == 7000d);
		finalPrice = cartService.getFinalPrice(new Cart(12000d), CustomerType.PREMIUM.name());
		Assert.assertTrue(finalPrice == 10200d);
		finalPrice = cartService.getFinalPrice(new Cart(20000d), CustomerType.PREMIUM.name());
		Assert.assertTrue(finalPrice == 15800d);
		finalPrice = cartService.getFinalPrice(new Cart(30000d), CustomerType.PREMIUM.name());
		Assert.assertTrue(finalPrice == 22800d);
	}

	@Test
	void inValidCustomerTest() {
		try {
			double finalPrice = cartService.getFinalPrice(new Cart(4000d), "Invalid Customer");
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), Errors.INVALID_CUSTOMER_TYPE.getMessage());
		}
	}

	@Test
	void inValidAmountTest() {
		try {
			double finalPrice = cartService.getFinalPrice(new Cart(-4000d), CustomerType.REGULAR.name());
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), Errors.INVALID_CART.getMessage());
		}

	}
}
