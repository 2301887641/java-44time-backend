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
            //给html添加style样式
            htmlStyle: function () {
                let monsterStyle = 'box-shadow:0 0 4px rgba(0,0,0,.2);font-family:"Microsoft YaHei",SimSun;position:fixed;border-radius: 4px;height: 40px;color: #fff;padding:0 15px;background: #4c93ff;top:74px;left:50%;margin-left:-112px;opacity:1;display:block;z-index:95;',
                    innerStyle = "height:100%;",
                    iconTips='display: inline-block;width: 22px;height: 45px;margin: 0 1px 0 0;padding-left: 1px;',
                    iconSuccess='line-height: 41px;font-size: 13px;';



            }
        }
        let inners = new inner(message, type = "success");
        inners.init()


    }
}