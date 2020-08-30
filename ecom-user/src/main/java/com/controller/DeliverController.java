package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class DeliverController {
    @Resource
    RestTemplate restTemplate;
    private final static String PROVIDE = "ecom-deliver/";
    // 返回物流配送页面
    @GetMapping("/delivers")
    public String showDeliver(Map<String, Object> map) {
        List<Object> delivers = restTemplate.getForObject("http://" + PROVIDE + "delivers", List.class);
        map.put("delivers", delivers);
        return "deliverpages/deliver/delivers";
        //return "deliverpages/deliver/abc";
    }

    @PutMapping("/deliver/{id}")
    public String dispatch(Map<String, Object> map, @PathVariable("id") String id) {
        List<Object> delivers = restTemplate.getForObject("http://" + PROVIDE + "deliver/" + id, List.class);
        map.put("delivers", delivers);
        return "deliverpages/deliver/delivers";

    }

    @GetMapping("/deliverCancel")
    public String showCancel(Map<String, Object> map) {
        List<Object> delivers = restTemplate.getForObject("http://" + PROVIDE + "deliverCancel", List.class);
        map.put("delivers", delivers);
        return "deliverpages/deliver/delivercancel";
    }

    // 物流员点击"配送退回"
    @PutMapping("/delivercancel/{id}")
    public String deliverCancel(Map<String, Object> map,
                                @PathVariable("id") String id){
        List<Object> delivers = restTemplate.getForObject("http://" + PROVIDE + "deliverCancel/" + id, List.class);
        map.put("delivers", delivers);
        return "deliverpages/deliver/delivercancel";
    }
}
