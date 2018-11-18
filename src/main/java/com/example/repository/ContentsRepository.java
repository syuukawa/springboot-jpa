package com.example.repository;

import com.example.entity.ContentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by jiutian on 2018/11/14.
 */
@Repository("contentsRepository")
public interface ContentsRepository extends JpaRepository<ContentsTable, Long> {

    /**
     * @Query注解的使用
     */
//    @Query("select * from ContentsTable ")
    List<ContentsTable> findByStatus(String status);


}
