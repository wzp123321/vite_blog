package com.java.blog.service;

import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.dao.DictionaryResMapper;
import com.java.blog.vo.DictionaryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryResMapper dictionaryResMapper;

    private Logger logger = LoggerFactory.getLogger(DictionaryService.class);


    /**
     * 新增字典
     *
     * @param type
     * @param title
     * @param description
     * @param parentId
     * @param parentTitle
     * @return
     */
    public boolean getDictionaryInsert(int type, String title, String description, Integer parentId, String parentTitle) {
        logger.info("新增字典" + type + "-" + title + "-" + description + "-" + parentId + "-" + parentTitle);
        DictionaryVo dictionaryVo = new DictionaryVo();
        dictionaryVo.setType(type);
        dictionaryVo.setTitle(title);
        dictionaryVo.setParentId(parentId);
        dictionaryVo.setParentTitle(parentTitle);
        dictionaryVo.setDescription(description);
        return dictionaryResMapper.getDictionaryInsert(dictionaryVo) > 0;
    }

    /**
     * 编辑
     *
     * @param id
     * @param type
     * @param title
     * @param description
     * @param parentId
     * @param parentTitle
     * @return
     */
    public boolean getDictionaryUpdate(Integer id, Integer type, String title, String description, Integer parentId, String parentTitle) {
        DictionaryVo dictionaryVo = dictionaryResMapper.getDictionaryViInfoById(id);

        if (dictionaryVo == null) {
            throw new CommonOperationException("id不存在！");
        }
        dictionaryVo.setId(id);
        dictionaryVo.setType(type);
        dictionaryVo.setTitle(title);
        dictionaryVo.setParentId(parentId);
        dictionaryVo.setParentTitle(parentTitle);
        dictionaryVo.setDescription(description);
        dictionaryVo.setModifyTime(new Date());

        logger.info("编辑字典" + id + "-" + type + "-" + title + "-" + description + "-" + parentId + "-" + parentTitle);
        return dictionaryResMapper.getDictionaryVoUpdate(dictionaryVo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean getDictionaryVoDelete(Integer id) {
        DictionaryVo dictionaryVo = dictionaryResMapper.getDictionaryViInfoById(id);

        if (dictionaryVo == null) {
            throw new CommonOperationException("id不存在");
        }
        return dictionaryResMapper.getDictionaryDeleteById(id) > 0;
    }

    /**
     * 分页查询
     *
     * @param type
     * @param page
     * @param limit
     * @return
     */
    public ModelMap getDictionaryList(Integer type, int page, int limit) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        int count = dictionaryResMapper.getDictionaryVoCount(type);
        List<DictionaryVo> dictionaryVos = dictionaryResMapper.getDictionaryList(type, skip, limit);
        ModelMap modelMap = new ModelMap();
        modelMap.put("list", dictionaryVos);
        modelMap.put("total", count);
        return modelMap;
    }
}
