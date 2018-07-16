package com.time.article.admin.controller.business.permission;

import com.time.article.core.message.result.Result;
import com.time.article.service.api.business.permission.ResourceService;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public Result save(ResourceDto resourceDto){
        resourceService.insert(resourceDto);
        return Result.success();
    }

    @GetMapping("/getParent")
    public Result list(){
        ResourceDto resourceDto = new ResourceDto();
        return null;
//        resourceDto.set
    }
}
