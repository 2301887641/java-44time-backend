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
    //http请求
    Http: {
        http: function (url, option, callback) {
            let csrf = $("meta[name='_csrf']").attr("content"),
                csrfHeader = $("meta[name='_csrf_header']").attr("content"),
                //mode: 'no-cors' 这个属性不要配置
                options = {
                    method: 'GET',
                    cache: 'no-cache',
                    redirect: 'follow',
                    credentials: 'include',
                    headers: {
                        "Content-Type": "application:/x-www-form-urlencoded",
                        [csrfHeader]:csrf
                    }
                }, opt = {}
            Object.assign(opt, options, option)
            fetch(url, opt).then((response) => {
                if (response.ok) {
                    return response;
                }
                let error = new Error(response.status)
                error.response = response
                throw error
            }).then((data) => {
                return data.json();
            }).then((json) => {
                callback(json)
            }).catch((error, res) => {
                console.log(error.response)
            })
        }
    }
}
