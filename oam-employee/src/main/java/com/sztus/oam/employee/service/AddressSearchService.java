package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.oam.lib.core.enumerate.CodeEnum;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.employee.object.domain.EmployeeAddress;
import com.sztus.oam.employee.repository.reader.EmployeeAddressReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressSearchService {
    @Autowired
    private EmployeeAddressReader EmployeeAddressReader;

    public JSONObject searchEmployeeAddressByEmployeeId(Long EmployeeId){
        LambdaQueryWrapper<EmployeeAddress> EmployeeAddressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeAddressLambdaQueryWrapper.eq(EmployeeAddress::getEmployeeId,EmployeeId);
        List<EmployeeAddress> EmployeeAddressList = EmployeeAddressReader.list(EmployeeAddressLambdaQueryWrapper);
        if(!EmployeeAddressList.isEmpty()){
            return JSON.parseObject(AjaxResult.success(EmployeeAddressList, CodeEnum.SUCCESS.getText()));

        }else {
            return JSON.parseObject(AjaxResult.failure("No data found"));
        }
    }
}
