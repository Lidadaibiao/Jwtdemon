package com.lidadaibiao.jwt.dao;

import com.lidadaibiao.jwt.pojo.LtActive;
import com.lidadaibiao.jwt.pojo.LtUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 11:29
 */
@Mapper
public interface LtMapper {

    LtUser getUserByUserNameAndPassWord(Map<String,String> map);

    int insertUser(LtUser ltUser);

    List<LtActive> getActiveByUserId(String userId);

    int delActiveById(String activeId);

    int insertActive(LtActive ltActive);
}
