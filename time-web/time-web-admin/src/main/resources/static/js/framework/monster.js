export function monster() {


}

monster.prototype = {
    Constructor: monster,
    //小贴士
    tips: (function () {
        //常量
        const MESSAGE = {
            success: "修改成功"
        }

        function inner() {
            //tips类型
            this.type = {success: "blue-tips", failure: "red-tips"};
            this.icon = {success: "icon-checkmark", failure: "icon-notification"}
        }

        inner.prototype = {
            Constructor: inner,
            //初始化 查看元素是否已存在
            init: function (message, typeClass, iconClass) {
                //元素不存在
                if (!$(".monster-tips").length) {
                    $(this.build(message, typeClass, iconClass)).appendTo("body");
                    return
                }
            },
            //构造
            build: function (message, typeClass, iconClass) {
                let html = [];
                html.push('<div class="monster-tips ' + typeClass + '">');
                html.push('<div class="tip-inner">');
                html.push('<span class="tip-icon ' + iconClass + '"></span>');
                html.push('<span class="tip-msg">' + message + '</span>');
                html.push('<span class="tip-icon tips-close icon-cross"></span>')
                html.push('</div></div>');
                html = html.join("");
                return html;
            },
            //成功
            success: function (message = MESSAGE.success) {
                this.init(message, this.type.success, this.icon.success)
            }
        }
        let inners = new inner()
        monster.tips = inners
    })()
}