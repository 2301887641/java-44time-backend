    import King from './king.js'
    /**
     * 构造函数
     * @param descriptor  校验器
     * @param formName    表单名称
     * @param callback    回调函数
     * @constructor
     */
     export   function Validate_bak(descriptor, formName, callback) {
        //form表单名称
        this.formName = formName
        //校验规则
        this.descriptor = descriptor
        //创建监听器对象
        this.observer = new King.Observer()
        //错误数量
        this.errorNum = 0
        //确定错误状态 是否可以发送表单
        this.isError = false
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
        //事件集合
        this.events = new Map()
        //鼠标事件
        this.blur()
    }

    //常量池
    Validate_bak.ConstansPool = {
        errorClass: "-error-notice"
    }

    Validate_bak.fn = Validate_bak.prototype = {
        constructor: Validate_bak,
        //删除对象的事件信息
        deleteEvents: function (key) {
            let event = this.events.get(key)
            !!event && this.events.delete(event)
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
                    return "." + inputName + Validate_bak.ConstansPool.errorClass
                }
            }
            let InnerUtil = new InnerUtils(), that = this, fn;
            //监听必填
            this.observer.listen("required", fn = function (ele, inputName, descriptor) {
                if (King.Utils.string.isEmpty($(ele).val())) {
                    that.isError = true
                    $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                }else{
                    $(InnerUtil.buildClass(inputName)).html()
                }
            })
            //监听正则
            this.observer.listen("regex", fn = function (ele, inputName, descriptor) {
                let content = $(ele).val()
                if (!King.utils.isEmptyString(content) && !descriptor.regex.test(content)) {
                    that.isError = true
                    $(InnerUtil.buildClass(inputName)).html(descriptor.message)
                } else{
                    that.isError = false
                    $(InnerUtil.buildClass(inputName)).html("")
                }
            })
        },
        /**
         * 添加鼠标移出事件
         */
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
        /**
         * 触发
         * @param element    元素
         * @param isRequired  是否校验必填
         * @returns {*|boolean}
         */
        troggle: function (element, isRequired) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //单个验证
            if (King.Utils.object.isObject(descriptor)) {
                return this.objectAssign(element, isRequired)
            } else if (Array.isArray(descriptor)) {
                //是数组的话
                return this.arrAssign(element, isRequired)
            }
        },
        /**
         * 规则为单个对象的校验
         * @param element  表单里面的元素
         * @param isRequired  是否校验必填
         * @returns {boolean}
         */
        objectAssign: function (element, isRequired) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            Object.assign(rule, this.rules, descriptor)
            if (rule.required && isRequired) {
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
         * @param isRequired  是否校验必填
         * @param isEmpty   鼠标移入移出可否为空 可用在按钮提交
         * @returns {boolean}
         */
        arrAssign: function (element, isRequired) {
            let descriptor = this.events.get(element).descriptor, rule = {}
            //是数组的话
            for (let item of descriptor) {
                Object.assign(rule, this.rules, item)
                if (rule.required && isRequired) {
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
            let forms = Array.from(this.formName.elements), eleName, descriptor;
            for (let i of forms) {
                eleName = i.name, descriptor = this.descriptor[eleName];
                //如果input有name属性 并且 验证因子里面也有的话
                if (eleName && descriptor) {
                    if (this.troggle(i, true)) {
                        break;
                    }
                }
            }
            return this.isError
        }
    }
