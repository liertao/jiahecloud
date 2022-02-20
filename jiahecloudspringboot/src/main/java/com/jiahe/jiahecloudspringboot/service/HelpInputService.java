package com.jiahe.jiahecloudspringboot.service;

import com.jiahe.jiahecloudspringboot.mapper.HelpInputMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 辅助帮助服务类
 */
@Service
public class HelpInputService {

    @Resource
    private HelpInputMapper helpInputMapper;

    /**
     * 获取所有的项目信息
     * @return
     * @throws Exception
     */
    public List<Map> loadPorjects(Map params)throws Exception{
        return helpInputMapper.loadPorjects(params);
    }

    /**
     * 获取所有的 费用类型信息
     * @return
     * @throws Exception
     */
    public List<Map> loadCostType()throws Exception{
        return helpInputMapper.loadCostType();
    }

    /**
     * 根据年份获取所有 work_date表的所有日期数据
     * @return
     * @throws Exception
     */
    public List<Map> loadDateByYear(Map params)throws Exception{
        return helpInputMapper.loadDateByYear(params);
    }

    /**
     * 根据日期修改  woek_dates表的休息日  信息
     * @param params
     * @throws Exception
     */
    public void updateWorkDatesByDate(Map params)throws Exception{
        helpInputMapper.updateWorkDatesByDate(params);
    }

    /**
     * 处理project_cost表最大id 小于 project_cost_id的最大序列值的问题   加50
     * @throws Exception
     */
    public void manProIds()throws Exception{
        helpInputMapper.manProIds();
    }
}
