package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.lib.core.type.NumberCode;
import com.sztus.oam.lib.core.util.CreatePwUtil;
import com.sztus.oam.lib.core.util.NumVerifyUtil;
import com.sztus.oam.lib.core.util.UuidUtil;
import com.sztus.oam.employee.object.domain.Account;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.repository.mapper.AccountMapper;
import com.sztus.oam.employee.repository.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeRegisterService {
    @Autowired
    private EmployeeMapper EmployeeMapper;
    @Autowired
    private AccountMapper accountMapper;

    // Options注解的作用是主键返回，因为注册时需要在Employee以外其他的表存入Employee_id,而这个Employee_id就是Employee表里的id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject register(Employee Employee) {
        String emailValidationResult = NumVerifyUtil.emailMatcher(Employee.getEmail());
        String passwordValidationResult = NumVerifyUtil.passwordMatcher(Employee.getPassword());
        String EmployeenameValidationResult = NumVerifyUtil.EmployeenameMatcher(Employee.getFirstName());
        String phoneNumberValidationResult = NumVerifyUtil.phoneNumberMatcher(Employee.getTelephone());
        Account account = new Account();
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getFirstName, Employee.getFirstName()).or().eq(Employee::getEmail, Employee.getEmail())
                                                                        .or().eq(Employee::getNickname, Employee.getNickname())
                                                                        .or().eq(Employee::getTelephone, Employee.getTelephone());
        List<Employee> existingEmployeeList = EmployeeMapper.selectList(EmployeeLambdaQueryWrapper);
        for (Employee existingEmployee : existingEmployeeList) {
            if (existingEmployee != null) {
                if (existingEmployee.getFirstName().equals(Employee.getFirstName())) {
                    return JSON.parseObject(AjaxResult.failure("Employeename already exists"));
                } else if(existingEmployee.getEmail().equals(Employee.getEmail())) {
                    return JSON.parseObject(AjaxResult.failure("email already exists"));
                }
                else if(existingEmployee.getNickname().equals(Employee.getNickname())) {
                    return JSON.parseObject(AjaxResult.failure("nickname already exists"));
                } else {
                    return JSON.parseObject(AjaxResult.failure("telephone already exists"));
                }
            }
        }

        // 验证用户名格式
        if (!EmployeenameValidationResult.equals("success")) {
            return JSON.parseObject(EmployeenameValidationResult);
        }
        // 验证邮箱格式
        else if (!emailValidationResult.equals("success")) {
            return JSON.parseObject(emailValidationResult);
        }
        // 验证手机格式
        else if (!phoneNumberValidationResult.equals("success")) {
            return JSON.parseObject(phoneNumberValidationResult);
        }
        // 验证密码格式
        else if (!passwordValidationResult.equals("success")) {
            return JSON.parseObject(passwordValidationResult);
        }
        // 验证密码和确认密码是否匹配
        else if (!Employee.getPassword().equals(Employee.getConfirmPassword())) {
            return JSON.parseObject(AjaxResult.failure("InputPassword does not match with ConfirmPassword"));
        } else {
            // 全部验证通过后，将需要的值全部set到对应的对象上，最后再存入到数据库，注意：因为其他关联表需要Employee_id,所以要先存Employee表的数据
            String salt = CreatePwUtil.generateSalt();
            String uuid = UuidUtil.getUuid();
            String accessToken = UuidUtil.getUuid();
            account.setPassword(CreatePwUtil.hashPassword(Employee.getPassword(), salt));
            account.setSalt(salt);
            account.setRoleId(NumberCode.Employee);
            account.setOpenId(uuid);
            account.setAccessToken(accessToken);
            Employee.setOpenId(uuid);
            Employee.setPosition(NumberCode.Employee);
            Employee.setCreatedAt(System.currentTimeMillis());
            Employee.setUpdatedAt(System.currentTimeMillis());
            accountMapper.insert(account);
            EmployeeMapper.insert(Employee);
            return JSONObject.parseObject(AjaxResult.success());
        }
    }
}

