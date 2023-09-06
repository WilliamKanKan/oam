package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "sign_in_record")
public class SignInRecord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer type;
    private Long signInAt;
    private String ip;
    private String userAgent;
    private String signInCode;
    private String userData;

}
