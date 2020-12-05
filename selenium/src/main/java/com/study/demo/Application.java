package com.study.demo;

import com.study.demo.config.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author yhhu
 * @date 2020/11/24
 * @description
 */
@SpringBootApplication
public class Application {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        applicationContext = context;
        runAutoChrome();
        System.exit(0);
    }

    private static void runAutoChrome() {
        Properties bean = applicationContext.getBean(Properties.class);
        ChromeOptions options = new ChromeOptions();
// 设置允许弹框
        options.addArguments("disable-infobars", "disable-web-security");
// 设置无gui 开发时还是不要加，可以看到浏览器效果
//        options.addArguments("--headless");
        String driverPath = bean.getDriverPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        RemoteWebDriver driver = new ChromeDriver(options);
        driver.get("http://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("hello");
        driver.findElement(By.id("su")).click();
    }
}
