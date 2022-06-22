package com.register.endservice.endservice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.register.endservice.endservice.Dto.RequestDto.CreateUserRequest;
import com.register.endservice.endservice.Dto.RequestDto.UpdateUserRequest;
import com.register.endservice.endservice.Dto.ResponseDto.AllUsersResponse;
import com.register.endservice.endservice.Dto.ResponseDto.CreateUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.FetchUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.UpdateUserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<FetchUserResponse> GetUser (String request) throws JsonProcessingException;
    Flux<AllUsersResponse> GetUsers();
    Mono<CreateUserResponse> CreateUser(CreateUserRequest newUser);
    Mono<String> DeleteUser(Integer Id);
    Mono<UpdateUserResponse> Update(UpdateUserRequest updateUserRequest);
}
