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

import java.util.List;

@Service
@Slf4j
public class EditEmployeeService {
    @Autowired
    private EmployeeWriter EmployeeWriter;

    public JSONObject editEmployeeById(Long id, Employee Employee) {
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getId, id);
        Employee newEmployee = EmployeeWriter.getOne(EmployeeLambdaQueryWrapper);

        if (newEmployee != null) {
            LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.notIn(Employee::getId, id);
            List<Employee> EmployeeList = EmployeeWriter.list(lambdaQueryWrapper);

            for (Employee existingEmployee : EmployeeList) {
                if (existingEmployee.getFirstName().trim().equals(Employee.getFirstName().trim())) {
                    return JSON.parseObject(AjaxResult.failure("用户名已存在！"));
                }
                if (existingEmployee.getTelephone().trim().equals(Employee.getTelephone().trim())) {
                    log.info(existingEmployee.getNickname());
                    log.info(Employee.getNickname());
                    return JSON.parseObject(AjaxResult.failure("手机号已存在！"));
                }
                if (existingEmployee.getNickname().trim().equals(Employee.getNickname().trim())) {
                    return JSON.parseObject(AjaxResult.failure("昵称已存在！"));
                }
                if (existingEmployee.getEmail().trim().equals(Employee.getEmail().trim())) {
                    return JSON.parseObject(AjaxResult.failure("邮箱已存在！"));
                }
            }

            newEmployee.setFirstName(Employee.getFirstName());
            newEmployee.setGender(Employee.getGender());
            newEmployee.setPosition(Employee.getPosition());
            newEmployee.setBirthday(Employee.getBirthday());
            newEmployee.setAvatarUrl(Employee.getAvatarUrl());
            newEmployee.setStatus(Employee.getStatus());
            newEmployee.setUpdatedAt(System.currentTimeMillis());
            newEmployee.setNickname(Employee.getNickname());

            boolean updateResult = EmployeeWriter.updateById(newEmployee);
            if (updateResult) {
                return JSON.parseObject(AjaxResult.success());
            } else {
                return JSON.parseObject(AjaxResult.failure("编辑操作未成功"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("用户不存在"));
        }
    }
}



