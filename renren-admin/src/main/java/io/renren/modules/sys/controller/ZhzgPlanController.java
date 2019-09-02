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

import io.renren.modules.sys.entity.ZhzgPlanEntity;
import io.renren.modules.sys.service.ZhzgPlanService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Rzq
 * @email ${email}
 * @date 2019-09-02 10:31:30
 */
@RestController
@RequestMapping("sys/zhzgplan")
public class ZhzgPlanController {
    @Autowired
    private ZhzgPlanService zhzgPlanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:zhzgplan:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zhzgPlanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{planId}")
    @RequiresPermissions("sys:zhzgplan:info")
    public R info(@PathVariable("planId") Long planId){
        ZhzgPlanEntity zhzgPlan = zhzgPlanService.getById(planId);

        return R.ok().put("zhzgPlan", zhzgPlan);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:zhzgplan:save")
    public R save(@RequestBody ZhzgPlanEntity zhzgPlan){
        zhzgPlanService.save(zhzgPlan);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:zhzgplan:update")
    public R update(@RequestBody ZhzgPlanEntity zhzgPlan){
        ValidatorUtils.validateEntity(zhzgPlan);
        zhzgPlanService.updateById(zhzgPlan);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:zhzgplan:delete")
    public R delete(@RequestBody Long[] planIds){
        zhzgPlanService.removeByIds(Arrays.asList(planIds));

        return R.ok();
    }

}
