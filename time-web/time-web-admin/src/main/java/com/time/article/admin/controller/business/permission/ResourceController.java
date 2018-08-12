package com.time.article.admin.controller.business.permission;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.ValidatorUtils;
import com.time.article.service.api.business.TestService;
import com.time.article.service.api.business.permission.ResourceService;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 资源
 * @author suiguozhen on 18/07/12
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private TestService testService;

    @GetMapping
    public Result index(ResourceCriteriaDto resourceCriteriaDto) {
        testService.save();
        return null;
//        return Result.success(resourceService.converterToTree(resourceService.getList(resourceCriteriaDto)));
    }

    @GetMapping("/{id}")
    public Result info(@PathVariable Integer id){
        testService.add();
        return Result.success(resourceService.getById(id));
    }

    @PostMapping
    public Result save(@Valid ResourceDto resourceDto, BindingResult result){
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }

        resourceService.insert(resourceDto);
        return Result.success();
    }

    @PutMapping
    public Result edit(@Valid ResourceDto resourceDto,BindingResult result){
        if(result.hasErrors()){
            return ValidatorUtils.validateHasError(result);
        }
        resourceService.update(resourceDto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        resourceService.delete(id);
        return Result.success();
    }

}
