package com.sztus.oam.employee;


import com.sztus.oam.lib.core.constant.GlobalConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = GlobalConst.SCAN_BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = GlobalConst.SCAN_BASE_PACKAGE)
public class EmployeeApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(EmployeeApplication.class, args);

    }

}
