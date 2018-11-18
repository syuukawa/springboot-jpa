package com.example.service;

import com.example.entity.BqiTickerTable;
import com.example.repository.TickerRepository;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service("tickerService")
public class TickerServiceImpl implements ITickerService {

    @Autowired
    private TickerRepository tickerRepository;

//    /**
//     * <p>Description: 根据ID获取虚拟币的详细信息</p>
//     * <p>param  </p>
//     * <p>author zhouhe</p>
//     */
//    @Override
//    public BqiTickerTable GetBqiTicker(String name) throws IOException {
//        BqiTickerTable returnResult = new BqiTickerTable();
//        String url = "https://public.bqi.com/public/v1/ticker?code=" + name;
//        url = url + "&convert=CNY&start=0&limit=1&isKLine=1";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        HttpResponse response = httpClient.execute(httpGet);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
//            String result = EntityUtils.toString(response.getEntity());
////            JSONObject accountJson = JSONObject.parseObject(result);
//            Gson gson = new Gson();
//            returnResult = gson.fromJson(result.replace("[","").replace("]",""), BqiTickerTable.class);
////            System.out.println(gson.toJson(returnResult));
//        }
//        httpGet.releaseConnection();
//        return returnResult;
//    }

    /**
     * <p>Description: 根据Name 查询 BQI中的记录 然后保存ticker的信息到数据表中</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Override
    public void SaveBqiTicker(Integer id,String name) throws IOException {
        System.out.println("SaveBqiTicker Start Time : " + new Date());
        BqiTickerTable returnResult = new BqiTickerTable();
        String url = "https://public.bqi.com/public/v1/ticker?code=" + name;
        url = url + "&convert=CNY&start=0&limit=1&isKLine=1";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
//            JSONObject accountJson = JSONObject.parseObject(result);
            Gson gson = new Gson();
            returnResult = gson.fromJson(result.replace("[", "").replace("]", ""), BqiTickerTable.class);
//            System.out.println(gson.toJson(returnResult));
        }
        httpGet.releaseConnection();
        if (returnResult != null) {
            if(id != null){
                returnResult.setCid(id);
            }
            tickerRepository.save(returnResult);
        }
        System.out.println("SaveBqiTicker End Time : " + new Date());
    }

//
//    public static void main(String[] args) throws IOException {
//        TickerServiceImpl tickerService = new TickerServiceImpl();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            tickerService.SaveBqiTicker("Bitcoin");
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("consume pool -> " + (end - start) + " ms");
//        //10次请求 用了16秒左右的时间
//    }

}
