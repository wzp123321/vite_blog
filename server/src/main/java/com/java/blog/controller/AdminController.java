package com.java.blog.controller;

import com.auth0.jwt.interfaces.Claim;
import com.java.blog.service.AdminManageService;
import com.java.blog.utils.TokenUtils;
import com.java.blog.vo.ReturnResultVo;
import io.swagger.annotations.Api;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(description = "管理员模块")
@RestController()
@RequestMapping(value = "/blogManage/admin")
public class AdminController {

    @Autowired
    private AdminManageService adminManageService;

    /**
     * 新增
     *
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/add")
    public ReturnResultVo getAdminVoAdd(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password
    ) {
        boolean flag = adminManageService.getAdminVoInsert(name, password);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ReturnResultVo getAdminVoLogin(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        ModelMap modelMap = adminManageService.getAdminLogin(username, password);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }

    /**
     * 删除
     *
     * @param name
     * @return
     */
    @PostMapping("delete")
    public ReturnResultVo getAdminVoDelete(
            @RequestParam(value = "name", required = true) String name
    ) {
        boolean flag = adminManageService.getAdminVoDelete(name);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * 更新
     *
     * @param id
     * @param name
     * @param password
     * @return
     */
    @PostMapping("update")
    public ReturnResultVo getAdminVoUpdate(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password
    ) {
        boolean flag = adminManageService.getAdminVoUpdate(id, name, password);
        return new ReturnResultVo(200, flag ? "SUCCESS" : "FAIL", flag);
    }

    /**
     * token校验登陆状态
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("verify")
    public ReturnResultVo getAdminTokenVerify(
            HttpServletRequest httpServletRequest
    ) {
        String token = httpServletRequest.getHeader("s-Auth-Token");
        Map<String, Claim> tokenMap = TokenUtils.verifyToken(token);
        ModelMap modelMap = TokenUtils.getTokenInfo(token);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }

    /**
     * 列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    public ReturnResultVo getAdminVoList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        ModelMap modelMap = adminManageService.getAdminVoList(page, limit);
        return new ReturnResultVo(200, "SUCCESS", modelMap);
    }
}
