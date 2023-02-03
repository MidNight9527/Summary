package com.example.springboottest2.controller;

import com.example.springboottest2.entity.History;
import com.example.springboottest2.service.HistoryService;
import com.example.springboottest2.entity.http.Resp;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import javax.annotation.Resource;
import java.util.List;

/**
* (history)表控制层
*
* @author touchfish
*/
@RestController
@RequestMapping("history")
public class HistoryController {
    /**
    * 服务对象
    */
    @Resource
    private HistoryService historyService;
    
    @ApiOperation(value = "添加")
    @PostMapping("/insert")
    public Resp insert(@RequestBody History history) {
        return historyService.insert(history) > 0 ? Resp.success() : Resp.fail();
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public Resp update(@RequestBody History history) {
        return historyService.updateByPrimaryKey(history) > 0 ? Resp.success() : Resp.fail();
    }
    
    @ApiOperation(value = "根据ID查询")
    @PostMapping("/select")
    public Resp select(Integer id) {
        return Resp.success(historyService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public Resp delete(@RequestParam Integer id) {
        return historyService.deleteByPrimaryKey(id) > 0 ? Resp.success() : Resp.fail();
    }

    @ApiOperation(value = "查看全部")
    @PostMapping("/selectAll")
    public Resp selectAll() {
        return Resp.success(historyService.selectAll());
    }

    @ApiOperation(value = "查看全部-分页")
    @GetMapping("/pageSelectAll")
    public Resp pageSelectAll(@RequestParam Integer pageNum, @RequestParam Integer size) {
        PageHelper.startPage(pageNum, size);
        List<History> list = historyService.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.getTotal();
        return Resp.success(pageInfo);
    }
    
    @ApiOperation(value = "根据字段查询")
    @PostMapping("/selectByAll")
    public Resp selectByAll(@RequestBody(required = false) History history) {
        return Resp.success(historyService.selectByAll(history));
    }

    @ApiOperation(value = "根据用户id拉取历史")
    @PostMapping("/selectByUserId")
    public Resp selectByUserId(int userid, @RequestParam Integer pageNum, @RequestParam Integer size) {
        PageHelper.startPage(pageNum, size);
        PageInfo pageInfo = new PageInfo(historyService.selectByUserId(userid));
        pageInfo.getTotal();
        return Resp.success(pageInfo);
    }
}
