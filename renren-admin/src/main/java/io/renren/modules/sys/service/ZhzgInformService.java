package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ZhzgInformEntity;

import java.util.Map;

/**
 * 
 *
 * @author Rzq
 * @email ${email}
 * @date 2019-09-02 10:31:31
 */
public interface ZhzgInformService extends IService<ZhzgInformEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

