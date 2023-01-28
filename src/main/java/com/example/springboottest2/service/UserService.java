package com.example.springboottest2.service;

import com.example.springboottest2.entity.http.Resp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.springboottest2.entity.User;
import com.example.springboottest2.mapper.UserMapper;import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public int deleteByPrimaryKey(Integer userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public List<User> selectByAll(User user) {
        return userMapper.selectByAll(user);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public Resp login(String userName, String password) {
        User tempUser = new User();
        tempUser.setUsername(userName);
        tempUser.setPassword(password);
        List<User> tempList = selectByAll(tempUser);
        if (tempList.size() == 0) {
            return Resp.fail();
        } else {
            return Resp.success(tempList.get(0));
        }
    }
}


