package emlakburada.service.BaseService;

import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.User;

public class UserBaseService {

    protected User convertToUserEntity(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhoto(userRequest.getPhoto());
        user.setBio(userRequest.getBio());
        user.setUserType(userRequest.getUserType());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());
        return user;
    }

    protected UserResponse convertToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoto(user.getPhoto());
        userResponse.setBio(user.getBio());
        userResponse.setUserType(user.getUserType());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }
}
