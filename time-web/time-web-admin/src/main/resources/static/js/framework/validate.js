(function ($, w, King, ConstantsPool) {
    function Validate(factor) {
        this.factor = factor;
        //是否刷新遍历表单
        this.isRefresh = false
        //存放原始form数据
        this.form = new Map();
        //默认提供规则
        this.rules = {
            //必填
            required: false,
            //提示信息
            message: "",
            //正则
            regex: "",
            //默认离焦
            focus: true,
            //回调
            callback: null
        }
    }


    Validate.pro = Validate.prototype
    Validate.pro.verify = function (formName, callback) {
        //获取form下所有控件  并转成真正的数组
        let forms = Array.from(formName.elements)
        this.iteratorForm(forms)
        this.iteratorFactor()
    }
    Validate.pro.iteratorFactor = function () {
        for (let item of Object.keys(this.factor)) {
            let rule = {}
            // console.log(item)
            //如果在form元素中存在的话
            if (this.form.get(item)) {
                //判断是否是数组
                console.log(this.factor[item])
                Object.assign(this.rules, this.factor[item])
                // console.log(rule)
            }
        }
    }
    //迭代表单  只迭代一次即可
    Validate.pro.iteratorForm = function (forms) {
        if (this.form.size > 0 && !this.isRefresh) {
            return
        }
        for (item of forms) {
            if (!!item.name) {
                this.form.set(item.name, item)
            }
        }
    }
    w.Validate = Validate
})($, window, King, ConstantsPool)