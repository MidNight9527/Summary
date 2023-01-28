package com.example.springboottest2.controller;

import com.example.springboottest2.entity.http.Resp;
import com.example.springboottest2.service.SummaryService;
import com.example.springboottest2.utils.summary.Summary;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("summary")
public class SummaryController {

    @Resource
    SummaryService summaryService;

    @ApiOperation(value = "通过文字获取摘要")
    @PostMapping("/getabsfromstr")
    public Resp getAbstractFromStr(String docContent, float inPercent, int userid) {
        return Resp.success(summaryService.AbsFromStr(docContent, inPercent, userid));
    }

}
