package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ZhzgTerminlDao;
import io.renren.modules.sys.entity.ZhzgTerminlEntity;
import io.renren.modules.sys.service.ZhzgTerminlService;


@Service("zhzgTerminlService")
public class ZhzgTerminlServiceImpl extends ServiceImpl<ZhzgTerminlDao, ZhzgTerminlEntity> implements ZhzgTerminlService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ZhzgTerminlEntity> page = this.page(
                new Query<ZhzgTerminlEntity>().getPage(params),
                new QueryWrapper<ZhzgTerminlEntity>()
        );

        return new PageUtils(page);
    }

}
