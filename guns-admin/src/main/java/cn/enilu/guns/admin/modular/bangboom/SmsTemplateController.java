package cn.enilu.guns.admin.modular.bangboom;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.bean.annotion.core.BussinessLog;
import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.dictmap.CfgDict;
import cn.enilu.guns.bean.entity.cpic.SmsTemplate;
import cn.enilu.guns.bean.entity.system.Cfg;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.bean.vo.query.SearchFilter;
import cn.enilu.guns.dao.cpic.SmsTemplateRepository;
import cn.enilu.guns.service.system.CfgService;
import cn.enilu.guns.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CfgController
 *
 * @author enilu
 * @version 2018/8/9 0009
 */

@Controller
@RequestMapping("/smsTemplate")
public class SmsTemplateController extends BaseController {
    @Autowired
    private CfgService cfgService;

    @Autowired
    private SmsTemplateRepository repository;

    private static String PREFIX = "/bangboom/smsTemplate/";
    /**
     * 跳转到参数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "smsTemplate.html";
    }

    /**
     * 跳转到添加参数
     */
    @RequestMapping("/smsTemplate_add")
    public String add() {
        return PREFIX + "smsTemplate_add.html";
    }

    /**
     * 跳转到修改参数
     */
    @RequestMapping("/smsTemplate_update/{cfgId}")
    public String update(@PathVariable Integer cfgId, Model model) {
//        Cfg cfg = cfgService.get(cfgId);
        SmsTemplate cfg = repository.findById(cfgId).get();
        model.addAttribute("item",cfg);
        return PREFIX + "smsTemplate_edit.html";
    }

    /**
     * 获取参数列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
//        Page<Cfg> page = new PageFactory<Cfg>().defaultPage();
//        if(StringUtils.isNotEmpty(cfgName)){
//            page.addFilter(SearchFilter.build("cfgName", SearchFilter.Operator.LIKE, cfgName));
//        }
//        if(StringUtils.isNotEmpty(cfgValue)){
//            page.addFilter(SearchFilter.build("cfgValue", SearchFilter.Operator.LIKE, cfgValue));
//        }
//        page = cfgService.queryPage(page);
//        return packForBT(page);
        return repository.findAll();
    }

    /**
     * 新增参数
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "添加参数", key = "cfgName",dict = CfgDict.class)
    public Object add(SmsTemplate smsTemplate) {
       repository.save(smsTemplate);
        return SUCCESS_TIP;
    }

    /**
     * 删除参数
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除参数", key = "cfgId",dict = CfgDict.class)
    public Object delete(@RequestParam Integer cfgId) {
//        cfgService.delete(cfgId);
        repository.delete(repository.findById(cfgId).get());
        return SUCCESS_TIP;
    }

    /**
     * 修改参数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "编辑参数", key = "cfgName",dict = CfgDict.class)
    public Object update(SmsTemplate cfg) {
//       cfgService.update(cfg);
        repository.save(cfg);
        return SUCCESS_TIP;
    }

    /**
     * 参数详情
     */
    @RequestMapping(value = "/detail/{cfgId}")
    @ResponseBody
    public Object detail(@PathVariable("cfgId") Integer cfgId) {
//        return cfgService.get(cfgId);
        return repository.findById(cfgId).get();
    }

}