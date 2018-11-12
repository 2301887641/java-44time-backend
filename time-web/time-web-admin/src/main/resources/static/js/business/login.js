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
    }
}

let validate = new Validate(descriptor, loginForm, (error, filed) => {
    console.log(error)
})

let cancel;
$("#submit").click(() => {

    let csrf = $("meta[name='_csrf']").attr("content"),
        csrfHeader = $("meta[name='_csrf_header']").attr("content");

    let _this = this;
    if (cancel != null) {
        cancel.abort()
    }
    cancel = $.ajax({
        url: '/authentication/form',
        method: 'post',
        data: $("form").serialize(),
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            [csrfHeader]: csrf
        },
        dataType:"json",
        success:function(res){
            console.log(res)
        },
        error:function(error){
            console.log(error)
        }
    })


    // axios({
    //     method: 'post',
    //     url: '/authentication/form',
    //     data: $("form").serialize(),
    //     headers: {
    //         'Content-Type': 'application/x-www-form-urlencoded',
    //         [csrfHeader]: csrf,
    //         cancelToken: new CancelToken(function executor(c) {
    //             console.log(4545454)
    //             //这个executor 函数接受一个cancel function作为参数
    //             cancel = c;
    //         })
    //     }
    // }).then((res) => {
    //     if (axios.isCancel(res)) {
    //         console.log('Request canceled', res);
    //     } else {
    //         console.log(333333)
    //         // 处理错误
    //     }
    // });

    // axios.post("/authentication/form", {username:"admin",password:"123456"}).then((response) => {
    //     console.log(response)
    // }).catch((error) => {
    //     console.log(error)
    // })
    // if (validate.verify()) {
    //     let data=$("form").serialize()
    //     King.Http.http("/authentication/form",{
    //         body:data,
    //         method:"post"
    //     },function(result){
    //         console.log(result)
    //     })
    // }
});