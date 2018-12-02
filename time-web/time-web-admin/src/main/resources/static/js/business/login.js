import {Validate} from '../framework/validate.js'
import {core} from '../framework/core.js'
import {CanvasAnimate} from '../plugin/canvas/canvas.js'
import {monster} from "../framework/monster.js";
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
    $(this).attr("src",core.constant.URL.CAPTCHA + "?time=" + Math.random(1))
})

//表单验证
const descriptor = {
    username: {
        required: true,
        message: core.constant.VALIDATION.ACCOUNT_RQUIRED
    },
    password: {
        required: true,
        message: core.constant.VALIDATION.PASSWORD_REQUIRED
    },
    captcha: {
        required: true,
        message: core.constant.VALIDATION.CAPTCHA_REQUIRED
    },
}

let validate = new Validate(descriptor, $("form"))
$("#submit").click(function () {
    if (validate.verify()) {
        http.request({
            url: core.constant.URL.LOGIN_AUTHENTICATION,
            data: $("form").serialize(),
            method: "post"
        }, function (res) {
            if (res.retCode === 200) {
                monster.tips.success({message:core.constant.MESSAGE.LOGIN.LOGIN_SUCCESS})
                return
            }
            if(res.retCode !== 500) {
                monster.tips.failure({message:core.constant.MESSAGE.LOGIN.CAPTCHA_EXPIRED})
            }
            $("input[name='captcha']").val("");
            captcha.attr("src", core.constant.URL.CAPTCHA + "?time=" + Math.random(1));
        }, this)
    }
});