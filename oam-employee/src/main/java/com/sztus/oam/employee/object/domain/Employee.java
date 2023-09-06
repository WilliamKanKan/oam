package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName(value ="employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long departmentId;
    private Long siteId;
    private String position;
    private String openId;
    private String avatarUrl;
    private String employeeNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String telephone;
    private Integer status;
    private Long createdAt;
    private Long updatedAt;
    private Integer feature;
    private Integer category;
    private Integer level;
}
