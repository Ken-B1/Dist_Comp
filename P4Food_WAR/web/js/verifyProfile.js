var uname = $('#userid').text();
    if(uname=""){
        $('#loginmenu').show();
        $('#usernmenu').hide();
    }else{
        $('#loginmenu').hide();
        $('#usernmenu').show();
    }