package com.time.article.admin.controller.business.permission;

import com.time.article.core.message.result.Result;
import com.time.article.service.api.business.permission.ResourceService;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suiguozhen on 18/07/12
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/doTreegrid")
    public Result doTreegrid(){
        return null;
    }


    @RequestMapping("/add")
    public Result doSave(ResourceDto resourceDto){
        resourceService.insert(resourceDto);
        return Result.success();
    }
}
