package com.sztus.oam.employee.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.oam.lib.cache.core.SimpleRedisRepository;
import com.sztus.oam.lib.core.enumerate.CodeEnum;
import com.sztus.oam.lib.core.type.AjaxResult;
import com.sztus.oam.lib.core.type.NumberCode;
import com.sztus.oam.lib.core.type.RedisKeyType;
import com.sztus.oam.employee.object.domain.Employee;
import com.sztus.oam.employee.repository.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class SendCodeService {
    @Autowired
    private EmployeeMapper EmployeeMapper;
    @Autowired
    private SimpleRedisRepository simpleRedisRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public JSONObject codeSend(String telephone,String email) {
        LambdaQueryWrapper<Employee> EmployeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        EmployeeLambdaQueryWrapper.eq(Employee::getTelephone, telephone);
        Employee Employee = EmployeeMapper.selectOne(EmployeeLambdaQueryWrapper);
        if (Employee != null) {
            String verifyCodeKey = simpleRedisRepository.generateKey(RedisKeyType.VERIFY_CODE, telephone);
            String code = simpleRedisRepository.get(verifyCodeKey);
            if (code == null) {
                String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
                simpleRedisRepository.set(verifyCodeKey, verifyCode, NumberCode.TIMEOUT);
                sendEmail(email, "Verification Code", "Your verification code is: " + verifyCode + ", 5 minutes expired!");
                JSONObject response = JSONObject.parseObject(AjaxResult.success(CodeEnum.SUCCESS.getText()));
                response.remove("data");
                return response;
            } else {
                return JSON.parseObject(AjaxResult.failure("Code exist"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("Employee not exist"));
        }

    }
    public void sendEmail(String recipientEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(NumberCode.EMAIL_FROM);
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
