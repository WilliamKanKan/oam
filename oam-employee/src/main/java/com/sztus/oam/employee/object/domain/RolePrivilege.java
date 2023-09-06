package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName(value ="role_privilege")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolePrivilege implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long roleId;
    private Long resourceId;
    private int privilege;
}
