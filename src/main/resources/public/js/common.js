/**
 * Created by lzz on 2018/2/1.
 */



function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}

function getUrlPath(url) {
    var arr = url.split("//");
    var str = arr[1];
    var pos = str.indexOf("/");
    var res = "";
    if(pos != -1){
        res = str.substring(pos);
    }
    console.log(res);
    return res;
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}