import {Validate} from '../framework/validate.js'
import {King} from '../framework/king.js'
import {CanvasAnimate} from '../plugin/canvas/canvas.js'

canvas()
function canvas() {
    //画布背景
    var root = document.querySelector("#root");
    var a = new CanvasAnimate(root, {
        length: 20,
        clicked: false,
        moveon: true
    });
    a.Run();
}


//验证码点击
let captchaUrl = "/captcha"
$(".captcha").click(function () {
    $(this).attr("src", captchaUrl + "?=" + Math.random(1))
})

//表单验证
const descriptor = {
    username: {
        required: true,
        message: "请输入账号"
    },
    password: {
        required: true,
        message: "请输入密码"
    },
    captcha: {
        required: true,
        message: "请输入验证码"
    }
}

let validate = new Validate(descriptor, $("form"))

$("#submit").click(function () {
    if (validate.verify()) {
        King.http({
            url: "/authentication/form",
            data: $("form").serialize(),
            method: "post"
        }, function (res) {
            console.log(res)
        }, this)
    }
});