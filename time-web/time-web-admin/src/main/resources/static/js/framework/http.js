import {monster} from "./monster.js";
import {Core} from './core.js';
/**
 * http请求相关
 * @type {{http: http.http}}
 */
export const http={
    //http请求 重复请求会销毁之前的提交
    request: function (option, callback, element) {
        this.cancel && this.cancel.abort()
        let csrf = Core.cookieUtils.get("XSRF-TOKEN"), opt = {}, ele = $(element),
            icon =$(Core.ConstantsPool.ICON.SPINNER),
            options = {
                url: "",
                method: "get",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-XSRF-TOKEN': csrf
                },
                dataType: "json",
                beforeSend: function () {
                    !!element && ele.prepend(icon) && ele.attr("disabled", "true")
                },
                complete: function () {
                    !!element && ele.removeAttr("disabled") && icon.remove()
                },
                success: function (res) {
                    if (res.retCode === 500) {
                        monster.tips.failure({message: res.retInfo})
                    }
                    (callback instanceof Function) && callback(res)
                },
                error: function (error) {
                    monster.tips.failure({message: "网络连接失败!"})
                }
            };
        if (!csrf) {
            throw Error("csrf undefined");
        }
        Object.assign(opt, options, option)
        this.cancel = $.ajax(opt)
    }
}