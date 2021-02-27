package xyz.study.demo;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.study.demo.entity.FundData;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();
        map.put("Referer", "http://fund.eastmoney.com/");
        map.put("Host", "fundapi.eastmoney.com");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        map.put("Cookie", "em_hq_fls=js; qgqp_b_id=9715e29311d3fc5888ee05d9afbfcb92; AUTH_FUND.EASTMONEY.COM_GSJZ=AUTH*TTJJ*TOKEN; waptgshowtime=2020917; st_si=25027075577965; ASP.NET_SessionId=t1vuewxy0cbz5wgyu2adoib5; HAList=a-sz-002127-%u5357%u6781%u7535%u5546%2Cd-hk-06862%2Ca-sz-000066-%u4E2D%u56FD%u957F%u57CE%2Cf-0-399006-%u521B%u4E1A%u677F%u6307; cowCookie=true; intellpositionL=1215.35px; st_asi=delete; intellpositionT=499.8px; searchbar_code=320007; EMFUND1=09-19%2013%3A04%3A14@%23%24%u9E4F%u626C%u5229%u6CA3%u77ED%u503AE@%23%24006831; EMFUND0=09-19%2001%3A35%3A46@%23%24%u5609%u5B9E%u589E%u957F%u6DF7%u5408@%23%24070002; EMFUND2=09-19%2013%3A42%3A08@%23%24%u519C%u94F6%u65B0%u80FD%u6E90%u4E3B%u9898@%23%24002190; EMFUND3=09-19%2016%3A40%3A24@%23%24%u94F6%u6CB3%u521B%u65B0%u6210%u957F%u6DF7%u5408@%23%24519674; EMFUND4=09-19%2016%3A50%3A54@%23%24%u4E2D%u878D%u4EA7%u4E1A%u5347%u7EA7%u6DF7%u5408@%23%24001701; EMFUND5=09-19%2022%3A34%3A36@%23%24%u5357%u534E%u745E%u626C%u7EAF%u503AC@%23%24005048; EMFUND6=09-19%2021%3A51%3A18@%23%24%u5DE5%u94F6%u65B0%u8D8B%u52BF%u7075%u6D3B%u914D%u7F6E%u6DF7%u5408A@%23%24001716; EMFUND7=09-19%2022%3A36%3A19@%23%24%u534E%u5B89%u521B%u4E1A%u677F50%u6307%u6570%u5206%u7EA7B@%23%24150304; EMFUND9=09-20%2010%3A03%3A59@%23%24%u8BFA%u5B89%u6210%u957F%u6DF7%u5408@%23%24320007; EMFUND8=09-20 10:24:15@#$%u5B89%u4FE1%u6C11%u7A33%u589E%u957F%u6DF7%u5408C@%23%24008810; st_pvi=58947008966039; st_sp=2020-07-25%2000%3A32%3A56; st_inirUrl=https%3A%2F%2Fwww.eastmoney.com%2F; st_sn=294; st_psi=20200920102414487-0-3267835636");
        LocalDate nowDate = LocalDate.now();
        String url = "http://fund.eastmoney.com/data/rankhandler.aspx?op=dy&dt=kf&ft=all&rs=&gs=0&sc=qjzf&st=desc&sd=" + nowDate.toString() + "&ed=" + nowDate.toString() + "&es=0&qdii=&pi=1&pn=50" + "&dx=0&v=0." + Calendar.getInstance().getTimeInMillis();
        Connection connect = Jsoup.connect(url);
        connect.timeout(99999999);
        connect.headers(map);
        Connection.Response execute = connect.execute();
        FundData fundData = JSONObject.parseObject(execute.body().split("var rankData = ")[1].split(";")[0], FundData.class);
        fundData.getDatas().forEach(System.out::println);
    }

    @Test
    public void hotTopic() throws IOException {
        String url = "http://api.fund.eastmoney.com/ztjj/GetZTJJList?callback=json&tt=0&dt=syl&st=SYL_W&_=" + System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        map.put("Referer", "http://fund.eastmoney.com/");
        map.put("Host", "fundapi.eastmoney.com");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        map.put("Content-Type", "application/json");
        map.put("Cookie", "em_hq_fls=js; qgqp_b_id=9715e29311d3fc5888ee05d9afbfcb92; AUTH_FUND.EASTMONEY.COM_GSJZ=AUTH*TTJJ*TOKEN; waptgshowtime=2020917; st_si=25027075577965; ASP.NET_SessionId=t1vuewxy0cbz5wgyu2adoib5; HAList=a-sz-002127-%u5357%u6781%u7535%u5546%2Cd-hk-06862%2Ca-sz-000066-%u4E2D%u56FD%u957F%u57CE%2Cf-0-399006-%u521B%u4E1A%u677F%u6307; cowCookie=true; intellpositionL=1215.35px; st_asi=delete; intellpositionT=499.8px; searchbar_code=320007; EMFUND1=09-19%2013%3A04%3A14@%23%24%u9E4F%u626C%u5229%u6CA3%u77ED%u503AE@%23%24006831; EMFUND0=09-19%2001%3A35%3A46@%23%24%u5609%u5B9E%u589E%u957F%u6DF7%u5408@%23%24070002; EMFUND2=09-19%2013%3A42%3A08@%23%24%u519C%u94F6%u65B0%u80FD%u6E90%u4E3B%u9898@%23%24002190; EMFUND3=09-19%2016%3A40%3A24@%23%24%u94F6%u6CB3%u521B%u65B0%u6210%u957F%u6DF7%u5408@%23%24519674; EMFUND4=09-19%2016%3A50%3A54@%23%24%u4E2D%u878D%u4EA7%u4E1A%u5347%u7EA7%u6DF7%u5408@%23%24001701; EMFUND5=09-19%2022%3A34%3A36@%23%24%u5357%u534E%u745E%u626C%u7EAF%u503AC@%23%24005048; EMFUND6=09-19%2021%3A51%3A18@%23%24%u5DE5%u94F6%u65B0%u8D8B%u52BF%u7075%u6D3B%u914D%u7F6E%u6DF7%u5408A@%23%24001716; EMFUND7=09-19%2022%3A36%3A19@%23%24%u534E%u5B89%u521B%u4E1A%u677F50%u6307%u6570%u5206%u7EA7B@%23%24150304; EMFUND9=09-20%2010%3A03%3A59@%23%24%u8BFA%u5B89%u6210%u957F%u6DF7%u5408@%23%24320007; EMFUND8=09-20 10:24:15@#$%u5B89%u4FE1%u6C11%u7A33%u589E%u957F%u6DF7%u5408C@%23%24008810; st_pvi=58947008966039; st_sp=2020-07-25%2000%3A32%3A56; st_inirUrl=https%3A%2F%2Fwww.eastmoney.com%2F; st_sn=294; st_psi=20200920102414487-0-3267835636");
        Connection connect = Jsoup.connect(url).ignoreContentType(true);
        connect.timeout(99999999);
        connect.headers(map);
        Connection.Response execute = connect.execute();
        String body = execute.body();
//        List<HotTopic> list = new ArrayList<>();
        Map<String, Object> hotTopicByWeek = JSONObject.parseObject(body.substring(5, body.length() - 1), Map.class);
        System.out.println(hotTopicByWeek);
    }

    @Test
    public void testPlugin() throws IOException {
        String url = "https://fundmobapi.eastmoney.com/FundMNewApi/FundMNFInfo?pageIndex=1&pageSize=200&plat=Android&appType=ttjj&product=EFund&Version=1&deviceid=e2064f69-3af6-426d-a1e5-aa6e9a5452da&Fcodes=001606,519126,519736,001630,008282";
        Map<String, String> map = new HashMap<>();
        map.put("Host", "fundmobapi.eastmoney.com");
        Connection connect = Jsoup.connect(url).ignoreContentType(true);
        Connection.Response execute = connect.execute();
        String body = execute.body();
        System.out.println(body);
    }
}
