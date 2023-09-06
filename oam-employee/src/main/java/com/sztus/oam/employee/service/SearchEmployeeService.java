package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.oam.lib.core.enumerate.CodeEnum;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.repository.reader.EmployeeReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchEmployeeService {
    @Autowired
    private EmployeeReader EmployeeReader;

    public JSONObject searchEmployeeForAll(Long pageId,Long pageSize,String nickname){
        Page<Employee> EmployeePage = new Page<>(pageId,pageSize);
        LambdaQueryChainWrapper<Employee> lambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(EmployeeReader.getBaseMapper());
        if(nickname!=null){
            lambdaQueryChainWrapper.like(Employee::getNickname,nickname);
        }
        Page<Employee> page = lambdaQueryChainWrapper.page(EmployeePage);
        return JSONObject.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }
}
