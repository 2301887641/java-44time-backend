import {Validate} from '../framework/validate.js'
import {King} from '../framework/king.js'
import {CanvasAnimate} from '../plugin/canvas/canvas.js'
import {monster} from "../framework/monster.js";


$("#root").attr({
    width:document.body.offsetWidth,
    height:document.body.offsetHeight
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
    }
}

let validate = new Validate(descriptor, $("form"))
$("#submit").click(function () {
    monster.tips.failure({message:"修改成功水电费是否啥打法是否打算阿斯顿发送到发送到发送"})
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
