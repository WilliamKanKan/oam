package com.sztus.oam.employee.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.oam.employee.service.RoleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleSearchService roleSearchService;
    @GetMapping(value = "/search/page/{pageId}")
    public JSONObject searchRoleForAll(@PathVariable("pageId") Long pageId, @RequestParam Long pageSize){
        return roleSearchService.searchRoleForAll(pageId,pageSize);
    }
}
