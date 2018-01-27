/*! basetools - 2014-12-23
* author:zhaoqiang
* Includes: form2json,
* 页面通用公共方法js，所有通用方法集中写在这里 */

/*  checkbox插件  开始 */
 (function($){
    $.fn.extend({
        /*
         <input type="checkbox" class="checkboxCtrl" group="c1" />全选
         <button type="button" class="checkboxCtrl" group="c1" selectType="invert">反选</button>
         * */
        checkboxCtrl: function(parent){
            return this.each(function(){
                var $trigger = $(this);
                $trigger.bind("click",function(){
                    var group = $trigger.attr("group");
                    if ($trigger.is(":checkbox")) {
                        var type = $trigger.is(":checked") ? "all" : "none";
                        if (group) $.checkbox.select(group, type, parent);
                    } else {
                        if (group) $.checkbox.select(group, $trigger.attr("selectType") || "all", parent);
                    }
                    $(parent).find(":checkbox[name='"+group+"']").click(function(){
                        var $tmp=$(":checkbox[name='"+group+"']");
                        $trigger.attr('checked',$tmp.length==$tmp.filter(':checked').length);
                    });
                });
            });
        }
    });

    $.checkbox = {
        selectAll: function(_name, _parent){
            this.select(_name, "all", _parent);
        },
        unSelectAll: function(_name, _parent){
            this.select(_name, "none", _parent);
        },
        selectInvert: function(_name, _parent){
            this.select(_name, "invert", _parent);
        },
        select: function(_name, _type, _parent){
            $parent = $(_parent || document);
            $checkboxLi = $parent.find(":checkbox[name='"+_name+"']");
            switch(_type){
                case "invert":
                    $checkboxLi.each(function(){
                        $checkbox = $(this);
                        $checkbox.prop('checked', !$checkbox.is(":checked"));
                    });
                    break;
                case "none":
                    $checkboxLi.prop('checked', false);
                    break;
                default:
                    $checkboxLi.prop('checked', true);
                    break;
            }
        }
    };
})(jQuery);

/*  checkbox插件  结束 */


//添加类bootstrap icheck
(function($) {
    $.icheck = {
        init: function() {
            var _this = this;
            _this._checkbox = "checkbox";
            _this._radio = "radio";
            _this._disabled = "disabled";
            _this._enable = "enable";
            _this._checked = "checked";
            _this._hover = "hover";
            _this._arrtype = [_this._checkbox, _this._radio];
            _this._mobile = /ipad|iphone|ipod|android|blackberry|windows phone|opera mini|silk/i.test(navigator.userAgent);
            $.each(_this._arrtype, function(k, v) {
                _this.click(v);
                if(!_this._mobile){
                    _this.mouseover(v);
                    _this.mouseout(v);
                }
            });
        },
        click: function(elem) {
            var _this = this;
            elem = "." + elem;
            $(document).on("click", elem, function() {
                var $this = $(this),
                    _ins = $this.find("ins");
                if (!(_ins.hasClass(_this._disabled) || _ins.hasClass(_this._enable))) {
                    if ( !/radio/ig.test(elem) && !! _ins.hasClass(_this._checked)) {
                        _ins.removeClass(_this._checked).addClass(_this._hover);
                    } else {
                        if (/radio/ig.test(elem)) {
                            var _name = $this.attr("name");
                            $(elem + "[name=" + _name + "]").find("ins").removeClass(_this._checked);
                        }
                        $(elem).find("ins").removeClass(_this._hover);
                        _ins.addClass(_this._checked);
                    }
                }
            });
        },
        mouseover: function(elem) {
            var _this = this;
            elem = "." + elem;
            $(document).on("mouseover", elem, function() {
                var $this = $(this);
                var _ins = $this.find("ins");
                if (!(_ins.hasClass(_this._disabled) || _ins.hasClass(_this._enable) || _ins.hasClass(_this._checked))) {
                    _ins.addClass(_this._hover);
                    $this.css("cursor","pointer");
                } else{
                    $this.css("cursor","default");
                }
            });
        },
        mouseout: function(elem) {
            var _this = this;
            elem = "." + elem;
            $(document).on("mouseout", elem, function() {
                $(elem).find("ins").removeClass(_this._hover);
            });
        }
    };

    $.icheck.init();

})(jQuery);



//  form2json 将表单中提交的数据转换成json   开始

/**
 * 序列化表单中的属性（参数）值，  例如："prop1=value1&prop2=value2"
 *  当然 'prop.subprop=value' 形式也是支持的。
 *  @serializedParams looks like "prop1=value1&prop2=value2".  
Nested property like 'prop.subprop=value' is also supported 

*  页面调用形式： var json = $("#testform").form2json();
*            alert(json);
*  
**/
function paramString2obj (serializedParams) {
    var obj={};
    function evalThem (str) {
        var attributeName = str.split("=")[0];
        var attributeValue = str.split("=")[1];
       /* if(!attributeValue){
            return ;
        }
         */
        if(attributeValue == null){
            return ;
        }
        var array = attributeName.split(".");
        for (var i = 1; i < array.length; i++) {
            var tmpArray = Array();
            tmpArray.push("obj");
            for (var j = 0; j < i; j++) {
                tmpArray.push(array[j]);
            };
            var evalString = tmpArray.join(".");
            // alert(evalString);
            if(!eval(evalString)){
                eval(evalString+"={};");                
            }
        };
     
        eval("obj."+attributeName+"='"+attributeValue+"';");
         
    };
 
    var properties = serializedParams.split("&");
    for (var i = 0; i < properties.length; i++) {
        evalThem(properties[i]);
    };
 
    return obj;
}

$.fn.form2json = function(){
    var serializedParams = this.serializeForm();
    //这里用decodeURIComponent是因为serialize()方法是对数据进行encodeURIComponent过的
    var obj = paramString2obj(decodeURIComponent(serializedParams));
    return JSON.stringify(obj);
}

//form2json 将表单中提交的数据转换成json   结束



//重新包装数据格式
function parseData(data){
    if(data.result!=null && data.result.result!=null){
        var resultData;
        if(Array.isArray(data.result.result))
        {
            resultData =  {
                "aaData": data.result.result,
                "iTotalRecords": data.result.totalRows,
                "iRecordsDisplayrds": data.result.totalRows
            }
        }else if(Array.isArray(data.result.result.list)) {//result内部为带有统计信息列表
            resultData =  {
                "aaData": data.result.result.list,
                "iTotalRecords": data.result.totalRows,
                "iRecordsDisplayrds": data.result.totalRows
            }
        }

        return resultData;
    }else{
        return {
            "aaData": [],
            "iTotalRecords": 0,
            "iRecordsDisplayrds": 0
        };
    }
}


//全选事件
function checkAll(all,id){
    if($(all).is(":checked")){
        $("#"+id+" tbody tr td:first-child").each(function(){
            $(this).find("input[type=checkbox]").prop("checked",true);
        });
    }else{
        $("#"+id+" tbody tr td:first-child").each(function(){
            $(this).find("input[type=checkbox]").prop("checked",false);
        });
    }
}

//复选框事件
function tabCheck(a){
    if($(a).is(":checked")){
        $(a).prop("checked",true);
    }else{
        $(a).prop("checked",false);
    }
}

function backTip(data,flag){
    var iconcls= "fa fa-check fa-2x fadeInRight animated";
    var col = '#739E73';
    if(flag=='N'){
        iconcls = "fa fa-times fa-2x fadeInRight animated";
        col = '#C46A69';
    }
    $.smallBox({
        title : "提示信息",
        content : "<i class='fa fa-clock-o'></i> <i>"+data.message+"</i>",
        color : col,
        iconSmall : iconcls,
        timeout : 3000
    });
}


/**
 * 适用于rest的ajax，封装了错误处理机制
 */
jQuery.restAjax = function(data){
    if($("#loading").size()<1){
        $("body").append('<div id="loading"></div>')
        $("#loading").css({"position":"fixed","_position":"absolute","top":"50%","left":"50%"
            ,"width":"124px","height":"124px","overflow":"hidden","background":"url(http://7xrml4.com2.z0.glb.qiniucdn.com/ajax-loader.gif) no-repeat"
            ,"z-index":"7","margin":"-62px 0 0 -62px","display":"none"
        });
    }
    $("#loading").show();

    if(!data.cache && data.type.toUpperCase() == "GET"){
        data.url = data.url.indexOf('?') > 0 ? (data.url + "&expireToken="+Math.random()) : (data.url + "?expireToken="+Math.random());
    }

    data.beforeSend = function (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("appKey", "LexingInternalApp");
        XMLHttpRequest.setRequestHeader("Content-Type", "application/json;charset=utf-8");
    },

    data.complete = function(){
        $("#loading").fadeOut();
    }

    data.error = function(response){
        if(response.getResponseHeader("content-Type").indexOf("application/json") > -1){
            var content = $.parseJSON(response.responseText);
            if(content.status === 500 || content.status === 401 || content.status === 403 || content.status === 405){
                backTip(content,'N');
            }
        }else{
            backTip({message:'请求发生未知错误!'},'N');
        }

    };
    jQuery.ajax(data);
}

/**
 * 和PHP一样的时间戳格式化函数
 * @param  {string} format    格式
 * @param  {int}    timestamp 要格式化的时间 默认为当前时间
 * @return {string}           格式化的时间字符串
 */
function date(format, timestamp){
    var a, jsdate=((timestamp) ? new Date(timestamp) : new Date());
    var pad = function(n, c){
        if((n = n + "").length < c){
            return new Array(++c - n.length).join("0") + n;
        } else {
            return n;
        }
    };
    var txt_weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    var txt_ordin = {1:"st", 2:"nd", 3:"rd", 21:"st", 22:"nd", 23:"rd", 31:"st"};
    var txt_months = ["", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];
    var f = {
        // Day
        d: function(){return pad(f.j(), 2)},
        D: function(){return f.l().substr(0,3)},
        j: function(){return jsdate.getDate()},
        l: function(){return txt_weekdays[f.w()]},
        N: function(){return f.w() + 1},
        S: function(){return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th'},
        w: function(){return jsdate.getDay()},
        z: function(){return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0},

        // Week
        W: function(){
            var a = f.z(), b = 364 + f.L() - a;
            var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
            if(b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b){
                return 1;
            } else{
                if(a <= 2 && nd >= 4 && a >= (6 - nd)){
                    nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
                    return date("W", Math.round(nd2.getTime()/1000));
                } else{
                    return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
                }
            }
        },

        // Month
        F: function(){return txt_months[f.n()]},
        m: function(){return pad(f.n(), 2)},
        M: function(){return f.F().substr(0,3)},
        n: function(){return jsdate.getMonth() + 1},
        t: function(){
            var n;
            if( (n = jsdate.getMonth() + 1) == 2 ){
                return 28 + f.L();
            } else{
                if( n & 1 && n < 8 || !(n & 1) && n > 7 ){
                    return 31;
                } else{
                    return 30;
                }
            }
        },

        // Year
        L: function(){var y = f.Y();return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0},
        //o not supported yet
        Y: function(){return jsdate.getFullYear()},
        y: function(){return (jsdate.getFullYear() + "").slice(2)},

        // Time
        a: function(){return jsdate.getHours() > 11 ? "pm" : "am"},
        A: function(){return f.a().toUpperCase()},
        B: function(){
            // peter paul koch:
            var off = (jsdate.getTimezoneOffset() + 60)*60;
            var theSeconds = (jsdate.getHours() * 3600) + (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
            var beat = Math.floor(theSeconds/86.4);
            if (beat > 1000) beat -= 1000;
            if (beat < 0) beat += 1000;
            if ((String(beat)).length == 1) beat = "00"+beat;
            if ((String(beat)).length == 2) beat = "0"+beat;
            return beat;
        },
        g: function(){return jsdate.getHours() % 12 || 12},
        G: function(){return jsdate.getHours()},
        h: function(){return pad(f.g(), 2)},
        H: function(){return pad(jsdate.getHours(), 2)},
        i: function(){return pad(jsdate.getMinutes(), 2)},
        s: function(){return pad(jsdate.getSeconds(), 2)},
        //u not supported yet

        // Timezone
        //e not supported yet
        //I not supported yet
        O: function(){
            var t = pad(Math.abs(jsdate.getTimezoneOffset()/60*100), 4);
            if (jsdate.getTimezoneOffset() > 0) t = "-" + t; else t = "+" + t;
            return t;
        },
        P: function(){var O = f.O();return (O.substr(0, 3) + ":" + O.substr(3, 2))},
        //T not supported yet
        //Z not supported yet

        // Full Date/Time
        c: function(){return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P()},
        //r not supported yet
        U: function(){return Math.round(jsdate.getTime()/1000)}
    };

    return format.replace(/[\\]?([a-zA-Z])/g, function(t, s){
        if( t!=s ){
            // escaped
            ret = s;
        } else if( f[s] ){
            // a date function exists
            ret = f[s]();
        } else{
            // nothing special
            ret = s;
        }
        return ret;
    });
}


//form2json 将表单中提交的数据转换成json   结束

function jsonToStr(obj){
    var THIS = this;
    switch(typeof(obj)){
        case 'string':
            return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
        case 'array':
            return '[' + obj.map(THIS.jsonToStr).join(',') + ']';
        case 'object':
            if(Object.prototype.toString.call(obj) === '[object Array]'){
                var strArr = [];
                var len = obj.length;
                for(var i=0; i<len; i++){
                    strArr.push(THIS.jsonToStr(obj[i]));
                }
                return '[' + strArr.join(',') + ']';
            }else if(obj==null){
                return 'null';
            }else{
                var string = [];
                for (var property in obj) string.push(THIS.jsonToStr(property) + ':' + THIS.jsonToStr(obj[property]));
                return '{' + string.join(',') + '}';
            }
        case 'number':
            return obj;
        case false:
            return obj;
        case 'boolean':
            return obj;
    }
}

function strToJson(str){
    if(str != null && typeof str != 'undefined')
    {
        str = str.replace(/\r\n/g,"\\n");
    }
    return eval('(' + str + ')');
}
