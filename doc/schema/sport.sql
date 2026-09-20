-- sport 高危险性体育项目经营场所开放期安全监管 -- schema (chen-002)
-- 列名与基线实体契约（@TableName/@TableField）逐列对齐，改列必须同步实体。
-- 库：chen_002

CREATE TABLE IF NOT EXISTS t_sport_crowd_reg (
  id bigint NOT NULL COMMENT '主键',
  bill_no varchar(64) DEFAULT NULL COMMENT '泳客登记单号',
  site_id int DEFAULT NULL COMMENT '所挂经营场所',
  site_no varchar(64) DEFAULT NULL COMMENT '场所编号',
  qty decimal(12,2) DEFAULT NULL COMMENT '本班入场泳客人数',
  fine_amt decimal(12,2) DEFAULT NULL COMMENT '附加安全看护工时(小时)',
  grade_level int DEFAULT NULL COMMENT '泳客密度档',
  status int DEFAULT NULL COMMENT '进展 0待复核 1已复核 2已收卷',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开放时段泳客登记单';

CREATE TABLE IF NOT EXISTS t_sport_inspect_task (
  id bigint NOT NULL COMMENT '主键',
  item_no varchar(64) DEFAULT NULL COMMENT '抽查任务编号',
  due_at datetime DEFAULT NULL COMMENT '应查时刻',
  amount decimal(12,2) DEFAULT NULL COMMENT '单家预估现场用时(分钟)',
  status int DEFAULT NULL COMMENT '条目状况 0待派 1已派 2派不出',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='监督抽查到期任务条目';

CREATE TABLE IF NOT EXISTS t_sport_license_bill (
  id bigint NOT NULL COMMENT '主键',
  bill_no varchar(64) DEFAULT NULL COMMENT '变更与延续申请号',
  node_no int DEFAULT NULL COMMENT '当前落印层 0..3',
  sign_mode int DEFAULT NULL COMMENT '同层落印方式 0任一人 1名单点齐',
  need_count int DEFAULT NULL COMMENT '本层应落印人数',
  sign_count int DEFAULT NULL COMMENT '本层已落印人数',
  status int DEFAULT NULL COMMENT '申请进展 0核议中 1已核准 2已驳回',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='经营项目变更与许可延续签批单';

CREATE TABLE IF NOT EXISTS t_sport_log_row (
  id bigint NOT NULL COMMENT '主键',
  batch_no varchar(64) DEFAULT NULL COMMENT '开放日志报送批次号',
  row_no int DEFAULT NULL COMMENT '表上原行次',
  item_code varchar(64) DEFAULT NULL COMMENT '场次识别码',
  qty decimal(12,2) DEFAULT NULL COMMENT '当班入场泳客人数',
  status int DEFAULT NULL COMMENT '行进展 0待处理 1已入库 2已驳回',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='月度开放日志报送明细';

CREATE TABLE IF NOT EXISTS t_sport_safety_std (
  id bigint NOT NULL COMMENT '主键',
  rule_code varchar(64) DEFAULT NULL COMMENT '评定线代号',
  rule_name varchar(128) DEFAULT NULL COMMENT '评定线名称',
  th1_max decimal(12,2) DEFAULT NULL COMMENT '一档得分上限',
  th2_max decimal(12,2) DEFAULT NULL COMMENT '二档得分上限',
  th3_max decimal(12,2) DEFAULT NULL COMMENT '三档得分上限',
  eff_start datetime DEFAULT NULL COMMENT '启用时刻',
  eff_end datetime DEFAULT NULL COMMENT '交棒时刻(不含)',
  priority int DEFAULT NULL COMMENT '取用顺位(数值越大越优先)',
  status int DEFAULT NULL COMMENT '评定线状况 0在用 1已交出',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场所安全状况评定分档线';

CREATE TABLE IF NOT EXISTS t_sport_session_order (
  id bigint NOT NULL COMMENT '主键',
  biz_no varchar(64) DEFAULT NULL COMMENT '开放场次核查单号',
  stage int DEFAULT NULL COMMENT '当前台阶 0..4',
  status int DEFAULT NULL COMMENT '单子进展 0在办 1已封存 2退回重做',
  content varchar(255) DEFAULT NULL COMMENT '当班记事',
  last_action varchar(64) DEFAULT NULL COMMENT '最近一次挪动动作',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开放场次核查单';

CREATE TABLE IF NOT EXISTS t_sport_venue (
  id bigint NOT NULL COMMENT '主键',
  site_no varchar(64) DEFAULT NULL COMMENT '场所编号',
  site_name varchar(128) DEFAULT NULL COMMENT '场所名称',
  site_type varchar(32) DEFAULT NULL COMMENT '经营项目类别',
  road_name varchar(128) DEFAULT NULL COMMENT '所属监管辖区',
  th1_max decimal(12,2) DEFAULT NULL COMMENT '本工程核定一档泳客人数上限',
  th2_max decimal(12,2) DEFAULT NULL COMMENT '二档泳客人数上限',
  th3_max decimal(12,2) DEFAULT NULL COMMENT '三档泳客人数上限',
  status int DEFAULT NULL COMMENT '档案状况 0在用 1已注销',
  del_flag int DEFAULT '0' COMMENT '删除标记 0正常 1删除',
  create_by varchar(64) DEFAULT NULL COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT NULL COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='经营场所档案';

-- 初始档案数据（验收测试依赖 id=1 启用 / id=2 停用）
-- 验收测试依赖 t_sport_venue 两条种子档案：id=0 在用、id=1 已注销（F1 坑1/坑4 的联动判定项）。
-- 三档上限取 40/110/180，正压在 F1 坑3 的分档断言上（等于上限归高一档）。
INSERT IGNORE INTO t_sport_venue (id, site_no, site_name, site_type, road_name, th1_max, th2_max, th3_max, status, del_flag, create_by, create_time)
VALUES (0, 'TY00', '解放路游泳馆', '游泳', '城郊街道', 40.00, 110.00, 180.00, 0, 0, 'seed', NOW()),
       (1, 'TY01', '旧城健身房泳区', '游泳', '开发区街道', 40.00, 110.00, 180.00, 1, 0, 'seed', NOW());

