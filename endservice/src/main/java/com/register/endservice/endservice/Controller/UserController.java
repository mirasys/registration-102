package com.register.endservice.endservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.register.endservice.endservice.Dto.RequestDto.CreateUserRequest;
import com.register.endservice.endservice.Dto.RequestDto.GetUserRequest;
import com.register.endservice.endservice.Dto.RequestDto.UpdateUserRequest;
import com.register.endservice.endservice.Dto.ResponseDto.AllUsersResponse;
import com.register.endservice.endservice.Dto.ResponseDto.CreateUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.FetchUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.UpdateUserResponse;
import com.register.endservice.endservice.Service.Impl.UserServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/service")
public class UserController {

    private UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<FetchUserResponse> getUser(@RequestBody String request) throws JsonProcessingException {
        return userService.GetUser(request);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<AllUsersResponse> getUsers(){
        return userService.GetUsers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<CreateUserResponse> create(@RequestBody CreateUserRequest request){
        return userService.CreateUser(request);
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> deleteUser(@RequestParam("Id") Integer Id){
        return userService.DeleteUser(Id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UpdateUserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return userService.Update(updateUserRequest);
    }

}
