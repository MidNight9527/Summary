package com.example.springboottest2.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.springboottest2.mapper.HistoryMapper;
import com.example.springboottest2.entity.History;import java.util.List;

@Service
public class HistoryService {

    @Resource
    private HistoryMapper historyMapper;


    public int deleteByPrimaryKey(Integer historyid) {
        return historyMapper.deleteByPrimaryKey(historyid);
    }


    public int insert(History record) {
        return historyMapper.insert(record);
    }


    public int insertSelective(History record) {
        return historyMapper.insertSelective(record);
    }


    public History selectByPrimaryKey(Integer historyid) {
        return historyMapper.selectByPrimaryKey(historyid);
    }


    public int updateByPrimaryKeySelective(History record) {
        return historyMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(History record) {
        return historyMapper.updateByPrimaryKey(record);
    }

    public List<History> selectByAll(History history) {
        return historyMapper.selectByAll(history);
    }

    public List<History> selectAll() {
        return historyMapper.selectAll();
    }

    public List<History> selectByUserId(int userid) {
        History tempHistoty = new History();
        tempHistoty.setHistoryuserid(userid);
        return selectByAll(tempHistoty);
    }
}


