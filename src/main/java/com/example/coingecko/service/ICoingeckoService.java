package com.example.coingecko.service;

import com.example.coingecko.entity.CoinAndImage;

import java.util.List;

//https://api.coingecko.com/api/v3/coins/list
public interface ICoingeckoService {

    //保存币的基本信息和图片信息
    void saveCoinAndImage(List<CoinAndImage> coinAndImage);

    List<CoinAndImage> getCoinAndImages();

}
