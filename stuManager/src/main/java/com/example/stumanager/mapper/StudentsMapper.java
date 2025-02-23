package com.example.stumanager.mapper;

import com.example.stumanager.model.Students;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author meira
* @description 针对表【students】的数据库操作Mapper
* @createDate 2025-02-23 16:12:19
* @Entity com.example.stumanager.model.Students
*/
@Mapper
@Repository
public interface StudentsMapper {
    Students queryById(Integer id);
    Students login(String code,String password);
}




