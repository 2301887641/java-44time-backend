import {Core} from './core.js'

export function Monster() {
}

//常量
Monster.CONSTANTS = {
    //横线
    TRANSVERSE_LINE:"-",
    //tips相关
    TIPS: {
        CLASS_PREFIX:"monster",
        BASE_CLASS_NAME: "monster-tips",
        SUCCESS_TYPE: "success",
        FAILURE_TYPE:"failure"
    }
}

Monster.prototype = {
    Constructor: Monster,
    //小贴士
    tips: (function (Monster) {
        function Inner() {
            this.options = {
                success: {
                    iconClass: "icon-chenggong",
                    typeClass: Monster.CONSTANTS.TIPS.BASE_CLASS_NAME + Monster.CONSTANTS.TRANSVERSE_LINE + Monster.CONSTANTS.TIPS.SUCCESS_TYPE,
                    message: Core.constant.MONSTER.SUCCESS
                },
                failure: {
                    iconClass: "icon-cuowu",
                    typeClass: "red-tips",
                    message: Core.constant.MONSTER.FAILURE
                }
            }
            this.config = {
                //持续
                duration: 2,
                //可关闭
                closable: false
            }
        }

        Inner.prototype = {
            Constructor: Inner,
            //初始化 查看元素是否已存在
            init: function (options) {
                this.animate($(this.build(options)).appendTo("body"), options);
            },
            //动画
            animate(element, options) {
                element.css("animation-delay", ".1s," + options.duration + "s");
                setTimeout(function () {
                    element.remove()
                }, (options.duration + 1) * 1000)
            },
            //构造
            build: function (options) {
                let html = [];
                let baseClass = Monster.CONSTANTS.TIPS.BASE_CLASS_NAME +Monster.CONSTANTS.SPLIT_STRING+ Monster.CONSTANTS.TIPS;
                html.push('<div class="' + baseClass + " " + options.typeClass + '">');
                html.push('<div class="tip-content">');
                html.push('<span class="tip-icon iconfont tip-start ' + options.iconClass + '"></span>');
                options.closable && html.push('<span class="tip-icon iconfont icon-guanbi tip-end"></span>')
                html.push('<span class="tip-msg">' + options.message + '</span>');
                html.push('</div></div>');
                html = html.join("");
                console.log(html)
                return html;
            },
            //成功  带默认值
            success: function (arg = {message, duration} = {
                message: this.options.success.message,
                duration: this.options.duration
            }) {
                this.init(Object.assign(this.options.success, this.config, arg))
            },
            failure: function (arg = {message, duration} = {
                message: this.options.success.message,
                duration: this.options.duration
            }) {
                this.init(Object.assign(this.options.failure, this.config, arg))
            }
        }
        Monster.tips = new Inner()
    })(Monster)
}