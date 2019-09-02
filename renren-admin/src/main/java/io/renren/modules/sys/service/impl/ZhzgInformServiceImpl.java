package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ZhzgInformDao;
import io.renren.modules.sys.entity.ZhzgInformEntity;
import io.renren.modules.sys.service.ZhzgInformService;


@Service("zhzgInformService")
public class ZhzgInformServiceImpl extends ServiceImpl<ZhzgInformDao, ZhzgInformEntity> implements ZhzgInformService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ZhzgInformEntity> page = this.page(
                new Query<ZhzgInformEntity>().getPage(params),
                new QueryWrapper<ZhzgInformEntity>()
        );

        return new PageUtils(page);
    }

}
