package emlakburada.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import emlakburada.Constant.Messages;
import emlakburada.core.DataResult;
import emlakburada.core.Result;
import emlakburada.core.SuccessDataResult;
import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.enums.UserType;
import emlakburada.model.Product;
import emlakburada.model.User;
import emlakburada.repository.ProductRepository;
import emlakburada.repository.UserRepository;
import emlakburada.service.ProductService;
import emlakburada.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    private ProductService productService;


    @BeforeEach
    void setup(){

        Mockito.
                when(userRepository.findAll()).
                thenReturn(prepareMockUserList());

    }

    private List<User> prepareMockUserList() {
        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(new User(UserType.INDIVIDUAL, "alperen", "alperen@alperen.com"));
        fakeUserList.add(new User(UserType.INDIVIDUAL, "brock", "brokc@brock.com"));
        return fakeUserList;
    }

    @Test
    void getAllUsersTest(){

        DataResult<List<UserResponse>> users = userService.getAllUsers();

        assertNotNull(users);
        assertThat(users.getData().size()).isNotZero();

    }

    @Test
    void saveUserTest(){
         Result result = userService.saveUser(prepareUser());

         assertNotNull(result);
         assertEquals(result.getMessage(),Messages.userAdded);
    }

    private UserRequest prepareUser() {
        return new UserRequest(UserType.INDIVIDUAL, "alperen","alperen@alperen.com","123456","","","123456789");
    }

    @Test
    void getUserProductsByUserIdWithUser(){

        User user = prepareUser2();
        Product product = prepareProduct();


        Mockito
                .when(productRepository.getByName(prepareProduct().getName()))
                .thenReturn(product);
        Mockito
                .when(userRepository.getUserById(prepareUser2().getId()))
                .thenReturn(user);


        DataResult<Product> product2 = userService.getUserProductsByUserId(user.getId());

        assertNotNull(user);
        assertEquals("alperen",user.getName());
        assertEquals(1,user.getId());
        assertEquals(product2.getMessage(),Messages.userProductsListed);


    }

    private User prepareUser2(){
        User user = new User();
        user.setId(1);
        user.setName("alperen");
        user.setPassword("123");
        return user;
    }

    private Product prepareProduct(){
        Product product = new Product();
        product.setId(1);
        product.setName("AdvertPackage");
        product.setPrice(100);
        return product;
    }

}
