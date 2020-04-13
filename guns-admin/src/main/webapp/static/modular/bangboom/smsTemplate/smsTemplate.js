/**
 * 系统参数管理初始化
 */
var SmsTemplate = {
    id: "smsTemplateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SmsTemplate.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自增主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '短信模版代码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            // {title: '参数值', field: 'cfgValue', visible: true, align: 'center', valign: 'middle'},
            {title: '短信模版内容', field: 'content', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SmsTemplate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SmsTemplate.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加系统参数
 */
SmsTemplate.openAddCfg = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统参数',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/smsTemplate/smsTemplate_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统参数详情
 */
SmsTemplate.openCfgDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统参数详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/smsTemplate/smsTemplate_update/' + SmsTemplate.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统参数
 */
SmsTemplate.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/smsTemplate/delete", function (data) {
            Feng.success("删除成功!");
            SmsTemplate.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cfgId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询系统参数列表
 */
SmsTemplate.search = function () {
    var queryData = {};
    queryData['code'] = $("#code").val();
    // queryData['cfgValue'] = $("#cfgValue").val();
    SmsTemplate.table.refresh({query: queryData});
};

SmsTemplate.reset = function () {
    $("#code").val("");
    $("#content").val("");
    this.search();
};
$(function () {
    var defaultColunms = SmsTemplate.initColumn();
    var table = new BSTable(SmsTemplate.id, "/smsTemplate/list", defaultColunms);
    table.setPaginationType("server");
    SmsTemplate.table = table.init();
});
