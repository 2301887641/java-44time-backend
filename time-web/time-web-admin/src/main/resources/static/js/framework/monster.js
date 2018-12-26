import {Core} from './core.js'

export function Monster() {
}

//常量
Monster.CONSTANTS = {
    //横线
    TRANSVERSE_LINE: "-",
    //默认要插入的容器
    DEFAULT_APPEND_CONTAINER:"body",
    //默认持续时间
    DEFAULT_DURATION:2,
    //默认持续的毫秒
    DEFAULT_DURATION_SECOND:1000,
    //tips相关
    TIPS: {
        BASE_CLASS_NAME: "monster-tips",
        SUCCESS_TYPE: "success",
        FAILURE_TYPE: "failure",
        CONTENT_CLASS_NAME: "monster-tips-content",
        ICON_PUBLIC_CLASS: "iconfont",
        ICON_GUANBI: "icon-guanbi",
        CONTENT_MSG: "monster-tips-content-msg",
        CONTENT_END: "monster-tips-content-end",
        ICON_SUCCESS_CLASS:"icon-chenggong",
        ICON_FAILURE_CLASS:"icon-cuowu"
    }
};

Monster.prototype = {
    Constructor: Monster,
    //小贴士
    tips: (function (Monster) {
        function Inner() {
            this.options = {
                success: {
                    iconClass: Monster.CONSTANTS.TIPS.ICON_SUCCESS_CLASS,
                    typeClass: Monster.CONSTANTS.TIPS.BASE_CLASS_NAME + Monster.CONSTANTS.TRANSVERSE_LINE + Monster.CONSTANTS.TIPS.SUCCESS_TYPE,
                    message: Core.constant.MONSTER.SUCCESS
                },
                failure: {
                    iconClass: Monster.CONSTANTS.TIPS.ICON_FAILURE_CLASS,
                    typeClass: Monster.CONSTANTS.TIPS.BASE_CLASS_NAME + Monster.CONSTANTS.TRANSVERSE_LINE + Monster.CONSTANTS.TIPS.FAILURE_TYPE,
                    message: Core.constant.MONSTER.FAILURE
                }
            }
            this.config = {
                //持续
                duration: Monster.CONSTANTS.DEFAULT_DURATION,
                //可关闭
                closeable: false
            }
        }

        Inner.prototype = {
            Constructor: Inner,
            //初始化 查看元素是否已存在
            init: function (options) {
                this.animate($(this.build(options)).appendTo(Monster.CONSTANTS.DEFAULT_APPEND_CONTAINER), options);
            },
            //动画
            animate(element, options) {
                element.css("animation-delay", ".1s," + options.duration + "s");
                setTimeout(function () {
                    element.remove()
                }, (options.duration + 1) * Monster.CONSTANTS.DEFAULT_DURATION_SECOND)
            },
            //构造
            build: function (options) {
                let html = [];
                html.push('<div class="' + Monster.CONSTANTS.TIPS.BASE_CLASS_NAME + " " + options.typeClass + '">');
                html.push('<div class="' + Monster.CONSTANTS.TIPS.CONTENT_CLASS_NAME + '">');
                html.push('<span class="' + Monster.CONSTANTS.TIPS.ICON_PUBLIC_CLASS + " " + options.iconClass + '"></span>');
                html.push('<span class="' + Monster.CONSTANTS.TIPS.CONTENT_MSG + '">' + options.message + '</span>');
                options.closeable && html.push('<span class="' + Monster.CONSTANTS.TIPS.ICON_PUBLIC_CLASS + " " + Monster.CONSTANTS.TIPS.ICON_GUANBI + " " + Monster.CONSTANTS.TIPS.CONTENT_END + '"></span>')
                html.push('</div></div>');
                html = html.join("");
                return html;
            },
            //成功  带默认值
            success: function (arg = {message, duration} = {
                message: this.options.success.message,
                duration: this.options.duration
            }) {
                this.init(Object.assign(this.options.success, this.config, arg))
            },
            failure: function (arg = {message, duration, closeable} = {
                message: this.options.success.message,
                duration: this.options.duration,
                closeable: this.config.closeable
            }) {
                this.init(Object.assign(this.options.failure, this.config, arg))
            }
        }
        Monster.tips = new Inner()
    })(Monster)
}