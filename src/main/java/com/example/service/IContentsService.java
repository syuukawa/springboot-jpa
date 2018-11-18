package com.example.service;

import com.example.entity.ContentsTable;

import java.util.List;

public interface IContentsService {

    /**
     * <p>Description: </p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    List<ContentsTable> findByStatus(String status);
}
