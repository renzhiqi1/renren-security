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

import io.renren.modules.sys.entity.ZhzgTerminlEntity;
import io.renren.modules.sys.service.ZhzgTerminlService;
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
@RequestMapping("sys/zhzgterminl")
public class ZhzgTerminlController {
    @Autowired
    private ZhzgTerminlService zhzgTerminlService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:zhzgterminl:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = zhzgTerminlService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{terminalId}")
    @RequiresPermissions("sys:zhzgterminl:info")
    public R info(@PathVariable("terminalId") Integer terminalId){
        ZhzgTerminlEntity zhzgTerminl = zhzgTerminlService.getById(terminalId);

        return R.ok().put("zhzgTerminl", zhzgTerminl);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:zhzgterminl:save")
    public R save(@RequestBody ZhzgTerminlEntity zhzgTerminl){
        zhzgTerminlService.save(zhzgTerminl);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:zhzgterminl:update")
    public R update(@RequestBody ZhzgTerminlEntity zhzgTerminl){
        ValidatorUtils.validateEntity(zhzgTerminl);
        zhzgTerminlService.updateById(zhzgTerminl);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:zhzgterminl:delete")
    public R delete(@RequestBody Integer[] terminalIds){
        zhzgTerminlService.removeByIds(Arrays.asList(terminalIds));

        return R.ok();
    }

}
