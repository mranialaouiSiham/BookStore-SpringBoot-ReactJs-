package com.bookstore.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookstore.main.models.Author;
import com.bookstore.main.models.Book;
import com.bookstore.main.models.Category;
import com.bookstore.main.models.EOrderStatus;
import com.bookstore.main.models.Language;
import com.bookstore.main.models.OrderItem;
import com.bookstore.main.models.Orders;
import com.bookstore.main.models.User;
import com.bookstore.main.payload.request.CartItem;
import com.bookstore.main.payload.request.OrdersRequest;
import com.bookstore.main.repository.BookRepository;
import com.bookstore.main.repository.OrderItemRepository;
import com.bookstore.main.repository.OrdersRepository;
import com.bookstore.main.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrdersService.class})
@ExtendWith(SpringExtension.class)
class OrdersServiceTest {
    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @MockBean
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testCheckout() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        when(this.ordersRepository.save((Orders) any())).thenReturn(orders);
        when(this.orderItemRepository.saveAll((Iterable<OrderItem>) any())).thenReturn(new ArrayList<>());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setId(123L);
        user1.setRoles(new HashSet<>());

        OrdersRequest ordersRequest = new OrdersRequest();
        ordersRequest.setCartItemList(new ArrayList<>());
        ordersRequest.setAddress("42 Main St");
        assertSame(orders, this.ordersService.checkout(user1, ordersRequest));
        verify(this.ordersRepository).save((Orders) any());
        verify(this.orderItemRepository).saveAll((Iterable<OrderItem>) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testCheckout2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        when(this.ordersRepository.save((Orders) any())).thenReturn(orders);
        when(this.orderItemRepository.saveAll((Iterable<OrderItem>) any())).thenReturn(new ArrayList<>());

        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setId(123L);
        user1.setRoles(new HashSet<>());

        ArrayList<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(123L, 1));

        OrdersRequest ordersRequest = new OrdersRequest();
        ordersRequest.setCartItemList(cartItemList);
        ordersRequest.setAddress("42 Main St");
        assertSame(orders, this.ordersService.checkout(user1, ordersRequest));
        verify(this.ordersRepository).save((Orders) any());
        verify(this.orderItemRepository).saveAll((Iterable<OrderItem>) any());
        verify(this.bookRepository, atLeast(1)).findById((Long) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testCreateOrderItem() {
        Language language = new Language();
        language.setLanguage("en");
        language.setId(123L);

        Category category = new Category();
        category.setId(123L);
        category.setName("Name");

        Author author = new Author();
        author.setId(123L);
        author.setAuthor("JaneDoe");

        Book book = new Book();
        book.setLanguage(language);
        book.setCategory(category);
        book.setAuthor(author);
        book.setPrice(10.0);
        book.setId(123L);
        book.setImageUrl("https://example.org/example");
        book.setTitle("Dr");
        book.setDescription("The characteristics of someone or something");
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        OrderItem actualCreateOrderItemResult = this.ordersService.createOrderItem(orders, 123L, 1);
        assertEquals("Dr", actualCreateOrderItemResult.getTitle());
        assertEquals(1, actualCreateOrderItemResult.getQuantity());
        assertEquals(10.0, actualCreateOrderItemResult.getPurchasePrice());
        assertSame(orders, actualCreateOrderItemResult.getOrders());
        assertEquals("https://example.org/example", actualCreateOrderItemResult.getImageUrl());
        verify(this.bookRepository).findById((Long) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testGetOrdersByUser() {
        ArrayList<Orders> ordersList = new ArrayList<>();
        when(this.ordersRepository.findByUser((User) any())).thenReturn(ordersList);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());
        List<Orders> actualOrdersByUser = this.ordersService.getOrdersByUser(user);
        assertSame(ordersList, actualOrdersByUser);
        assertTrue(actualOrdersByUser.isEmpty());
        verify(this.ordersRepository).findByUser((User) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testGetAllOrders() {
        ArrayList<Orders> ordersList = new ArrayList<>();
        when(this.ordersRepository.findAll()).thenReturn(ordersList);
        List<Orders> actualAllOrders = this.ordersService.getAllOrders();
        assertSame(ordersList, actualAllOrders);
        assertTrue(actualAllOrders.isEmpty());
        verify(this.ordersRepository).findAll();
    }

    @Test
    void testUpdateOrderStatus() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        Optional<Orders> ofResult = Optional.of(orders);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setId(123L);
        user1.setRoles(new HashSet<>());

        Orders orders1 = new Orders();
        orders1.setOrderStatus(EOrderStatus.PROCESSING);
        orders1.setOrderItems(new ArrayList<>());
        orders1.setId(123L);
        orders1.setAddress("42 Main St");
        orders1.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders1.setOrderDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        orders1.setUser(user1);
        when(this.ordersRepository.save((Orders) any())).thenReturn(orders1);
        when(this.ordersRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(orders1, this.ordersService.updateOrderStatus(123L, EOrderStatus.PROCESSING));
        verify(this.ordersRepository).findById((Long) any());
        verify(this.ordersRepository).save((Orders) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testUpdateOrderStatus2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        when(this.ordersRepository.save((Orders) any())).thenReturn(orders);
        when(this.ordersRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.ordersService.updateOrderStatus(123L, EOrderStatus.PROCESSING));
        verify(this.ordersRepository).findById((Long) any());
    }

    @Test
    void testGetOrder() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(123L);
        user.setRoles(new HashSet<>());

        Orders orders = new Orders();
        orders.setOrderStatus(EOrderStatus.PROCESSING);
        orders.setOrderItems(new ArrayList<>());
        orders.setId(123L);
        orders.setAddress("42 Main St");
        orders.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orders.setOrderDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        orders.setUser(user);
        Optional<Orders> ofResult = Optional.of(orders);
        when(this.ordersRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(orders, this.ordersService.getOrder(123L));
        verify(this.ordersRepository).findById((Long) any());
        assertTrue(this.ordersService.getAllOrders().isEmpty());
    }

    @Test
    void testGetOrder2() {
        when(this.ordersRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.ordersService.getOrder(123L));
        verify(this.ordersRepository).findById((Long) any());
    }
}

