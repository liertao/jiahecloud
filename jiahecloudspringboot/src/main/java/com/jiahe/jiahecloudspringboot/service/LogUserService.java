package com.jiahe.jiahecloudspringboot.service;

import com.jiahe.jiahecloudspringboot.mapper.LogUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class LogUserService {

    @Resource
    private LogUserMapper logUserMapper;

    public List<Map> checkLogin(Map params)throws Exception{
        return logUserMapper.checkLogin(params);
    }

    public List<Map> loadEmpByDeptCode(Map params)throws Exception{
        return logUserMapper.loadEmpByDeptCode(params);
    }

    public List<Map> loadPageEmpByDeptCode(Map params)throws Exception{
        return logUserMapper.loadPageEmpByDeptCode(params);
    }

    public Long loadCountEmpByDeptCode(Map params)throws Exception{
        return logUserMapper.loadCountEmpByDeptCode(params);
    }
}
