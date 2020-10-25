package xyz.study.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Date;

/**
 * @author Tiger
 * @date 2020-10-25
 * @see xyz.study.demo
 **/
@SpringBootApplication
public class JsoupApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsoupApplication.class, args);
    }

    public void test() throws IOException {
        Date date=new Date();
        Document doc = Jsoup.connect("http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=gp&rs=&gs=0&sc=zzf&st=desc&sd=2019-10-25&ed=2020-10-25").get();
        System.out.println(doc.getAllElements());
    }
}
