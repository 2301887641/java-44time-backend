import {Validate} from '../framework/validate.js'
import {Core} from '../framework/core.js'
import {CanvasAnimate} from '../plugin/canvas/canvas.js'
import {Monster} from "../framework/monster.js";
import {http} from "../framework/http.js";
//背景全屏
$("#root").attr({
    width: document.body.offsetWidth,
    height: document.body.offsetHeight
})

canvas()

function canvas() {
    //画布背景
    let root = document.querySelector("#root"),
        canvas = new CanvasAnimate(root, {
            length: 20,
            clicked: false,
            moveon: true
        });
    canvas.Run();
}

let captcha=$(".captcha");

//验证码点击
captcha.click(function () {
    $(this).attr("src",Core.constant.URL.CAPTCHA + "?time=" + Math.random(1))
})

//表单验证
const descriptor = {
    username: {
        required: true,
        message: Core.constant.VALIDATION.ACCOUNT_RQUIRED
    },
    password: {
        required: true,
        message: Core.constant.VALIDATION.PASSWORD_REQUIRED
    },
    captcha: {
        required: true,
        message: Core.constant.VALIDATION.CAPTCHA_REQUIRED
    },
}

let validate = new Validate(descriptor, $("form"))
$("#submit").click(function () {
    if (validate.verify()) {
        http.request({
            url: Core.constant.URL.LOGIN_AUTHENTICATION,
            data: $("form").serialize(),
            method: "post"
        }, function (res) {
            if (res.retCode === Core.constant.MESSAGE.HTTP.SUCCESS) {
                Monster.tips.success({message:Core.constant.MESSAGE.LOGIN.LOGIN_SUCCESS})
                return
            }
            if(res.retCode !== Core.constant.MESSAGE.HTTP.ERROR) {
                Monster.tips.failure({message:Core.constant.MESSAGE.LOGIN.CAPTCHA_EXPIRED,closeable:true})
            }
            $("input[name='captcha']").val("");
            captcha.attr("src", Core.constant.URL.CAPTCHA + "?time=" + Math.random(1));
        }, this)
    }
});