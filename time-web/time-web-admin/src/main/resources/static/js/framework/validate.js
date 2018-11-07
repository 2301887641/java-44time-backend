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
        //错误数量
        this.errorNum = 0
        //常用属性设置
        this.attribute = {
            isBlur: true
        }
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
        //input的事件集合 存放
        this.inputEventMap = new Map()
        //事件集合
        this.events = new Map()
        this.blur()
    }

    Validate.fn = Validate.prototype = {
        constructor: Validate,
        listen: function () {
            function InnerUtils(inputName) {
            }

            InnerUtils.prototype = {
                //构建className
                buildClass: function (inputName) {
                    return "." + inputName + ConstantsPool.classConstant.errorClass
                }
            }
            let InnerUtil = new InnerUtils(), that = this, fn;
            this.observer.listen("required", fn = function (ele, inputName, descriptor) {
                if (King.utils.isEmptyString($(ele).val())) {
                    $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                    // ++that.errorNum
                    // that.events.set(ele, fn)
                }
            })
            this.observer.listen("regex", fn = function (ele, inputName, descriptor) {
                if (!King.utils.isEmptyString($(ele).val())) {
                    $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                    // ++that.errorNum
                    // that.event.set(ele, fn);
                }
            })
        },
        //鼠标移出事件
        blur: function () {
            //将form中的input转换为数组
            let forms = Array.from(this.formName.elements), eleName, even, fn, descriptor, that = this;
            for (let input of forms) {
                eleName = input.name, descriptor = this.descriptor[eleName]
                //如果input有name属性 并且 验证因子里面也有的话
                if (eleName && descriptor) {
                    event = this.events.get(input)
                    if (event) {
                        input.removeEventListener("blur", event)
                    }
                    fn = function () {
                        that.troggle(input, false)
                    }
                    ++that.errorNum
                    if (that.events.get(input)) {
                        that.events.delete(input)
                    }
                    that.events.set(input, {fn, descriptor});
                    //不能反复添加事件
                    input.addEventListener("blur", fn)
                }
            }
        },
        troggle: function (element, isRequered) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //单个验证
            if (King.utils.isObject(descriptor)) {
                this.objectAssign(element, false)
            } else if (Array.isArray(descriptor)) {
                //是数组的话
                this.arrAssign(element, false)
            }
        },
        //规则为对象的规则校验
        objectAssign: function (element, isRequered) {
            Object.assign(rule, this.rules, descriptor)
            if (rule.required && isRequered) {
                this.observer.troggle("required", element, element.name, descriptor)
                //不允许向下继续执行
                return true
            }
            if (rule.regex) {
                this.observer.troggle("regex", element, element.name, descriptor)
                //不允许向下继续执行
                return true
            }
            //可以继续向下判断执行
            return false
        },
        arrAssign: function (element, isRequered) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //是数组的话
            for (let item of descriptor) {
                Object.assign(rule, this.rules, item)
                if (rule.required && isRequered) {
                    this.observer.troggle("required", element, element.name, item)
                    return true
                }
                if (rule.regex) {
                    this.observer.troggle("regex", element, element.name, item)
                    //不允许向下继续执行
                    return true
                }
            }
            return false
        },
        verify: function () {
            let assign = new Assign();
            //将form中的input转换为数组
            let forms = Array.from(this.formName.elements), eleName = null, descriptor = null, that = this;
            for (let i of forms) {
                eleName = i.name, descriptor = this.descriptor[eleName];
                //如果input有name属性 并且 验证因子里面也有的话
                if (eleName && descriptor) {
                    //单个验证
                    if (King.utils.isObject(descriptor) && that.errorNum < 1) {
                        // if (assign.objectAssign(i, descriptor)) {
                        //     break;
                        // }
                    } else if (Array.isArray(descriptor) && that.errorNum < 1) {
                        //是数组的话
                        if (assign.arrAssign.call(this, i, descriptor)) {
                            break;
                        }
                    }
                }
            }
        }
    }
    //验证是否通过
    w.Validate = Validate
})($, window, King, ConstantsPool)