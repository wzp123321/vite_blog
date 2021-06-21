package com.java.blog.service;

import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.dao.AdminResMapper;
import com.java.blog.utils.TokenUtils;
import com.java.blog.utils.UUIDUtils;
import com.java.blog.vo.AdminVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;

@Service
public class AdminManageService {

    @Autowired
    private AdminResMapper adminResMapper;

    private Logger logger = LoggerFactory.getLogger(AdminManageService.class);

    /**
     * 管理媛登录
     *
     * @param name
     * @param password
     * @return
     */
    public ModelMap getAdminLogin(String name, String password) {
        AdminVo adminVo = adminResMapper.getAdminVoInfoVByName(name);
        String token = "";
        if (adminVo == null) {
            throw new CommonOperationException("用户不存在或密码错误！");
        }
        String salt = adminVo.getSalt();

        String desPwd = UUIDUtils.getEncrpytedPassword(password, salt);

        if (desPwd.equals(adminVo.getPassword())) {
            token = TokenUtils.createToken(adminVo.getId(), name);
        } else {
            throw new CommonOperationException("用户不存在或密码错误！");
        }
        ModelMap modelMap = TokenUtils.getTokenInfo(token);
        modelMap.put("token", token);
        logger.info("管理员" + name + "登陆成功");
        return modelMap;
    }

    /**
     * 新增管理媛
     *
     * @param name
     * @param password
     * @return
     */
    public boolean getAdminVoInsert(String name, String password) {
        boolean flag = false;

        String salt = UUIDUtils.getRandomMD5();

        AdminVo adminVo = adminResMapper.getAdminVoInfoVByName(name);

        String destPwd = UUIDUtils.getEncrpytedPassword(password, salt);

        if (adminVo != null) {
            throw new CommonOperationException("用户名已存在！");
        } else {
            flag = adminResMapper.getAdminBoInsert(name, salt, destPwd) > 0;
        }
        logger.info("新增管理员" + name);
        return flag;
    }

    /**
     * 删除管理媛
     *
     * @param name
     * @return
     */
    public boolean getAdminVoDelete(String name) {
        AdminVo adminVo = adminResMapper.getAdminVoInfoVByName(name);

        if (adminVo == null) {
            throw new CommonOperationException("用户不存在！");
        }
        logger.info("管理员" + name + "被删除");
        return adminResMapper.getAdminVoDelete(name) > 0;
    }

    /**
     * 修改管理媛
     *
     * @param name
     * @param password
     * @return
     */
    public boolean getAdminVoUpdate(Integer id, String name, String password) {
        AdminVo adminVo = adminResMapper.getAdminVoInfoById(id);

        if (adminVo == null) {
            throw new CommonOperationException("用户不存在！");
        }
        String salt = adminVo.getSalt();
        String desPwd = UUIDUtils.getEncrpytedPassword(password, salt);
        logger.info("管理员" + name + "信息被修改");
        return adminResMapper.getAdminVoUpdate(id, name, desPwd, new Date()) > 0;
    }

    /**
     * 分页查询管理员媛列表
     *
     * @param page
     * @param limit
     * @return
     */
    public ModelMap getAdminVoList(int page, int limit) {
        int skip = page == 1 ? 0 : (page - 1) * limit;
        List<AdminVo> adminVos = adminResMapper.getAdminVoList(skip, limit);
        int total = adminResMapper.getAdminVoTotal();
        ModelMap modelMap = new ModelMap();
        modelMap.put("list", adminVos);
        modelMap.put("total", total);
        return modelMap;
    }
}
