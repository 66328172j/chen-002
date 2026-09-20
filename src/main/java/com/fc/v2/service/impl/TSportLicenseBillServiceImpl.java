package com.fc.v2.service.impl;

import org.springframework.stereotype.Service;

import com.fc.v2.mapper.auto.TSportLicenseBillMapper;
import com.fc.v2.model.auto.TSportLicenseBill;
import com.fc.v2.service.ITSportLicenseBillService;

/**
 * 经营项目变更与许可延续签批单 Service业务层处理（approval-chain 形状：多阶段签批）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TSportLicenseBillServiceImpl implements ITSportLicenseBillService {

    private static final int MAX_NODE = 2;
    private static final int MODE_OR = 0;
    private static final int MODE_AND = 1;
    private static final int STATUS_RUNNING = 0;
    private static final int STATUS_PASS = 1;
    private static final int STATUS_VETO = 2;

    @javax.annotation.Resource
    private TSportLicenseBillMapper sportLicenseBillMapper;

    @Override
    public TSportLicenseBill selectTSportLicenseBillById(Long id) {
        return this.sportLicenseBillMapper.selectById(id);
    }

    @Override
    public TSportLicenseBill approve(Long id, String approver, String comment) {
        TSportLicenseBill r = this.sportLicenseBillMapper.selectById(id);
        if (r == null || approver == null || approver.trim().isEmpty()) {
            return null;
        }
        r.setNodeNo(Integer.valueOf((r.getNodeNo() == null ? 0 : r.getNodeNo()) + 1));
        r.setStatus(Integer.valueOf(r.getNodeNo() >= MAX_NODE ? STATUS_PASS : STATUS_RUNNING));
        this.sportLicenseBillMapper.updateById(r);
        return r;
    }

    @Override
    public TSportLicenseBill reject(Long id, String approver, String comment) {
        TSportLicenseBill r = this.sportLicenseBillMapper.selectById(id);
        if (r == null) {
            return null;
        }
        this.sportLicenseBillMapper.updateById(r);
        return r;
    }

    @Override
    public TSportLicenseBill rollback(Long id, String comment) {
        TSportLicenseBill r = this.sportLicenseBillMapper.selectById(id);
        if (r == null) {
            return null;
        }
        int node = r.getNodeNo() == null ? 0 : r.getNodeNo();
        r.setNodeNo(Integer.valueOf(Math.max(0, node - 1)));
        this.sportLicenseBillMapper.updateById(r);
        return r;
    }
}
