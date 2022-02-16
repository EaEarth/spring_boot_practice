package com.practice.userservice.controller;

import com.practice.userservice.entity.User;
import com.practice.userservice.grpc.model.*;
import com.practice.userservice.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GrpcController extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserService userService;

    /**TODO: split function and build grcp response in seperated function**/

    public void save(GrcpUser user, StreamObserver<GrcpUser> responseObserver) {
        responseObserver.onNext(userService.save(user));
        responseObserver.onCompleted();
    }

    public void getById(UserId userId, StreamObserver<GetUserResponse> responseObserver) {
        responseObserver.onNext(userService.getById(userId.getId()));
        responseObserver.onCompleted();
    }

    public void update(UserEntity userInfo, StreamObserver<GetUserResponse> responseObserver) {
        GetUserResponse userData = userService.getById(userInfo.getId());
        if(userData.hasUserFound()){
            User _user = new User();
            _user.setUserId(userInfo.getId());
            _user.setFirstName(userInfo.getFirstName());
            _user.setLastName(userInfo.getLastName());
            _user.setAge(userInfo.getAge());
            User info = userService.saveToDb(_user);
            UserEntity response = UserEntity.newBuilder()
                    .setId(info.getUserId())
                    .setFirstName(info.getFirstName())
                    .setLastName(info.getLastName())
                    .setAge(info.getAge())
                    .build();
            responseObserver.onNext(GetUserResponse.newBuilder()
                    .setUserFound(response)
                    .build());
            responseObserver.onCompleted();
            return;
        }
        responseObserver.onNext(userData);
        responseObserver.onCompleted();
    }

    public void delete(UserId user, StreamObserver<ResponseMessage> responseObserver) {
        userService.deleteById(user.getId());
        ResponseMessage message = ResponseMessage.newBuilder().setStatus(20).setMessage("OK").build();
        responseObserver.onNext(message);
        responseObserver.onCompleted();
    }
}
