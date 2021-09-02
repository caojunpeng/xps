
    //阻止冒泡
    function cancelBubble(){
        var e=getEvent();
        if(window.event){
            //e.returnValue=false;//阻止自身行为
            e.cancelBubble=true;//阻止冒泡
        }else if(e.preventDefault){
            //e.preventDefault();//阻止自身行为
            e.stopPropagation();//阻止冒泡
        }
    }

    //login
    function menuToPage(url) {
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            success: function (obj) {
                $(".layui-body").html(obj);
            }
        });
    }
