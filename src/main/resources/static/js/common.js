function showMsg(msg, success,url) {
    var lf=msg.length*10;
    lf=lf>70?lf:70;
    msg = success ? "<i class='icon glyphicon glyphicon-ok-sign'></i>" + msg : "<i class='icon glyphicon glyphicon-info-sign'></i>" + msg;
    $(".show-msg").html(msg);
    $(".show-msg").css("margin-left",-lf+"px");
    $(".show-msg").show();
    setTimeout(function () {
        $(".show-msg").hide();
    }, 2000);
    if (success&&undefined!=url) {
        setTimeout(function () {
            location.href=url;
        }, 1000);
    }
}


function myDiv(text) {
    this.Dom = document.createElement("div");
    this.Dom.className="show-msg";
    this.Dom.innerHTML = text;
}

myDiv.prototype.appendTo = function (node) {
    node.append(this.Dom);
    return this;
}
myDiv.prototype.css = function (option) {
    for (var key in option) {
        this.Dom.style[key] = option[key];
    }
    return this;
}

/*
var div=new myDiv("dib");
div.appendTo(document.body).css({
    "height":"200px",
    "width":"200px",
    "backgroundColor":"red"
});*/
