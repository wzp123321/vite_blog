package com.java.blog.service;

import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.dao.EnumResMapper;
import com.java.blog.vo.EnumVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;

@Service
public class EnumManageService {

    @Autowired
    private EnumResMapper enumResMapper;

    private Logger logger = LoggerFactory.getLogger(AdminManageService.class);

    /**
     * 新增枚举
     *
     * @param code
     * @param title
     * @return
     */
    public boolean getEnumVoInsert(int code, String title) {
        EnumVo enumVo = enumResMapper.getEnumVoByCode(code);
        if (enumVo != null) {
            throw new CommonOperationException("当前key已存在！");
        }
        logger.info("新增枚举" + code);
        return enumResMapper.getEnumVoInsert(code, title) > 0;
    }

    /**
     * 删除枚举
     *
     * @param id
     * @return
     */
    public boolean getEnumVoDelete(int id) {
        logger.info("枚举" + id + "删除");
        return enumResMapper.getEnumVoDelete(id) > 0;
    }

    /**
     * 编辑枚举
     *
     * @param id
     * @param code
     * @param title
     * @return
     */
    public boolean getEnumVoUpdate(int id, int code, String title) {
        EnumVo enumVo = enumResMapper.getEnumVoByCode(code);
        if (enumVo == null) {
            throw new CommonOperationException("当前code不存在！");
        }
        logger.info("枚举" + code + "更新");
        return enumResMapper.getEnumVoUpdate(id, code, title, new Date()) > 0;
    }

    /**
     * 查询列表
     *
     * @return
     */
    public ModelMap getEnumVoList() {
        ModelMap modelMap = new ModelMap();
        List<EnumVo> enumVos = enumResMapper.getEnumVoList();
        modelMap.put("list", enumVos);
        return modelMap;
    }
}
