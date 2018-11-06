(function ($, w, King, ConstantsPool) {
    /**
     * 构造函数
     * @param descriptor  校验器
     * @param formName    表单名称
     * @param callback    回调函数
     * @constructor
     */
    function Validate(descriptor, formName, callback) {
        this.formName = formName
        this.descriptor = descriptor
        //创建监听器对象
        this.observer = new King.observer()
        //触发错误记录
        this.event = new Map();
        //错误数量
        this.errorNum = 0
        //监听发布订阅
        this.listen()
        //默认提供规则
        this.rules = {
            //false为选填 如果填写了还可以控制正则来判断格式
            required: false,
            //提示信息
            message: "",
            //正则
            regex: "",
            //回调
            callback: null
        }
    }

    Validate.pro = Validate.prototype;
    Validate.pro.listen = function () {
        function InnerUtils(inputName) {

        }

        InnerUtils.prototype = {
            //构建className
            buildClass: function (inputName) {
                return "." + inputName + ConstantsPool.classConstant.errorClass;
            }
        }
        let InnerUtil = new InnerUtils(), that = this, fn = null;
        this.observer.listen("required", fn = function (ele, inputName, descriptor) {
            if (King.utils.isEmptyString($(ele).val())) {
                $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                ++that.errorNum
                that.event.set(ele, fn);
            }
        })
    }
    //验证是否通过
    Validate.pro.verify = function () {
        function Assign(rules) {
            this.rules = rules
        }

        Assign.prototype = {
            //规则为对象的规则校验
            objectAssign: function (element, descriptor) {
                let rule = {}
                Object.assign(rule, this.rules, descriptor)
                if (rule.required) {
                    that.observer.troggle("required", element, element.name, descriptor)
                    //不允许向下继续执行
                    return true
                }
                //可以继续向下判断执行
                return false
            },
            arrAssign: function (element, descriptor) {
                //是数组的话
                let rule = {}
                for (let item of descriptor) {
                    Object.assign(rule, this.rules, descriptor[item])
                    if (rule.required) {
                        that.observer.troggle("required", element, element.name, item)
                        return true
                    }
                }
                return false
            }
        }
        let assign = new Assign(this.rules);
        //将form中的input转换为数组
        let forms = Array.from(this.formName.elements), eleName = null, descriptor = null, that = this;
        for (let i of forms) {
            eleName = i.name, descriptor = this.descriptor[eleName];
            //如果input有name属性 并且 验证因子里面也有的话
            if (eleName && descriptor) {
                //单个验证
                if (King.utils.isObject(descriptor) && that.errorNum < 1) {
                    if (assign.objectAssign(i, descriptor)) {
                        break;
                    }
                } else if (Array.isArray(descriptor) && that.errorNum < 1) {
                    //是数组的话
                    // if (assign.arrAssign(i, descriptor)) {
                    //     break;
                    // }
                }
            }
        }
    }
    w.Validate = Validate
})($, window, King, ConstantsPool)