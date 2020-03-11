function showMsg(msg, success,url) {
    var lf=msg.length*10;
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
