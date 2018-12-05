package com.example.controller;

import com.example.coingecko.entity.Coin;
import com.example.coingecko.entity.CoinAndImage;
import com.example.coingecko.resource.CoingeckoResource;
import com.example.coingecko.service.CoingeckoService;
import com.example.service.IContentsService;
import com.example.service.ITickerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledControllerBack {

    @Autowired
    private IContentsService contentsService;

    @Autowired
    private ITickerService tickerService;


    @Autowired
    private CoingeckoService coingeckoService;

//
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void pushDataScheduled() throws IOException {
//        System.out.println(" Start Time : " + new Date());
//        List<ContentsTable> contentsTableList = contentsService.findByStatus("publish");
//        for (ContentsTable contentsTable : contentsTableList) {
////            try {
////                Thread.sleep(5000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            System.out.println(contentsTable.getCid());
//            tickerService.SaveBqiTicker(contentsTable.getCid(),contentsTable.getSlug());
//        }
//        System.out.println(" End Time : " + new Date());
//    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void pushCoin() throws IOException {
        System.out.println(" Start Time : " + new Date());
        CoingeckoResource resource = new CoingeckoResource();

        List<CoinAndImage> coinAndImages = new ArrayList<>();
        List<Coin> coinList = resource.GetCoinLists();

        for(Coin coin : coinList){
            CoinAndImage coinAndImage = new CoinAndImage();
//            Image image = resource.GetImageByCoinId(coin.getId());
            coinAndImage.setId(coin.getId().replace(" ","-"));
            coinAndImage.setName(coin.getName());
            coinAndImage.setSymbol(coin.getSymbol());
//            coinAndImage.setSmall(image.getThumb());
//            coinAndImage.setThumb(image.getThumb());
//            coinAndImage.setLarge(image.getLarge());
            coinAndImages.add(coinAndImage);
//            coingeckoService.saveCoinAndImage(coinAndImage);
        }
        System.out.println("size : " + coinAndImages.size() );
        coingeckoService.saveCoinAndImage(coinAndImages);
        System.out.println(" End Time : " + new Date());
    }

//
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void pushCoinAndImage() throws IOException {
//        System.out.println(" Start Time : " + new Date());
//        CoingeckoResource resource = new CoingeckoResource();
//        List<CoinAndImage> coinAndImages = new ArrayList<>();
//        List<Coin> coinList = resource.GetCoinLists();
//        for(Coin coin : coinList){
//            CoinAndImage coinAndImage = new CoinAndImage();
//            Image image = resource.GetImageByCoinId(coin.getId());
//            coinAndImage.setId(coin.getId().replace(" ","-"));
//            coinAndImage.setName(coin.getName());
//            coinAndImage.setSymbol(coin.getSymbol());
//            coinAndImage.setSmall(image.getThumb());
//            coinAndImage.setThumb(image.getThumb());
//            coinAndImage.setLarge(image.getLarge());
//            coinAndImages.add(coinAndImage);
////            coingeckoService.saveCoinAndImage(coinAndImage);
//        }
//        System.out.println("size : " + coinAndImages.size() );
//        coingeckoService.saveCoinAndImage(coinAndImages);
//        System.out.println(" End Time : " + new Date());
//    }


}
