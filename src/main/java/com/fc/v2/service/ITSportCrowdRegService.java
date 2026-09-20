package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TSportCrowdReg;

import java.util.List;

/**
 * 开放时段泳客登记单 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITSportCrowdRegService {

    /** 按主键查询 */
    TSportCrowdReg selectTSportCrowdRegById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TSportCrowdReg> selectTSportCrowdRegList(Wrapper<TSportCrowdReg> queryWrapper);

    /** 新增 */
    int insertTSportCrowdReg(TSportCrowdReg record);

    /** 修改 */
    int updateTSportCrowdReg(TSportCrowdReg record);

    /** 批量删除 */
    int deleteTSportCrowdRegByIds(String ids);

    /** 按主键删除 */
    int deleteTSportCrowdRegById(Long id);
}
