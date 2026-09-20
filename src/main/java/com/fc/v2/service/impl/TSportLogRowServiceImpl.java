package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TSportLogRowMapper;
import com.fc.v2.model.auto.TSportLogRow;
import com.fc.v2.service.ITSportLogRowService;

/**
 * 月度开放日志报送明细 Service业务层处理（batch-process 形状：整批提交）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TSportLogRowServiceImpl implements ITSportLogRowService {

    private static final int MAX_ROWS = 500;
    private static final int STATUS_OK = 1;
    private static final int STATUS_FAIL = 2;

    @javax.annotation.Resource
    private TSportLogRowMapper sportLogRowMapper;

    @Override
    public TSportLogRow selectTSportLogRowById(Long id) {
        return this.sportLogRowMapper.selectById(id);
    }

    @Override
    public int submitBatch(String batchNo, List<TSportLogRow> rows) {
        String no = rows.get(0).getBatchNo();
        java.util.List<TSportLogRow> errors = new java.util.ArrayList<TSportLogRow>();
        int seq = 0;
        for (TSportLogRow r : rows) {
            if (r.getItemCode() == null || r.getItemCode().trim().isEmpty()
                    || r.getQty() == null
                    || r.getQty().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                seq++;
                r.setRowNo(Integer.valueOf(seq));
                r.setBatchNo(no);
                r.setStatus(STATUS_FAIL);
                this.sportLogRowMapper.insert(r);
                errors.add(r);
            }
        }
        if (!errors.isEmpty()) {
            return 0;
        }
        int ok = 0;
        for (TSportLogRow r : rows) {
            r.setBatchNo(no);
            r.setStatus(STATUS_OK);
            this.sportLogRowMapper.insert(r);
            ok++;
        }
        return ok;
    }

    @Override
    public List<TSportLogRow> listErrors(String batchNo) {
        return this.sportLogRowMapper.selectList(new QueryWrapper<TSportLogRow>()
                .eq("batch_no", batchNo).eq("status", STATUS_FAIL));
    }
}
