package com.jiahe.jiahecloudspringboot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用加载配置信息工具类
 */
@Configuration
@PropertySource(value = "classpath:application.yml")
public class AppConfig {

    @Value("${app.maxBtnTag}")
    private Integer maxBtnTag;
    @Value("${app.kyPort}")
    private String kyPort;

    public Integer getMaxBtnTag() {
        return maxBtnTag;
    }

    public void setMaxBtnTag(Integer maxBtnTag) {
        this.maxBtnTag = maxBtnTag;
    }

    public String getKyPort() {
        return kyPort;
    }

    public void setKyPort(String kyPort) {
        this.kyPort = kyPort;
    }
}
