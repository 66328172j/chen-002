package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TSportCrowdReg;
import com.fc.v2.service.ITSportCrowdRegService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 开放时段泳客登记单 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "开放时段泳客登记单")
@Controller
@RequestMapping("/SportCrowdRegController")
public class SportCrowdRegController extends BaseController {

    private final String prefix = "admin/sportCrowdReg";

    @Autowired
    private ITSportCrowdRegService sportCrowdRegService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("sport:sportCrowdReg:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "开放时段泳客登记单集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("sport:sportCrowdReg:list")
    @ResponseBody
    public ResultTable list(TSportCrowdReg record) {
        QueryWrapper<TSportCrowdReg> queryWrapper = new QueryWrapper<TSportCrowdReg>();
        startPage();
        com.github.pagehelper.PageInfo<TSportCrowdReg> page =
                new com.github.pagehelper.PageInfo<TSportCrowdReg>(sportCrowdRegService.selectTSportCrowdRegList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "开放时段泳客登记单新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("sport:sportCrowdReg:add")
    @ResponseBody
    public AjaxResult add(TSportCrowdReg record) {
        return toAjax(sportCrowdRegService.insertTSportCrowdReg(record));
    }

    @Log(title = "开放时段泳客登记单修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("sport:sportCrowdReg:edit")
    @ResponseBody
    public AjaxResult editSave(TSportCrowdReg record) {
        return toAjax(sportCrowdRegService.updateTSportCrowdReg(record));
    }

    @Log(title = "开放时段泳客登记单删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("sport:sportCrowdReg:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sportCrowdRegService.deleteTSportCrowdRegByIds(ids));
    }
}
