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
        //删除对象的事件信息
        deleteEvents: function (key) {
            let event = this.events.get(key)
            if (event) {
                this.events.delete(event)
            }
        },
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
                }
            })
            this.observer.listen("regex", fn = function (ele, inputName, descriptor) {
                let content = $(ele).val()
                if (!King.utils.isEmptyString(content) && !descriptor.regex.test(content)) {
                    $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                } else {
                    $(InnerUtil.buildClass(inputName)).html("")
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
                    !!event && input.removeEventListener("blur", event)
                    fn = function () {
                        that.troggle(input, false)
                    }
                    ++that.errorNum
                    that.deleteEvents(input)
                    that.events.set(input, {fn, descriptor});
                    //不能反复添加事件
                    input.addEventListener("blur", fn)
                }
            }
        },
        troggle: function (element, isRequered, isEmpty) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //单个验证
            if (King.utils.isObject(descriptor)) {
                return this.objectAssign(element, isRequered, isEmpty)
            } else if (Array.isArray(descriptor)) {
                //是数组的话
                return this.arrAssign(element, isRequered, isEmpty)
            }
        },
        /**
         * 规则为单个对象的校验
         * @param element  表单里面的元素
         * @param isRequered  是否校验必填
         * @param isEmpty   鼠标移入移出可否为空 可用在按钮提交
         * @returns {boolean}
         */
        objectAssign: function (element, isRequered, isEmpty) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            Object.assign(rule, this.rules, descriptor)
            if (rule.required && isRequered && isEmpty) {
                this.observer.troggle("required", element, element.name, descriptor)
                return true
            }
            if (rule.regex) {
                this.observer.troggle("regex", element, element.name, descriptor)
                return true
            }
            return false
        },
        /**
         *  规则为数组的校验
         * @param element  表单里面的元素
         * @param isRequered  是否校验必填
         * @param isEmpty   鼠标移入移出可否为空 可用在按钮提交
         * @returns {boolean}
         */
        arrAssign: function (element, isRequered, isEmpty) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //是数组的话
            for (let item of descriptor) {
                Object.assign(rule, this.rules, item)
                if (rule.required && isRequered && isEmpty) {
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
        //点击表单触发
        verify: function () {
            //将form中的input转换为数组
            let forms = Array.from(this.formName.elements), eleName, descriptor, that = this;
            for (let i of forms) {
                eleName = i.name, descriptor = this.descriptor[eleName];
                //如果input有name属性 并且 验证因子里面也有的话
                if (eleName && descriptor) {
                    console.log(that.events.size)
                    if (that.troggle(i, true, true)) {
                        break;
                    }
                }
            }
        }
    }
    //验证是否通过
    w.Validate = Validate
})($, window, King, ConstantsPool)