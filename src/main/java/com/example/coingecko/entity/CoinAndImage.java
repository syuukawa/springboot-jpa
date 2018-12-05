package com.example.coingecko.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_coin_image")
@Data
public class CoinAndImage {

    @Id
    private String id;

    private String symbol;

    private String name;

    private String thumb;

    private String small;

    private String large;
}
