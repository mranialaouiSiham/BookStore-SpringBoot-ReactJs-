package com.bookstore.main;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Book;
import com.bookstore.main.models.User;
import com.bookstore.main.payload.request.CartItem;
import com.bookstore.main.payload.request.OrdersRequest;
import com.bookstore.main.repository.UserRepository;
import com.bookstore.main.services.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	LanguageService languageService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	@Autowired
	BookService bookService;

	@Autowired
	OrdersService ordersService;

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void addDummyLanguage() {
		Faker faker = new Faker();
		for (String l	: faker.lorem().words(10)) {
			languageService.save(l);
		}
	}

	@Test
	void addDummyCategory() {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			categoryService.addCategory(faker.book().genre());
		}
	}

	@Test
	void addDummyAuthor() {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			authorService.addAuthor(faker.name().fullName());
		}
	}

	@Test
	void addDummyBook() {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setTitle(faker.book().title());
			book.setDescription(faker.lorem().paragraph());
			book.setPrice(faker.number().randomDouble(2, 1, 100));
			book.setImageUrl(faker.internet().image());
			Author author = authorService.getAuthor(
					(long) (Math.random() * authorService.count()) + 1
			);

			book.setAuthor(author);

			book.setCategory(categoryService.getCategory(
					(long) (Math.random() * categoryService.count()) + 1
			));

			book.setLanguage(languageService.getLanguage(
					(long) (Math.random() * languageService.count()) + 1
			));

			bookService.addBook(book);
		}
	}

	@Test
	void checkout() {
		User user = userRepository.findByEmail("admin@gmail.com").get();
		OrdersRequest ordersRequest = new OrdersRequest();
		ordersRequest.setAddress("123, ABC Street, XYZ City");
		List<CartItem> cartItemList = new ArrayList<>();
		cartItemList.add(new CartItem(14L, 1));
		cartItemList.add(new CartItem(15L, 2));
		ordersRequest.setCartItemList(cartItemList);

		ordersService.checkout(user, ordersRequest);
	}


}
