package com.register.endservice.endservice.Dto.RequestDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRequest {
    private Integer id;
    private String username;
    private String password;
}
