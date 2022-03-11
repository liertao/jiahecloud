package com.jiahe.jiahecloudspringboot.mapper;

import com.jiahe.jiahecloudspringboot.common.entity.ProjectCost;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DailyMapper {

    /**
     * 查询日志信息
     * @param params
     * @return
     * @throws Exception
     */
    public List<Map> loadDaily(Map params)throws Exception;

    /**
     * 日志新增
     * @param projectCost
     * @throws Exception
     */
    public void dailyAdd(ProjectCost projectCost)throws Exception;

    /**
     * 根据id删除日志
     * @param params
     * @return
     * @throws Exception
     */
    public void delDailyById(Map params)throws Exception;

    /**
     * 加载当前日期的所有日志  工作日期
     * @return
     * @throws Exception
     */
    public List<String> loadDailyCurrentWorkDate(Map params)throws Exception;

    /**
     * 查询应该写但是没写的日志日期
     * @return
     * @throws Exception
     */
    public List<String> loadUNWriteWorkDate(Map params)throws Exception;

    /**
     * 根据指定日期查询当天的日志信息
     * @param params
     * @return
     * @throws Exception
     */
    public List<String> loadDailyByDate(Map params)throws Exception;

    public List<ProjectCost> selectUserinfo(ProjectCost projectCost)throws Exception;

    public Long selectUserCount(ProjectCost projectCost)throws Exception;
}
