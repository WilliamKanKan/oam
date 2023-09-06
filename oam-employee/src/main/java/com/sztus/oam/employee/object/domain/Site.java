package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "site")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Site implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String address;
    private String siteName;
    private Long status;
    private String telephone;
    private Long createdAt;
    private Long updatedAt;
}

