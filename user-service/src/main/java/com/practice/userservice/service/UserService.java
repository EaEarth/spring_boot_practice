package com.practice.userservice.service;

import com.practice.userservice.entity.User;
import com.practice.userservice.grpc.model.GetUserResponse;
import com.practice.userservice.grpc.model.GrcpUser;
import com.practice.userservice.grpc.model.ResponseMessage;
import com.practice.userservice.grpc.model.UserEntity;
import com.practice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**TODO: split function and build grcp response in seperated function**/

    public User saveToDb(User userInfo) {
        return userRepository.save(userInfo);
    }

    public GrcpUser save(GrcpUser user) {
        User userInfo = new User();
        userInfo.setAge(user.getAge());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        User userEntity = saveToDb(userInfo);
        return GrcpUser.newBuilder()
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setAge(userEntity.getAge())
                .build();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponse getById(Long id) {
        Optional<User> user = getUser(id);
        if(user.isPresent()) {
            User userEntity = user.get();
            UserEntity userGrcp = UserEntity.newBuilder()
                    .setId(userEntity.getUserId())
                    .setFirstName(userEntity.getFirstName())
                    .setLastName(userEntity.getLastName())
                    .setAge(userEntity.getAge())
                    .build();
            return GetUserResponse.newBuilder()
                    .setUserFound(userGrcp)
                    .build();
        }
        ResponseMessage message = ResponseMessage.newBuilder().setStatus(404).setMessage("User not found").build();
        return GetUserResponse.newBuilder()
                .setUserNotFound(message)
                .build();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /** For REST api**/
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    public Optional<User> getById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
}
