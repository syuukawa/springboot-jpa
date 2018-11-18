package com.example.service;

import java.io.IOException;

public interface ITickerService {


//    /**
//     * <p>Description: 根据名称获取虚拟币的详细信息</p>
//     * <p>param  </p>
//     * <p>author zhouhe</p>
//     */
//    BqiTickerTable GetBqiTicker(String name) throws IOException;


    /**
     * <p>Description: 根据Name 查询 BQI中的记录 然后保存ticker的信息到数据表中</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    void SaveBqiTicker(Integer id,String name) throws IOException;
}
