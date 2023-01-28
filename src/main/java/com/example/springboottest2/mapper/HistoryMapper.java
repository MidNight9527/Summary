package com.example.springboottest2.mapper;

import com.example.springboottest2.entity.History;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(Integer historyid);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyid);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> selectByAll(History history);

    List<History> selectAll();
}