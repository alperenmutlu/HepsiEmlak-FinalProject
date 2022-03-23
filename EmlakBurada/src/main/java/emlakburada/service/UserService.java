package emlakburada.service;

import emlakburada.Constant.Messages;
import emlakburada.core.*;
import emlakburada.dto.request.ProductRequest;
import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Product;
import emlakburada.model.User;
import emlakburada.model.UserProductDetail;
import emlakburada.repository.UserRepository;
import emlakburada.service.BaseService.ProductBaseService;
import emlakburada.service.BaseService.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends UserBaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductBaseService productBaseService;

    @Autowired
    private ProductService productService;


    public DataResult<List<UserResponse>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return new SuccessDataResult<List<UserResponse>>
                (users.stream()
                        .map(user -> convertToUserResponse(user))
                        .collect(Collectors.toList()), Messages.allusersListed);
    }

    public Result saveUser(UserRequest userRequest){

        boolean result = checkIfUserExists(userRequest.getEmail());

        if(result == false){
            return new ErrorResult(Messages.userAlreadyExists);
        }

        userRepository.save(convertToUserEntity(userRequest));
        return new SuccessResult(Messages.userAdded);
    }


    public DataResult<UserResponse> getUserById(Integer id){
        Optional<User> user = userRepository.findById(id);
        return new SuccessDataResult<UserResponse>(convertToUserResponse(user.get()), Messages.userListed);
    }


    public DataResult<Product> getUserProductsByUserId(Integer userId){

        User user = userRepository.getUserById(userId);

        if(user != null){
            Product product = user.getProduct();

            return new SuccessDataResult<Product>(product,Messages.userProductsListed);
        }
        return new ErrorDataResult<>(Messages.userNotFound);

    }


    public boolean checkIfUserExists(String mail){
        if(userRepository.getUserByEmail(mail) != null){
            return false;
        }
        return true;

    }

}
