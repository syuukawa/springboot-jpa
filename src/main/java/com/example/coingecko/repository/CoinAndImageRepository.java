package com.example.coingecko.repository;

import com.example.coingecko.entity.CoinAndImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by jiutian on 2018/11/14.
 */
@Repository("coinAndImageRepository")
public interface CoinAndImageRepository extends JpaRepository<CoinAndImage, String> {

    List<CoinAndImage>  findAll();

    CoinAndImage findById(String coinId);
}
