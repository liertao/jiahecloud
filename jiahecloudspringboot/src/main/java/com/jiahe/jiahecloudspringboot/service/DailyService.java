package com.jiahe.jiahecloudspringboot.service;

import com.jiahe.jiahecloudspringboot.common.entity.ProjectCost;
import com.jiahe.jiahecloudspringboot.mapper.DailyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DailyService {

    @Resource
    private DailyMapper dailyMapper;

    public List<Map> loadDaily(Map params)throws Exception{
        return dailyMapper.loadDaily(params);
    }

    public void dailyAdd(ProjectCost projectCost)throws Exception{
        dailyMapper.dailyAdd(projectCost);
    }

    public void delDailyById(Map params)throws Exception{
        dailyMapper.delDailyById(params);
    }

    public List<String> loadDailyCurrentWorkDate(Map params)throws Exception{
        return dailyMapper.loadDailyCurrentWorkDate(params);
    }

    public List<String> loadUNWriteWorkDate(Map params)throws Exception{
        return dailyMapper.loadUNWriteWorkDate(params);
    }

    public List<String> loadDailyByDate(Map params)throws Exception{
        return dailyMapper.loadDailyByDate(params);
    }

    public List selectUserinfo(ProjectCost projectCost)throws Exception{
        return dailyMapper.selectUserinfo(projectCost);
    }

    public Long selectUserCount(ProjectCost projectCost)throws Exception{
        return dailyMapper.selectUserCount(projectCost);
    }
}
