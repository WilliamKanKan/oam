package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "department")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long companyId;
    private Long siteId;
    private Long parentId;
    private String departmentName;
    private Long status;
    private Long createdAt;
    private Long updatedAt;
}
