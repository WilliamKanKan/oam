package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "account")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openId;
    private Integer roleId;
    private String password;
    private String salt;
    private String accessToken;
    private Long expiredAt;
}
