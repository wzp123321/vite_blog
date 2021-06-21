package com.java.blog.service;

import com.java.blog.common.exception.CommonOperationException;
import com.java.blog.dao.BannerResMapper;
import com.java.blog.vo.BannerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class BannerManageService {

    @Autowired
    private BannerResMapper bannerResMapper;

    private Logger logger = LoggerFactory.getLogger(BannerManageService.class);

    /**
     * 新增banner
     *
     * @param title
     * @param imageUrl
     * @param articleId
     * @return
     */
    public boolean getBannerVoInsert(String title, String imageUrl, String articleId) {
        logger.info("新增banner" + title + "-" + imageUrl + "-" + articleId);
        return bannerResMapper.getBannerVoInsert(title, imageUrl, articleId) > 0;
    }

    /**
     * 编辑
     *
     * @param id
     * @param title
     * @param imageUrl
     * @param articleId
     * @param state
     * @return
     */
    public boolean getBannerVoUpdate(int id, String title, String imageUrl, String articleId, Integer state) {
        logger.info("编辑banner" + id + "-" + title + "-" + imageUrl + "-" + articleId + "-" + state);
        return bannerResMapper.getBannerVoUpdate(id, title, imageUrl, articleId, state) > 0;
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    public BannerVo getBannerVoInfoById(int id) {
        BannerVo bannerVo = bannerResMapper.getBannerVoById(id);
        if (bannerVo == null) {
            throw new CommonOperationException("banner不存在！");
        }
        logger.info("banner详情" + bannerVo);
        return bannerVo;
    }

    /**
     * 分页加载
     *
     * @param page
     * @param limit
     * @param state
     * @return
     */
    public ModelMap getBannerVoList(int page, int limit, Integer state) {
        ModelMap modelMap = new ModelMap();
        int skip = page == 1 ? 0 : (page - 1) * limit;
        int total = bannerResMapper.getBannerVoTotal(state);
        List<BannerVo> bannerVos = bannerResMapper.getBannerVoList(skip, limit, state);
        modelMap.put("list", bannerVos);
        modelMap.put("total", total);
        return modelMap;
    }

    /**
     * 删除Banner
     * @param id
     * @return
     */
    public boolean getBannerDelete(Integer id) {
        return bannerResMapper.getBannerVoDelete(id) > 0;
    }
}
