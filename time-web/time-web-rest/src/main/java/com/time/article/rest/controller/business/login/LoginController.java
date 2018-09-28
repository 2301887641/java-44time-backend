package com.time.article.rest.controller.business.login;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.ValidatorUtils;
import com.time.article.service.dto.business.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author suiguozhen on 18/07/03
 */
@Api(tags = {"后台登陆、验证码"}, description = "LoginController")
@RestController
public class LoginController {

}
