/**
 * Created by lzz on 2018/2/1.
 */

function  alert_msg(msg) {
    var str = "<strong>Warn!</strong> <span> please check format </span>";
    if( msg ){
        str = "<strong>Warn!</strong> <span>" + msg + "</span>";
    }
    return str;
}
function checkGet(load_obj,check_url, url, callback, errorcall) {
    $.ajax({
        type:'get',
        url: check_url,
        beforeSend:function(XMLHttpRequest){
            $(load_obj).button('loading');
        },
        success:function (check_res) {
            console.log(check_res);
            if( check_res.code == 0 ){
                $.get(url, callback(check_res));
            }else{
                errorcall(check_res);
            }
        },
        complete:function(XMLHttpRequest){
            $(load_obj).button('reset');
        },
        error:errorcall
    });

}
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