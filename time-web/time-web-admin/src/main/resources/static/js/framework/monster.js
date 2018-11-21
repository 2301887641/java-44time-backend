export function monster() {
}

monster.prototype = {
    Constructor: monster,
    //小贴士
    tips: (function () {
        function inner() {
            this.options = {
                success: {
                    iconClass: "icon-chenggong",
                    typeClass: "green-tips",
                    message: "操作成功"
                },
                failure: {
                    iconClass: "icon-gantan",
                    typeClass: "red-tips",
                    message: "操作失败"
                },
                //持续
                duration: 2000
            }
        }

        inner.prototype = {
            Constructor: inner,
            //初始化 查看元素是否已存在
            init: function (message, typeClass, iconClass) {
                //元素不存在
                if (!$(".monster-tips").length) {
                    let element = $(this.build(message, typeClass, iconClass)).appendTo("body");
                    return
                }
            },
            //动画
            animate(element) {
                element.animate({top: "4rem"}, {
                    duration: 200, easing: "linear", complete: function () {
                        setTimeout(function () {
                            element.fadeOut()
                        }, 2000)
                    }
                })
            },
            //构造
            build: function (message, typeClass, iconClass) {
                let html = [];
                html.push('<div class="monster-tips">');
                html.push('<div class="tip-content ' + typeClass + '">');
                html.push('<span class="tip-icon iconfont tip-start ' + iconClass + '"></span>');
                html.push('<span class="tip-msg">' + message + '</span>');
                html.push('<span class="tip-icon iconfont icon-guanbi tip-end"></span>')
                html.push('</div></div>');
                html = html.join("");
                return html;
            },
            //成功  带默认值
            success: function (arg = {message, duration} = {
                message: this.options.success.message,
                duration: this.options.duration
            }) {
                this.init(Object.assign(this.options.success,arg))
            }
        }
        monster.tips = new inner()
    })()
}