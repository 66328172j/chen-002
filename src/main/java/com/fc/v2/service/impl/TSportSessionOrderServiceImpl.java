package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TSportSessionOrderMapper;
import com.fc.v2.model.auto.TSportSessionOrder;
import com.fc.v2.service.ITSportSessionOrderService;

/**
 * 开放场次核查单 Service业务层处理（state-machine 形状：单据流转）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TSportSessionOrderServiceImpl implements ITSportSessionOrderService {

    private static final int MAX_STAGE = 3;
    private static final int STATUS_ACTIVE = 1;
    private static final int STATUS_TERMINAL = 2;

    @javax.annotation.Resource
    private TSportSessionOrderMapper sportSessionOrderMapper;

    @Override
    public TSportSessionOrder selectTSportSessionOrderById(Long id) {
        return this.sportSessionOrderMapper.selectById(id);
    }

    @Override
    public List<TSportSessionOrder> selectTSportSessionOrderList(QueryWrapper<TSportSessionOrder> queryWrapper) {
        return this.sportSessionOrderMapper.selectList(queryWrapper);
    }

    @Override
    public TSportSessionOrder advance(Long id, String remark) {
        TSportSessionOrder r = this.sportSessionOrderMapper.selectById(id);
        if (r == null) {
            return null;
        }
        int st = r.getStage() == null ? 0 : r.getStage();
        r.setStage(Math.min(st + 2, MAX_STAGE));
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.sportSessionOrderMapper.updateById(r);
        return r;
    }

    @Override
    public TSportSessionOrder rollback(Long id, String remark) {
        TSportSessionOrder r = this.sportSessionOrderMapper.selectById(id);
        if (r == null) {
            return null;
        }
        r.setStage(0);
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.sportSessionOrderMapper.updateById(r);
        return r;
    }

    @Override
    public boolean updateContent(Long id, String remark) {
        TSportSessionOrder r = this.sportSessionOrderMapper.selectById(id);
        if (r == null) {
            return false;
        }
        r.setContent(remark);
        return this.sportSessionOrderMapper.updateById(r) > 0;
    }

    @Override
    public boolean remove(Long id) {
        TSportSessionOrder r = this.sportSessionOrderMapper.selectById(id);
        if (r == null) {
            return false;
        }
        return this.sportSessionOrderMapper.deleteById(id) > 0;
    }

}
