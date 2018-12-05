package com.example.feixiaohao;

import com.example.file.AppendToFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://www.open-open.com/jsoup/dom-navigation.htm
public class CoinDetailsConverter {
    private static CoinDetailsConverter instance = null;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回单例 * * @return
     */
    public static CoinDetailsConverter getInstance() {
        if (instance == null) instance = new CoinDetailsConverter();
        return instance;
    }

    public static void GetCoinDetail(String urlFxhCurrenciesParams, String urlFxhCoindetailsParams, String urlCoingeckoParams, String coinParams, String outputPathParams, String fileNameParams) throws Exception {

        String urlFeixiaohao = urlFxhCurrenciesParams + coinParams;
        Document docFeixiaohao = Jsoup.connect(urlFeixiaohao).userAgent(USER_AGENT).get();
        Elements elementsFeixiaohaos = docFeixiaohao.getElementsByAttributeValue("class", "secondPark");

        List<Map<String, Object>> listA = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTitle1 = new HashMap<String, Object>();
        mapTitle1.put("# 1 介绍", "");
        listA.add(mapTitle1);

        //TODO 001 基本信息
        for (Element elementFxh01 : elementsFeixiaohaos) {
            Elements tag01 = elementFxh01.getElementsByTag("li");
            for (Element elementFxh02 : tag01) {
                Map<String, Object> map = new HashMap<String, Object>();
//                System.out.println(elementFxh02.text());
                if (elementFxh02.text().contains("英文名：") || elementFxh02.text().contains("中文名：") || elementFxh02.text().contains("发行时间：") ||
                        elementFxh02.text().contains("众筹价格：") || elementFxh02.text().contains("相关概念：")) {
//                    System.out.println(elementFxh02.text());
                    map.put("+ " + elementFxh02.text(), "");
                }
                if (elementFxh02.text().contains("网站：")) {
                    Elements tag02 = elementFxh02.getElementsByTag("a");
                    for (Element elementFxh03 : tag02) {
//                        System.out.println(elementFxh03.text() + ": " + "http:" + elementFxh03.getElementsByAttribute("href").attr("href"));
                        String httpLink = "http:" + elementFxh03.getElementsByAttribute("href").attr("href");
                        map.put("+ " + elementFxh03.text(), "[" + httpLink + "]" + "(" + httpLink + ")");
                    }
                }
                if (elementFxh02.text().contains("区块站：")) {
                    Elements tag02 = elementFxh02.getElementsByTag("a");
                    for (Element elementFxh03 : tag02) {
                        map = new HashMap<String, Object>();
//                        System.out.println(elementFxh03.text() + ": " + "http:" + elementFxh03.getElementsByAttribute("href").attr("href"));
                        String httpLink = "http:" + elementFxh03.getElementsByAttribute("href").attr("href");
                        map.put("+ " + elementFxh03.text(), "[" + httpLink + "]" + "(" + httpLink + ")");
                        listA.add(map);
                    }
                }
                listA.add(map);
            }
        }

        Elements elementsFirst = docFeixiaohao.getElementsByAttributeValue("class", "firstPart");
        for (Element elementFxh01 : elementsFirst) {
            Map<String, Object> map = new HashMap<String, Object>();
            //总量和发行量获取
            Elements tag01 = elementFxh01.getElementsByAttributeValue("class", "cell");
//            System.out.println(tag01.eq(1).text());
            map.put("+ " + tag01.eq(1).text(), "");
            listA.add(map);
        }
        AppendToFile.appendMethodB(outputPathParams + fileNameParams, listA);


        //TODO 002 简介
        String urlJianjie = urlFxhCoindetailsParams + coinParams;
        Document docJianjie = Jsoup.connect(urlJianjie).userAgent(USER_AGENT).get();
        Elements elementsJianjie = docJianjie.getElementsByAttributeValue("class", "artBox");

        List<Map<String, Object>> listB = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTitle2 = new HashMap<String, Object>();
        mapTitle2.put("# 2 简介", "");
        listB.add(mapTitle2);
        for (Element elementJianjie01 : elementsJianjie) {
            Map<String, Object> map = new HashMap<String, Object>();
//            System.out.println(elementJianjie01.text());
            map.put("+ " + elementJianjie01.text(), "");
            listB.add(map);
        }
        AppendToFile.appendMethodB(outputPathParams + fileNameParams, listB);


        //TODO 003 媒体以及链接
        String urlCoingecko = urlCoingeckoParams + coinParams;
        Document doc = null;
        try {
            doc = Jsoup.connect(urlCoingecko).userAgent(USER_AGENT).get();
            Elements elements = doc.getElementsByAttributeValue("class", "col-md-9 col-lg-7 p-0");

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> mapTitle3 = new HashMap<String, Object>();
            mapTitle3.put("# 3 媒体", "");
            list.add(mapTitle3);

            //TODO 基本成功了
            for (Element element : elements) {
                Elements links = element.getElementsByTag("a");
                for (Element element1 : links) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    String hrefLink = element1.getElementsByAttribute("href").attr("href").replace(",", "");
                    String hrefString = "[" + hrefLink + "]" + "(" + hrefLink + ")";
                    map.put("+ " + element1.text(), hrefString);
                    list.add(map);
                }
            }
            AppendToFile.appendMethodA(outputPathParams + fileNameParams, list);
        } catch (IOException e) {
            System.out.println("Error Coin : " + coinParams);
//            e.printStackTrace();
        }

        //TODO 004 上币平台列表
        String urlPt = urlFxhCurrenciesParams + coinParams;
        Document docPt = Jsoup.connect(urlPt).userAgent(USER_AGENT).get();
        Elements elementsPt = docPt.getElementsByAttributeValue("class", "table3 tableMaxWidth");

        List<Map<String, Object>> listD = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTitle4 = new HashMap<String, Object>();
        mapTitle4.put("# 4 平台", "");
        listD.add(mapTitle4);
        for (Element element : elementsPt) {
            Elements linksTr = element.getElementsByTag("tr");
            for (Element element01 : linksTr) {
                Map<String, Object> mapTitleSub = new HashMap<String, Object>();
                Elements linksTd = element01.getElementsByTag("td");
                if (!linksTd.eq(1).html().equals("")) {
//                    System.out.println(linksTd.eq(1).text());
                    mapTitleSub.put("+ " + linksTd.eq(1).text(), "");
                    listD.add(mapTitleSub);
                }
            }
        }
        AppendToFile.appendMethodB(outputPathParams + fileNameParams, listD);

        List<Map<String, Object>> listE = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapTitle5 = new HashMap<String, Object>();
        mapTitle5.put("# 5 分析 ", "");
        mapTitle5.put("+ " + "[" + urlFeixiaohao + "]" + "(" + urlFeixiaohao + ")", "");
        mapTitle5.put("+ " + "[" + urlCoingecko + "]" + "(" + urlCoingecko + ")", "");
        listE.add(mapTitle5);

        AppendToFile.appendMethodB(outputPathParams + fileNameParams, listE);

        System.out.println("Success +++");
    }

    public static List<String> GetCoinList(String urlParams, String pageNumParams) throws Exception {
        List<String> coinResult = new ArrayList<>();
//TODO 001 获取非小号上的所有币种
        String urlPt = null;
        if (pageNumParams.equals("1")) {
            urlPt = urlParams;

        } else {
            urlPt = urlParams + "list_" + pageNumParams + ".html";
        }
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
//                    System.out.println("https://www.feixiaohao.com" + element1.getElementsByAttribute("href").attr("href"));
                    String coinLink = "https://www.feixiaohao.com" + element1.getElementsByAttribute("href").attr("href");
                    System.out.println(coinLink);
                    coinResult.add(coinLink);
                }
            }
        }
        return coinResult;
    }

    public static void main(String[] args) throws Exception {
        List<String> coinList = new ArrayList<>();
        String urlParams = "https://www.feixiaohao.com/";
        String pageNumParams = "1";
        coinList = GetCoinList(urlParams, pageNumParams);
        System.out.println(coinList.size());
        Integer index = 1;
        for (String coin : coinList) {
            String fxhCurrencies = "https://www.feixiaohao.com/currencies/";
            String fxhCoindetails = "https://www.feixiaohao.com/coindetails/";
            String coingeckoUrl = "https://www.coingecko.com/en/coins/";
            //TODO"xyo-network";
            coin = coin.replace("https://www.feixiaohao.com/currencies/", "").replace("/", "");
            String outputPath = "/Users/zhouhe/file/001Page/";
            String fileName = coin + ".txt";
            System.out.println(index);
            GetCoinDetail(fxhCurrencies, fxhCoindetails, coingeckoUrl, coin, outputPath, fileName);
            index++;
        }
    }


}

