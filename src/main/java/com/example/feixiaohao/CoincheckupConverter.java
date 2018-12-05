package com.example.feixiaohao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoincheckupConverter {
    private static CoincheckupConverter instance = null;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回单例 * * @return
     */
    public static CoincheckupConverter getInstance() {
        if (instance == null) instance = new CoincheckupConverter();
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
        String url = "https://coincheckup.com/coins/xyo-network/";
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();

        //TODO 基本成功了

        String coin_social = doc.getElementsByAttributeValue("class", "section-body").html();
        System.out.println(coin_social);
//        Elements elements = doc.select("div.section-body");
//        for(Element element : elements){
//            System.out.println(element.text() + element.getElementsByAttribute("href").attr("href"));
//        }
    }
}

