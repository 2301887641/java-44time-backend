export function monster() {


}

monster.prototype = {
    Constructor: monster,
    //小贴士
    tips: function (message, type = "success") {
        function inner(message, type) {
            if ($.trim(message).length === 0) {
                throw Error("no message");
            }
        }

        inner.prototype = {
            Constructor: inner,
            //tips类型
            type: {
                //失败
                failure: "red-tips"
            },
            //初始化 查看元素是否已存在
            init: function () {
                //元素不存在
                if (!$(".monster-tips").length) {
                    $(this.build(message, type)).appendTo("body")
                    return
                }
            },
            //构造
            build: function (message, key) {
                let html = [];
                !!this.type[key] ? html.push('<div class="monster-tips ' + this.type[key] + '">') :
                    html.push('<div class="monster-tips" style="' + monsterStyle + '">');
                html.push('<div class="tip-inner">');
                html.push('<span class="tip-icon icon-tips-success"></span>');
                html.push('<span class="tip-msg">' + message + '</span>');
                html.push('</div></div>');
                html = html.join("")
                return html;
            },
        }
        let inners = new inner(message, type = "success");
        inners.init()


    }
}