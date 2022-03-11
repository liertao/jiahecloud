package com.jiahe.jiahecloudspringboot.controller;

import com.google.gson.JsonObject;
import com.jiahe.jiahecloudspringboot.common.entity.LogId;
import com.jiahe.jiahecloudspringboot.common.entity.ProjectCost;
import com.jiahe.jiahecloudspringboot.common.util.CommonUtils;
import com.jiahe.jiahecloudspringboot.common.util.TokenUtil;
import com.jiahe.jiahecloudspringboot.service.LogUserService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
            String token = TokenUtil.createToken(map);
            if(map.get("logPwd").equals(CommonUtils.encrypt(params.get("logPwd")))){
                result.put("root", map);
                result.put("success", true);
                result.put("code", 20000);
                result.put("token", token);
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

    /**
     * 登出
     * @param params
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login/logout")
    public Map logout(@RequestParam Map<String, String> params, HttpServletResponse response)throws Exception{
        Map result = new HashMap();
        try {
            result.put("success", true);
            result.put("code", 20000);
            result.put("token", null);
            result.put("msg","登出成功！");
        }catch (Exception e){
            result.put("success", false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 根据token获取用户的logId 和 logName
     * @param params
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login/userinfo")
    public Map getUserInfo(@RequestParam Map<String, String> params, HttpServletResponse response)throws Exception{
        Map result = new HashMap();
        String token = params.get("token")==null?"":params.get("token").toString();
        String logId = TokenUtil.getLogIdFromToken(token, "logId");
        String logName = TokenUtil.getLogIdFromToken(token,"logName");
        JSONObject json = new JSONObject();
        json.accumulate("name", logName)
                .accumulate("logId", logId)
                .accumulate("logName", logName);
        try {
            result.put("success", true);
            result.put("code", 20000);
            result.put("data", json.toString());
            result.put("msg","校验成功！");
        }catch (Exception e){
            result.put("success", false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    @RequestMapping("/department/loadEmpByDeptCode")
    public Map loadEmpByDeptCode(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Map result = new HashMap();
        try {
            List userinfos = logUserService.loadEmpByDeptCode(params);
            result.put("success", true);
            result.put("code", 20000);
            result.put("msg", "加载成功");
            result.put("root", userinfos);
        }catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
