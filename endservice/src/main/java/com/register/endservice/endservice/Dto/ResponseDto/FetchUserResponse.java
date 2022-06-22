package com.register.endservice.endservice.Dto.ResponseDto;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class FetchUserResponse {
    private String title;
    private String firstName;
    private String lastName;
    private String sex;
    private Long bvn;
    private String homeAddress;
    private String emailAddress;
    private Long mobileNumber;
}
