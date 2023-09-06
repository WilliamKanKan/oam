package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sztus.oam.lib.cache.core.SimpleRedisRepository;
import com.sztus.oam.lib.core.enumerate.CodeEnum;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.lib.core.type.NumberCode;
import com.sztus.oam.lib.core.type.RedisKeyType;
import com.sztus.oam.lib.core.util.CreatePwUtil;
import com.sztus.oam.employee.object.domain.*;
import com.sztus.oam.employee.object.response.AccountDTO;
import com.sztus.oam.employee.object.response.PrivilegeDTO;
import com.sztus.oam.employee.repository.mapper.AccountMapper;
import com.sztus.oam.employee.repository.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeLoginService {
    @Autowired
    private EmployeeMapper EmployeeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SimpleRedisRepository simpleRedisRepository;

    public JSONObject loginByEmployeename(Employee Employee) {
        AccountDTO accountDTO = new AccountDTO();
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getFirstName, Employee.getFirstName());
        Employee existingEmployee = EmployeeMapper.selectOne(EmployeeLambdaQueryWrapper);
        if (existingEmployee != null) {
            LambdaQueryWrapper<Account> accountLambdaQueryWrapper = new LambdaQueryWrapper<>();
            accountLambdaQueryWrapper.eq(Account::getOpenId, existingEmployee.getOpenId());
            Account account = accountMapper.selectOne(accountLambdaQueryWrapper);
            if (account != null) {
                String codePassword = CreatePwUtil.hashPassword(Employee.getPassword(), account.getSalt());
                if (!account.getPassword().equals(codePassword)) {
                    return JSON.parseObject(AjaxResult.failure("Password is not matched"));
                } else {
                    loginSuccess(account, accountDTO, existingEmployee);
                    return JSONObject.parseObject(AjaxResult.success(account.getAccessToken(), CodeEnum.SUCCESS.getText()));
                }
            } else {
                return JSON.parseObject(AjaxResult.failure("Account not found"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("Employee not exist"));
        }
    }

    public JSONObject loginByTelephone(String telephone, String verifyCode) {
        AccountDTO accountDTO = new AccountDTO();
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getTelephone, telephone);
        Employee existingEmployee = EmployeeMapper.selectOne(EmployeeLambdaQueryWrapper);
        if (existingEmployee != null) {
            LambdaQueryWrapper<Account> accountLambdaQueryWrapper = new LambdaQueryWrapper<>();
            accountLambdaQueryWrapper.eq(Account::getOpenId, existingEmployee.getOpenId());
            Account account = accountMapper.selectOne(accountLambdaQueryWrapper);
            if (account != null) {
                String verifyCodeKey = simpleRedisRepository.generateKey(RedisKeyType.VERIFY_CODE, telephone);
                String code = simpleRedisRepository.get(verifyCodeKey);
                if (!verifyCode.equals(code)) {
                    return JSON.parseObject(AjaxResult.failure("Code is incorrect or expired"));
                } else {
                    loginSuccess(account, accountDTO, existingEmployee);
                    return JSONObject.parseObject(AjaxResult.success(account.getAccessToken(), CodeEnum.SUCCESS.getText()));
                }
            } else {
                return JSON.parseObject(AjaxResult.failure("Account not found"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("Employee not exist"));
        }
    }

    public void loginSuccess(Account account, AccountDTO accountDTO, Employee existingEmployee) {
        String accountKey = simpleRedisRepository.generateKey(RedisKeyType.ACCOUNT, account.getAccessToken());
        String privilegeKey = simpleRedisRepository.generateKey(RedisKeyType.AUTHORIZATION, account.getAccessToken());
        accountDTO.setAccessToken(account.getAccessToken());
        accountDTO.setNickname(existingEmployee.getNickname());
        accountDTO.setAvatarUrl(existingEmployee.getAvatarUrl());
        accountDTO.setPosition(existingEmployee.getPosition());
        accountDTO.setOpenId(existingEmployee.getOpenId());
        accountDTO.setEmployeeId(existingEmployee.getId());
        MPJLambdaWrapper<Employee> mpjLambdaWrapper = new MPJLambdaWrapper<Employee>()
                .select(Resource::getCode)
                .select(RolePrivilege::getPrivilege)
                .select(Resource::getUrl)
                .leftJoin(Role.class, Role::getId, Employee::getPosition)
                .leftJoin(RolePrivilege.class, RolePrivilege::getRoleId, Role::getId)
                .leftJoin(Resource.class, Resource::getId, RolePrivilege::getResourceId).eq(Employee::getId, existingEmployee.getId());
        List<PrivilegeDTO> privileges = EmployeeMapper.selectJoinList(PrivilegeDTO.class, mpjLambdaWrapper);
        simpleRedisRepository.set(privilegeKey, JSON.toJSONString(privileges), NumberCode.EXPIRED_TIME);
        simpleRedisRepository.set(accountKey, JSON.toJSONString(accountDTO), NumberCode.EXPIRED_TIME);
        account.setExpiredAt(System.currentTimeMillis() + NumberCode.EXPIRED_TIME * 1000);
        LambdaUpdateWrapper<Account> accountLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        accountLambdaUpdateWrapper.eq(Account::getOpenId, account.getOpenId());
        accountMapper.update(account, accountLambdaUpdateWrapper);
    }

}
