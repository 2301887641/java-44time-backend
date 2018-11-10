import {King} from './king.js'


/**
 * 构造函数
 * @param descriptor  校验器
 * @param formName    表单名称
 * @constructor
 */
export function Validate(descriptor, formName) {
    //form表单名称
    this.formName = formName
    //校验规则
    this.descriptor = descriptor
    //在事件监听器里面标识错误 不再继续向下检测
    this.disableSubmit = false
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
    //初始化
    this.init()
}

//常量池
Validate.ConstansPool = {
    errorClass: "-error-notice",
    //校验相关
    verify: {
        required: "required",
        regex: "regex"
    },
    //事件相关
    event: {
        blur: "blur",
        keyDown:"keydown"
    },
    //元素相关
    element: {
        input: "input"
    }
}
Validate.fn = Validate.prototype = {
    constructor: Validate,
    //初始化
    init: function () {
        //创建监听器对象
        this.observer = new King.Observer()
        //事件集合
        this.eventMap = new Map()
        //已放入监听的错误集合
        this.listenErrorMap = new Map()
        //监听发布订阅
        this.listen()
        //鼠标事件
        this.blur()
    },
    //根据元素返回对应的事件
    switchEvent: function (element) {
        let elementName = element.tagName.toLowerCase(), event;
        switch (elementName) {
            case Validate.ConstansPool.element.input:
                event = Validate.ConstansPool.event.blur;
                break;
            default:
                event = Validate.ConstansPool.event.blur;
                break;
        }
        return event;
    },
    //元素内容是否为空
    elementIsEmpty: function (element) {
        let elementName = element.tagName.toLowerCase(), ele = $(element)
        switch (elementName) {
            case Validate.ConstansPool.element.input:
                return King.Utils.string.isEmpty(ele.val())
            default:
                return King.Utils.string.isEmpty(ele.val())
        }
    },
    /**
     * 监听器
     */
    listen: function () {
        //内置工具类
        function InnerUtils(inputName) {
        }

        InnerUtils.prototype = {
            //构建className
            buildClass: function (inputName) {
                return "." + inputName + Validate.ConstansPool.errorClass
            },
            //向listenErrorMap中添加
            addErrorMap: function (key, value) {
                this.listenErrorMap.set(key, value)
            },
            getErrorMap: function (key) {
                this.listenErrorMap.get(key)
            },
            removeErrorMap(key) {
                this.listenErrorMap.delete(key)
            }
        }
        let InnerUtil = new InnerUtils(), that = this, fn;
        /**
         * 监听必填项
         * element  元素对象
         * eleTagName   元素的tagName
         * descriptor   校验因子
         */
        this.observer.listen(Validate.ConstansPool.verify.required, fn = function (element, eleTagName, descriptor, allow) {
            let error = $(InnerUtil.buildClass(eleTagName))
            if (that.elementIsEmpty(element) && allow) {
                error.html(descriptor.message)
                InnerUtil.addErrorMap.call(that, element, Validate.ConstansPool.verify.required)
                that.disableSubmit = true
            }
            if (!allow) {
                that.disableSubmit = false
                error.html("")
                InnerUtil.removeErrorMap.call(that, element)
            }
        })
        //监听正则
        this.observer.listen(Validate.ConstansPool.verify.regex, fn = function (ele, inputName, descriptor) {
            let content = $(ele).val()
            if (!King.utils.isEmptyString(content) && !descriptor.regex.test(content)) {
                $(InnerUtil.buildClass(inputName)).html(descriptor.message)
            } else {
                $(InnerUtil.buildClass(inputName)).html("")
            }
        })
    },
    /**
     * 触发
     * @param element
     * @param allow
     * @returns {boolean}
     */
    trigger: function (element, allow) {
        let descriptor = this.eventMap.get(element).descriptor, rule = {}, arr = [];
        //单个验证
        if (King.Utils.object.isObject(descriptor)) {
            arr.push(descriptor)
            Object.assign(rule, this.rules, descriptor)
        } else if (Array.isArray(descriptor)) {
            arr = descriptor
        }
        //是数组的话
        for (let item of arr) {
            Object.assign(rule, this.rules, item)
            if (rule.required) {
                this.observer.trigger(Validate.ConstansPool.verify.required, element, element.name, item, allow)
            }
            if (rule.regex) {
                this.observer.trigger(Validate.ConstansPool.verify.regex, element, element.name, item, allow)

            }
            return true
        }
    },
    /**
     * 添加鼠标移出事件
     */
    blur: function () {
        //将form中的input转换为数组
        let forms = Array.from(this.formName.elements), eleName, even, eventName, eventMap, fn, descriptor,
            that = this;
        for (let input of forms) {
            eleName = input.name, descriptor = this.descriptor[eleName]
            //如果input有name属性 并且 验证因子里面也有的话
            if (eleName && descriptor) {
                //如果存在先删除掉事件
                fn = function () {
                    that.trigger(input, false)
                }
                //获取元素要添加的事件名称
                eventName = this.switchEvent(input);
                this.eventMap.set(input, {fnName: eventName, fn: fn, descriptor: descriptor});
                //不能反复添加事件
                input.addEventListener(eventName, fn)
                input.addEventListener(Validate.ConstansPool.event.keyDown, function () {
                    that.observer.trigger(Validate.ConstansPool.verify.required, this, this.name, that.eventMap.get(this).descriptor, false)
                })
            }
        }
    }
    ,
    //点击表单触发
    verify: function () {
        this.disableSubmit = false
        //错误数量
        if (this.listenErrorMap.size > 0) {
            return false
        }
        //将form中的input转换为数组
        let forms = Array.from(this.formName.elements), eleName, descriptor;
        for (let i of forms) {
            eleName = i.name, descriptor = this.descriptor[eleName];
            //如果input有name属性 并且 验证因子里面也有的话
            if (eleName && descriptor && !this.disableSubmit) {
                this.trigger(i, true)
            }
        }
        return true
    }
}