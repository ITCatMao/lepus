package com.catty.lepus.dao;

import com.catty.lepus.common.MySqlExtensionMapper;
import com.catty.lepus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends MySqlExtensionMapper<User> {

    User selectByUserName(String username);

//    int updateByPrimaryKeySelective(User user);

}