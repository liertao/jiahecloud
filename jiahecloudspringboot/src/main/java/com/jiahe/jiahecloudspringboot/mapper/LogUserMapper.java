package com.jiahe.jiahecloudspringboot.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LogUserMapper {

    /**
     * 校验登录信息
     * @param params
     * @return
     * @throws Exception
     */
    public List<Map> checkLogin(Map params)throws Exception;
}
