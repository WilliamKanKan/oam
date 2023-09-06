package com.sztus.oam.gateway;

import com.sztus.oam.lib.core.constant.GlobalConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TimeZone;

/**
 * @author Andy
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = GlobalConst.SCAN_BASE_PACKAGE)
public class GatewayApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(GatewayApplication.class, args);

    }

}
