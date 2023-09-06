package com.sztus.oam.employee.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO implements Serializable {
    private Integer position;
    private Long EmployeeId;
    private String openId;
    private String nickname;
    private String avatarUrl;
    private String accessToken;
}
