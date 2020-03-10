package com.study.demo;

import com.study.demo.IOC.IOCLoader;
import com.study.demo.IOC.bean.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

/**
 * @author Tiger
 * @date 2019-12-18
 * @see com.study.demo
 **/
@SpringBootApplication
public class IOC_AOPApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(IOC_AOPApplication.class, args);
        String location= Objects.requireNonNull(IOCLoader.class.getClassLoader().getResource("IOC.xml")).getFile();
        System.out.println(location);
        IOCLoader iocLoader=new IOCLoader(location);
        Student student = (Student) iocLoader.getBean("student");
        System.out.println(student.toString());
    }
}
