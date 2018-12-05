package com.example.feixiaohao;

import com.example.file.AppendToFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://www.open-open.com/jsoup/dom-navigation.htm
public class GetAllCoinListConverter {
    private static GetAllCoinListConverter instance = null;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回单例 * * @return
     */
    public static GetAllCoinListConverter getInstance() {
        if (instance == null) instance = new GetAllCoinListConverter();
        return instance;
    }

    public static void main(String[] args) throws Exception {

        //TODO 001 获取非小号上的所有币种
        String urlPt = "https://www.feixiaohao.com/";

        Document docPt = Jsoup.connect(urlPt).userAgent(USER_AGENT).get();
        Elements elementsPt = docPt.getElementsByAttributeValue("class", "new-table new-coin-list");

        List<Map<String, Object>> listD = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTitle4 = new HashMap<String, Object>();
        mapTitle4.put("# 币种列表", " 7 ");
        listD.add(mapTitle4);
        for (Element element : elementsPt) {
            Elements linksTr = element.getElementsByTag("tr");
            for (Element element01 : linksTr) {
                Map<String, Object> mapTitleSub = new HashMap<String, Object>();
                Elements linksTd = element01.getElementsByTag("td");
                System.out.println(linksTd.eq(1).text());
                Elements hrefs = linksTd.eq(1).val("href");
                for (Element element1 : hrefs) {
                    String hrefPlatform = "https://www.feixiaohao.com/" + element1.getElementsByAttribute("href").attr("href");
                    System.out.println("https://www.feixiaohao.com" + element1.getElementsByAttribute("href").attr("href")); //TODO http://exchange/bitmex/
//                    String urlPt = "https://www.feixiaohao.com/exchange/?mineable=1";
//                    Document docPlatform = Jsoup.connect(hrefPlatform).userAgent(USER_AGENT).get();
//                    Elements elementsPlatform = docPlatform.getElementsByAttributeValue("class", "web");
//                    for (Element element2 : elementsPlatform) {
//                        Elements linksSpans = element2.getElementsByTag("span");
//                        Elements spanHrefs = linksSpans.eq(0).val("href");
//                        for (Element element3 : spanHrefs) {
//                            System.out.println("http:" + element3.getElementsByAttribute("href").attr("href"));
//                            mapTitleSub.put(linksTd.eq(1).text(), "http:" + element3.getElementsByAttribute("href").attr("href"));
//                            listD.add(mapTitleSub);
//                        }
//                    }
                }
            }
        }
        AppendToFile.appendMethodB("/Users/zhouhe/file/AllcoinList.txt", listD);
        System.out.println("Success +++");
    }
}

