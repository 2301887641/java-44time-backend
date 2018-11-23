import {Validate} from '../framework/validate.js'
import {King} from '../framework/king.js'
import {CanvasAnimate} from '../plugin/canvas/canvas.js'
import {monster} from "../framework/monster.js";
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

//验证码点击
let captchaObject = {
    url: "/captcha",
    refresh: function () {
        return this.url + "?=" + Math.random(1)
    }
}


$(".captcha").click(function () {
    $(this).attr("src", captchaObject.refresh())
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
    },
}

let validate = new Validate(descriptor, $("form"))
$("#submit").click(function () {
    if (validate.verify()) {
        King.http({
            url: "/authentication/form",
            data: $("form").serialize(),
            method: "post"
        }, function (res) {
            if (res.retCode === 200) {
                monster.tips.success({message:"登陆成功,即将进行跳转"})
                return
            }
            if(res.retCode !== 500) {
                monster.tips.failure({message:"验证码已过期,请重新输入"})
            }
            $("input[name='captcha']").val("");
            $(".captcha").attr("src", captchaObject.refresh());
        }, this)
    }
});
