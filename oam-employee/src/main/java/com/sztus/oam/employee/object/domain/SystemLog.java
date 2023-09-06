package com.sztus.oam.employee.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "system_log")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemLog implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String logDescription;
    private Integer logType;
    private String operatedBy;
    private Long operatedAt;
    private String original;
    private String current;
    private Integer status;

}
