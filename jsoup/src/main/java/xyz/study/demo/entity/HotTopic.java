package xyz.study.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yhhu
 * @date 2020/12/12
 * @description http://api.fund.eastmoney.com/ztjj/GetZTJJList?callback=jQuery18303824361632463704_1607758038102&tt=0&dt=syl&st=SYL_W&_=1607760198055
 */
@Data
public class HotTopic {
    //    索引
    private String IndexCode;
    //    行业代码
    private String BKCode;
    //    行业名称
    private String BKName;
    //    交易日
    private String TradeDate;
    //    最新价格
    private BigDecimal NewPrice;
    //    实时涨幅
    private BigDecimal ZDF;
    //    近一周
    private BigDecimal SYL_W;
    //    近一月
    private BigDecimal SYL_M;
    //    近一季
    private BigDecimal SYL_Q;
    //    近一年
    private BigDecimal SYL_1N;
}
