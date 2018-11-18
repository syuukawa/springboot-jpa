package com.example.controller;

import com.example.entity.ContentsTable;
import com.example.service.IContentsService;
import com.example.service.ITickerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledController {


    @Autowired
    private IContentsService contentsService;

    @Autowired
    private ITickerService tickerService;


    @Scheduled(cron = "0 0/5 * * * ?")
    public void pushDataScheduled() throws IOException {
        System.out.println(" Start Time : " + new Date());
        List<ContentsTable> contentsTableList = contentsService.findByStatus("publish");
        for (ContentsTable contentsTable : contentsTableList) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tickerService.SaveBqiTicker(contentsTable.getName());
        }
        System.out.println(" End Time : " + new Date());
    }
}
