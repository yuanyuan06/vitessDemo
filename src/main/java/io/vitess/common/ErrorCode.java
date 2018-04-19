package io.vitess.common;

/**
 * 系统消息提示统一使用
 * CUSTOMIZATION_TIP
 * @author fanht
 *
 */
public interface ErrorCode {

    String BUSINESS_EXCEPTION = "business_exception_";
    int SYSTEM_ERROR = 1;
    int SYSTEM_ERROR_IMPORT_FILE_IS_NULL = 20;
    int CUSTOMIZATION_TIP = 99999999; // {0}

    int ERROR_NOT_SPECIFIED = -1;
    int SESSION_TIMEOUT = -2;
    int NO_SUFFICICENT_PRIVILEGE = -10;

    /** 调用天猫接口异常 */
    int TOP_API_ERROR = 19001;
    
    /**
     * PDA ERROR CODE
     */
    int PDA_SYS_ERROR = 1000; // 系统错误
    int PDA_IS_LIMIT = 1001; // PDA机器未认证
    int PDA_CODE_NOT_FOUND = 1002; // 单据未找到
    int PDA_EXECUTE_ERROR = 1003; // 无法执行PDA
    int PDA_SKU_NOT_FOUND = 1010; // 商品未找到
    int PDA_SKU_NOT_IN_ORDER = 1011; // 商品不再单据中
    int PDA_LOCATION_NOT_FOUND = 1020; // 库位未找到
    int PDA_LOCATION_NOT_ENOUGHT_CAPACITY = 1021; // 库容不足
    int PDA_LOCATION_NOT_SUPPORT_TRANSTYPE = 1022; // 不支持作业类型
    int PDA_LOCATION_NOT_SUPPORT_INBOUND_MODE = 1023; // 单批隔离无法存放
    int PDA_LOCATION_IS_LOCKED = 1024; // 库位已锁定
    int PDA_PLAN_QTY_LQ_ACTUAL = 1025; // 数量已大于总量

    int SO_CODE_IS_NULL = 2001; // 接口未找到订单号
    int SO_TO_WH_INVENTORY_IS_NULL = 2002; // 无可用库存
    int NO_ROLE_TO_ACCESS = 2003; // 无权访问

    int OUT_BOUND_NEED_WRAP_STUFF = 4050; // 当前订单出口需要输入包材条码
    // 作业申请单{0}，状态已经被修改,不能重复操作
    int STA_STATUS_ERROR = 10000;
    int STA_PO_ERROR = 10001;
    int STA_STALINE_EMPTY = 10002;
    int STA_STALINE_LOCATION_QUANTITY_EMPTY = 10003;
    // business_exception_10004=作业申请单Excel第[{0}]库位[编码={1}]不存在
    int STA_STALINE_LOCATION_NOT_EXIST = 10004;
    int STA_SKU_BARCODE_CODE_EMPTY = 10005;
    int STA_QUANTITY_NOT_SAME = 10006;
    // business_exception_10007=作业申请条码[{0}]JMCode[{1}]实际上架数量与计划执行量不相等
    int STA_QUANTITY_ERROR = 10007;
    int STA_QUANTITY_UNPLANNED = 10008;
    // business_exception_10009=作业申请单有未完成上架的作业明细单,在未完成之前不能进行收货操作
    int STA_HAS_CREATED_STV = 10009;
    // business_exception_stv_10101=作业明细单的状态错误,已被其他用户修改
    int STV_STATUS_ERROR = 10101;
    // business_exception_stv_10102=当前仓库库位不足,请修复后再尝试此操作
    int STV_LOCATION_NOT_ENOUGH = 10102;
    // business_exception_stv_10103=请初始化可销售的库存状态数据后再尝试此操作
    int STV_NO_INVENTORY_STATUS = 10103;
    // business_exception_stv_10104=商品条码{0}对应编码为{1}的库位不适合当前操作,请自选合适的库位
    int STV_LOCATION_ERROR = 10104;
    // business_exception_stv_10105=商品条码{0}对应编码为{1}的库位的库容不适合当前操作,请自选合适的库位
    int STV_LOCATION_QUANTITY_ERROR = 10105;
    // business_exception_stv_10106=商品条码{0}对应编码为{1}的库位已经被占用,请自选合适的库位
    int STV_LOCATION_OCCUPY_ERROR = 10106;
    // business_exception_10107=作业申请单行[{0}]对应编码为{1}的库位不适合当前操作,请自选合适的库位
    int STV_LOCATION_LINE_ERROR = 10107;
    // business_exception_10108=系统找不到适合作业单类型[{0}]相对应的作业类型
    int STV_TRAN_TYPE_ERROR = 10108;

    // business_exception_10109= 行[{0}] 商品{0} 批次未找到
    int STV_BATCH_CODE_IS_NULL = 10109;

    // business_exception_10010=根据采购单创建作业申请单时,采购单{0}的状态必须是新建[=1],但是目前状态是{1}.
    int PO_STATUS_CREATE_ERROR = 10010;
    // business_exception_10011=作业申请单收货时,采购单{0}的状态必须是已确认[=5]/部分收货[=10],但是目前状态是{1}.
    int PO_STATUS_RECEIVE_ERROR = 10011;
    // business_exception_10012=作业申请单完成时,采购单{0}的状态必须是已确认[=5]/部分收货[=10],但是目前状态是{1}.
    int PO_STATUS_CLOSE_ERROR = 10012;

    // business_exception_10013=作业申请单第{0}行收货数量是{1},但是SN序列号的数量是{2}.
    int ERROR_QUANTITY_SNS = 10013;

    // business_exception_10014=sheet{0} SN号 [{1}]有重复.
    int ERROR_SN_IS_NOT_UNIQUE = 30014;

    int PDA_NO_LOG = 10014;
    
    int NEED_REASON = 400002;

    // 系统错误或SN号有重复
    int PO_SYSTEM_ERROR = 10100;

    int STA_CREATE_ERROR = 10200;
    int NO_INVENTORY = 10201;
    int PICKING_LIST_NOT_FOUND = 10202;
    int USER_NOT_FOUND = 10203;
    int OPERATION_UNIT_NOT_FOUNT = 10204;
    int STA_NOT_FOUND = 10205;
    int STA_CANCELED_ERROR = 10206;
    int PICKING_LIST_IN_STA_IS_NOT_NULL = 10207;
    int STA_OUTBOUND_ERROR = 10208;
    int PICKING_LIST_CANCEL_ERROR = 10209;
    int STV_NOT_FOUND = 10210;
    int STV_LINE_NOT_FOUND = 10233;
    int NO_OCCUPIED_INVENTORY = 10211;
    int PACKAGE_INFO_NOT_FOUND = 10212;
    int PACKAGE_IS_HAND_OVER = 10213;
    int HAND_OVER_LIST_CREATE_ERROR = 10214;
    int HAND_OVER_NOT_FOUND = 10215;
    int HAND_OVER_NOT_ENOUGNT_MESSAGE = 10216;
    int HAND_OVER_LIST_LINE_NOT_FOUND = 10217;
    int HAND_OVER_LIST_IS_FINISHED = 10218;
    int OPERATION_UNIT_TYPE_ERROR = 10219;
    int PACKAGE_INFO_NO_ENOUGHT_MESSAGE = 10220;
    int PACKAGE_INFO_IS_EXIST = 10221;
    int HAND_OVER_STA_NOT_FUND = 10222;
    int HAND_OVER_STA_NOT_HAND_OVER = 10223;
    int SKU_NOT_FOUND = 10300;
    int OUT_BOUND_NEED_WRAP_STUFF_NOT_FOUND = 10333;
    int LOCATION_NOT_FOUND = 10301;
    int TRANSTACTION_TYPE_NOT_FOUND = 10302;
    int INVENTORY_STATUS_NOT_FOUND = 10303;
    int INVENTORY_STATUS_NOT_FOUND_BY_NAME = 103031;
    int TRANSTACTION_TYPE_IS_SYSTEM = 10304;
    int SKU_NOT_ENOUGHT_INVNENTORY = 10310;
    int STA_PACKAGE_INFO_NOT_ALL_CHECKED = 10311;

    int SKU_NOT_ENOUGHT_INVNENTORY_2 = 10312;
    int SKU_NOT_MATCH_OUTBOUND_STVLINE = 10313;

    int INVENTORY_CHECK_NOT_FOUND = 10320;
    int INVENTORY_CHECK_STATUS_NOT_UNEXECUTE = 10321;
    int INVENTORY_CHECK_CONFIRM_USER_EMPTY = 10322;
    int INVENTORY_CHECK_FINISHED = 10323;
    int INVENTORY_CHECK_LINE_NO_LOCATION = 10324;
    int INVENTORY_CHECK_STATUS_ERROR = 10325;

    int SALES_ORDER_STATUS_ERROR = 10350;
    int RA_SALES_ORDER_STATUS_ERROR = 10351;
    int RA_STATUS_ERROR_NO_INBOUND = 10352;
    int RA_STATUS_ERROR_NO_CHECKED = 10353;
    int UPDATE_K3INFO_FOR_SO_ERROR = 10354;

    int WH_DISTRICT_HAS_INVENTORY = 10401;
    int WH_LOCATION_HAS_INVENTORY = 10402;
    int WH_LOCATION_SAME_CODE = 10403;
    int WH_LOCATION_SAME_BAR_CODE = 10404;
    int WH_LOCATION_NO_TRANSACTION_TYPE = 10405;
    int WH_LOCATION_NO_SKU_FOUND = 10406;
    int WH_TRANTYPE_DIRECTION_NOT_MATCH = 10407;
    // business_exception_10408=第{0}行的库位编码格式错误,请按[行标识X/XY/XYZ/XYZC]格式填写
    int WH_LOCATION_SYSCOMPILECODE_ERROR = 10408;
    // business_exception_10409=第{0}行的库位编码[{1}]在仓库已经存在
    int WH_LOCATION_SYSCOMPILECODE_EXISTS = 10409;
    // business_exception_10410=第{0}行的库位条码[{1}]在仓库已经存在
    int WH_LOCATION_BAR_CODE_EXISTS = 10410;
    // business_exception_10411=第{0}行的库位编码[{1}]与第{2}行库位编码重复
    int WH_LOCATION_SYSCOMPILECODE_EXISTS_REDUPLICATE = 10411;
    // business_exception_10411=第{0}行的库位条码[{1}]与第{2}行库位条码重复
    int WH_LOCATION_BAR_CODE_EXISTS_REDUPLICATE = 10412;
    int WH_DISTRICE_NOT_FOUND = 10413;
    int WH_LOCATION_IS_LOCKED_OR_OCCPUAID = 10414;

    int TRANSACTION_TYPE_TRANIST_INNER_NOT_FOUND = 10500;
    int TRANIST_INNER_LINE_EMPTY = 10501;
    int TRANSACTION_TYPE_INVENTORY_CHECK_OUT_NOT_FOUND = 10502;
    int TRANSACTION_TYPE_INVENTORY_CHECK_IN_NOT_FOUND = 10503;
    int OWNER_IS_NULL = 10504;
    int INVENTORY_STATUS_CHANGE_LINE_EMPTY = 10505;
    int TRANSACTION_TYPE_INVENTORY_STATUS_CHANGE_OUT_NOT_FOUND = 10506;
    int TRANSACTION_TYPE_INVENTORY_STATUS_CHANGE_IN_NOT_FOUND = 10507;
    int TRANSACTION_TYPE_VMI_INVENTORY_CHECK_IN_NOT_FOUND = 10520;
    int TRANSACTION_TYPE_VMI_INVENTORY_CHECK_OUT_NOT_FOUND = 10521;

    int SKU_QTY_NOT_EQ_FOR_INV_STATUS_CHANGE = 10509;
    int SKU_QTY_NOT_EQ = 10510;
    int TRANIST_CROSS_STV_LINE_EMPTY = 10511;
    int TRANSACTION_TYPE_TRANSIT_CROSS_OUT_NOT_FOUND = 10512;
    int TRANSIT_INNER_CALCEL_ERROR = 10513;
    int TRANSIT_CROSS_CALCEL_ERROR = 10514;
    int INVENTORT_STATUS_CHANGE_CALCEL_ERROR = 10515;
    int OUT_OF_BOUND_FAILURE = 10550; // 操作失败：作业单[{0}]出库失败
    int INVENTORY_SKU_LOCATION_IS_SINGLE_STOREMODE_ERROR = 20704;

    int EXCEL_IMPORT_SKU_STOREMODE_ERROR = 20703;

    /** 请导入正确格式的EXCEL文件 */
    int EXCEL_IMPORT_FILE_READER_ERROR = 10700;
    int EXCEL_IMPORT_INVENTORY_INITIALIZE_TYPE = 10701;
    int EXCEL_IMPORT_LOCATION_NOT_FOUND = 10702;
    int EXCEL_IMPORT_SKU_BARCODE_NOT_FOUND = 10703;
    int EXCEL_IMPORT_SKU_CODE_NOT_FOUND = 10704;
    int EXCEL_IMPORT_INVENTORY_NOT_EMPTY = 10705;
    int EXCEL_IMPORT_COMPANY_SHOP_NOT_FOUND = 10706;
    int EXCEL_IMPORT_INITIALIZE_TRANSATION_TYPE_NOT_FOUND = 10707;
    int EXCEL_IMPORT_LOCATION_NOT_SUPPORT_TRANSACTION_TYPE = 10708;
    int EXCEL_IMPORT_INVENTORY_STATUS_NOT_FOUND = 10709;
    int EXCEL_IMPORT_BETWEENLIBARYMOVE_INITIALIZE_TYPE = 10710;
    int EXCEL_IMPORT_BETWEENLIBARYMOVE_NOT_QUANTITY = 10711;
    int EXCEL_IMPORT_BETWEENLIBARYMOVE_NOT_SKU = 10712;
    int START_OPERATION_UNIT_NOT_FOUNT = 10713;
    int END_OPERATION_UNIT_NOT_FOUNT = 10714;
    int BETWENLIBARY_STA_CREATE_ERROR = 10715;
    int BETWEENLIBARYMOVE_STA_CREATE_ERROR = 10716;
    int EXCEL_IMPORT_TRANSACTION_TYPE_NOT_FOUND = 10717;
    int BETWENLIBARY_STA_NOT_FOUND = 10718;
    int BETWENLIBARY_STA_OCCUPIED_ERROR = 10719;
    int EXCEL_IMPORT_TRANSIT_INNER_TRANSATION_TYPE_NOT_FOUND = 10720;
    int BETWENLIBARY_STV_NOT_FOUND = 10721;
    int BETWENLIBARY_NO_OCCUPIED_INVENTORY = 10722;
    int BETWENLIBARY_MAINWAREHOUSE_NOT_MOVE_SKU = 10723;
    int EXCEL_IMPORT_LPCODE_NOT_FOUND = 10724;
    int EXCEL_IMPORT_SEND_QTY_LS_ZERO = 10725;
    int EXCEL_IMPORT_SKU_NOT_FOUND = 10726;
    int EXCEL_IMPORT_STA_FONDU = 10727;
    int EXCEL_IMPORT_SLIP_CODE_IS_EXISTS = 10728;
    int EXCEL_IMPORT_SLIP_CODE_HEAD_NOT_MATCH = 10729;
    int EXCEL_IMPORT_STA_CREATE_NOT_ENOUNGT_INVENTORY = 10730;
    int EXCEL_IMPORT_SLIP_CODE_LINE_NOT_MATCH = 10731;
    int EXCEL_IMPORT_SN_EXISTS = 10732;
    int EXCEL_IMPORT_SN_NOT_EXISTS = 10733;
    int EXCEL_IMPORT_SN_HAS_SAME = 10734;
    int EXCEL_IMPORT_SKU_QTY_NOT_EQ = 10735;
    int EXCEL_IMPORT_EXCEL_IS_NULL = 10736;
    int EXCEL_IMPORT_SKU_SN_NOT_FOUND = 10737;
    int BETWENLIBARY_STV_OUTBOUND_NO_FINISH = 10738;
    int BETWENLIBARY_STA_QTY_IS_NOT_NULL = 10739;
    /** 请不要导入空的EXCEL文件 */
    int EXCEL_IMPORT_EMPTY = 10740;
//    int COMBOSKU_SKU_DUPLICATE = 10741;//combo商品码和sku码有重复
    
    int EXCEL_IMPORT_VMI_INVCK_SKU_NOT_FOUND = 10800;
    int EXCEL_IMPORT_VMI_INVCK_NOLOCATION = 10801;
    int EXCEL_IMPORT_VMI_INVCK_LOCATION_NOT_EXIST = 10802;
    int EXCEL_IMPORT_VMI_INVCK_SKU_QUANTITY_NOT_SAME = 10803;
    int EXCEL_IMPORT_VMI_INVCK_STATUS_NOT_EXIST = 10804;
    int EXCEL_IMPORT＿VMI_INVCK_LOCKED = 10805;
    int EXCEL_IMPORT＿VMI_INVCK_SKU_LACK = 10806;


    int EXCEL_IMPORT_INVENTORY_CHECK_NO_LOCATION = 10900;
    int EXCEL_IMPORT_IN_OUT_IS_NOT_EQUALS = 10910;
    int EXCEL_IMPORT_IN_OUT_IS_NOT_EQUALS_FOR_INV_STATUS_CHANGE = 10911;
    int EXCEL_PARSE_ERROR = 10912;
    int SKU_STYLE_NOT_EXIST = 10913;

    int CREATE_PO_STA_STA_ALREADY_EXISTS = 10999;

    int OCCPUAID_INVENTORY_ERROR_NO_ENOUGHT_QTY = 11000;
    int SKU_NO_INVENTORY_QTY = 11001;
    int MODIFT_TRANS_STA_STATUS_ERROR = 11002;

    int TRACKING_IS_NULL = 12001;
    int WEIGHT_IS_NULL = 12002;
    int PICKING_USER_IS_NULL = 12003;
    int LOCATION_IS_LOCKED_OR_OCCUPAID = 12004;
    int INVNETORY_CHECK_CREATE_ERROR = 12005;
    int DISTRICT_LOCATION_IS_LOCKED_OR_OCCUPAID = 12006;

    int SKU_BAR_CODE_NOT_NULL = 12007;
    int SON_SKU_BAR_SIMILAR = 12008;

    int SKU_SN_NOT_FOUND = 12050;
    int SALES_OUTBOUND_STA_NOT_FOUNT = 12051;
    int EXPRESS_ORDER_TRACKINGNO_CHECK = 12052;
    int EXPRESS_ORDER_TRACKINGNO_NO_LEGAL = 12053;
    int EXPRESS_ORDER_TRACKINGNO_UNIQUENE = 12054;
    int EXPRESS_ORDER_TRACKINGNO_EXIST_UNIQUENE = 12055;

    // business_exception_13001=SN序列号{0}不在当前计划内
    int SNS_SKU_NO_PLAN = 13001;
    // business_exception_13002=对应SKU编码{0},条码{1}的数量是{2},但是SN序列号的数量是{3}
    int SNS_ACTUAL_ERROR = 13002;
    // business_exception_13003=导入SN序列号的Excel必须选择SKU编码/条码方式导入
    int SNS_SKU_TYPE = 13003;
    // business_exception_13004=导入SN序列号的Excel必须有数据
    int SNS_NO_DATA = 13004;
    // business_exception_13005=第{0}行条码{1}对应编码为{2}的商品必须填写序列号
    int SNS_SKU_NO_DATA = 13005;

    int SN_INSERT_UQ_ERROR = 13010;

    int SNSKU_QTY_NOTEQUAL_LINESKU_QTY = 14001;

    // 导入的SN号{0}有重复
    int IMPORT_SN_ISNOT_UNIQUE = 14002;

    // 入库需要SN号的商品数量{0}与当前系统内SN数量{1}不一致
    int SKU_QUANTITY_NOT_EQUALS_SN_QUANTITY = 14003;

    // SKU{0}需要SN号数量{1}与Excel中的SN号数量{2}不一致
    int SKU_QUANTITY_NOT_MATCHING_SN_QUANTITY = 14004;
    // SKU [ {0} ]在当前Excel中不存在
    int SKU_NOT_FOUND_IN_EXCEL = 14005;
    int SKU_NOT_FOUND_IN_CURRENT_STA = 14009; // sku[{0}]不在当前作业单中
    // 导入的SN号{0}不符合规则
    int IMPORT_SN_ISNOT_MEET_REGULATION = 14006;

    // 作业类型不正确
    int PREDEFINED_STA_TYPE_INCORRECT = 14501;
    // 事物类型未找到
    int PREDEFINED_TRANSACTION_TYPE_INCORRECT = 14502;

    int EXCEL_ERROR_TRANSPORTATOR_NULL = 15001; // 快递商未找到
    int EXCEL_ERROR_DISTRICT_NOT_FOUND = 15002;// 区域未找到
    int EXCEL_ERROR_TRANS_DISTRICT_NOT_FOUND = 15003; // 大区编码未找到
    int EXCEL_ERROR_TRANS_MIN_AND_BASE_ERROR = 15004; // 保底与基础不能都不为0
    int EXCEL_ERROR_TRANS_COST = 15005; // 首重价格与首重重复
    int EXCEL_ERROR_DEPARTURE_IS_NULL = 15006; // 发运地未找到
    int EXCEL_ERROR_DELIVERY_COST_ZERO = 15007; // 续重续重价格不能为0
    int EXCEL_MANDATORY_COLUMN_INVALID = 15008; // EXCEL列数据不正确

    // 公司不能为空
    int TRANSPORTATOR_REF_CMPPANY_IS_NULL = 15101;
    // 店铺不能为空
    int TRANSPORTATOR_REF_SHOP_IS_NULL = 15102;
    // 物流商不能为空
    int TRANSPORTATOR_REF_TRANSPORTATOR_IS_NULL = 15103;
    // 仓库不能为空
    int TRANSPORTATOR_REF_WAREHOUSE_IS_NULL = 15104;

    // 店铺还有业务没有完成
    int SHOP_REF_SHOP_BUSSINESS = 15105;
    // 仓库还有业务没有完成
    int SHOP_REF_SHOP_WAREHOUSE = 15106;

    // 计划发货量不正确
    int TRANS_DELIVERY_CFG_INCORRECT = 15200;

    int STA_TYPE_ERROR = 152001; // 作业单类型错误

    int NOT_FIND_STA = 152002; // 为找到作业单

    int STV_LINE_SKU_STORE_MODE_NOT_FOUND = 15211; // 作业单中商品【{0}】存放模式未找到
    int LOCATION_INBOUND_NOT_SUPPORT = 15212; // 入库作业失败，商品批次隔离无法入库：
    int LOCATION_INBOUND_NOT_SUPPORT_DETIAL = 15213; // 库位[{0}]，商品名称[{1}]编码[{2}]
    int LOCATION_NOT_USING = 15214; // 库位[{0}]被锁定或不可使用
    int LOCATION_NOT_ENOUGHT_CAPACITY = 15215; // 库位[{0}]库容不足

    int NO_SUPPORT_TRANSPORTATOR = 20001; // 物流无法送达
    int NO_SUPPORT_TRANSPORTATOR_NO_COST = 20002; // 所有支持物流未维护价格组 {0}
    int TRANSPORTATOR_NOT_FOUND = 20003; // 物流经销商未找到[{0}]
    int WH_NO_DEPARTURE = 20004; // 仓库[{0}]未维护发运地
    int TRNANSPORTATOR_ALL_OUT_OF_LIMIT = 20005; // 该仓库中所有物流发货量均超过当日限制
    int PROVINCE_NOT_FOUND = 20006; // 省份未找到[{0}]

    int SALES_MODE_PAYMENT_SKU_COST_IS_NULL = 20010; // 付款经销商品出库必须含有成本
    int CANNOT_DELETE_ALL_LINES = 30011;//不能将订单的行删完

    int PREDEFINED_EXCEL_IMPORT_SKU_CODE_NOT_FOUND = 25000;// sheet{0} 单元格 {1} 商品 [{2}] 未找到

    int NO_FINISH_STA = 30006;

    int STA_IS_NULL = 30010;

    int STV_NOT_INV_STATUS = 31001;// Excel第[{0}]行库位状态类型不能为空

    int PDA_POST_LOG_STATUS_ERROR = 31010; // PDA日志状态错误
    int STA_NOT_EXECUTE = 31200; // 作业单[{0}]无法执行
    int INBOUND_PLAN_NOT_EQ_EXEQTY = 31201;// 商品[{0}]计划量[{1}]不等于执行量[{2}]
    int INBOUND_SN_PLAN_NOT_EQ_EXEQTY = 31202;// 商品[{0}] SN号导入计划量[{1}]不等于执行量[{2}]
    int INBOUND_PDA_NOT_INV_STATUS = 31203;// 未找到库存状态
    int INBOUND_PDA_NOT_CLERROR = 31204;// 请处理PDA错误日志
    int INBOUND_PDA_QTY_ERROR = 31205;// 商品[{0}]执行量[{1}]大于 计划量[{2}]

    int VMI_INSTRUCTION_TYPE_ERROR = 30020;

    // 找不到盘点批
    int INV_CHECK_IS_NULL = 40000;

    int INV_CHECK_IMPORT_IS_NULL = 40001;// 导入的文件未找到数据

    int INV_CHECK_SKUCOST_IS_NULL = 40002;// 第{0}行库存成本成本不正确
    int INV_CHECK_OWNER_IS_NULL = 40003;// 第{0}行店铺不能为空或找不到店铺
    int INV_CHECK_IMPORT_COUNT_ERROR = 40004;// 导入数据量不等于预执行数据量
    int INV_CHECK_IMPORT_NOT_SKU = 40005;// 未找到需处理SKU: {0} 库位为：{1}
    int INV_CHECK_SKU_IS_NULL = 40006;// 第{0}行 商品不能为空
    int INV_CHECK_LOCATION_IS_NULL = 40007;// 第{0}行 库位不能为空
    int INV_CHECK_QUANTITY_ERROR = 40008;// 第{0}行 数量错误，必须和盘盈数量相等
    int INV_CHECK_IMPORT_NOT_FOUND = 40009;// 第{0}行商品：{1} 库位：{2} 的需处理数据未找到
    int INV_CHECK_IS_UNTREATED = 40010;// 还存在未处理的盘盈数据(店铺和库存成本)
    int VMI_INV_CHECK_CONFIRM_NUM_NOT_SAME = 40011;// VMI库存调整存数与库存占用数不相同

    int SKU_TOGETHER_LOC_IS_SKU = 41000;// 第{0}行 商品：{1} 是单批隔离的商品，而库位:{2} 已经存在批次不同的{3}商品

    int EXE_SKU_TOGETHER_LOC_IS_SKU = 41001;// 商品：{0} 是单批隔离的商品，而库位:{1} 已经存在批次不同的{2}商品

    int INBOUND_IMPORT_AMOUNT_CONFIRM_RECEIVE = 50010; // sheet{0} 单元格 {1} 商品 [{2}]
                                                       // 当前收货数量[{3}]大于系统中未完成收货量 {4}

    int INBOUND_IMPORT_AMOUNT_CONFIRM_RECEIVE_NOT_FOUND = 50020; // sheet{0} 单元格 {1} 商品 [{2}]
                                                                 // 不在当前收货批中

    int INBOUND_IMPORT_AMOUNT_CONFIRM_RECEIVE_NEED_SN = 50030; // sheet{0} 单元格 {1} 商品 [{2}] 需要sn号,且
                                                               // 数量为[{3}]

    int IMPUT_SNSKU_IS_NOT_SNSKU = 42000;// sheet{0} 单元格 {1} 商品 [{2}] 非SN号商品

    int SN_IS_NOT_OUT_SN = 43000; // {0} 商品 SN {1} 不是出库时的SN号商品
    int IN_SN_COUNT_IS_NOT_OUT_SN_COUNT = 43001; // 入库 SN号商品数量 不等于 SN号商品的出库数量

    int OU_NOT_FOUND = 43010;// 组织未找到
    int WAREHOUSE_SHOP_NO_REF = 43011;// 行[{0}]仓库[{1}店铺[{2}]未关联

    int PDA_KEY_IS_PRESENCE = 50000; // PDA 编码 {0} 已存在

    int ORDER_ACCOUNTING_SOCOUNTMODEL_NAME_IS_NULL = 51000; // sheet{0} 单元格 {1} 订单核算模式名称不能为空
    int ORDER_ACCOUNTING_SOCOUNTMODEL_NAME_NOT_FOUND = 51002;// sheet{0} 单元格 {1}
                                                             // 订单核算模式名称[{2}]有误，系统不存在

    int WAREHOUSE_OU_CODE_IS_NULL = 51004; // sheet{0} 单元格 {1} 仓库编码不能为空
    int WAREHOUSE_NOT_FOUND_BY_OU_CODE = 51006; // sheet{0} 单元格 {1} 仓库编码[{2}]有误，系统不存在

    int USER_PASSWORD_LENGTH_IS_ERROR = 101; // 密码长度不正确
    int USER_PASSWORD_LENGTH_IS_ERROR_2 = 100; // 密码长度不正确
    int USER_PASSWORD_AND_CONFIRMPASSWORD_NOT_EQUAL = 102; // 两次密码输入不正确
    int USER_RAW_PASSWORD_IS_ERROR = 103; // 原始密码输入错误
    int USER_PASSWORD_NEW_IS_NULL = 104; // 新密码不能为空
    int USER_PASSWORD_REGULATION_ERROR = 105; // 输入的新密码不符合密码规则
    int USER_PASSWORD_REGULATION_IS_NULL = 106; // 密码规则为空
    int USER_CONFIRMPASSWORD_IS_NULL = 107; // 确认密码不能为空
    int USER_LOGIN_NAME_IS_NULL = 108; // 用户名{0}系统不存在
    int USER_RAW_PASSWORD_IS_NULL = 109; // 原始密码不能为空

    int EXTERNAL_EXECUTION_NOT_EQ_PLAN = 60000; // sheet{0} 单元格 {1} 执行量不等于计划执行量
    int EXTERNAL_SN_NOT_OUT_SN = 60001; // sheet{0} 单元格 {1} 执行量不等于计划执行量
    int EXTERNAL_SN_SKU_IS_NOT_NULL = 60002; // 计划执行SN号{0} 未找到
    int EXTERNAL_SN_IS_NOT_NULL = 60003; // sheet{0} 单元格 SN 号 不能为空
    int EXTERNAL_SN_IS_NULL = 60004; // sheet{0} 单元格 SN：{0} 号不是移库出库的SN
    int RSORDER_CODE_IS_NULL = 60005; // sheet{0} 单元格 {1}单据{2}不在该对账单内
    int RSORDER_CODE_IS_EXIST = 60006; // sheet{0} 单元格 {1}订单{2}重复导入
    int RSORDER_UNITPRICE_IS_NULL = 60007; // sheet{0} 单元格 {1} 单价不能为空
    int RSORDER_TOTOLPRICE_IS_EXIST = 60008; // sheet{0} 单元格 {1} 总价不能为空
    int RSORDER_QTY_IS_EXIST = 60009; // sheet{0} 单元格 {1} 数量不能为空
    int RSORDERLINE__TOTALACTUAL_UNEQUALLY = 60010; // sheet12 单元格 {0}单据{1} 的订单总金额与sheet1中的销售总金额有差异
    int VMI_FLITTING_OUT_SHOP_REF = 60011; // 仓库[{0}]未绑定 店铺[{1}] 不能执行此动作

    int START_OWNER_NOT_FOUNT = 60100;// 源头店铺[{0}]未找到
    int END_OWNER_NOT_FOUNT = 60101;// 目标店铺[{0}]未找到
    int COMAPNY_SHOP_IS_NOT_FOUND = 8500; // 所选店铺系统不存在
    int TWO_COMAPNY_SHOP_IS_NOT_RELATIVE = 8510; // 所选店铺未关联同一家仓库
    int STA_IS_NOT_FROZEN = 3306; // 当前作业单{0}不是冻结状态，无需解冻
    int STA_IS_ALREADY_PDA_INBOUND = 1521; // 当前作业单[{0}] 已经PDA收货了,无需重新操作
    int STV_NOT_FOUND_GENERIC = 1522;


    int VMI_NOT_FOUND_SHOP = 70001; // 没有找到VMI转店或转仓店铺

    int EXCEL_LOCATION_NOT_FOUND = 1700;
    int EXCEL_QUANTITY_IS_NEGATIVE = 1702;
    int EXCEL_DISTRICT_LOCATION_IS_LOCKED_OR_OCCUPAID = 1800;
    int WAREHOUSE_DISTRICT_NOT_FOUND = 1805;

    int VMI_ADJUSTME_ERROR = 80001; // 反馈指令生成失败
    int VMI_ADJUSTMENT_PO_ERROR = 80002;// ESPRIT调整单PO号不存在 或者 调整单中存在指令中没有的SKU
    int VMI_ADJUSTMENT_QYT_ERROR = 80003;// ESPRIT调整单不允许存在负值
    int VMI_TRANSFER_STA_NULL = 80004;// 源入库单为空
    int VMI_INBOUND_PO_NO_IS_NULL = 80005;// 入库单PO为空
    int VMI_INBOUND_PO_TYPE_ERROR = 80006;// 单元格{0}PO类型{1}不存在。
    int VMI_INBOUND_PO_NUM_ERROR = 80007;// 单元格{0}PO号不能为空。
    int VMI_ADJUSTME_INSTRUCTION_NULL = 80008; // 找不到调整指令，无法确认差异。
    int VMI_ADJUST_NOT_FOUND = 80009;// 调整单未找到
    int VMI_ADJUST_HAS_FINISH = 80010;// 调整单[{0}]已经完成
    int VMI_ESPRIT_NOT_FOUND_SKU = 80011;// 此相关单据号没有找到要创建的SKU。
    int VMI_ESPRIT_SKU_CREATE_ERROR = 80012;// 商品[{0}]创建失败
    int VMI_NEED_NOT_CREATE_SKU = 80013;// 当前选择店铺无法创建商品
    int VMI_RETURN_TO_LOCATION_ERROR = 80014;// 为找到入库目标地址

    int PL_OUTPUT_COUNT_ERROR = 81001;// 配货单已导出，不能重复操作


    int PREDEFINED_OUT_CREATE_INV_ERROR = 82001;// 作业单：商品条码：[{0}]、商品编码：[{1}]、扩展属性：[{2}]
                                                // ，库存状态为{3}，没有足够库存[{4}]
    int PREDEFINED_OUT_BY_SN_IS_NOT_INFO = 82002;// 根据SN[{}]找到商品[SKU编码{0}],库存状态为{0} 不在出库单内
    int PREDEFINED_OUT_BY_SKU_NUB_ERROR = 82003;// 商品[SKU编码{0}],执行量不等于计划执行量
    int PREDEFINED_OUT_CREATE_OWNER_WH_ERROR = 82004;// 店铺[{0}] 和 仓库[{0}]不是同一公司
    int PREDEFINED_OUT_OUT_STATUS_ERROR = 82005;// 当前负向采购出库单前置单据[{0}] 状态错误
    int PREDEFINED_OUT_OUT_IS_NOT_SLIP_CODE = 82006;// 未找到前置单据号

    int NOT_FOUND_ALIPAYID = 90001; // 流水号{0}在系统中不存在
    int EXISTS_ALIPAYID = 90002; // 流水号{0}在系统中已存在
    int SHOP_NOT_FOUND = 135;

    int STA_CANCELED_ERROR_VMI_WH = 83001;// 销售-作业单取消失败，外包仓无法取消
    int STA_CANCELED_ERROR_VMI_RO_WH = 83002;// 退货入库-作业单取消失败，外包仓无法取消



    int IDS_BASE_ERROR_CODE = 99990000;
    int IDS_BASE_ERROR_CODE_SYSTEM_ERROR = 99991002;
    int VMI_WH_RTN_OUTBOUND_MISS_MSG = 99992001;
    int IDS_STA_CANCELED_ERROR_VMI_WH = 99993001;// 销售-作业单取消失败，外包仓无法取消
    int OUTSOURCING_RTN_BATCH_TYPE_ERROR = 99993002;// 接收确认 批次 类型有误
    int OUTSOURCING_RTN_BATCH_ID_ERROR = 99993003;// 接收确认 批次 ID有误
    int OUTSOURCING_OUT_ORDER_IS_NULL = 99993004;// 未接收到相关出库单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_ORDER_CANCEL_IS_NULL = 99993005;// 未接收到相关确认取消单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_RETURN_IN_IS_NULL = 99993006;// 未接收到退换货入库单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_IN_BOUND_IS_NULL = 99993007;// 未接收到入库单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_BO_OUT_BOUND_IS_NULL = 99993008;// 未接收到其他出库单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_CF_SKU_IS_NULL = 99993009;// 未接收到确认接收SKU 或者 XML格式有误 未解析出数据
    int OUTSOURCING_CF_ORDER_IS_NULL = 99993010;// 未接收到确认接收单据 或者 XML格式有误 未解析出数据
    int OUTSOURCING_OP_CODE_ERROR = 99995010;// OP CODE 参数不正确{}

    int VMI_WH_XML_ERROR_MSG = 99994001;// 未获取到XML数据 或 XML 数据格式错误


    int MSGOUTBOUNDORDER_IS_NOT_FOUND = 5504;
    int STA_CANCEL_FAILURE_STA_HAS_OUT_BOUND = 5505;
    int STA_CANCEL_FAILURE_STA_HAS_CANCEL_CLOSE = 5506;
    int STA_CANCEL_FAILURE_STA_NOT_FOUND = 5507;
    int STA_CANCEL_FAILURE_TONKEN_INVALIDATION = 5508;
    int STA_CANCEL_FAILURE_STA_ORDER_CODE_IS_NULL = 5509;
    int STA_CANCEL_FAILURE_SYSTEM_ERROR = 5510;
    int STA_CANCEL_FAILURE_NO_FEEBACK_MESSAGE = 5511;
    int STA_CANCEL_FAILURE_FEEBACK_MESSAGE_IS_NULL = 5512;

    int WAREHOUSE_NOT_FOUND = 6610;
    int WAREHOUSE_OU_NOT_FOUND = 6611;
    int TRANSACTION_TYPE_NOT_FOUND = 6612;

    int IDS_STA_NOT_PURVIEW = 99992002;// 无权限访问IDS接口

    int PURCHASE_SKU_TYPE = 9001; // Excel必须选择SKU编码/条码方式导入
    int PURCHASE_NO_DATA = 9002; // Excel导入数据不能为空
    int IMPORT_SKU_ISNOT_UNIQUE = 9003; // 导入的Sku编码/条码不能重复
    int IMPORT_SKU_ISNOT_MEET_REGULATION = 9004; // 商品[SKU编码/条码{0}],不存在
    int IMPORT_QTY_IS_OVER = 9005; // 商品[SKU编码/条码{0}],本次上架数量与输入上架数量不相等
    int IMPORT_STVLINE_QTY_IS_NOT_MATCH = 9006; // 导入商品数量与本次上架商品数量不相等

    int IO_EXCEPTION = 1000001;// IO 异常
    int FILE_NOT_FOUND = 1000002;// 文件未找到

    int STA_CANCELED = 80015; // 作业单已经取消
    int NOT_CANCELED_NODE = 80016; // 无法取消，请重新确认该订单当前节点
    /** 系统自动创建退款失败，该订单存在有效退款。 */
    int AUTO_REFUND_APPLICATION_CREATE_REPEAT = 80017;
    /** 系统自动创建退款失败，该订单为货到付款不产生退款。 */
    int AUTO_REFUND_APPLICATION_CREATE_NOT = 80018;
    /** 系统自动创建退款申请失败！ */
    int AUTO_REFUND_APPLICATION_CREATE_ERROR = 80019;
    /** 订单[{0}]当前节点不是取消节点，请重新确认此订单当前节点 */
    int NOT_CANCEL_NODE = 80020;
    /** 订单[{0}]修改失败，{1} */
    int SO_MODIFY_ERROR = 80021;
    /** 无法修改，请重新确认该订单当前节点 */
    int NOT_MODIFY_NODE = 80022;
    /** 请选择要操作的数据 */
    int EMPTY_IDS = 80023;
    int CANCEL_ERROR_CS_SO = 80024; // 订单{0}取消失败，挂起的云栈订单不允许手动取消

    int WAREHOUSELOCATION_IS_NULL = 83002;// 库位不存在

    int NIKE_WEBSERVERCE_INVENTORY_STATUS_NOT_FOUND = 19915;// 15
    int NIKE_WEBSERVERCE_SKU_NOT_FOUND = 19914; // 14
    int NIKE_WRAEHOUSE_NOT_FOUND = 20000;// 100
    int NIKE_SHOP_NOT_FOUND = 20000;// 100
    int NIKE_WEBSERVERCE_TRANSACTIONTYPE_NOT_FOUND = 20000;// 100
    int NIKE_WEBSERVERCE_BUSINESS_EXCEPTION = 20000;// 100
    // int NIKE_WEBSERVERCE_INVENTORYSTATUS_NOT_FOUND = 9950;
    int NIKE_WEBSERVERCE_OUTBOUND_INFO_IS_NULL = 19912; // 12
    int NIKE_WEBSERVERCE_INVENTORY_IS_LACK = 19911; // 11

    int HANDOVER_LIST_TRACKING_NO_IS_MULTIPLE = 30025;

    int INVENTORY_CHECK_IMPORT_BY_LOCATION_IS_NULL = 40056;
    int LOCATION_IS_INVALID = 40088;

    int STV_LINE_IN_BOUND_TIME_IS_NULL = 11111111;// 找不到入库时间
    int CARTON_NOT_FOUND = 70010;
    int CARTON_IS_FINISHED = 70011;
    int CARTON_SKU_ERROR = 70012;
    int CARTON_SKU_NOT_IN_PLAN = 70013;
    int CURRENT_STA_IS_NOT_OCCUPIED = 70014;
    int EXCEL_IMPORT_PRODUCT_NOT_FOUND = 70016;
    int EXCEL_IMPORT_PRODUCT_BOX_QUANTITY_ERROR = 70017;

    int SKU_NOT_FOUND_FOR_EXCEL_IMPORT = 83010;
    int WAREHOUSE_DISTRICT_NOT_FOUND_FOR_EXCEL_IMPORT = 83011;
    int WAREHOUSE_DISTRICT_NOT_PICKING_DISTRICT = 83012;
    int SKU_PROVIDE_WARNINGPRE_IS_ERROR = 83013;
    int BETWEENLIBARYMOVE_STA_CREATE_ERROR_NOT_SUFFICIENTINVENTORY = 84014;
    int CROSS_OUT_MAINHOUSE_IS_NULL = 84015;
    int CROSS_IN_MAINHOUSE_IS_NULL = 84016;
    int MSGRTNOUTBOUNDLINE_IS_NULL = 84017;
    int THREEPL_OUT_BOUND_FAILED_MSG_RETURN_SKU_MATCH = 84018;
    int MSG_SKU_QUANTITY_GREATE_OUT_BOUND_SKU_QUANTITY = 84019;
    int SKU_CREATE_ERROR = 84020;

    int NOT_PICKING_LIST = 85000;// 未找到配货清单

    int OMS_OUT_BOUND_ERROR = 86001;// 存在工作流，调用OMS出库接口失败，请联系后台。
    int SF_CAN_NOT_SEND = 86002;// 顺风无法送达

    int WORK_LINE_NO_IS_NULL = 86100;// 流水开票号未找到


    int ESP_PO_NOT_TYPE = 86300;// PO{0} 类型未找到
    int ESP_PO_NOT_TYPE_INV = 86350;// PO{0}发票号未找到,请在[ESP单据类型导入]中更新发票号。
    int ESP_PO_NOT_INV = 86400;// PO单{0} 对应的发票未找到，请先维护好发票号以及发票系数并且绑定。
    int ESP_PO_TYPE_ERROR = 86450;// PO{0} 是非进口类型，操作失败。
    int ESP_PO_IS_NOT_NULL = 86451;// PO{0} 不存在。
    int ESP_PO_INV_IS_NOT_NULL = 86452;// 发票号{0} 不存在。

    int WH_DISTRICT_TYPE_ERROR = 87000;// 仓库库区类型错误！联系后台。
    int INV_STATUS_LOC_ERROR = 87100;// 库存状态修改中存在暂存的库位{0}，并且还存在其他库位，库存状态修改单里面如果存在暂存区里面库位，那么只能针对这一个暂存区库位上的商品进行修改。

    int OUT_BOUND_GI_LOCATION = 88000;// 商品{0}有{1}件，是占用暂存区{2}库位上面，暂存区商品不接受此出库方式，占用失败。


    // "店铺所属公司不是仓库所属公司"
    int SHOP_COMPANY_NOT_WH_COMPANY = 91000;
    // "店铺所属公司不是仓库所属公司"
    int EMS_ORDER_ERROR = 91001;
    // 实际补货不等于建议补货数量
    int SKU_MOVE_SUGGEST_QTY_ERROR = 100000;

    int IN_BOUND_AFFIRM = 200001;// 非可确认单据！请导入收货数据，再做确认。
    int NOT_FOUND_CONFIRM_STV = 200002;// 未找到可核对入库单
    int STV_STRUTS_ERROR = 200003;// 作业明细单状态错误
    int SKU_IS_NOT_DIFFERENCE = 200004;// 商品{0}不在调整商品范围内
    int DIFFERENCE_NUMBER_ERROR = 200005;// 商品{0},{1}不在调整后收货数量大于剩余计划量
    int SKU_NOT_ESSENTIAL = 200006;// 商品{0}基本信息(长、宽、高、净重)没有维护
    int IN_BOUND_SHELVES = 200007;// 非可上架单据
    int IS_NOT_INBOUND_ORDER_A = 200008;// 非可收货单，存在未完成的上架单
    int IS_NOT_INBOUND_ORDER_B = 200009;// 非可收货单，存在未审核的收货单
    int IS_NOT_INBOUND_ORDER_C = 200010;// 非可收货单，存在已审核但未完成的收货单
    int SKU_NOT_PLOT = 200011;// 商品{0}，不在计划内
    int SKU_QTY_IS_NOT_NULL = 200012;// 商品{0}，数量不能为空！
    int SKU_QTY_IS_ERROR = 200013;// 商品{0}，{1}数量错误，不能小于0

    String CONNECT_TAOBAO_CLIENT_ERROR = "CONNECT_TAOBAO_CLIENT_ERROR";

    int IMPOERT_DATA_NOTUNIQUE = 1201;
    int IMPORT_DATA_NOTEXISTS = 1202;
    int IMPOERT_DATA_ERROR = 1203;
    int PRO_NO_DATA = 1204;
    int UPDATE_PRO_INFO_ERROR = 1205;
    int CALL_OMS_ERROR = 1206;

    /***************************************************** O2O **********************************************************/



    /***************************************************** oms **********************************************************/
    int PRODUCT_TEMPLATE_NAME_IS_EXIST = 104; // {0}模版名称已存在
    int PRODUCT_TEMPLATE_CODE_IS_EXIST = 105; // {0}模版编码已存在
    int PRODUCT_TEMPLATE_UPDATE_ERROR = 106; // 商品模版更新失败
    int PRODUCT_TEMPLATE_IS_USE = 107; // 商品模版已经有商品使用不能修修改

    int BRAND_CODE_IS_EXIST = 10001; // 品牌编码已经存在
    int BRAND_NAME_IS_EXIST = 10002; // 品牌名称已经存在
    int BRAND_NOT_EXISTS = 10003; // 品牌不存在
    int SUPPLIER_NOT_EXISTS = 10004; // 供应商不存在
    int PRODUCT_TEMPLATE_NOT_EXISTS = 10005; // 商品模板不存在
    int PRODUCT_IS_NULL = 10006;// 单元格[{0}]商品编码{1}不存在
    int DTTCODE_IS_NULL = 11006;// 单元格[{0}]模板编码不存在
    int PROVINCE_IS_NULL = 11106;// 单元格[{0}]省份{1}不存在
    int Mobile_AND_PHONE_NULL = 100000002;// 两个不能全部都为空
    int OuterOrder_IS_Null = 10000001;// 单元格[{0}]{1}不存在
    int EXPCODE_IS_NULL = 11117;// 单元格[{0}]默认物流商编码{1}不存在
    int PROMOTION_NAME_IS_NULL = 12117;// 单元格[{0}]不能为空
    int PROVINCE_DOUBLE_IS_NULL = 11116;// 单元格[{0}]省份{1}重复
    int PROVINCE_CITY_DOUBLE_IS_NULL = 51116;// 单元格[{0}]省市区[{1}{2}{3}]重复
    int REACHABLEAREA_DOUBLE_IS_NULL = 61116;// 单元格[{0}]开通城市与未开通城市只允许填写其中一项
    int PRODUCT_IS_EXISTED = 10007; // 商品编码已存在
    int PRODUCT_CAN_NOT_BE_MODIDIED = 10008; // 创建方式为自动创建的商品不能被修改

    int PRODUCT_TEMPLATE_NOT_MATCH = 10009; // 商品模板不匹配
    int PRODUCT_CODE_DUPL = 10010; // 数据行${index}:[${object}]中存在重复数据：${value}
    int SUPPLIER_CODE_NOT_EXISTED = 10011; // 供应商编码不存在
    int SUPPLIER_CODE_NOT_EXISTED_INDEX = 10111; // 数据行${index}:供应商编码不存在：${value}
    int TAXRATECODE_NOT_EXISTED_INDEX = 10112; // 数据行${index}:采购税率编码不存在：${value}
    int SELLRATECODE_NOT_EXISTED_INDEX = 10113; // 数据行${index}:销售税率编码不存在：${value}
    int PRODUCT_CODE_EXISTED = 10012; // 数据行${index}:[${object}]中商品编码已存在：${value}
    int PRODUCT_CODE_EXISTED_1 = 100122; // 数据行${index}:[${object}]中客户[{value}]商品编码已存在：${value}
    int PRODUCT_NOT_EXISTED = 10013; // 对应商品不存在：${0}
    int K3CUSTOMER_ADD_FAILE = 10014;// K3客户添加失败
    int K3CUSTOMER_IS_EXISTS = 10015;// K3客户代码已经存在
    int K3CUSTOMER_UPDATE_FAILE = 10016;// K3客户修改失败

    int USER_NOT_BELONG_STORE = 10017;// 用户不属于当前店铺
    int PARSE_EXCEL_ERROR = 10018;// 解析Excel文件不成功
    int PRODUCT_CODE_MANUAL = 10019; // 数据行${index}:[${object}]Sku导入只允许手工创建的商品：${value}
    int COMPANY_SHOP_NOT_EXISTED = 100019;// 店铺不存在

    int PRODUCT_KEYPRO_NOT_MATCH_WITH_TEMPLATE = 10020; // 商品[{0}]关键属性与对应模板[{0}]不匹配


    int TRANSPORTATOR_CODE_IS_EXIST = 20001; // 物流服务商编码已经存在
    int TRANSPORTATOR_NAME_IS_EXIST = 20002; // 物流服务商名称已经存在
    int TRANSPORTATOR_EXPCODE_IS_EXIST = 20003; // 内部平台对接编码已经存在
    int SUPPLIER_CODE_IS_EXIST = 20011; // 采购供应商编码已经存在
    int SUPPLIER_FICODE_IS_EXIST = 20012; // K3代码已经存在

    int LINE_PRODUCT_NOT_EXISTS = 20020; // 明细行中第[{0}]行商品编码[{1}]不存在
    int PRODUCT_RELATED_TEMPLATE_NOT_EXISTS = 20021; // 非法商品,该商品[{0}]没有相关的商品模板
    int LINE_PRODUCT_KEYPROPERTY_IS_NULL = 20022; // 明细行中第[{0}]行商品[{1}]具有关键属性,明细行中关键属性不能为空
    int LINE_SKU_NOT_EXISTS = 20023; // 明细行中第[{0}]行商品[{1}][{2}]对应的SKU不存在

    int PURCHASE_ORDER_UPDATE_ERROR = 11001; // 采购单更新失败
    int PURCHASE_ORDER_STOCKIN_ERROR = 11111; // 采购单[{0}]过仓失败
    int PURCHASE_ORDER_SKU_JMCODE_UNIQUE = 11002; // 商品编码[{0}]重复
    int SUPPLIER_SKU_CODE_EXISTS = 20024; // //同品牌同销售模式下货号不能重复,品牌:[{1}],销售模式:[{2}],货号:[{2}]
    int PRODUCT_TYPE_ERROR = 20100; // 数据行${index}:[${object}]中数据有误：${value}

    int LINE_SKU_NOT_EXISTS2 = 20025; // 明细行中第[{0}]行商品skucode:[{1}]对应的SKU不存在
    int PROMOTION_ACTIVITY_IS_EXISTS = 21103; // 促销名称已存在
    int PROMOTION_ACTIVITY_RULE_IS_NOT_EXISTS = 21104; // 促销规则不存在
    int PROMOTION_ACTIVITY_PRODUCT_IS_NOT_ISGIFTS = 21105; // 该商品不属于赠品
    int PROMOTION_ACTIVITY_PRODUCT_AND_GIFT_NOT_EXISTS = 21106;// "是买赠类型的话，规则完善这边（绑定主卖品，必须绑定赠品加这个校验)"
    int PRODUCT_TAGS_IS_EXISTS = 21107; // 商品标签名称不能重复
    int PROMOTION_ACTIVITY_PRODUCT_NOT_EXISTS = 21108;// 买赠类型:必须绑定主卖品
    int PROMOTION_ACTIVITY_PRODUCT_AND_GIFT_NOT_NULL = 21109;// 主卖品和赠品列不能为空
    int PROMOTION_ACTIVITY_GIFT_NOT_NULL = 21110;// 赠品列不能为空
    int PROMOTION_RULE_LEVEL_IS_EXISTS = 21111;// "规则等级不能重复
    int OUSMSCONFIG_OUID_SYSTYPE_IS_EXISTS = 21112;// "组织下不能配置重复的短信类型!"
    int SMSTEMPLATE_SYSTYPE_IS_EXISTS = 21113;// 短信类型已经存在
    int REFUND_APPLICATION_REPEAT = 21214;// [{0}]下子单据[{1}]已存在有效的退款
    int OUTER_ORDER_CODE_EXIST = 21001; // 平台订单编码[{0}]已存在
    int PO_DISCARD_FAIL = 20027;// 采购单作废失败
    int PO_DISCARD_STATUS_FAIL = 20028;// 采购单已作废
    int PO_DISCARD_STATUS_FAIL2 = 20029;// 采购单作废失败，新建或审批不通过状态才能作废
    int SALES_ORDER_CODE_NOT_EXIST = 21100; // 销售单不存在code:[{0}]
    int SALES_ORDER_STATUS_IS_NOT_CREATED = 21101; // 销售单状态不是新建状态
    int STORE_PRODUCT_ATTACH_ERROR1 = 21201;// 销售模式不规范
    int STORE_PRODUCT_ATTACH_ERROR2 = 21202;// 查无此商品
    int SALES_ORDER_CHOOSE_TRANSPORTATOR = 21203;// 所选订单中有支付方式为[货到付款]的单据，但所选物流不支持[货到付款],请重新选择！
    int REFUND_APPLICATION_REPEAT1 = 21223;// 该订单{0}状态非取消,作废,不能生成未发货退款单
    int REFUND_APPLICATION_HAS_NO_SO_RECORD = 21500;// 淘宝订单[{0}]未找到可做退款申请的子订单
    int REFUND_APPLICATION_HAS_NO_RA_RECORD = 21501;// 退货单[{0}]未找到可做退款申请的子退货单
    int REFUND_APPLICATION_RA_TYPE_UPDATE = 21502;// 只能修改为同类型退款，如：退货退款（正常发货）、退货退款（延迟发货）
    int REFUND_APPLICATION_SO_TYPE_UPDATE = 21503;// 只能修改为同类型退款，如：未发货退款（正常取消）、未发货退款（延迟发货取消）
    int REFUND_APPLICATION_OTHER_TYPE_UPDATE = 21504;// 该类型的退款不能更改退款原因
    int REFUND_APPLICATION_CREATE_NO_REFUND = 21505;// 创建失败，相关费用信息不能为空
    int REFUND_APPLICATION_UPDATE_ERROR = 21506;// 该退款申请状态已发生改变，保存失败
    int REFUND_APPLICATION_SEND_ERP_ERROR = 21507;// 提交失败，退款单[{0}]关联的退货申请[{1}]还未同步到ERP
    int REFUND_APPLICATION_CREATE_ERROR = 21508;// 退款关联的订单[{0}]状态是作废状态，不能创建该类型的退款申请
    int REFUND_APPLICATION_CREATE_CLAIM_ERROR = 21509;// 退款关联的订单[{0}]状态不是在途、关闭或完成状态，不能创建索赔退款申请
    int REFUND_APPLICATION_CREATE_SERVICE_RF_ERROR = 21510;// 退款关联的订单[{0}]状态是作废状态，不能创建服务退款申请
    int BASEINFO_ERROR_WAREHOUSE_NOT_EXIST = 21102; // 基础信息维护错误，没有绑定仓库

    int SALES_ORDER_TRANSPORTATOR_IS_SUPPORT_COD = 21204;// 所选物流服务商不支持[货到付款],请重新选择！
    int SALES_ORDER_CANCEL_REFUND_EXIST = 21205;// 该订单存在退货退款申请，无法复制/作废

    int SALES_ORDER_CHOOSE_TRANSPORTATOR_STATUS_ERROR = 21206;// [{0}]状态不支持，只能操作状态为"已提交"的订单！
    int SALES_ORDER_CHOOSE_TRANSPORTATORINFO = 21207;// [{0}]支付方式为[货到付款],但所选物流不支持[货到付款],请重新选择！
    int SALES_ORDER_INFO_ERROR = 21208;// 数据异常，[{0}]订单发货信息为空！
    int SALES_ORDER_CREATE_TASK_ERROR = 21209;// 工作流创建失败
    int USER_CREATE_TASK_NO_AUTHORITY = 21210;// 用户[{0}]无创建工作流权限
    int SO_MOBILE_PHONE_NOT_NULL = 31310;// 该订单发货信息手机和收货人电话不能全部为空
    int SO_PROMOTION_NOT_NULL = 41310;// 订单对应的活动促销不存在,请维护活动商品
    
    int SO_OMNICHANNEL_PARAM = 66666;// 此店铺开启了全渠道拆单，但平台全渠道标示为空
    
    int SO_QUERY_STORE_ERROR = 67777; //查询不到门店
    
    int SALES_ORDER_ORDERTYPE_ERROR = 21221;// 订单类型不是店铺默认订单类型
    int SALES_ORDER_POLICY_ERROR = 21211;// 订单类型跟支付方式不匹配
    int SALES_ORDER_BATCH_UPDATE_ISNEEDINVOICE_STATUS_ERROR = 21212;// [{0}]状态不支持，只能操作状态为"新建"或"已提交"或"挂起待确认",且当前节点非[待系统过仓],非[过仓失败待处理],非[待财务拆票]"的订单！
    int SALES_ORDER_O2O_CANCEL_FAIL = 21213;// 外部系统订单取消失败
    int SALES_ORDER_O2O_CANCEL_FAIL_1 = 21243;// 订单已部分发货,不允许取消
    int SKU_IMPORTER_UPC_IS_NULL = 300001;// 导入商品UPC为空
    int SKU_IMPORTER_SKU_EXIST = 300002;// SKU已经存在
    int SKU_IMPORTER_SKU_CODE_NULL = 10000003;// SKU编码不存在

    int RETURN_APPLICATION_IS_NULL = 120001;// 退货单不存在
    int REFUND_APPLICATION_IS_NOT_NULL = 1220001;// 退换货单[{0}]存在非作废的退款单,不允许作废
    int RETURN_REFUND_APPLICATION_IS_NOT_NULL = 1220002;// 当前退货单有有效的退款单[ {0} ]，不能取消
    int REFUND_APPLICATION_FINISH = 12200012;// 退换货单[{0}]节点非[待外部系统入库反馈],不允许确认完成
    int RETURN_APPLICATION_WORK_TASK_ERROR = 120002;// 退货单[{0}]流程扭转失败
    int ROOT_SO_CAN_NOT_CREATE_RETURN_APP = 120003;// 退货订单{soCode}不存在或该订单当前状态非[在途]、[交易关闭]、[已完成]状态,不能进行退换货操作
    int ROOT_SO_CAN_NOT_CREATE_RETURN_APP_1 = 120023;// 退货订单{soCode}不存在或该订单当前状态非[在途]、[已完成]状态,不能进行退换货操作
    int ROOT_SO_CAN_NOT_CREATE_RETURN_APP_NOWARE = 120013;// 退货订单{soCode}不存在或该订单当前状态非[在途]、[已完成]状态,不能进行无仓模式退换货操作
    int EXISTS_UN_CANCEL_AND_UN_FINISHED_RA = 120004;// 退货订单{soCode}当前对应有非作废、非完成、非换货已出库的退换货申请,此时不能进行退换货操作
    int RA_UNPAD_MUST_FULLY_RETURN = 120014;// 退货订单{0}不是全额付款，必选全部退货
    int NOT_ROOT_SO_CAN_NOT_CREATE_RA = 120005;// 退货订单{soCode}是退换货所产生的新订单,而非买家最初下的原始订单,不能针对该类订单进行退换货
    int NOT_FULLPAYED_SO_CAN_NOT_CREATE_RA = 120010;// 销售单[{0}]未完成全额付款，无法创换货单
    int SO_NOT_EXIST = 120006; // 订单{soCode}不存在
    int SO_NOT_TRACK_NO = 222222; // 退回单号必填
    int SOCODES_NOT_EXIST = 121006; // 单元格[{0}]订单号[{1}]不存在
    int RFCODES_NOT_EXIST = 121206; // 单元格[{0}]退款申请编码[{1}]不存在
    int NOT_SET_WHCUSTOMER = 121007; // 单元格[{0}]商品[{1}]未设置仓储客户
    int PROD_WHCUSTOMER_NOTEQUAL_SHOP = 121008; // 单元格[{0}]商品[{1}]设置仓储客户与当前店铺不一致
    int RETURN_QTY_CAN_NOT_BE_ZERO = 120007; // 退货商品数量不能为0
    int RETURN_QTY_CAN_NOT_MORE_THAN_SL_QTY = 120008;// 退货数量不能大于订单明细中购买数量
    int RETURN_APP_STATUS_CAN_NOT_INBOUND = 120009; // 退换货申请[{0}]当前状态非[新建已提交],不能进行入库操作
    int RETURN_APP_NOT_EXISTS = 120030; // 找不到对应的退换货申请[{0}],操作失败

    int SALES_ORDER_TRANSIT_TO_CLOSED_ERROR = 120031;// 源订单工作流流转至交易关闭失败
    int SO_HAS_FULLY_RETURN = 120032; // 原订单[{0}]已全部退货,不能再进行退换货处理
    int SALES_ORDER_IS_NOT_ISEXITS = 120033; // 订单数据异常,ID:[{0}]不存在
    int SO_DELIVER_IS_NOT_ISEXITS = 120034; // 订单发货信息数据异常,ID:[{0}]不存在
    int SO_TRANS_IS_NOT_ISEXITS = 120035; // 物流服务商数据异常,ID:[{0}]不存在
    int SO_RUNTIME_UPDATE = 120038; // 订单[{0}]已通过导入方式创建过退货申请,不允许再次通过该功能操作

    int WMS_SO_CANCEL_ERROR = 120051;// WMS取消订单失败

    int SO_CANCEL_WAIT = 120052;// 取消申请成功,等待反馈

    int RETURN_APPLICATION_IS_NOT_FINISH = 120042; // [{0}]原退换货单已作废，无法进行退款操作

    int SO_INVOICE_TAX_IS_EXIST = 120100; // 订单发票已存在，无法重复开票
    int SO_INVOICE_TAX_AMT_RULE_NOT_DEFINE = 120101; // 生成发票数据计算开票金额时，开票金额计算规则[{0}]未定义

    int PURCHASE_ORDER_CREATE_TASK_ERROR = 212091;// 工作流扭转失败
    int PURCHASE_ORDER_UPDATE_STATUS_ERROR = 212092; // 采购单状态只有在新建和驳回才能修改，更新失败
    int RETURN_PURCHASE_ORDER_UPDATE_STATUS_ERROR = 212093; // 负向采购单状态只有在新建未提交才能修改，更新失败

    int SO_INVOICE_ADD_RA_EXIST_ERROR = 32000; // 订单存在非取消的退换货申请，无法补开发票[{0}]

    int SHOP_NAME_UPDATE_EXIST = 25106; // 店铺名称[{0}]已经存在，无法更新

    int SO_CAN_NOT_CREATE_RETURN_NO_WARE = 130003;// 销售订单[{0}]非无仓模式创建，无法创建无仓模式退货单
    int SO_CAN_NOT_CREATE_RETURN_WARE = 130004;// 销售订单[{0}]无仓模式创建，无法创建非无仓模式退货单
    int ROOT_SO_CAN_NOT_CREATE_RETURN_APP_NO_WARE = 130005;// 销售订单{soCode}当前状态非[在途]、[交易关闭]、[已完成]状态,不能进行退换货操作
    int STORE_JUMBOMART_INDIVIDUAL_TOURIST_IS_NOT_FULLPAYMENT = 130006;// 订单[{0}],[尊宝网散客订单]未全额收款不能执行审批操作


    int WMS_OUT_BOUND_ERROR = 86001;// 调用WMS出库接口失败，请联系后台

    int EXCEL_READ_ERROR = 86002;// EXCEL读取内容失败，内容为空，或数据格式不正确
    /**
     * 规则内容为空
     */
    int RULE_CONTENT_EMPTY = 86004;
    /**
     * 规则名称为空
     */
    int RULE_NAME_EMPTY = 86005;
    /**
     * 规则名称重复
     */
    int RULE_NAME_DUPLICATE = 86006;
    int SO_INVOICE_DATA_ERROR = 86003;// 导入数据存在问题，请查看数据[{0}]
    int RETURN_NO_WARE_CREATE_SO_RO_ERROR = 860031;// 无仓模式部分退货创SO,RO失败，请查看数据

    int RETURN_PO_WORKFLOW_ERROR = 860032;// 调用WMS接口失败，请查看商品库存

    int INVALID_WORK_TASK_ERROR = 860033;// 工作流扭转失败，该用户没有操作权限
    int INVALID_ERP_RMI_ERROR = 860034;// 调用ERP接口失败，请联系系统管理员

    int INVALID_WORK_TASK_ACTOR = 900000; // 当前用户无该操作权限,若需要开通,请联系销售运营部分配对应职位
    int SLIP_TYPE_NOT_MATCH = 900001; // 当前单据与当前工作流不匹配
    int TRANSITION_NOT_EXISTS = 900002;// 所操作的工作流中没有该流转定义,请联系IT服务
    int WORK_FLOW_DEFINITION_ERROR = 900003;// 当前工作流未明确定义,请联系IT服务
    int WORK_FLOW_NODE_NOT_EXISTS = 900004;// 工作流节点未定义,请联系IT服务
    int WORK_FLOW_NOT_EXISTS = 900005;// 工作流未定义,请联系IT服务
    int FORK_TRANSITION_NOT_EXISTS = 900006;// Fork节点流转异常,请联系IT服务,工作流编码[{0}]/task编码[{1}]/开始节点[{2}]/当前节点[{3}]
    int WORK_FLOW_TASK_NOT_EXISTS = 900007;// 单据没有对应的工作流实例,工作流编码[{0}]
    int TASK_CURRENT_NODE_ERROR = 900008;// 当前节点已发生变化,当前工作流流转失败,请联系IT服务,工作流编码[{0}]/task编码[{1}]/开始节点[{2}]/当前节点[{3}]
    int TRANSITION_FLOW_FAIL = 900009;// 工作流流转路径异常,请联系IT服务

    final static String WORK_FLOW_CREATE_ERROR = "工作流创建失败，用户无权限操作！";
    final static String WORK_FLOW_CREATE_WAIT = "已向外包仓申请取消,等待外包仓取消确认！";
    final static String WORK_FLOW_NOT_EXIST = "工作流不存在，请联系后台！";

    final static String UnionWorkSpaceSoCode = "相关单据号";
    final static String BeforeWarehouse = "原仓库";
    final static String CurrentWarehouse = "当前仓";
    final static String user = "操作人";
    final static String userDatetime = "操作时间";
    final static String AUTO_REFUND_APPLICATION_CREATE_SUCCESS = "系统自动创建退款成功，退款编号：";

    int PROMOTION_RULL_NOT_EXIST_ERROR = 1900008;// 促销活动不存在
    int PROMOTION_PRODUCT_NOT_EXIST_ERROR = 1900009;// 促销活动对应促销商品不存在
    int PROMOTION_GIFT_NOT_EXIST_ERROR = 1900010;// 促销活动对应促销赠品不存在

    int COMPANY_SHOP_TRANSPATOR_IS_NULL = 2900010;// 销售单必须维护物流服务商【{0}】
    int TRANSPATOR_IS_NULL = 2900011;// 请选择物流服务商

    int SALES_ORDER_WORKFLOW_NODE_IS_NOT_CANCEL = 1900018;// 销售订单当前不是取消节点，无法复制作废，当前节点为【{0}】，请联系后台

    public static final int WORKFLOW_NODE_NO_MISMATCHING = 340000; // {0}非【{1}】节点，此操作无法继续
    public static final int INTERFACE_RMI_PACS_RESPONSE_MSG = 341001; // PACS反馈结果：{0}

    public static final int INTERFACE_RMI_REMOTE_ACCESS_EXCEPTION = 380001; // 不能与远程PACS系统建立可用的链接
    public static final int INTERFACE_RMI_REQUEST_PARAM_EXCEPTION = 380002; // 请求PACS接口【{0}】参数有误，错误原因：{1}
    /** 请求PACS接口异常，请联系PACS相关人员 */
    public static final int INTERFACE_RMI_REQUEST_UNKNOWN_EXCEPTION = 380003;
    public static final int INTERFACE_RMI_O2O_SO_MODIFY_FAIL = 390001; // 调用外部接口失败
    public static final int INTERFACE_RMI_SO_MODIFY_FAIL = 390011; // 调用WMS接口失败,原因[{0}]
    public static final int SO_SUGGESTED_TRANSPORTATOR_NOT_EXIST = 390101; // WMS为订单【{0}】建议物流商【{1}】不存在！
    public static final int SO_DELIVER_INFO_NOT_EXIST = 390102; // 销售订单【订单编号：{0}】对应的发货信息不存在！
    public static final int SO_WORK_TASK_FLOW_NOT_EXIST = 390201; // 销售订单【订单编号:{0}】没有对应的工作处理流！
    public static final int SO_WORK_TASK_FLOW_NOT_WAITING_TRANS_CONFIRM_NODE = 390202; // 销售订单【订单编号:{0}】当前节点不是【待确认物流服务商】，处理失败，当前节点为【{1}】，请联系后台！
    public static final int SO_WORK_TASK_FLOW_FROM_TRANS_CONFIRM_NODE_TO_SYS_WH = 390203; // 销售订单【订单编号:{0}】扭转工作流：[待确认物流服务商]到[待系统过仓]失败
    public static final int SO_WORK_TASK_FLOW_NOT_WH_READY_NODE = 390204; // 销售订单【订单编号:{0}】当前节点不是【库房准备中】，处理失败，当前节点为【{1}】，请联系后台
    public static final int SO_WORK_TASK_FLOW_FROM_WH_PREPARE_NODE_TO_TRANS_CONFIRM = 390205; // 销售订单工作流扭转：[12.库房准备中]
    public static final int SO_BRANCH_WAREHOUSE_CODE_VALIDATE_ERROR_MSG = 400001; // 分仓编码错误【错误原因：{0}】
                                                                                  // -->
                                                                                  // [33.待确认物流服务商]失败
    public static final int SO_SPLIT_ERROR_MSG = 390300; // 拆单失败，原因：{0}
    public static final int SO_CODE_CAN_NOT_EMPTY = 390301; // 销售订单编号不能为空
    public static final int QUERY_CONDITION_INSTANCE_FAIL = 390302; // 查询条件实例化对象失败
    public static final int SUBMIT_TO_WH_QUEUE_ERROR_MSG = 390306; // 请选择需要提交到过仓队列的数据
    public static final int CS_SO_SUBMIT_TO_WH_QUEUE_ERROR_MSG = 390307; // 订单{0}挂起原因是以云栈订单原因挂起，不能手动提交！
    public static final int CS_SO_CANLE_ERROR_MSG = 390308; // 订单{0}挂起原因是以云栈订单原因挂起，不能取消！

    /**
     * EBS相关
     */
    int EBS_SUPPLIER_NAME_IS_EXIST = 490001; // 采购供应商编码已经存在
    public static final int EBS_COMPANYSHOP_APPLY_INFO_NOTFULL = 490002; // 店铺申请创建时信息不完整
    public static final int EBS_COMPANYSHOP_APPLY_PARENTNODE_INVALID = 490003; // 店铺申请创建时 父组织无效
    public static final int EBS_COMPANYSHOP_APPLY_NAMEORCODE_EXIST = 490004; // 店铺申请创建时 代码/名称已存在
    public static final int EBS_COMPANYSHOP_APPLY_FAILED = 490005;// 店铺申请失败
    public static final int EBS_COMPANYSHOP_CREATE_FAILED = 490006;// 店铺创建失败
    public static final int EBS_WELFARE_RECEIVE_CREATE_FAILED = 490007;// 福利领取创建失败
    public static final int EBS_IOAPPLY_CREATE_FAILED = 490008;// 申请创建失败
    int EBS_CUSTOMER_ADD_FAILE = 490011;// 客户添加失败
    int EBS_CUSTOMER_UPDATE_FAILE = 490012;// 客户更新失败
    int EBS_SUPPLIER_ADD_FAILE = 490013;// 供应商添加失败
    int EBS_CLIENTCREDIT_APPLY_FAILE = 40021;// 客户申请授信失败

    /**
     * 常用地址保存時信息不全
     */
    public static final int COMMON_ADDRESS_SAVE_INFO_NOTFULL = 500001;


    public static final int EBS_WELFARE_RECEIVE_APPLY_INFO_NOTFULL = 510001; // 福利领取申请创建时信息不完整
    public static final int EBS_WELFARE_RECEIVE_APPLY_INFO_NOCHOOSE_PRODUCT = 510002; // 福利领取申请创建时
                                                                                      // 未选择福利
    public static final int EBS_WELFARE_RECEIVE_APPLY_INFO_WELFARELINE_NOTFULL = 510003; // 福利领取申请创建时福利信息有误！
    public static final int EBS_WELFARE_RECEIVE_APPLY_NOTEXIST_WAREHOUSE = 510004; // 福利领取申请创建时仓库不存在！
    public static final int EBS_WELFARE_RECEIVE_APPLY_CANNOT_EDIT = 510005; // 当前申请状态已改变，不可编辑！

    public static final int SO_TRADE_EXPORT_CAN_NOT_MORE_THAN_SL_QTY = 1221001; // 销售订单-交易确认批量导入刷交易关闭不能超过200条数据
    public static final int OPERATION_BILL_TYPE_NOT_EXISTED = 600000; // 作业单类型[{0}]未定义，找不到对应的处理器
    public static final int SO_TOTAL_ACTUAL_NOT_EQUALS_SUM_OF_LINE_TOTAL = 1221004; // 订单金额异常，订单头上totalActual与订单行中实际行总计(total:扣除积分抵扣金额前实际行总计)不一致
                                                                                    // public static
                                                                                    // final int
                                                                                    // OPERATION_BILL_TYPE_NOT_EXISTED
                                                                                    // = 600000;
                                                                                    // //作业单类型[{0}]未定义，找不到对应的处理器
    public static final int REF_SLIP_CODE_NOT_EXISTED = 600001; // OMS中找不到对应的单据[{0}]
    public static final int NG_PO_STATUS_CANT_INBOUND = 600003; // 采购退货单[{1}]当前状态非审核通过，不允许执行出库操作!
    public static final int INTERFACE_NOT_EIXISTED = 600004; // 对应的接口[{0}]不存在，不允许该操作
    public static final int SHOP_AND_WAREHOSUE_NOT_BELONG_SAME_COMPANY = 600005; // 店铺[{0}]与仓库[{1}]不属于同一家公司，操作失败!

    public static final int WMS_INTERFACE_RETURN_ERROR = 600006; // WMS处理失败：[{0}]
    public static final int WMS_INTERFACE_NOT_EXISTED = 600007; // WMS对应接口不存在：[{0}]

    public static final int FYH_NOT_EXISTED_RELATED_OUTBOUND_BILL = 600010; // 费用化找不到相关的出库单据
    public static final int DJR_NOT_EXISTED_RELATED_OUTBOUND_BILL = 600016; // 送修入库找不到相关的出库单据
    public static final int FYH_QTY_OVER_RELATED_OUTBOUND_QTY = 600011; // 商品[{0}]当前费用化数量[{1}]已超出原出库单据当前未费用化数量[{2}]件
    public static final int DJR_QTY_OVER_RELATED_OUTBOUND_QTY = 600012; // 商品[{0}]当前等价入数量[{1}]已超出原出库单据当前出库数量[{2}]件
    public static final int CUSTOMER_NOT_EXISTED_CREDIT_BALANCE = 600013; // 当前客户[{0}][{1}]不存在授信额度，不能创建授信订单
    public static final int CUSTOMER_CREDIT_BALANCE_OVER = 600014; // 当前客户[{0}][{1}]授信额度余额为[{2}]，当前订单金额[{3}]已超出[{4}],创单失败
    public static final int ORDER_TYPE_NOT_SUPPORT_CREDIT_ORDER = 600015; // 当前订单类型不允许创建授信订单
    public static final int CUSTOMER_CREDIT_END_TIME_FAIL = 600017; // 当前客户[{0}][{1}]授信有效期为[{2}]，授信已失效，该客户当前不允许创建授信订单
    public static final int PURCHASE_ORDER_PAYMENT_TYPE = 700015; // 采购单商品限定为付款经销商品,[{0}]不允许导入
    public static final int PURCHASE_ORDER_NOT_PRINT = 700016; // 只能打印采购状态为【‘审批通过’，‘部分入库’，‘部分入库已关闭(不再入库)’，‘完成’】的数据
    public static final int CONNECT_TAOBAO_ERROR = 30001;
    // NIKE官方异常
    public static final int NIKE_CREATE_ORDER_DATE_EXCEPTION = 400000; // NIKE Create Order date
                                                                       // Exception
    public static final int WORK_FLOW_CREATE_ERROR2 = 200001;// 工作流创建失败[{0}],请联系后台

    public static final int CREATE_SO_ASSIGN_ERROR = 210001; // 创单失败,平摊金额存在误差,sum(行total)[{0}]不等于订单头上totalActual[{0}]

    public static final int NIKE_GLOBAL_RETURN_SKU_NOT_EXIST = 700001; // Nike Global
                                                                       // 退货单[{0}]中的商品sku[{1}]不存在
    public static final int CURTIME_CANNOT_CREATE_EBSSYN = 700002; // 当前时间oms正在同步信用余额，前后10分钟内不允许创建授信订单
    public static final int BILLTRANSINFO_ISEMPTY = 700003; // 销售出库反馈时，发货信息丢失！

    public static final int MQV5_MSG_OPERA_ERROR = 800001; // 接收消息处理失败！
    // VMI
    public static final int BRAND_LIMIT_THE_FUNCTION = 51;

    public static final int CLOUD_STACK_SO_MODIFY_ERROR = 80025;// 订单[{0}]为云栈订单，当前节点不能修改地址及物流服务商信息

    public static final int SOCODES_NOT_EXIST_THIS_SHOP = 131006; // 单元格[{0}]订单号[{1}]不存在当前店铺
    
    public static final int CONCURRENCY_PROCESS_ERROR = 4000001;//系统正在处理中，不用发起重复请求

}
