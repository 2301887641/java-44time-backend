(function ($, w, King, ConstantsPool) {
    function Validate(factor) {
        this.factor = factor;
    }

    Validate.pro = Validate.prototype
    Validate.pro.verify = function (formName, callback) {
        //获取form下所有控件
        let forms = formName.elements
        this.iterator()
    }
    Validate.pro.iterator=function(){
       for(let key of Object.keys(this.factor)){
           console.log(this.factor[key])
       }

    }
    w.Validate = Validate
})($, window, King, ConstantsPool)