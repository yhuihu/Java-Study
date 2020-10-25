package xyz.study.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author Tiger
 * @date 2020-10-25
 * @see xyz.study.demo
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JsoupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {
    @Test
    public void test() throws IOException {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(Calendar.getInstance().getTimeInMillis());
//        }
//        long l = new Random(System.currentTimeMillis() * 1000).nextLong();
//        Document doc = Jsoup.connect("http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=gp&rs=&gs=0&sc=zzf&st=desc&sd=2019-10-25&ed=2020-10-25"+ Calendar.getInstance().getTimeInMillis()).get();
        Document doc = Jsoup.connect("http://fund.eastmoney.com/data/fundranking.html#thh;c0;r;szzf;pn50;ddesc;qsd20191025;qed20201025;qdii;zq;gg;gzbd;gzfs;bbzt;sfbb").get();
        System.out.println(doc.getAllElements());

    }
}
