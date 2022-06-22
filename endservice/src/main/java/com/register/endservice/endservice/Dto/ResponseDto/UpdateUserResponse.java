package com.register.endservice.endservice.Dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor
public class UpdateUserResponse {
    private Integer id;
    private String username;
    private String password;
    private String title;
    private String firstName;
    private String lastName;
    private String sex;
    private Long bvn;
    private String postalAddress;
    private String homeAddress;
    private String emailAddress;
    private Long mobileNumber;
}
