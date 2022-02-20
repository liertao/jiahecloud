package com.jiahe.jiahecloudspringboot.mapper;

import java.util.List;
import java.util.Map;

/**
 * 辅助帮助接口
 */
public interface HelpInputMapper {

    /**
     * 辅助帮助 ：获取所有项目信息
     * @return
     * @throws Exception
     */
    public List<Map> loadPorjects(Map params)throws Exception;

    /**
     * 辅助帮助： 获取所有的费用类型
     * @return
     * @throws Exception
     */
    public List<Map> loadCostType()throws Exception;

    /**
     * 根据年份获取所有 work_date表的所有日期数据
     * @return
     * @throws Exception
     */
    public List<Map> loadDateByYear(Map params)throws Exception;

    /**
     * 根据年份修改  work_date表的休息日信息
     * @param params
     * @return
     * @throws Exception
     */
    public void updateWorkDatesByDate(Map params)throws Exception;

    /**
     * 处理id问题
     * @throws Exception
     */
    public void manProIds()throws Exception;
}
