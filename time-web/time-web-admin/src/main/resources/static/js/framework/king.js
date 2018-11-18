/**
 * 基类
 * @type {{utils}}
 */
export const King = {
    //工具相关
    Utils: {
        //对象相关
        object: {
            //判断是否是对象
            isObject: function (obj = {}) {
                return obj.constructor === Object;
            },
            //判断是否是空对象
            isEmpty: function (obj = {}) {
                return Object.keys(obj).length === 0
            },
        },
        //字符串相关
        string: {
            //判断字符串是否为空
            isEmpty: function (content) {
                return $.trim(content).length === 0;
            },
        },
        //cookie相关
        cookie: {
            get: function (name) {
                let arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
                if(arr = document.cookie.match(reg)){
                    return (arr[2]);
                }else{
                    return null;
                }
            }
        }
    },
    //常量相关
    ConstantsPool: {
        URL: {
            LOGIN: "/login"
        }
    },
    //发布订阅模式
    Observer: function () {
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
    },
    //http请求 重复请求会销毁之前的提交
    http: function (option, callback, element) {
        this.cancel && this.cancel.abort()
        let icon = '<i class="fa fa-spinner fa-pulse text-white"></i>',
            csrf = King.Utils.cookie.get("XSRF-TOKEN"),
            csrfHeader = $("meta[name='_csrf_header']").attr("content"), opt = {},
            options = {
                url: "",
                method: "get",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    [csrfHeader]: csrf
                },
                dataType: "json",
                beforeSend: function () {
                    !!element && $(element).prepend(icon) && $(element).attr("disabled", "true")
                },
                complete: function () {
                    !!element && $(element).removeAttr("disabled") && $(element).find("i").remove()
                },
                success: function (res) {
                    (callback instanceof Function) && callback(res)
                },
                error: function (error) {
                    console.log(error)
                }
            };
        if(!csrf){
            console.log("csrf undefined")
            return
        }
        opt.url = basePath + opt.url
        Object.assign(opt, options, option)
        this.cancel = $.ajax(opt)
    }
}
