package com.boardgame.cafe.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String username;

    private String email;

    private String phone;

    private String password;

    @JsonProperty("role_id")
    private Long roleId;
}