package com.example.stumanager.mapper;

import com.example.stumanager.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//这个注解表示这是一个mapper，跟Spring有关
@Mapper
//这个注解 跟Mybatis有关
@Repository
public interface AdminMapper {

    Admin queryById(Integer id);
}
