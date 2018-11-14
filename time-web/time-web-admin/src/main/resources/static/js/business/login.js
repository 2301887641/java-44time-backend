import {Validate} from '../framework/validate.js'
import {King} from '../framework/king.js'

$(background)

//粒子背景特效
function background() {
    $('body').particleground({
        dotColor: 'rgba(255,255,255,0.6)',
        lineColor: 'rgba(200,200,200,0.4)',
        lineWidth: 2.2,
        particleRadius: 10,
        proximity: 120,
        density: 16300,
        minSpeedX: 0.2,
        minSpeedY: 0.2,
        maxSpeedX: 0.2,
        maxSpeedY: 0.2
    });
}

const descriptor = {
    username: {
        required: true,
        message: "请输入账号"
    },
    password: {
        required: true,
        message: "请输入密码"
    },
    captcha:{
        required:true,
        message:"请输入验证码"
    }
}

let validate = new Validate(descriptor, $("form"))

$("#submit").click(function() {
    if (validate.verify()) {
        King.http({
            url:"/authentication/form",
            data:$("form").serialize(),
            method:"post"
        },function(res){console.log(res)},this)
    }
});