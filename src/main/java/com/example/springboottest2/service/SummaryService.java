package com.example.springboottest2.service;

import com.example.springboottest2.entity.History;
import com.example.springboottest2.utils.summary.Summary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SummaryService {

    Summary mSumMan = new Summary();

    @Resource
    HistoryService historyService;

    public String AbsFromStr(String docContent, float inPercent, int userid){
        String text = mSumMan.AbsFromStr(docContent, inPercent);
        History tempHistory = new History();
        tempHistory.setHistoryuserid(userid);
        tempHistory.setOriginaltext(docContent);
        tempHistory.setSummarytext(text);
        historyService.insert(tempHistory);
        return text;
    }

}
