/**
 * Created by lzz on 2018/3/21.
 */

$(document).ready(function () {
    var height = $(window).height() - 50;
    var style_height = "height:" + height + "px";
    $("#browser-iframe").attr("style", style_height);
    var urlHistory = getUrlList();
    $("#browser-url").autocomplete({
        source: urlHistory,
        close: function( event, ui ) {
            request_url();
        }
    });
});

$("#browser-url").bind('keypress', function (event) {
    if (event.keyCode == "13") {
        request_url();
    }
});

$("#browser-url-refresh").click(function(){
    request_url();
});

function request_url() {
    var url = $("#browser-url").val().trim();
    if( url && url != null && url != "" && url.length > 12 ) {
        get("/tmp-proxy?url=" + url, function (obj) {
            $("#browser-iframe").attr("src", obj.result);
        });
    }
    var urlArr = getUrlList();
    if( urlArr.length > 10 ){
        urlArr.pop();
    }
    urlArr.push( url );
    var urlJson = {"list": urlArr};
    set_cookie("url_history", JSON.stringify( urlJson ) );
}


function getUrlList() {
    var urlarr = [];
    var urlstr = get_cookie("url_history");
    if( null != urlstr ){
        var urlJson = JSON.parse( urlstr );
        var urlList = urlJson["list"];
        urlarr = unique(urlList);
    }
    return urlarr;
}