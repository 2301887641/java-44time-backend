(function ($, w, King, ConstantsPool) {
    function Validate(factor, formName, callback) {
        this.factor = factor;
        //是否刷新遍历表单
        this.isRefresh = false
        //存放原始form数据
        this.form = new Map();
        //事件集合
        this.events = new Map();
        //form名称
        this.formName = formName;
        //错误保留
        this.errorMap = new Map();
        //默认提供规则
        this.rules = {
            //必填
            required: false,
            //提示信息
            message: "",
            //正则
            regex: "",
            //回调
            callback: null
        }
        this.init(formName, callback);
    }

    Validate.pro = Validate.prototype
    Validate.pro.init = function (formName, callback) {
        //获取form下所有控件  并转成真正的数组
        let forms = Array.from(formName.elements)
        this.iteratorForm(forms)
        this.iteratorFactor()
    }
    Validate.pro.verify = function () {


    }
    //验证因子
    Validate.pro.iteratorFactor = function () {
        for (let item of Object.keys(this.factor)) {
            let rule = {}
            //如果在form元素中存在的话
            let input = this.form.get(item)
            if (this.form.get(item)) {
                this.compare(input, item)
            }
        }
    }
    //比对是对象还是数组
    Validate.pro.compare = function (input, item) {
        if (King.utils.isObject(this.factor[item])) {
            let rule = {}
            Object.assign(rule, this.rules, this.factor[item])
            this.beforeComplete(input, rule)
            return
        }
        if (Array.isArray(this.factor[item])) {
            let factor = this.factor[item]
            for (i of factor) {

            }
        }
    }
    //完成校验
    Validate.pro.beforeComplete = function (input, rule) {
        let event = this.events.get(input), fn = null, that = this, name = input.name;
        if (event) {
            input.removeEventListener("blur", event)
        }
        //不能反复添加事件
        input.addEventListener("blur", fn = function () {
            if (that.requiredAndEmpty(input)) {
                $("." + name + ConstantsPool.classConstant.errorClass).html(rule.message)
            } else {
                $("." + name + ConstantsPool.classConstant.errorClass).html("")
            }

        })
        this.events.set(input, fn);
        // if (rule.required && this.requiredAndEmpty(input)) {
        //     this.errorMap.set(input,rule.message)
        // }

    }
    //必填提示
    Validate.pro.requiredAndEmpty = function (element) {
        return King.utils.isEmptyString($(element).val());
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