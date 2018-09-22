package com.time.article.rest.controller.business.login;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suiguozhen on 18/07/03
 */
@Api(tags = {"后台登陆、验证码"}, description = "LoginController")
//@RestController
public class LoginController {
//    @Autowired
//    private CasRestFormClient casRestFormClient;

//    @Autowired
//    private JwtGenerator generator;



//    @Value("${shiro.serviceUrl}")
//    private String serviceUrl;

    /**
     * 验证码常量
     */
    private static final String CAPTCHA = "captcha";

    /**
     * 登录操作
     * 登录后的操作需要
     * like this: http://localhost:8081/user/1?client_name&jwt&token=eyJjdHkiOiJKV1QiLC
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "rest登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,
                    dataType = "String", paramType = "path"),
            @ApiImplicitParam(name="password",value="密码",required=true,
                    dataType="String",paramType = "path"
            ),
            @ApiImplicitParam(name="captcha",value = "验证码",required = true,
                    dataType = "String",paramType = "path"
            ),
            @ApiImplicitParam(name="client_name",value="客户端",required = true,
                    dataType = "String",paramType = "path",defaultValue = "jwt"
            )
    })
    @PostMapping("/restLogin")
    @ResponseBody
    public Result restLogin(
            HttpServletRequest request,
            HttpServletResponse response,
//            @Valid SimplePrincipal simplePrincipal,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ValidatorUtils.validateHasError(result);
        }
        /*验证码比对*/
//        String captcha = (String) request.getSession().getAttribute(CAPTCHA);
//        if (!StringUtils.equals(captcha, simplePrincipal.getCaptcha())) {
//            return Result.failed(RestCodeEnum.CAPTCHA_ERROR.getCode(), RestCodeEnum.CAPTCHA_ERROR.getInfo());
//        }
//        Map<String, Object> map = new HashMap<>(16);
//        J2EContext context = new J2EContext(request, response);
//        final ProfileManager<CasRestProfile> manager = new ProfileManager(context);
//        final Optional<CasRestProfile> profile = manager.get(true);
//        获取ticket
//        TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceUrl, profile.get(), context);
//        根据ticket获取用户信息
//        final CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceUrl, tokenCredentials, context);
//        String userId = casProfile.getId();
//        生成jwt token
//        String token = generator.generate(casProfile);
//        map.put("token", token);
//        map.put("userId",casProfile.getId());
//        request.getSession().setAttribute(Constants.SESSION_USER_ID,userId);
//        return Result.success(map);
        return null;
    }
}
