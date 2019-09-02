package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ZhzgPlanDao;
import io.renren.modules.sys.entity.ZhzgPlanEntity;
import io.renren.modules.sys.service.ZhzgPlanService;


@Service("zhzgPlanService")
public class ZhzgPlanServiceImpl extends ServiceImpl<ZhzgPlanDao, ZhzgPlanEntity> implements ZhzgPlanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ZhzgPlanEntity> page = this.page(
                new Query<ZhzgPlanEntity>().getPage(params),
                new QueryWrapper<ZhzgPlanEntity>()
        );

        return new PageUtils(page);
    }

}
