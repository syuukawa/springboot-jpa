package com.example.coingecko.resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.coingecko.entity.Coin;
import com.example.coingecko.entity.CoinAndImage;
import com.example.coingecko.entity.Image;
import com.example.coingecko.service.CoingeckoService;
import com.example.coingecko.service.ICoingeckoService;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//https://api.coingecko.com/api/v3/coins/list
public class CoingeckoResource{

    /**
     * <p>Description: 虚拟币的简要信息列表获取</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    public List<Coin> GetCoinLists() throws IOException {

        final String url = "https://api.coingecko.com/api/v3/coins/list";
        List<Coin> coinLists = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
//            JSONObject accountJson = JSONObject.parseObject(result);
            coinLists = JSONArray.parseArray(result, Coin.class);
        }
        httpGet.releaseConnection();
        System.out.println("size = :" + coinLists.size());
        return coinLists;
    }

    public Image GetImageByCoinId(String id) throws IOException {

        String url = "https://api.coingecko.com/api/v3/coins/";
        url = url + id.replace(" ","-");
        url = url + "?tickers=false&market_data=false";
        System.out.println("url : " + url);

        Image image = new Image();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
            JSONObject accountJson = JSONObject.parseObject(result);
            Gson gson = new Gson();
            image = gson.fromJson(accountJson.get("image").toString(), Image.class);
        } else {
            image = new Image();
        }
        httpGet.releaseConnection();
//        System.out.println("image = :" +image);
        return image;
    }

    public static void main(String[] args) throws Exception {


    }

}
