package com.jiahe.jiahecloudspringboot.controller;

import com.jiahe.jiahecloudspringboot.common.util.CommonUtils;
import com.jiahe.jiahecloudspringboot.service.LogUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    private LogUserService logUserService = null;

    /**
     * 登录校验
     * @param params
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login/checklogin")
    public Map checkLogin(@RequestParam Map<String, String> params, HttpServletResponse response)throws Exception{
        Map result = new HashMap();
        List list = new ArrayList();
        try {
            list = logUserService.checkLogin(params);
            if(list.size() == 0){
                result.put("success", false);
                result.put("msg","用户不存在！");
                return result;
            }
            Map map = (Map) list.get(0);
            if(map.get("logPwd").equals(CommonUtils.encrypt(params.get("logPwd")))){
                result.put("root", map);
                result.put("success", true);
                result.put("msg","登录成功！");
            }else{
                result.put("success", false);
                result.put("msg","密码错误！");
            }
        }catch (Exception e){
            result.put("success", false);
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
