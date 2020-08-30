package com.controller;

import com.model.Deliver;
import com.sevice.AsyncDemo;
import com.sevice.IDeliverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeliverController {
    @Resource
    IDeliverService deliverService;
    @Resource
    AsyncDemo asyncDemo;
    // 物流按"物流配送"返回页面所需的数据
    @RequestMapping("/delivers")
    public List<Deliver> showDeliver() {
        List<Deliver> delivers = deliverService.findDeliverByStatus("未派送");
        return delivers;
    }

    // 物流用户按"配送"铵钮所返回的数据
    @RequestMapping("/deliver/{id}")
    public List<Deliver> dispatch(@PathVariable("id") String id) {
        // 更新物流状态
        deliverService.updateDeliver(id, "派送中");
        // 向定单模块、商品模块、用户模块发送信息
        asyncDemo.sendMessage(id, "派送中");
        List<Deliver> delivers = deliverService.findDeliverByStatus("未派送");
        return delivers;
    }

    // 物流用户进入配送退回页面所需要的数据
    @RequestMapping("/deliverCancel")
    public List<Deliver> showCancel() {
        List<Deliver> delivers = deliverService.findDeliverByStatus("派送中");
        return delivers;
    }

    // 物流用户点击配送退回按钮
    @RequestMapping("/deliverCancel/{id}")
    public List<Deliver> deliverCancel(@PathVariable("id") String id) {
        deliverService.updateDeliver(id, "物流退回");
        asyncDemo.sendMessage(id, "用户退回");
        List<Deliver> delivers = deliverService.findDeliverByStatus("派送中");
        return delivers;
    }
}
