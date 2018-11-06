(function ($, w, King, ConstantsPool) {
    King.utils = {
        //判断是否是对象
        isObject: function (obj = {}) {
            return obj.constructor === Object;
        },
        //判断是否是空对象
        isEmptyObject: function (obj = {}) {
            return Object.keys(obj).length === 0
        },
        //判断字符串是否为空
        isEmptyString: function (content) {
            return $.trim(content).length === 0;
        }

    }
    //发布订阅模式
    King.observer = function () {
        //缓存列表
        this.clientList = []
        //订阅函数
        King.observer.prototype.listen = function (key, fn) {
            if (!this.clientList[key])
                this.clientList[key] = [];
            this.clientList[key].push(fn);
        }
        //发布函数
        King.observer.prototype.troggle = function () {
            let key = Array.prototype.shift.call(arguments);
            fns = this.clientList[key];
            if (!fns || fns.length === 0)
                return false;
            for (let i = 0, fn; fn = fns[i++];) {
                fn.apply(this, arguments);
            }
        }
        //删除函数
        King.observer.prototype.remove = function (key, fn) {
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
    //headers头
    King.headers = {
        //请求头
        header: new Headers(),
        //获取请求头
        get: function () {
            return this.header;
        },
        //设置请求头
        set: function (key, val) {
            this.header.set(key, val);
            return this.header;
        }
    }
    //选项
    King.option = function (obj = {}) {
        let options = {
            method: 'GET',
            mode: "no-cors",
            cache: 'no-cache',
            redirect: 'follow',
            credentials: 'include',
            headers: King.headers.get()
        }
        if (King.utils.isEmptyObject(obj)) {
            return options
        }
        return Object.assign(options, obj)
    }

    /**
     * ajax get请求
     * @param url   请求地址
     * @param callback  回调
     * @param cors    是否跨域
     */
    King.get = function (url, callback, cors = false) {
        let option = King.option()
        if (cors) {
            option.mode = "cors"
        }
        fetch(url, option).then((response) => {
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
    /**
     * post请求
     * @param url
     * @param data
     * @param callback
     * @param cors
     */
    King.post = function (url, data, callback, cors = false) {
        let option = King.option({body: data, method: "POST"})
        if (cors) {
            option.mode = "cors"
        }
        fetch(url, option).then((response) => {
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
})($, window, King, ConstantsPool);
