package com.example.coingecko.service;

import com.example.coingecko.entity.CoinAndImage;
import com.example.coingecko.repository.CoinAndImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//https://api.coingecko.com/api/v3/coins/list
@Service("coingeckoService")
public class CoingeckoService implements ICoingeckoService {

    @Autowired
    private CoinAndImageRepository coinAndImageRepository;

    @Override
    public void saveCoinAndImage(List<CoinAndImage> coinAndImages) {
        coinAndImageRepository.save(coinAndImages);
    }

    @Override
    public List<CoinAndImage> getCoinAndImages() {
        return coinAndImageRepository.findAll();
    }
}
