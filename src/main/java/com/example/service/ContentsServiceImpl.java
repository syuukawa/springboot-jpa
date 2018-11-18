package com.example.service;

import com.example.entity.ContentsTable;
import com.example.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("contentsService")
public class ContentsServiceImpl implements IContentsService {

    @Autowired
    private ContentsRepository contentsRepository;

    @Override
    public List<ContentsTable> findByStatus(String status) {
        return contentsRepository.findByStatus(status);
    }

}
