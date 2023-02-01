package com.example.springboottest2.controller;

import com.example.springboottest2.entity.User;
import com.example.springboottest2.service.UserService;
import com.example.springboottest2.entity.http.Resp;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import com.example.springboottest2.config.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import javax.annotation.Resource;

/**
* (user)表控制层
*
* @author touchfish
*/
@RestController
@RequestMapping("user")
public class UserController {
    /**
    * 服务对象
    */
    @Resource
    private UserService userService;
    
    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    public Resp insert(@RequestBody User user) {
        return userService.insert(user) > 0 ? Resp.success() : Resp.fail();
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Resp update(@RequestBody User user) {
        return userService.updateByPrimaryKey(user) > 0 ? Resp.success() : Resp.fail();
    }
    
    @ApiOperation(value = "根据ID查询")
    @PostMapping("/select")
    public Resp select(Integer id) {
        return Resp.success(userService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Resp delete(@RequestParam Integer id) {
        return userService.deleteByPrimaryKey(id) > 0 ? Resp.success() : Resp.fail();
    }

    @ApiOperation(value = "查看全部")
    @PostMapping("/selectAll")
    public Resp selectAll() {
        return Resp.success(userService.selectAll());
    }

    @ApiOperation(value = "查看全部-分页")
    @PostMapping("/pageSelectAll")
    public Resp pageSelectAll(@RequestParam Integer pageNum, @RequestParam Integer size) {
        PageHelper.startPage(pageNum, size);
        PageInfo pageInfo = new PageInfo(userService.selectAll());
        pageInfo.getTotal();
        return Resp.success(pageInfo);
    }
    
    @ApiOperation(value = "根据字段查询")
    @PostMapping("/selectByAll")
    public Resp selectByAll(@RequestBody(required = false) User user) {
        return Resp.success(userService.selectByAll(user));
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Resp selectByAll(String userName, String password) {
        return Resp.success(userService.login(userName, password));
    }

    @PostMapping("/adminlogin")
    @ApiOperation(value = "管理端登录")
    public Resp adminLogin(String userName , String password){
        return userService.adminLogin(userName,password);
    }

    @PostMapping("/getuserinfo")
    @ApiOperation(value = "通过Token获取信息")
    public Resp getUserInfo(String token){
        return userService.getUserInfo(token);
    }
}
