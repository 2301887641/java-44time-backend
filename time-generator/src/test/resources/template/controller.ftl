package ${controllerTargetPackage};

import com.time.article.admin.annotation.Log;
import com.time.article.core.message.result.Result;
import com.time.article.core.utils.ValidatorUtils;
import com.time.article.dao.enums.business.log.LogEnum;
import ${serviceTargetPackage}.${serviceName};
import ${dtoCriteriaTargetPackage}.${dtoCriteriaName};
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ${controllerComment}
 *
 * @author ${author} on ${createTime}
 */
@RestController
@RequestMapping("${routerName}")
public class ${controllerName} {
    @Autowired
    private ${serviceName} ${serviceName?uncap_first};

    @GetMapping
    @Log("查询${controllerComment}")
    public Result index(${dtoCriteriaName} ${dtoCriteriaName?uncap_first}) {
        return Result.success(${serviceName?uncap_first}.converterToTree(resourceService.getList(resourceCriteriaDto)));
    }

    @Log("根据id查询${controllerComment}")
    @GetMapping("/{id}")
    public Result info(@PathVariable Integer id) {
        return Result.success(${serviceName?uncap_first}.getById(id));
    }

    @Log(value = "添加${controllerComment}", type = LogEnum.LOG_UPDATE)
    @PostMapping
    public Result save(@Valid ResourceDto resourceDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }
        return Result.success(${serviceName?uncap_first}.insert(resourceDto));
    }

    @Log(value = "修改${controllerComment}", type = LogEnum.LOG_UPDATE)
    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @Valid ResourceDto resourceDto, BindingResult result) {
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }
        resourceDto.setId(id);
        return Result.success(${serviceName?uncap_first}.treeUpdate(resourceDto));
    }

    @Log(value = "删除${controllerComment}", type = LogEnum.LOG_DELETE)
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(${serviceName?uncap_first}.treeDelete(id));
    }
}