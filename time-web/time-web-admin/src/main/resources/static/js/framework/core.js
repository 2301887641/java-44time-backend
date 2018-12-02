/**
 * 基类
 * @type {{utils}}
 */
export const Core = {
    //字符串相关
    stringUtils: {
        //判断字符串是否为空
        isEmpty: function (content) {
            return $.trim(content).length === 0;
        },
    },
    //对象相关
    objectUtils: {
        //判断是否是对象
        isObject: function (obj = {}) {
            return obj.constructor === Object;
        },
        //判断是否是空对象
        isEmpty: function (obj = {}) {
            return Object.keys(obj).length === 0
        },
    },
    //cookie相关
    cookieUtils: {
        get: function (name) {
            let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg)) {
                return (arr[2]);
            } else {
                return null;
            }
        }
    },
    //常量相关
    constant: {
        //monster UI
        MONSTER:{
            SUCCESS:"操作成功",
            FAILURE:"操作失败"
        },
        //url相关
        URL: {
            //登录url
            LOGIN: "/login",
            //验证码
            CAPTCHA:"/captcha",
            //登录认证地址
            LOGIN_AUTHENTICATION:"/authentication/form"
        },
        //icon字体
        ICON: {
            //loading
            SPINNER: '<i class="fa fa-spinner fa-pulse text-white"></i>'
        },
        //信息提示相关
        MESSAGE:{
            //登录页
            LOGIN:{
                LOGIN_SUCCESS:"登陆成功,即将进行跳转",
                CAPTCHA_EXPIRED:"验证码已过期,请重新输入"
            }
        },
        //验证信息相关
        VALIDATION:{
            ACCOUNT_RQUIRED:"请输入账号",
            PASSWORD_REQUIRED:"请输入密码",
            CAPTCHA_REQUIRED:"请输入验证码"
        }
    },
    //发布订阅模式
    observer: function () {
        //缓存列表
        this.clientList = []
        //订阅函数
        this.listen = function (key, fn) {
            if (!this.clientList[key])
                this.clientList[key] = [];
            this.clientList[key].push(fn);
        }
        //发布函数
        this.trigger = function () {
            let key = Array.prototype.shift.call(arguments);
            let fns = this.clientList[key];
            if (!fns || fns.length === 0)
                return false;
            for (let i = 0, fn; fn = fns[i++];) {
                fn.apply(this, arguments);
            }
        }
        //删除函数
        this.remove = function (key, fn) {
            let fns = this.clientList[key];
            if (!fns || fns.length === 0)
                return false;
            if (!fn) {
                fns && (fns.length = 0);
            } else {
                for (let i = fns.length - 1; i >= 0; i--) {
                    let _fn = fns[i];
                    if (_fn === fn) {
                        fns.splice(i, 1);
                    }
                }
            }
        }
    }
}