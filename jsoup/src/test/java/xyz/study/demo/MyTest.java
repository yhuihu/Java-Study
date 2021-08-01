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

    @Test
    public void translate() throws IOException {
//        String message = "流程ID,申请编号,业务受理号,操作类型,发起集中清分录入类型,机构号,柜员号,电子柜员号,运行状态,分行号,核心预开户账户会计日期,流程是否取消,影像id,扩展字段1,创建时间,更新时间,完成时间,主键,流程编号,交易码,标准清分、录入ID,统一任务号,任务类型,交易日期,交易开始时间,交易完成时间,业务ID,要素Id,要素名字,要素别名,要素值描述,要素的值,操作柜员,机构,是否退件:0-不退件,1-退件,退件Id,任务开始时间,任务结束时间,页码,凭证编号,凭证名称,CBR修改之前的FACTOR_VALUE数据,任务ID,任务状态,任务拥有者,任务执行者,执行机构,业务节点id,业务节点名称,任务创建时间,任务领取时间,功能码,主键id,活动编号,活动状态,推送状态,异常重试次数,请求报文,开始时间戳,结束时间戳,调用交易,凭证类型,凭证名字,退件码,退件类型,退件说明,退件备注,退件时间,退件来源,CBR是否修改,请求信息,响应信息,错误码,错误信息,生成时间,ID,是否已删除,影像页码,录入发送标记,录入状态,清分任务Id,录入任务Id,退件去向（CBD/CBE/CBR/CTA）,接口名称,请求报文结构,请求类型（JSON）,sqlMap查询名称,查询结果前缀,键名,索引,全局流水号,支取凭证类型,办理人类型,协定存款操作类型,是否为提前结清,申请状态,足迹状态,拟开户网点省,拟开户网点市,拟开户网点号,拟开户网点名,开户申请人,开户申请人证件种类,开户申请人证件号,开户申请人证件有效期限起始日,开户申请人证件有效期限截止日,开户申请人手机号,开户申请人邮箱,单位名称,开户主证类型,证明文件编号,证照-注册地址,证照-法定代表人/单位负责人姓名,证照-注册资金,证照-注册资金币种,证照-经营范围,证照-成立日期,证照-营业期限(起),证照-营业期限(止),账户名称,账号,账号生成方式, 预留账号, 客户号,客户类型,单位通讯地址,上级选择类型,上级单位名称,上级单位基本户开户许可证核准号,上级单位组织机构代码,上级单位负责人姓名,上级单位负责人证件种类,上级单位负责人证件号码,上级单位负责人证件有效期至,控制人员是否为单位法人/负责人,控制人员类型,控制人员操作类型,个人姓名/单位名称,证件种类,证件号码,证件有效期至,签约项目,预约开户日期,预约开户时间,法人证件影像ID,企业联网核查结果,全部流程完成标志,扩展字段,申请完成时间,申请人人脸识别结果,检索流水号,反洗钱是否命中,融安E信是否命中,是否勾选同意协议,足迹3状态,开户许可证核准号,开户许可证开户银行,开户许可证账号,开户许可证账号种类,主证是否是营业执照,开户联系人核查结果,法人是否同开户申请人,法人核查结果,拟开户网点区,行业类别,自动报备标记,通讯地址-国家,通讯地址-省,通讯地址-市,通讯地址-区,通讯地址-详细信息,通讯地址同,经营地址-国家,经营地址-省,经营地址-市,经营地址-区,经营地址-详细信息,经营地址同,单位账户性质,机构信用代码,税务登记证号,国税登记证号,地税登记证号,税务登记证有效期至,推介人工号,推介人所属机构,第三方渠道平台名称,本异地标志,机构信用代码-有效期至,微信公众号APPID,云端对公预开户推荐人名字,云端对公预开户推荐人工号,云端对公预开户推荐人网点,支票协议版本号,单位结算账户协议版本号,单位结算卡服务协议版本号,手机银行服务协议版本号,网银服务协议版本号,云端扫码流水号,邮编,分行审核结果（电子流）,分行审核日期（电子流）,人行核准结果（电子流）,人行核准日期（电子流）,人行审核退件原因,退件任务手工核准登记人行核准日期,是否为金融机构清算交收账户,账户类型,币种,分行审核退件原因,分行或人行审核退件标识,0-同开户申请人,1-同开户法人,见证行客户经理号,见证行客户经理名,尽职调查方式,见证行机构号,见证行机构名,见证行省直分行号,见证行分行号,见证行支行号,签约操作员 模式,来源渠道,控制人种类,交易类型,换单申请行,交易柜员号,交易机构号,交易柜员名称,交易机构名称,交易发起日期,业务类型,处理方式,客户账号,子账户序号,客户账户名称,负债账号 ,货币代号,账户钞汇标志,产品编号,账户分类,账户属性,支付条件 ,查询密码,打印标志,原凭证种类,原凭证批号,原凭证序号,凭证种类,凭证批号,凭证序号,摘要代码,摘要描述,经办人名称,经办人证件类型,经办人证件号码,备注信息,密码种类,证明种类,时点余额类型,合计金额,客户中文名,客户英文名,存款证明开立账户输入列表,操作标志,客户账号类型,集团母客户号,基准利率,当前执行利率,利率浮动类型,利率浮动值,利率浮动比例,利率生效日期,实际利率,优惠标志,利率编号 ,优惠值,利率浮动标志1,多层利率信息,交易币种,增减利息标志,利息调整金额,冲补账类型,销账待销账序号,错账原日期,错账客户账号,错账子账号序号,错账借贷标志,错账账户名称,错账货币代号,错账交易金额,补账借贷标志,补账客户账号,补账子账户序号,补账货币代号,补账钞汇标志,补账账户名称,补账金额,备注,实际利息发生额,调整后利息,产品代码,新产品编号,是否标志,组合产品号 ,存期,产品类型,产品所属对象,产品状态 ,是否透支定义,起始笔数,查询笔数,产品定活标志,渠道,适用机构类型,客户号 ,存款种类,冻结编号,冻/控种类,目前冻/控状态,执法人员姓名,执法人员证件号码,是否打印,交易柜员,备付金账户标志,凭证处理标志,凭证信息,产品说明,是否自选号码,客户自选号码,起息方式,起息日期,到期日期 ,计息标志,资金来源,开户金额 ,证明文件种类,转存方式 ,转存存期,转存金额,本金/利息转入账号,本金/利息转入系统账号,取息间隔,通兑范围,交易密码,资金来源账号,客户名称,钞汇标志,付款账户支付条件,签发日期,支付密码,外管账户性质,贷方累计限额,核准件编号,合同编号,是否外汇监管标志,外汇核查标志,备用金额,账户有效期 ,现金项目代码,是否收费标志,应收费用,账户分类代码1,账户分类代码2,账户分类代码3,揽存人员,账户经理名称,账户归属行行号,开户冻结标志,可售产品编号,可售产品名称,账户英文名称,账户英文简称,账户中文简称,领用支票标志,贷方累计限额币种,利率档次,账户到期日 ,来源账户账号,来源账户名称,来源账户行名,来源账户行号,付息频率,付款账户名称,相关业务编号,白名单标志,现金管理签约标志,托管类账户标志,自贸区账户标志,财政账户标志,对方金融机构代码,对方金融机构名称,监管账户类型,监管账户标志,推荐人信息列表,自贸区账户类型,财政存款账户类型,托管账户类型,同业存放账户类型,备付金账户类型,备用字符01,备用金额01,是否设置限额标志,开户机构,计费标志,资金监管标志,余额总账同步标志,新开客户账号标志,利息转入账号,利息转入客户账号,利息转入客户序号,指定去向,去向解除方式,定向客户账号,定向账户序号,罚息代码,预付息标志,预付息代码,付息日期确定方式,预付息付息频率,指定起始付息日期,利息分配方式,利息分配次数,预付息资金去向,支付计划列表,罚息标志,违约利率,逾期利率,责任中心,业务条线,业务性质,扶贫性质,对方责任中心,对方业务条线,对方业务性质,对方扶贫性质,允许现金存入标志,允许转账存入标志,允许现金支取标志,允许转账支取标志,农民工两类账户标志,定价日期,利率代码类型,产品利率代码,利率浮动标志,浮动周期,产品利率,开户金额,是否允许优惠,分行代码,期次,渠道类型,客户号,客户级别,是否员工客户,收费客户账户类型,卡产品编号,是否登记利率明细,利率靠档方式,利率编号类型,金额类型扩展维度,字符类型扩展维度,存期类型扩展维度,地区代码,外部信用等级,账户序号,允许账户序号为空标志,是否查询虚账户标志,是否查询销户账户标志,是否查询关联账户,查询类型,冻结操作标志,控制编号,控制种类,限制类型,执法人员1证件种类,交易金额,审批人,标志值,需控制/解控金额,解控金额,已解控金额,是否自动解控标志,冻结范围,控制终止日期,控制原因,控制通知书编号,控制证明文书类别,冻结来源,执法部门名称,执法人员1姓名,执法人员1证件号码,销户方式,账户余额,找零金额,通知编号,资金去向,取现币种,现金金额,挂失处理编号 ,开通日期,转入客户账号,组合账户序号,转入货币,转入钞汇标志,转入账户名称,销户金额 ,代理人证件类型,代理人证件号码,代理人姓名,找零币种,倒扣金额,发起类型,重账检查标志,行内行外,指定支付渠道,支付场景,优先级,业务种类,转账方式,指定日期,收款账户类型,收款账号,收款户名,收款行号,收款地址,是否待销账,付款账户类型,付款账号,付款户名,付款地址,现金科目代码,货币符号,密码校验标志,支付密码流水,凭证号码,凭证日期,批次号,证件类型,证件名称,代理标志,代理姓名,代理证件类型,代理证件号码,费用合计,工本费,邮电费,手续费,代理付款标志,代理付款账号,代理付款户名,票据号码,票据种类,票据日期,赔偿金额,拒付金额,原托金额,支付金额,多付金额,关联委托日期,关联业务参考号,费用编码,发报行的收费,收报行的收费,跨境附言,付款人账号2,付款人名称2,付款人地址2,收款人账号2,收款人名称2,收款人地址2,出票人名称,票据金额,票据张数,牌价,申请人账号,申请人名称,实际结算金额,汇票种类,拆借利率,拆借期限,收费单位流水号,所属期间,缴费类型,收费附言,手机号码,收费账户,收费户名,收费代码,付款账户子账户序号,收款账户子账户序号,扣划方式,原执法部门名称,扣划文书号,执法部门,是否扣划利息,查证平台日期,查证平台流水,确认标志,柜员流水号,记账凭证序号,核算机构,借贷标志,科目号,记账金额,记账账号,套号,原错账日期,原错账流水,发起方系统标识,全局流水,表内表外标志,新凭证种类,新凭证批号,新凭证序号,收款人客户账号,收款人子账户序号,收款人账号类型,收款人账户名称,收款人币种,收款人钞汇,现转标志,对方子账户序号,销账序号,利息,利息税,实际支取金额,单位开户行行名,产品期次名称,产品期次编号,罚息金额,核心流水,日期,机构名称,经办柜员号,经办柜员姓名,经办人姓名,经办人证件种类,期限,利率,原产品编号,原账户属性,现产品编号,现账户属性,调整原因,协定留存金额,生效日期,负债产品失效日,协定利率,执行利率,签约到期自动续约标志,协定存款标志,交易机构,交易时间,授权柜员,协定存款记录列表信息,利率名称,账户状态,层次利率编号,证明接收人,委托单位,存款证明登记号,存款证明账户明细列表,当前账户余额,开户日期 ,原错账流水号,转出明细,转出记账账户类型,转出机构,业务编码,账号序号,转出待销账序号,业务参考号,转入明细,转入记账账户类型,产品代码2,对方账号,转入机构,业务编码2,对方账号序号,转入待销账序号,业务参考号2,对方账户名称,转出账户明细,转入账户明细,查询打印标识,营业机构,柜员代码,尾箱号,尾箱权限状态,尾箱标志,尾箱类别,查询范围标志,笔数,成员数量,列表信息,对方柜员,控制列表类型,维护机构,维护柜员,维护日期,维护柜员流水,控制列表1,报表路径";
        String message = "流程ID";
        String url = "https://fanyi.baidu.com/v2transapi?from=zh&to=en";
        Map<String, String> map = new HashMap<>();
        map.put("Referer", "https://fanyi.baidu.com/translate?aldtype=16047&query=1&keyfrom=baidu&smartresult=dict&lang=auto2zh");
        map.put("Host", "fanyi.baidu.com");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.864.41");
        map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Cookie", "BIDUPSID=3E1AD90E834BB4567E5AA7A94B360FEC; PSTM=1604068366; BAIDUID=3E1AD90E834BB4562AF57294C79BB907:FG=1; BDUSS=NRbXJTVjY5OWxCLW9oVDJLczBjdmh2Z2xyZ2xCOTBiSFhJMVRRRmUwNkNrfkpmSVFBQUFBJCQAAAAAAAAAAAEAAAAnjOYpYTEzODI2NTM5NjA4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIIGy1-CBstfR2; BDUSS_BFESS=NRbXJTVjY5OWxCLW9oVDJLczBjdmh2Z2xyZ2xCOTBiSFhJMVRRRmUwNkNrfkpmSVFBQUFBJCQAAAAAAAAAAAEAAAAnjOYpYTEzODI2NTM5NjA4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIIGy1-CBstfR2; REALTIME_TRANS_SWITCH=1; HISTORY_SWITCH=1; FANYI_WORD_SWITCH=1; SOUND_SPD_SWITCH=1; SOUND_PREFER_SWITCH=1; __yjs_duid=1_638c4e9d787e73c5b11a7481480f36051619503694956; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; delPer=0; BAIDUID_BFESS=FBD56B8D3B825CB0D915D0B851CCD0A5:FG=1; PSINO=7; BDRCVFR[k2U9xfnuVt6]=mk3SLVN4HKm; H_PS_PSSID=33802_34004_33607_26350; BA_HECTOR=8ha5ak80a12h0185lm1gbreup0q; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1623047133; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1623047133; __yjs_st=2_Y2ZjZDA5ZGE1ZDAxM2ExNTcwNjg5ODVkOGJhNjdiN2Q2ZDQ2NTRkM2E0MGI2NDg3ZmQwMGJlMzA5MTg0YmM4NzhhMzdlMDI0MTAxODE1ODllMjBhMjdkZmUyNmJkNjI3ZTdjZmI1MjI0NmQ2NjVkOWE4Nzc4YTdlYmI0YjdhNDQ0ODAyYmU2OTcxNTViZmJmMTc5MzI3NWZmOTc2NDZhZjQ0NDFhODcwYmUzNWFkMWUyN2Q4NmRiZDYzMDExYzFlYmU4MjUzNDkwOGQ4NWRmYTBmYWViOWNkY2YxYzkzYjRkZjJiODk0MWY4Yzg5NDkyNTkyMDZiOWY1YTZhYTgzN183XzhjOTA2NTQ3; ab_sr=1.0.1_OTNhYmNhMjgzMTgwMDY4ZTJmNzQzZDlhNmQyM2I5ZTg4ZDg0ZDBiOTM2ZDFjMjFiNTRiZDQ1MmMxNTU3MzQ2YmIwNzZiNDhlMThjYTg1ZjRjZmJhNTY3ZjU1ODUyOTgxNmJhYmE2YjQ5N2Q5ZTU3Y2Q2ZjdiMjJkMTFjMTAyODg0MGE0MjRjZTBkM2JlYjFhMTIyMzViZDQyNGRmOGFiNTc2ODJiNmUxZTUwZTMwZDFhMzZmMzc4MTIwMjA3MDYx");
        Connection connect = Jsoup.connect(url).ignoreContentType(true);
        connect.timeout(99999999);
        connect.headers(map);
        Map<String, String> data = new HashMap<String, String>();
        data.put("from","zh");
        data.put("to","en");
        data.put("query",message);
        data.put("transtype","realtime");
        data.put("simple_means_flag","3");
        data.put("sign","232427.485594");
        data.put("token","e5f2616b91a3781d7adaa99fd7ad9e77");
        data.put("domain","common");
        connect.data(data);
        Connection.Response execute = connect.execute();
        String body = execute.body();
        System.out.println(body);
    }
}
