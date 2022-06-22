package com.register.endservice.endservice.Service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.register.endservice.endservice.Dto.RequestDto.CreateUserRequest;
import com.register.endservice.endservice.Dto.RequestDto.GetUserRequest;
import com.register.endservice.endservice.Dto.RequestDto.UpdateUserRequest;
import com.register.endservice.endservice.Dto.ResponseDto.AllUsersResponse;
import com.register.endservice.endservice.Dto.ResponseDto.CreateUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.FetchUserResponse;
import com.register.endservice.endservice.Dto.ResponseDto.UpdateUserResponse;
import com.register.endservice.endservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {


    private final WebClient webClient;

    @Autowired
    public UserServiceImplementation(WebClient.Builder builder){
        this.webClient = builder.defaultHeader(HttpHeaders.CONTENT_TYPE,"application/json").build();
    }

    @Override
    public Mono<FetchUserResponse> GetUser(String request) throws JsonProcessingException {
        // Convert the request into the java POJO class
        ObjectMapper mapper = new ObjectMapper();
        GetUserRequest getUserRequest = mapper.readValue(request,GetUserRequest.class);
        System.out.println(request);

        //pass it on to the webclient
        Mono<FetchUserResponse> getUserResponse = webClient
                .post()
                .uri("http://localhost:4400/api/register/getuser")
                .body(Mono.just(getUserRequest),GetUserRequest.class)
                .retrieve().bodyToMono(FetchUserResponse.class);
        // Convert the response into a string and return;
        return getUserResponse;
    }

    @Override
    public Flux<AllUsersResponse> GetUsers() {
        return webClient
                .get()
                .uri("http://localhost:4400/api/register/getall")
                .retrieve()
                .bodyToFlux(AllUsersResponse.class);
    }

    @Override
    public Mono<CreateUserResponse> CreateUser(CreateUserRequest newUser) {
        Mono<CreateUserResponse> createUserResponse = webClient
                .post()
                .uri("http://localhost:4400/api/register/create")
                .body(Mono.just(newUser),CreateUserRequest.class)
                .retrieve().bodyToMono(CreateUserResponse.class);
        // Convert the response into a string and return;
        return createUserResponse;
    }

    @Override
    public Mono<String> DeleteUser(Integer Id) {
        return webClient.delete()
                .uri("http://localhost:4400/api/register/delete/?Id=" + Id)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    if(response.statusCode().is4xxClientError()){
                        log.error("Response from Service is 4XX");
                    }else if(response.statusCode().is5xxServerError()){
                        log.error("Response from client is 5XX");
                    }else{
                        log.error("Error from client is 2XX");
                    }
                    return Mono.error(new RuntimeException());
                })
                .bodyToMono(String.class);
    }

    @Override
    public Mono<UpdateUserResponse> Update(UpdateUserRequest updateUserRequest) {
        return webClient
                .post()
                .uri("http://localhost:4400/api/register/edit")
                .body(Mono.just(updateUserRequest),UpdateUserRequest.class)
                .retrieve().bodyToMono(UpdateUserResponse.class);
    }

}
