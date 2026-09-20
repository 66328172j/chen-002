package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TSportSessionOrder;
import com.fc.v2.service.ITSportSessionOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 开放场次核查单 Controller（state-machine 形状：流转入口）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Api(value = "开放场次核查单")
@Controller
@RequestMapping("/sportSessionOrder")
public class SportSessionOrderController extends BaseController {

    private final String prefix = "admin/sportSessionOrder";

    @Autowired
    private ITSportSessionOrderService sportSessionOrderService;

    @ApiOperation(value = "流转台账跳转", notes = "流转台账跳转")
    @GetMapping("/view")
    @RequiresPermissions("sportSessionOrder:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "开放场次核查单流转台账", action = "list")
    @ApiOperation(value = "流转台账", notes = "流转台账")
    @GetMapping("/list")
    @RequiresPermissions("sportSessionOrder:list")
    @ResponseBody
    public ResultTable list(TSportSessionOrder record) {
        QueryWrapper<TSportSessionOrder> queryWrapper = new QueryWrapper<TSportSessionOrder>();
        startPage();
        com.github.pagehelper.PageInfo<TSportSessionOrder> page =
                new com.github.pagehelper.PageInfo<TSportSessionOrder>(sportSessionOrderService.selectTSportSessionOrderList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "开放场次核查单推进", action = "advance")
    @ApiOperation(value = "推进一档", notes = "推进一档")
    @PostMapping("/advance")
    @RequiresPermissions("sportSessionOrder:advance")
    @ResponseBody
    public AjaxResult advance(Long id, String remark) {
        return toAjax(sportSessionOrderService.advance(id, remark) != null ? 1 : 0);
    }

    @Log(title = "开放场次核查单回退", action = "rollback")
    @ApiOperation(value = "回退一档", notes = "回退一档")
    @PostMapping("/rollback")
    @RequiresPermissions("sportSessionOrder:rollback")
    @ResponseBody
    public AjaxResult rollback(Long id, String remark) {
        return toAjax(sportSessionOrderService.rollback(id, remark) != null ? 1 : 0);
    }
}
