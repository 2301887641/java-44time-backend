export  function King() {
}

King.utils = {
    //判断是否是对象
    isObject: function (obj = {}) {
        return obj.constructor === Object;
    },
    //判断是否是空对象
    isEmptyObject: function (obj = {}) {
        return Object.keys(obj).length === 0
    },
    //判断字符串是否为空
    isEmptyString: function (content) {
        return $.trim(content).length === 0;
    }
}
King.URL = {
    LOGIN: "/login"
}
King.ConstantsPool = {


}
