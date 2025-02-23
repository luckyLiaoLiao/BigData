package com.example.stumanager.mapper;

import com.example.stumanager.model.DormAdmins;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DormAdminsMapper {

    DormAdmins queryById(Integer id);
    DormAdmins login(String account,String password);
}
