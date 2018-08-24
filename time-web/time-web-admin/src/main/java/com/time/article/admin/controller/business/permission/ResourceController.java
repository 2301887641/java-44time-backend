package com.time.article.admin.controller.business.permission;

import com.time.article.admin.annotation.Log;
import com.time.article.core.message.result.Result;
import com.time.article.core.utils.ValidatorUtils;
import com.time.article.dao.enums.business.log.LogEnum;
import com.time.article.service.api.business.permission.ResourceService;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 资源
 *
 * @author suiguozhen on 18/07/12
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping
    @Log("查询资源")
    public Result index(ResourceCriteriaDto resourceCriteriaDto) {
        return Result.success(resourceService.converterToTree(resourceService.getList(resourceCriteriaDto)));
    }

    @Log("根据id查询资源")
    @GetMapping("/{id}")
    public Result info(@PathVariable Integer id) {
        return Result.success(resourceService.getById(id));
    }

    @Log(value = "添加资源", type = LogEnum.LOG_UPDATE)
    @PostMapping
    public Result save(@Valid ResourceDto resourceDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }
        return Result.success(resourceService.insert(resourceDto));
    }

    @Log(value = "修改资源", type = LogEnum.LOG_UPDATE)
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @Valid ResourceDto resourceDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }
        resourceDto.setId(id);
        return Result.success(resourceService.treeUpdate(resourceDto));
    }

    @Log(value = "删除资源", type = LogEnum.LOG_DELETE)
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(resourceService.treeDelete(id));
    }
}
