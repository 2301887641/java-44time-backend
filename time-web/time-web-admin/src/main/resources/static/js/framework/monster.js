export function Monster() {
}

Monster.prototype = {
    Constructor: monster,
    //小贴士
    tips: (function () {
        function Inner() {
            this.options = {
                success: {
                    iconClass: "icon-chenggong",
                    typeClass: "green-tips",
                    message: core.constant.MONSTER.SUCCESS
                },
                failure: {
                    iconClass: "icon-cuowu",
                    typeClass: "red-tips",
                    message: core.constant.MONSTER.FAILURE
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
            Constructor: inner,
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
                html.push('<div class="monster-tips ' + options.typeClass + '">');
                html.push('<div class="tip-content">');
                html.push('<span class="tip-icon iconfont tip-start ' + options.iconClass + '"></span>');
                options.closable && html.push('<span class="tip-icon iconfont icon-guanbi tip-end"></span>')
                html.push('<span class="tip-msg">' + options.message + '</span>');
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
            failure: function (arg = {message, duration} = {
                message: this.options.success.message,
                duration: this.options.duration
            }) {
                this.init(Object.assign(this.options.failure, this.config, arg))
            }
        }
        monster.tips = new Inner()
    })()
}