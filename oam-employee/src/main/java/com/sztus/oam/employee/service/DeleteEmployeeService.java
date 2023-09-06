package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.repository.writer.EmployeeWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteEmployeeService {
    @Autowired
    private EmployeeWriter EmployeeWriter;

    public JSONObject deleteEmployeeById (Long id){
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getId,id);
        Employee Employee = EmployeeWriter.getOne(EmployeeLambdaQueryWrapper);
        if(Employee!=null){
            boolean deleteResult = EmployeeWriter.removeById(Employee);
            if(deleteResult){
                return JSON.parseObject(AjaxResult.success());
            }else {
                return JSON.parseObject(AjaxResult.failure());
            }
        }else {
            return JSON.parseObject(AjaxResult.failure("Employee not exists!"));
        }
    }

}
