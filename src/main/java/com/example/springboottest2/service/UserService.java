package com.example.springboottest2.service;

import com.example.springboottest2.entity.http.Resp;
import com.example.springboottest2.utils.JwtUtils;
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

    public Resp adminLogin(String userName, String password) {
        User user = (User) login(userName ,password).getData();
        if(user != null){
            return Resp.success(JwtUtils.createJwtToken(user));
        }else{
            return Resp.fail();
        }
    }

    public Resp getUserInfo(String token) {
        User user = JwtUtils.getTokenInfo(token);
        if(user != null){
            return Resp.success(user);
        }else{
            return Resp.fail();
        }
    }

    public Resp register(User user) {
        System.out.println(123);
        String userName = user.getUsername();
        User tempUser = new User();
        tempUser.setUsername(userName);
        if(selectByAll(tempUser).size() == 0){
            insert(user);
            return Resp.success();
        }else{
            return Resp.fail();
        }
    }
}


