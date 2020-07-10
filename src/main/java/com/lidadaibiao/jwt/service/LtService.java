package com.lidadaibiao.jwt.service;

import com.lidadaibiao.jwt.dao.LtMapper;
import com.lidadaibiao.jwt.pojo.LtActive;
import com.lidadaibiao.jwt.pojo.LtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lidadaibiao
 * @date 2020/7/8 - 11:20
 */
@Service
public class LtService {

    @Autowired
    private LtMapper ltMapper;

    public LtUser getUserByUserNameAndPassWord(Map<String,String> map){
        return ltMapper.getUserByUserNameAndPassWord(map);
    }

    public int insertUser(LtUser ltUser){
        return ltMapper.insertUser(ltUser);
    }

    /**
     * 根据用户登录的ID 查询出活动目录
     */
    public List<LtActive> getActiveByUserId(String userId){
      return ltMapper.getActiveByUserId(userId);
    }

    public int delActiveById(String activeId) {
        return ltMapper.delActiveById(activeId);
    }

    public int insertActive(LtActive ltActive) {
        return ltMapper.insertActive(ltActive);
    }
}
