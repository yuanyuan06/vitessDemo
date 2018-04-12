CREATE TABLE if not exists `t_ma_tb_shop_info` (
  `ID` bigint(20) NOT NULL,
  `APP_KEY` varchar(100) DEFAULT NULL,
  `APP_SECRET` varchar(100) DEFAULT NULL,
  `BRAND` varchar(200) DEFAULT NULL,
  `BZ_SESSION_KEY` varchar(200) DEFAULT NULL,
  `COMPANY` varchar(200) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `PAYMENT_TYPE` int(11) DEFAULT NULL,
  `FENXIAO_NAME` varchar(100) DEFAULT NULL,
  `INDUSTRY` varchar(200) DEFAULT NULL,
  `INNER_SHOP_CODE` varchar(100) DEFAULT NULL,
  `INTERFACE_SECURITY_ACC` varchar(100) DEFAULT NULL,
  `IS_APPLY_SUGGESTED_TRANS` tinyint(4) DEFAULT NULL,
  `IS_APPLY_VMI_PROMOTION` tinyint(4) DEFAULT NULL,
  `IS_AUTO_COMMIT` tinyint(4) DEFAULT NULL,
  `IS_AUTO_COMMIT_TO_WH_FOR_O2O` tinyint(4) DEFAULT NULL COMMENT '是否自动过仓到仓库（O2O订单）',
  `IS_AUTO_FINISH_RA` tinyint(4) DEFAULT NULL,
  `IS_AUTO_ORDER` tinyint(4) DEFAULT NULL,
  `IS_BAOZUNREFUND` tinyint(4) DEFAULT NULL,
  `IS_BAOZUN_BILLING_INVOICE` tinyint(4) DEFAULT NULL,
  `IS_BILLING_INVOICE_DETAIL` tinyint(4) DEFAULT NULL,
  `IS_BOUND` tinyint(4) DEFAULT NULL,
  `IS_CANCEL_SO_AUTO` tinyint(4) DEFAULT NULL,
  `IS_COMMIT_SUCCESSIVELY` tinyint(4) DEFAULT NULL,
  `IS_DEFAULT_INVOICE` tinyint(4) DEFAULT NULL,
  `IS_DEFAULT_LAND_TRANS` tinyint(4) DEFAULT NULL,
  `IS_FORBID_OFFLINE` tinyint(4) DEFAULT NULL,
  `IS_IGNORE_MEMO` tinyint(4) DEFAULT NULL,
  `IS_MQ_INV` tinyint(4) DEFAULT NULL,
  `IS_MQ_LOGISTICS` tinyint(4) DEFAULT NULL,
  `IS_MQ_MD_PRICE` tinyint(4) DEFAULT NULL,
  `IS_MQ_ORDER` tinyint(4) DEFAULT NULL,
  `IS_MQ_SALE` tinyint(4) DEFAULT NULL,
  `IS_MQ_SKU` tinyint(4) DEFAULT NULL,
  `IS_MQ_SKU_PRICE` tinyint(4) DEFAULT NULL,
  `IS_MQ_SO` tinyint(4) DEFAULT NULL,
  `IS_MQ_SO_RESULT` tinyint(4) DEFAULT NULL,
  `IS_O2O_SHOP` tinyint(4) DEFAULT NULL,
  `IS_ON_SALES_SKU` tinyint(4) DEFAULT NULL,
  `IS_OPEN_SYNC_INV` tinyint(4) DEFAULT NULL,
  `IS_OPENED_WLB` tinyint(4) DEFAULT NULL,
  `IS_OPENED_API` bit(1) DEFAULT NULL,
  `IS_OWN_WMS` tinyint(4) DEFAULT NULL,
  `IS_REQUIRED_TRANS` tinyint(4) DEFAULT NULL,
  `IS_SMS` tinyint(4) DEFAULT NULL,
  `IS_SPLIT_BY_TYPE` bit(1) DEFAULT NULL,
  `IS_SYNC_INVENTORY` bit(1) DEFAULT NULL,
  `IS_SYNC_MANUAL_UPLOAD_SKU` tinyint(4) DEFAULT NULL,
  `IS_SYNC_MQSORESULT_ALONE` tinyint(4) DEFAULT NULL,
  `IS_SYNC_PARTLY_DF` tinyint(4) DEFAULT NULL,
  `IS_SYS_FINISH` tinyint(4) DEFAULT NULL,
  `LIFE_CYCLE_STATUS` int(11) DEFAULT NULL,
  `MAIL` varchar(150) DEFAULT NULL,
  `MANAGER_MODE` int(11) DEFAULT NULL,
  `MEMBER_NAME` varchar(100) DEFAULT NULL,
  `MOBILE` varchar(20) DEFAULT NULL,
  `MQ_ASN_RECEIVE` varchar(50) DEFAULT NULL,
  `MQ_FEEDBACK` varchar(50) DEFAULT NULL,
  `MQ_INC_INVENTORY` varchar(50) DEFAULT NULL,
  `MQ_INVENTORY` varchar(50) DEFAULT NULL,
  `MQ_LOGISTICS` varchar(50) DEFAULT NULL,
  `MQ_MD_PRICE` varchar(50) DEFAULT NULL,
  `MQ_NAME` varchar(50) DEFAULT NULL,
  `MQ_NOTIFY` varchar(50) DEFAULT NULL,
  `MQ_ORDER` varchar(50) DEFAULT NULL,
  `MQ_PRICE` varchar(50) DEFAULT NULL,
  `MQ_RTV` varchar(50) DEFAULT NULL,
  `MQ_SALE` varchar(50) DEFAULT NULL,
  `MQ_SKU` varchar(50) DEFAULT NULL,
  `MQ_SO` varchar(50) DEFAULT NULL,
  `OPEN_DATE` datetime DEFAULT NULL,
  `PRINT_SHOP_NAME` varchar(100) DEFAULT NULL,
  `RTN_WAREHOUSE_ADDRESS` varchar(100) DEFAULT NULL,
  `SESSION_KEY` varchar(200) DEFAULT NULL,
  `SETTLEMENT_MODE` int(11) DEFAULT NULL,
  `SHOP_ID` varchar(150) DEFAULT NULL,
  `SHOP_NAME` varchar(150) DEFAULT NULL,
  `SALES_MODES` varchar(128) DEFAULT NULL COMMENT '销售模式，半角逗号分隔',
  `SO_CODE_PREFIX` varchar(50) DEFAULT NULL,
  `SOURCE` varchar(100) DEFAULT NULL,
  `TELEPHONE` varchar(50) DEFAULT NULL,
  `TRANS_REGION_APPLY_TYPE` int(11) DEFAULT NULL,
  `VERSION` int(11) DEFAULT '0',
  `VMI_CODE` varchar(30) DEFAULT NULL,
  `VMI_WH_SOURCE` varchar(30) DEFAULT NULL,
  `WH_MODEL` int(11) DEFAULT NULL,
  `ZIPCODE` varchar(6) DEFAULT NULL,
  `OU_ID` bigint(20) DEFAULT NULL,
  `IS_AUTO_SPLIT_ORDER` int(1) DEFAULT '0' COMMENT '是否自动拆单',
  `NEED_VERIFY_EN_ADDRESS` int(1) DEFAULT '0' COMMENT '是否验证英文地址',
  `IS_AUTO_CREATE_RF` tinyint(4) DEFAULT '1' COMMENT '是否自动创建退款',
  `IS_SYNC_TO_OMS` tinyint(4) DEFAULT NULL COMMENT '是否需要同步数据至',
  `DEFTEM_ID` bigint(20) DEFAULT NULL COMMENT 'OMS物流模板',
  `IS_USE_DEFTRANS` tinyint(4) DEFAULT NULL,
  `IS_OPEN_CLOUD_STACK` tinyint(4) DEFAULT NULL,
  `SKU_SPLIT_TYPE` tinyint(2) NOT NULL DEFAULT '2' COMMENT '拆分类目, 1:按店铺SKU默认仓拆分订单, 2:不拆分',
  `IS_SUSPEND_FOR_SPECIAL_SKU` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否应用含特殊商品挂起',
  `DEF_INVOICE_TITLE_TYPE` tinyint(2) DEFAULT NULL COMMENT '默认发票抬头类型',
  `IS_PARSE_ORDER_SERVICE_INFO` tinyint(2) DEFAULT NULL COMMENT '是否解析订单服务信息',
  `PLATFORM_SHOP_CODE` varchar(100) DEFAULT NULL COMMENT '平台店铺编码',
  `BUSINESS_REGION_TYPE` varchar(50) DEFAULT NULL COMMENT '店铺销售区域',
  `IS_OPEN_SEQ_TO_WH` tinyint(4) DEFAULT NULL COMMENT '是否开启依次过仓逻辑',
  `SELLER_ADDRESS_ID` bigint(20) DEFAULT NULL COMMENT '卖家收货地址编号',
  `IS_REFUND_DIRECT` tinyint(4) DEFAULT '0' COMMENT '是否使用淘宝退款指令 ',
  `IS_CREATE_ETICKET` tinyint(4) DEFAULT '0' COMMENT '是否创建 电子凭证订单 ',
  `IS_OPEN_DATA_ANALYSIS` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开启数据分析',
  `IS_PARSE_NO_PAY_ORDER` tinyint(4) DEFAULT '0' COMMENT '是否抓起未付款订单',
  `IS_OPEN_DIRECT_WMS` tinyint(4) DEFAULT NULL,
  `INTERFACE_SHOP_CODE` varchar(80) DEFAULT NULL COMMENT '店铺code(pacs/wms/tmalloms交互使用)',
  `IS_ALLOW_DS` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否允许分仓发货',
  `COMPANY_NAME` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `FIXED_OPERATING_ITEM` varchar(50) DEFAULT NULL COMMENT '固定经营项目名称',
  `AUTO_SPLIT_TYPE` tinyint(4) DEFAULT NULL COMMENT '自动拆票类型：1.按单张发票最大开票金额及备注长度拆票；2.不支持拆票',
  `INVOICE_AMT_RULE` tinyint(4) DEFAULT NULL COMMENT '开票金额计算规则：1.只体现商品信息(扣除积分和折扣);2.发票明细包含两部分：商品明细、积分和拆扣明细',
  `MAX_INVOICE_AMT` decimal(15,2) DEFAULT NULL COMMENT '单张发票最大开票金额(如果不配置,则默认是 10000)',
  `IS_OPEN_SPLIT_ORDER_SEND_SMS` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否开启拆单发送短信功能',
  `IS_DA_SHOP` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是只做数据分析的店铺',
  `IS_SPECIFIC_SHOP_TO_EDW` tinyint(4) DEFAULT NULL,
  `IS_CREATE_AUTO_DELIVERY` tinyint(4) DEFAULT NULL COMMENT '是否创建自动发货订单',
  `SALES_MODE` int(5) DEFAULT NULL COMMENT '销售模式',
  `WH_CUSTOMER_CODE` varchar(100) DEFAULT NULL COMMENT '仓储客户代码',
  `IS_APPLY_SALES_MODE` tinyint(1) DEFAULT '0' COMMENT '是否开启订单行销售模式打标',
  `PLATFORM_TYPE` varchar(5) DEFAULT NULL COMMENT '店铺平台类型',
  `INVOICE_KIND` tinyint(1) DEFAULT '0' COMMENT '发票形式：false 纸质发票；true 电子发票',
  `IS_CAI_NIAO` tinyint(1) DEFAULT NULL COMMENT '是否菜鸟仓发货',
  `IS_SHARE_INVENTORY` tinyint(1) NOT NULL DEFAULT '0' COMMENT '店铺是否开启库存共享',
  `OWNER_CODE` varchar(250) DEFAULT NULL COMMENT '货主编码',
  `PROVINCE` varchar(100) DEFAULT NULL COMMENT '省',
  `CITY` varchar(100) DEFAULT NULL COMMENT '市',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '详细地址',
  `DISTRICT` varchar(250) DEFAULT NULL COMMENT '区',
  `IS_SURPORT_ONEKEY_SPLIT` tinyint(4) DEFAULT '0' COMMENT '是否支持一键拆单',
  `ONEKEY_SPLIT_UPPERBOUND` int(11) DEFAULT '2' COMMENT '一键拆单上限数',
  `INSTALL_COMPANY_NAME` varchar(100) DEFAULT NULL,
  `IS_NEED_REASON` tinyint(4) DEFAULT '1' COMMENT '0 不开启（默认） 1 开启。开启后取消订单时必须填写取消原因',
  `IS_ORDER_ROUTE` tinyint(5) DEFAULT '0' COMMENT '是否开启订单路由默认否',
  `CUSTOMER_CODE` varchar(100) DEFAULT NULL COMMENT '客户编码',
  `isOmnichannelParam` tinyint(1) NOT NULL DEFAULT '0' COMMENT '门店ID或者EC仓名字',
  `IS_APPLY_AUDIT_RULE` tinyint(4) DEFAULT '0' COMMENT '是否开启应用退款审核规则',
  `INDUSTRY_NAME` varchar(100) DEFAULT NULL COMMENT '行业名称',
  `CODE` varchar(100) DEFAULT NULL COMMENT '发送订单路由的店铺唯一CODE',
  `IS_OPEN_SKU_TYPE` tinyint(4) DEFAULT '0' COMMENT 'Tommy定制，是否开启商品类型配置0：禁止  1：开启',
  `IS_HANG_BY_ABROAD_ORDER` int(1) DEFAULT '0',
  `IS_ONE_BY_ONE_TO_WH` tinyint(1) DEFAULT '1' COMMENT '是否开启依次过仓',
  `OPEN_AG_REFUND` tinyint(1) DEFAULT NULL COMMENT '开启ag退款',
  `IS_SHIPPING_METHODS` tinyint(1) DEFAULT '0' COMMENT '是否开启配送方式拆单',
  `IS_TRANS` tinyint(1) DEFAULT '0' COMMENT 'LF仓对接有区域要求，定义和LF对接的物流商开关',
  `IS_PRESALE` tinyint(4) DEFAULT '0' COMMENT '是否开启预售',
  `IS_PREPACKAGE` tinyint(4) DEFAULT '0' COMMENT '是否开启预包装',
  `IS_TRACKING_NO` tinyint(4) DEFAULT '0' COMMENT '运单号必填',
  `IS_OPEN_MQ` tinyint(1) DEFAULT '0' COMMENT 'LF仓对接有区域要求，定义和LF对接的物流商开关',
  `IS_MULTI_SKU` tinyint(4) DEFAULT NULL COMMENT '一个平台对接码根据逻辑库位同步多份库存到平台',
  `IS_TRADE_TO_RETURN` tinyint(4) DEFAULT '0' COMMENT '是否支持换转退',
  `IS_PARALLEL` tinyint(4) DEFAULT '0' COMMENT '是否退换货并行',
  `RA_INBOUND_CONFIG` int(11) DEFAULT NULL COMMENT '退货入库配置',
  `RA_INBOUND_CHANNEL_CODE` varchar(100) DEFAULT NULL COMMENT '指定店铺入库配置项',
  `IS_CHG_PLATFORM_IVT` tinyint(4) DEFAULT '0' COMMENT '换货是否扣减平台库存',
  `AG_SECOND` tinyint(4) DEFAULT '0',
  `IS_QUERY_INV` tinyint(4) DEFAULT NULL COMMENT '同步库存后是否查询验证天猫的库存',
  `IS_OVER2STEP` tinyint(4) DEFAULT NULL COMMENT '同步库存是否全量转增量',
  `fenxiao_shop_code` bigint(20) DEFAULT NULL COMMENT '分销店铺id,只当其分销店铺的订单被当做本店铺订单拉入oms的时候设置值!!',
  `IS_RA_SMS` tinyint(4) DEFAULT '0',
  `IS_OPEN_DELIVERY_AND_INSTALL_SERVICE` tinyint(4) DEFAULT '0' COMMENT '是否开放送货上门并安装服务,如果不是1,将不能创建此项服务行',
  `IS_ENCRYPT` tinyint(4) DEFAULT '0' COMMENT '店铺是否加密（1加密，0未加密）',
  `ENCRYPT_TYPE` tinyint(2) DEFAULT '0' COMMENT '店铺加密类型- 0:直销; 1:分销; 2经销',
  `AG_OPEN_AUTO_REFUND` tinyint(4) DEFAULT '0',
  `IS_OPEN_ONLINE_EXCHANGE` tinyint(4) DEFAULT '0' COMMENT '是否开启平台线上换货（对接AG四期，天猫换货对接）',
  `IS_QIMEN` tinyint(4) DEFAULT '0' COMMENT '是否开启奇门',
  `IS_OPEN_QIMEN` tinyint(4) DEFAULT '0' COMMENT '是否开启奇门',
  `IS_INVALID_EXCHANGE_REASON` tinyint(4) DEFAULT '1' COMMENT '作废换货单时是否要求填写作废原因',
  `IS_OPEN_PUSH_TOGETHER` tinyint(4) DEFAULT '1' COMMENT '是否开启3.0换货过仓入库单和出库单分开推送',
  `LAST_PULL_EXCHANGE_DIRECT_TIME` datetime DEFAULT NULL COMMENT '最后拉取换货指令的时间',
  `INCREASE_MINUTES` int(11) DEFAULT '3' COMMENT '一次拉取换货的时间间隔(单位分钟)',
  `IS_AUTO_SPLIT_ORDER_RULE_FOR_WLB` tinyint(4) DEFAULT '0' COMMENT '是否允许对这个店铺的物流宝订单应用自动拆单规则',
  `IS_DIRECT_OMS` tinyint(4) DEFAULT '0' COMMENT '是否直连OMS(意思是 TOMS销售退换货过仓对应的下位系统是否是一个OMS系统而不是WMS)',
  PRIMARY KEY (`ID`),
  KEY `FK4211FEC618FC4239` (`OU_ID`) USING BTREE,
  KEY `FK4211FEC6ECB77F46` (`DEFTEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺';


CREATE TABLE if not exists `t_ma_def_trans_template` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `IS_EXCLUDE_EMS` bit(1) DEFAULT NULL,
  `LAST_MODIFY_TIME` datetime DEFAULT NULL,
  `LAST_MODIFY_USER` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `COD_TRANS_ID` bigint(20) DEFAULT NULL,
  `VERSION` int(11) DEFAULT '0' COMMENT 'version',
  PRIMARY KEY (`ID`),
  KEY `FKB95D02ABDEAB255A` (`COD_TRANS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='物流推荐主表';



CREATE TABLE if not exists `t_ma_def_trans_temp_detail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PROVINCE` varchar(255) DEFAULT NULL,
  `REACHABLE_AREA` varchar(255) DEFAULT NULL,
  `UN_REACHABLE_AREA` varchar(255) DEFAULT NULL,
  `TEMP_ID` bigint(20) DEFAULT NULL,
  `TRANS_ID` bigint(20) DEFAULT NULL,
  `VERSION` int(11) DEFAULT '0' COMMENT 'version',
  PRIMARY KEY (`ID`),
  KEY `IDX_TEMP_ID` (`TEMP_ID`) USING BTREE,
  KEY `FK435BEB4BD1961449` (`TEMP_ID`),
  KEY `FK435BEB4BE3B94E73` (`TRANS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COMMENT='物流推荐明细表';



CREATE TABLE if not exists `t_mq_delivery_info_log` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `version` datetime DEFAULT NULL,
  `receiver` varchar(1000) DEFAULT NULL,
  `contact_person` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `zipcode` varchar(20) DEFAULT NULL,
  `receiver_phone` varchar(1000) DEFAULT NULL,
  `receiver_mobile` varchar(1000) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `membre_email` varchar(1000) DEFAULT NULL,
  `lp_code` varchar(50) DEFAULT NULL,
  `so_log_id` bigint(19) DEFAULT NULL,
  `given_name` varchar(50) DEFAULT NULL,
  `family_name` varchar(50) DEFAULT NULL,
  `trans_service_type` int(4) DEFAULT NULL,
  `trans_time_type` int(4) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL,
  `town` varchar(50) DEFAULT NULL,
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`),
  KEY `FKD71A792E5D8DD384` (`so_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=366708 DEFAULT CHARSET=utf8 COMMENT='mq订单配送信息';


CREATE TABLE if not exists `t_mq_platform_member_log` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `version` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `login_name` varchar(1000) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `telephone` varchar(1000) DEFAULT NULL,
  `mobile` varchar(1000) DEFAULT NULL,
  `email` varchar(1000) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `vip_code` varchar(50) DEFAULT NULL,
  `DISTRICT` varchar(64) DEFAULT NULL COMMENT '区',
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46824764 DEFAULT CHARSET=utf8 COMMENT='mq订单会员信息';


CREATE TABLE if not exists `t_mq_so_line_log` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `version` datetime DEFAULT NULL,
  `jmsku_code` varchar(50) DEFAULT NULL,
  `bar_code` varchar(50) DEFAULT NULL,
  `qty` int(19) DEFAULT NULL,
  `unit_price` decimal(15,5) DEFAULT NULL,
  `total_actual` decimal(15,5) DEFAULT NULL,
  `so_log_id` bigint(19) DEFAULT NULL,
  `sku_combo_remark` varchar(2000) DEFAULT NULL,
  `extention_code` varchar(50) DEFAULT NULL,
  `list_price` decimal(15,5) DEFAULT NULL,
  `md_price` decimal(15,5) DEFAULT NULL,
  `coupon_code` varchar(100) DEFAULT NULL,
  `discount_fee` decimal(15,5) DEFAULT NULL,
  `line_total` decimal(15,5) DEFAULT NULL,
  `line_unit_price` decimal(15,5) DEFAULT NULL,
  `final_total_actual` decimal(15,5) DEFAULT NULL,
  `final_unit_price` decimal(15,5) DEFAULT NULL,
  `total_point_used` decimal(15,5) DEFAULT NULL,
  `ext_prop1` varchar(1000) DEFAULT NULL,
  `platform_line_id` varchar(100) DEFAULT NULL,
  `outer_point_value` decimal(15,5) DEFAULT NULL,
  `inner_point_value` decimal(15,5) DEFAULT NULL,
  `other_vc` decimal(15,5) DEFAULT NULL,
  `platform_source` varchar(500) DEFAULT NULL,
  `platform_sku_name` varchar(200) DEFAULT NULL,
  `warranty_months` int(19) DEFAULT NULL,
  `platform_wh_code` varchar(200) DEFAULT NULL,
  `is_prezzie` int(1) DEFAULT NULL,
  `EST_CON_TIME` varchar(50) DEFAULT NULL COMMENT '商家的预计发货时间',
  `PIC_PATH` varchar(150) DEFAULT NULL COMMENT '图片路径连接',
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `SUB_ORDER_TAX_FEE` decimal(15,2) DEFAULT '0.00' COMMENT '天猫国际官网直供子订单关税税费',
  `ASSEMBLY_RELA` bigint(20) DEFAULT NULL COMMENT '捆绑oid',
  `PART_MJZ_DISCOUNT` decimal(15,5) DEFAULT NULL COMMENT '整单分摊到行上的金额',
  `TOTAL_FEE` decimal(15,5) DEFAULT NULL COMMENT '平台total_fee:应付金额(商品价格*商品数量+手工调整金额-子订单级订单优惠金额)',
  `targetCode` varchar(100) DEFAULT NULL COMMENT '门店ID或者EC仓名字',
  `TARGET_CODE` varchar(100) DEFAULT NULL COMMENT '门店ID或者EC仓名字',
  `TARGET_TYPE` varchar(30) DEFAULT NULL COMMENT '门店类型',
  `ALLOCATION_CODE` varchar(50) DEFAULT NULL COMMENT '阿里星盘派单号',
  `FQG_NUM` int(2) DEFAULT NULL COMMENT '分期期数',
  `IS_FQG_S_FEE` int(1) DEFAULT NULL COMMENT '是否商家承担手续费',
  `ALLOCATION_TYPE` varchar(50) DEFAULT NULL COMMENT '全渠道订单类型 门店发货(STORE_DELIVER)或者门店自提(STORE_COLLECT)',
  `PLATFORM_SKU_ID` bigint(20) DEFAULT NULL COMMENT '平台宝贝ID',
  `COUPON_FEE` decimal(15,2) DEFAULT '0.00' COMMENT '天猫红包金额',
  PRIMARY KEY (`id`),
  KEY `fkc228400c5d8dd384` (`so_log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=465778 DEFAULT CHARSET=utf8 COMMENT='mq订单行';




CREATE TABLE if not exists `t_mq_so_log` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `version` datetime DEFAULT NULL,
  `shop_id` bigint(19) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `outer_create_time` datetime DEFAULT NULL,
  `payment_time` datetime DEFAULT NULL,
  `payment_type` varchar(50) DEFAULT NULL,
  `is_needed_invoice` int(1) DEFAULT NULL,
  `invoice_title` varchar(100) DEFAULT NULL,
  `invoice_content` varchar(500) DEFAULT NULL,
  `lp_code` varchar(50) DEFAULT NULL,
  `actual_trans_fee` decimal(15,5) DEFAULT NULL,
  `total_actual` decimal(15,5) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `error_msg` varchar(1000) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `account` varchar(200) DEFAULT NULL,
  `member_email` varchar(200) DEFAULT NULL,
  `delivery_remark` varchar(1000) DEFAULT NULL,
  `is_member_order` int(1) DEFAULT NULL,
  `coupon_code` varchar(100) DEFAULT NULL,
  `coupon_type` varchar(20) DEFAULT NULL,
  `coupon_discount_fee` decimal(15,5) DEFAULT NULL,
  `is_needed_packing` int(1) DEFAULT NULL,
  `gc_amt` decimal(15,5) DEFAULT NULL,
  `sgc_amt` decimal(15,5) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `oms_shop_id` bigint(19) DEFAULT NULL,
  `total_point_used` decimal(15,5) DEFAULT NULL,
  `platform_line_id` bigint(19) DEFAULT NULL,
  `total_outer_point` decimal(15,5) DEFAULT NULL,
  `total_inner_point` decimal(15,5) DEFAULT NULL,
  `total_vc` decimal(15,5) DEFAULT NULL,
  `ext_prop1` varchar(1000) DEFAULT NULL,
  `platform_order_type` varchar(100) DEFAULT NULL,
  `seller_memo` varchar(1000) DEFAULT NULL,
  `bpc_amt` decimal(15,5) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL,
  `is_lg_type` int(1) DEFAULT NULL,
  `platform_order_status` varchar(100) DEFAULT NULL,
  `platform_type` int(2) DEFAULT NULL,
  `alipay_account` varchar(1000) DEFAULT NULL,
  `alipay_no` varchar(50) DEFAULT NULL,
  `direction` int(1) DEFAULT NULL,
  `msg_batch_id` bigint(19) DEFAULT NULL,
  `store_code` varchar(100) DEFAULT NULL,
  `title_remark` varchar(500) DEFAULT NULL,
  `service_item_fee` decimal(15,5) DEFAULT NULL,
  `nike_service_level_code_type` int(2) DEFAULT NULL,
  `slip_code1` varchar(50) DEFAULT NULL,
  `slip_code2` varchar(50) DEFAULT NULL,
  `error_count` int(1) DEFAULT NULL,
  `pay_discount` decimal(15,5) DEFAULT NULL,
  `total_discount` decimal(15,5) DEFAULT NULL,
  `is_prezzie` int(1) DEFAULT NULL,
  `is_platform_delivery` int(1) DEFAULT NULL COMMENT '是否为平台发货',
  `cod_amt` decimal(15,5) DEFAULT NULL,
  `special_type` int(4) DEFAULT NULL,
  `ext_vc1` decimal(15,5) DEFAULT NULL COMMENT '虚拟支付金额扩展字段1',
  `ext_vc2` decimal(15,5) DEFAULT NULL COMMENT '虚拟支付金额扩展字段2',
  `ext_vc3` decimal(15,5) DEFAULT NULL COMMENT '虚拟支付金额扩展字段3',
  `ALIPAY_ID` bigint(19) DEFAULT NULL COMMENT '支付宝ID',
  `CREDIT_CARD_FEE` varchar(50) DEFAULT NULL COMMENT '使用信用卡支付金额数',
  `no_pay_status` tinyint(4) DEFAULT '0' COMMENT '未付款订单处理状态',
  `ORDER_TAX_FEE` decimal(15,2) DEFAULT '0.00' COMMENT '天猫国际官网直供主订单关税税费',
  `IS_BUNDLE` tinyint(1) DEFAULT NULL COMMENT '捆绑',
  `CUSTOMIZATION_MEMO` varchar(200) DEFAULT NULL,
  `CROSS_BONDED_DECLARE` tinyint(1) DEFAULT NULL COMMENT '跨境',
  `OMNICHANNEL_PARAM` varchar(1000) DEFAULT NULL COMMENT '全渠道参数String串',
  `INVOICE_KIND` tinyint(4) DEFAULT NULL COMMENT '发票类型，0电子或者1纸质',
  `BUYER_TAX_NO` varchar(50) DEFAULT NULL COMMENT '企业税号/纳税识别码',
  `STEP_TRADE_STATUS` varchar(50) DEFAULT NULL COMMENT '预售状态',
  `STEP_PAID_FEE` decimal(15,5) DEFAULT NULL COMMENT '预售已支付定金金额',
  `ALLOCATION_TYPE` varchar(50) DEFAULT NULL COMMENT '全渠道订单类型 门店发货(STORE_DELIVER)或者门店自提(STORE_COLLECT)',
  `BUSINESS_TYPE` int(11) DEFAULT NULL COMMENT '开票给企业或个人 1个人,2企业',
  PRIMARY KEY (`id`),
  KEY `IDX_MQ_SO_CODE` (`code`) USING BTREE,
  KEY `IDX_MQ_SO_CREATE_TIME` (`create_time`),
  KEY `IDX_MQ_SO` (`shop_id`,`platform_type`,`status`,`error_count`)
) ENGINE=InnoDB AUTO_INCREMENT=46824764 DEFAULT CHARSET=utf8 COMMENT='mq订单头';


CREATE TABLE if not exists `t_mq_so_packing_info_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MEMO` varchar(255) DEFAULT NULL,
  `PI_LEVEL` int(11) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `SO_LINE_LOG_ID` bigint(20) DEFAULT NULL,
  `SO_LOG_ID` bigint(20) DEFAULT NULL,
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`ID`),
  KEY `FK53A4E11C5D8DD384` (`SO_LOG_ID`) USING BTREE,
  KEY `FK53A4E11C240175` (`SO_LINE_LOG_ID`) USING BTREE,
  KEY `IDX_MQPI_MQ_SO` (`SO_LOG_ID`) USING BTREE,
  KEY `IDX_MQPI_MQ_SO_LINE` (`SO_LINE_LOG_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8 COMMENT='mq订单包装信息';

CREATE TABLE if not exists `t_mq_so_promotion_log` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `PLATFORM_LINE_ID` bigint(19) DEFAULT NULL,
  `PROMOTION_NAME` varchar(250) DEFAULT NULL COMMENT '优惠信息的名称',
  `GIFT_ITEM_NAME` varchar(250) DEFAULT NULL COMMENT '满就送商品时，所送商品的名称',
  `GIFT_ITEM_ID` varchar(100) DEFAULT NULL COMMENT '优惠券代码',
  `DISCOUNT_FEE` decimal(15,2) DEFAULT NULL COMMENT '优惠金额（免运费、限时打折时为空）,单位：元',
  `GIFT_ITEM_NUM` int(4) DEFAULT NULL COMMENT '赠品数量',
  `PLATFORM_ORDER_CODE` varchar(50) DEFAULT NULL,
  `VERSION` datetime DEFAULT NULL,
  `SO_LINE_LOG_ID` bigint(19) DEFAULT NULL,
  `SO_LOG_ID` bigint(19) DEFAULT NULL,
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '优惠活动的描述',
  `PROMOTION_ID` varchar(100) DEFAULT NULL COMMENT '优惠id',
  `SCOPE_TYPE` int(4) DEFAULT NULL COMMENT '1:整单金额促销 2:单行金额促销 3：整单平摊',
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`ID`),
  KEY `fka77065955d8dd384` (`SO_LOG_ID`) USING BTREE,
  KEY `FK35F3F1F5240175` (`SO_LINE_LOG_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=598690 DEFAULT CHARSET=utf8 COMMENT='mq订单促销信息';


CREATE TABLE if not exists `t_mq_so_service_line_log` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SO_LOG_ID` bigint(19) DEFAULT NULL COMMENT 'MQ_SO_LOG外键',
  `PLATFORM_LINE_ID` varchar(50) DEFAULT NULL COMMENT '虚拟服务子订单订单号',
  `SERVICE_ID` varchar(50) DEFAULT NULL COMMENT '服务数字id',
  `QTY` int(11) DEFAULT NULL COMMENT '购买数量，取值范围为大于0的整数',
  `UNIT_PRICE` decimal(15,5) DEFAULT NULL COMMENT '服务价格，精确到小数点后两位：单位:元',
  `TOTAL_ACTUAL` decimal(15,5) DEFAULT NULL COMMENT '服务子订单总费用(行总计unitPrice × qty)',
  `PAYMENT` decimal(15,5) DEFAULT NULL COMMENT '子订单实付金额',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `TMSER_SPU_CODE` varchar(50) DEFAULT NULL COMMENT '服务类型：干支：送货上门；干线：自提；安装：上门安装；',
  `SHOP_ID` bigint(20) DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`ID`),
  KEY `FK_SO_SERVICE_LINE_LOG_SO_ID` (`SO_LOG_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45048 DEFAULT CHARSET=utf8 COMMENT='mq订单服务信息';

CREATE TABLE if not exists `t_au_operation_unit` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) DEFAULT NULL COMMENT '组织编码',
  `OU_COMMENT` longtext COMMENT '备注',
  `FULL_NAME` longtext COMMENT '组织全称',
  `IS_AVAILABLE` tinyint(1) DEFAULT NULL COMMENT '组织是否可用',
  `NAME` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `VERSION` int(11) DEFAULT NULL COMMENT 'VERSION',
  `OU_TYPE_ID` bigint(20) DEFAULT NULL COMMENT '组织类型',
  `PARENT_OU_ID` bigint(20) DEFAULT NULL COMMENT '父组织',
  PRIMARY KEY (`ID`),
  KEY `FKCE622DBCCB7827E6` (`OU_TYPE_ID`) USING BTREE,
  KEY `FKCE622DBC66410664` (`PARENT_OU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39192 DEFAULT CHARSET=utf8 COMMENT='组织';


CREATE TABLE if not exists `t_au_operation_unit_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` longtext COMMENT '组织类型描述',
  `DISPLAY_NAME` longtext COMMENT '组织类型全称',
  `IS_AVAILABLE` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  `NAME` varchar(100) DEFAULT NULL COMMENT '组织类型简称/编码',
  `PARENT_OUT_ID` bigint(20) DEFAULT NULL COMMENT '组织类型，例如：店铺、分公司、经销商等',
  PRIMARY KEY (`ID`),
  KEY `FKEF324FDDE20771E0` (`PARENT_OUT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='组织类型';



CREATE TABLE if not exists `t_so_platform_so_log` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) DEFAULT NULL,
  `SHOP_ID` bigint(19) DEFAULT NULL,
  `MQ_SO_LOG_ID` bigint(19) DEFAULT NULL,
  `SOURCE_MSG` text,
  PRIMARY KEY (`ID`),
  KEY `IDX_SO_PTF_SO_LOG_MQ_SO_LOG` (`MQ_SO_LOG_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=365074 DEFAULT CHARSET=utf8 COMMENT='mq订单原数据';


CREATE TABLE if not exists `t_ma_shop_wh` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `LAST_MODIFY_TIME` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT '0',
  `WH_CODE` varchar(50) DEFAULT NULL,
  `WH_NAME` varchar(100) DEFAULT NULL,
  `SHOP_ID` bigint(20) DEFAULT NULL,
  `IS_DEFAULT` tinyint(4) DEFAULT NULL,
  `IS_PLATFORM_DEFAULT` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否平台默认发货仓库, 0:否，1:是',
  `IS_RTN_DEFAULT` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否默认的退货仓库，0:否，1:是',
  `DELIVERY_ADDRESS_DETAIL` varchar(255) DEFAULT NULL,
  `CONSIGN_NAME` varchar(255) DEFAULT NULL,
  `CONSIGN_PHONE` varchar(255) DEFAULT NULL,
  `PROVINCE` varchar(50) DEFAULT NULL,
  `CITY` varchar(50) DEFAULT NULL,
  `AREA` varchar(50) DEFAULT NULL,
  `TOWN` varchar(50) DEFAULT NULL,
  `SYSTEM_NAME` varchar(100) DEFAULT NULL COMMENT '仓储系统标示',
  `SYSTEM_CODE` varchar(100) DEFAULT NULL COMMENT '仓库系统CODE',
  `ONE_BY_ONE_TO_WH_LEVEL` int(2) DEFAULT NULL COMMENT '依次过仓优先级',
  `IS_PRE_PACKAGE` tinyint(4) DEFAULT '0' COMMENT '仓库是否支持预包装',
  `IS_TRACKING_NO` tinyint(4) DEFAULT '0' COMMENT '是否校验快递',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '地址',
  `RECEIVER` varchar(50) DEFAULT NULL COMMENT '联系人',
  `RECEIVER_PHONE` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `DISTRICT` varchar(50) DEFAULT NULL COMMENT '区',
  `MOBILE` varchar(50) DEFAULT NULL COMMENT '手机',
  `ZIP_CODE` varchar(50) DEFAULT NULL COMMENT '邮编',
  `IS_INVOKE_DELIVERY_SERVICE` tinyint(4) DEFAULT NULL COMMENT '是否调用快递服务',
  PRIMARY KEY (`ID`),
  KEY `FKF65551BA2E887CF9` (`SHOP_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=149190 DEFAULT CHARSET=utf8 COMMENT='店铺仓库表';


CREATE TABLE if not exists `t_ma_transportator` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_TIME` datetime DEFAULT NULL,
  `EXP_CODE` varchar(20) DEFAULT NULL,
  `FULL_NAME` varchar(255) DEFAULT NULL,
  `IS_SUPPORT_COD` tinyint(4) DEFAULT NULL,
  `JASPER_NORMAL` varchar(200) DEFAULT NULL,
  `JASPER_ONLINE` varchar(200) DEFAULT NULL,
  `K3_CODE` varchar(20) DEFAULT NULL,
  `LAST_MODIFY_TIME` datetime DEFAULT NULL,
  `LIFE_CYCLE_STATUS` int(11) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PLATFORM_CODE` varchar(20) DEFAULT NULL,
  `VERSION` datetime DEFAULT NULL,
  `QIMEN_CODE` varchar(20) DEFAULT NULL COMMENT '奇门物流编码',
  `PLATFORM_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='物流商主数据'



