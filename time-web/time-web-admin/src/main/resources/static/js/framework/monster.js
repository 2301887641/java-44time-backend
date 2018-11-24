export function monster() {
}

monster.prototype = {
    Constructor: monster,
    //小贴士
    tips: (function () {
        function inner() {
            this.container = {
                //元素
                element: null,
                //类型
                type: null
            };
            this.options = {
                success: {
                    iconClass: "icon-chenggong",
                    typeClass: "green-tips",
                    message: "操作成功"
                },
                failure: {
                    iconClass: "icon-cuowu",
                    typeClass: "red-tips",
                    message: "操作失败"
                }
            }
            this.config = {
                //持续
                duration: 200,
                //timeout
                timeout: 2000,
                //位置
                position: "4rem",
                //运动方式
                easing: "linear",
                //可关闭
                closable: false
            }
        }

        inner.prototype = {
            Constructor: inner,
            //初始化 查看元素是否已存在
            init: function (options) {
                this.animate($(this.build(options)).appendTo("body"), options);
            },
            //动画
            animate(element, options) {
                setTimeout(function () {

                }, options.timeout)
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
        monster.tips = new inner()
    })()
}