package com.example.feixiaohao;


import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import java.util.LinkedHashMap;
//import java.util.Map;

//https://www.jianshu.com/p/5f9492e81198
public class Feixiaohao {

    public static Object fecthNode(String url, String xpath) throws Exception {
        String html = null;
        try {
            Connection connect = Jsoup.connect(url);
            html = connect.get().body().html();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(html);
        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();

        Object result = xPath.evaluate(xpath, dom, XPathConstants.NODESET);

        return result;
    }

    // 获取xpath下的a标签的文本值及href属性值
    //**
    public static Map<String, String> fecthByMap(String url, String xpath) throws Exception {

        Map<String, String> nodeMap = new LinkedHashMap<>();
        Object result = fecthNode(url, xpath);

        if (result instanceof NodeList) {
            NodeList nodeList = (NodeList) result;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node == null) {
                    continue;
                }

                System.out.println(node.getAttributes() != null ? node.getAttributes() : "" + node.getAttributes());
//                System.out.println(node.getAttributes().getNamedItem("title") != null ? node.getAttributes().getNamedItem("title").getTextContent() : "");
//                System.out.println(node.getTextContent() + " : " + node.getAttributes().getNamedItem("href"));
//                System.out.println("=========================== 总记录数 ： " + i);
                //TODO 结果还是又问题，等待修改
            }
        }
        return nodeMap;
    }
//
//    /**
//     * 获取xpath下的某个属性值 /**
//     *
//     * @param url
//     * @param xpath
//     * @param attr
//     * @return
//     * @throws Exception
//     */
//    public static List<String> fecthAttr(String url, String xpath, String attr) throws Exception {
//        List<String> list = new ArrayList<>();
//        Object result = fecthNode(url, xpath);
//        if (result instanceof NodeList) {
//            NodeList nodeList = (NodeList) result;
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node == null) {
//                    continue;
//                }
////                list.add(node.getAttributes().getNamedItem(attr).getTextContent());
//                if(node.getAttributes().getNamedItem("href") != null){
//                    System.out.println(node.getTextContent() + " : " + node.getAttributes().getNamedItem("href").getTextContent());
//                }
//            }
//        }
//        return list;
//    }


    public static void main(String[] args) throws Exception {

        //TODO OK
//        fecthByMap("https://www.feixiaohao.com/coindetails/byteball/", "//div[@class='artBox']");
//        Byteball是一个被称为区块链3.0的新型平台。其实它并不是区块链，而是用比区块链更巧妙DAG技术作为底层。从而做到不需要POW和POS，照样做到了100%可靠。并且交易越是拥挤速度反而越快。
//        Byteball有一个非常好用的钱包。首次在加密世界采用类似AppleStore的模式，自由开发者可以在Byteball平台上自由开发各种应用。涉及到内盘交易所，类似telegram的隐私聊天。互助保险，赌球，彩票。
//        开发者非常活跃，基本每周都有代码提交。
//        在该系统中，还有一个隐私性超强的数字货币-黑球。第一次做到在总账上也无法追查到交易信息。
//        在最新的2.1版本中，开发者开发出TextCoin功能，让交易者能通过字符串进行轻松转账，用户可以通过QQ，微信，telegram，email等等社交工具轻松转账。大大拓宽了Bytes的使用场景，例如可以通过KYC认证的方式，通过字符串轻松获取20美元的红包。
//        Byteball不是通过ICO的方式售卖，而是通过一定的规则免费赠送给安装钱包的用户。并且发行量很少，只有100万。

        //TODO OK
//        fecthByMap("https://www.feixiaohao.com/currencies/byteball/", "//div[@class='secondPark']/*//span[@class='value']");
//        Byteball/GBYTE
//        字节雪球
//        3家
//        2017-01-30
//        －
//        网站1
//        区块站1

//        流通量
//        662,811 GBYTE
//                总发行量
//        1,000,000 GBYTE

//        fecthAttr("https://www.feixiaohao.com/currencies/byteball/", "//div[@class='secondPark']/*//span[@class='value']", "href");
        fecthByMap("https://medium.com/xyonetwork", "//div[@class='col u-xs-marginBottom10 u-paddingLeft9 u-paddingRight12 u-paddingTop0 u-sm-paddingTop20 u-paddingBottom25 u-size4of12 u-xs-size12of12 u-marginBottom30']");
    }
}
