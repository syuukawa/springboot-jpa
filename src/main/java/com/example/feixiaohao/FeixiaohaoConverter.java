package com.example.feixiaohao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeixiaohaoConverter {
    private static FeixiaohaoConverter instance = null;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回单例 * * @return
     */
    public static FeixiaohaoConverter getInstance() {
        if (instance == null) instance = new FeixiaohaoConverter();
        return instance;
    }

    /**
     * 将抓取到的html信息转为公司实体 * * @param url * @throws Exception
     */
    public void html2Company(String url, Integer currentPage) throws Exception {

        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
        //根据html结构，抓取有效数据
        String companyName = doc.getElementsByAttributeValue("class", "companyname").text();
        String contacts = doc.select("div.contxt p").eq(0).text();
        String encodePhone = doc.getElementsByAttributeValue("class", "phoneNumber").text();
        String area = doc.getElementById("detialAddr").text();
        //使用js引擎，调用js函数解密
        JavaScriptEngine jsEngine = JavaScriptEngine.getInstance();
        String mobile = jsEngine.decodeData(encodePhone);
//            if (StringUtils.isBlank(companyName) || !RegularUtils.isValidMobile(mobile) || StringUtils.isBlank(contacts)) {
//                return null;
//            } else {
//                Shop99Company shop99 = new Shop99Company();
//                shop99.setMobile(mobile);
//                shop99.setCompanyName(companyName);
//                shop99.setContacts(contacts);
//                return shop99;

    }

    public static void main(String[] args) throws Exception {
        String url = "https://www.feixiaohao.com/currencies/byteball/";
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
        String secondPark = doc.getElementsByAttributeValue("class", "secondPark").html();
//        String contacts = doc.select("div.secondPark li").eq(0).text();
//        System.out.println(contacts); //英文名：Byteball/GBYTE

        //TODO 基本成功了
        Elements elements = doc.select("div.secondPark li");
        for(Element element : elements){
            System.out.println(element.text() + element.getElementsByAttribute("href").attr("href"));
        }
//        英文名：Byteball/GBYTE
//        中文名：字节雪球
//        上架交易所： 3家#tickerlist
//        发行时间： 2017-01-30
//        白皮书： －
//        网站： 网站1//byteball.org/
//        区块站： 区块站1//explorer.byteball.org/
//        相关概念： 智能合约 社交通讯 支付概念 数据存储 资产交易 基于DAG 匿名货币/conceptcoin/9/
//                区域群： QQ交流群 微信交流群 区域电报群
//        风险提示：炒币有风险，入市需谨慎；平台价差大，搬砖须谨慎！
//        System.out.println(secondPark);
//        coingecko

    }
}

