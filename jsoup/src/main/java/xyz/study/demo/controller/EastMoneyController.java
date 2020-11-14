package xyz.study.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.study.demo.entity.FundData;
import xyz.study.demo.vo.ChooseFund;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yhhu
 * @date 2020/11/1
 * @description
 */
@RestController
public class EastMoneyController {
    @RequestMapping()
    public ResponseEntity<List<ChooseFund>> highInCome() throws IOException {
        Connection connect = Jsoup.connect("https://fundapi.eastmoney.com/fundtradenew.aspx?ft=hh&sc=1n&st=desc&pi=1&pn=100&cp=&ct=&cd=&ms=&fr=&plevel=&fst=&ftype=&fr1=&fl=0&isab=1");
        Map<String, String> map = new HashMap<>();
        map.put("Referer", "http://fund.eastmoney.com/");
        map.put("Host", "fundapi.eastmoney.com");
        connect.headers(map);
        Connection.Response execute = connect.execute();
        FundData fundData = JSONObject.parseObject(execute.body().split("var rankData = ")[1].split(";")[0], FundData.class);
        List<String> datas = fundData.getDatas();
        List<ChooseFund> list = new LinkedList<>();
        datas.forEach(item -> {
            String[] args = item.split("\\|");
            ChooseFund chooseFund = new ChooseFund(args[0], args[1], args[14]);
            list.add(chooseFund);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
