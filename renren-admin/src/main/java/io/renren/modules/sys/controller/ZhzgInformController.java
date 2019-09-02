package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ZhzgInformEntity;
import io.renren.modules.sys.service.ZhzgInformService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Rzq
 * @email ${email}
 * @date 2019-09-02 10:31:31
 */
@RestController
@RequestMapping("sys/zhzginform")
public class ZhzgInformController {
    @Autowired
    private ZhzgInformService zhzgInformService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:zhzginform:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zhzgInformService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{informId}")
    @RequiresPermissions("sys:zhzginform:info")
    public R info(@PathVariable("informId") Integer informId){
        ZhzgInformEntity zhzgInform = zhzgInformService.getById(informId);

        return R.ok().put("zhzgInform", zhzgInform);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:zhzginform:save")
    public R save(@RequestBody ZhzgInformEntity zhzgInform){
        zhzgInformService.save(zhzgInform);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:zhzginform:update")
    public R update(@RequestBody ZhzgInformEntity zhzgInform){
        ValidatorUtils.validateEntity(zhzgInform);
        zhzgInformService.updateById(zhzgInform);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:zhzginform:delete")
    public R delete(@RequestBody Integer[] informIds){
        zhzgInformService.removeByIds(Arrays.asList(informIds));

        return R.ok();
    }

}
