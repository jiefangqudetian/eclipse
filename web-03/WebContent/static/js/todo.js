(function() {
    

    var data = [
        {title:"学习node.js",done:false },
        {title:"给经理发邮件",done:false },
        {title:"发送销售合同",done:false },
        {title:"给女朋友买礼物",done:false },
        {title:"发送进货合同",done:true },
        {title:"学习javascript",done:true }
    ];

    for(var i = 0; i < data.length; i++) {
        var todo = data[i];
        creatTodoElement(todo);
    }
    //创建li标签，放到ul标签下面
    function creatTodoElement(todo) {
        // 1.创建动态标签
        var li = document.createElement("li");
        var a = document.createElement("a");
        var checkSpan = document.createElement("span");
        var textSpan = document.createElement("span");
        var text = document.createTextNode(todo.title);

        // 2.添加标签的class属性
        checkSpan.setAttribute("class","checkbox");
        textSpan.setAttribute("class","text");
        a.setAttribute("href","javascript:;");

        // 3.组合
        li.appendChild(a);
        li.appendChild(textSpan);

        a.appendChild(checkSpan);
        textSpan.appendChild(text);

        // 4.list标签放 ul列表中
        if(todo.done) {
            var doneList = document.querySelector("#doneList");
            doneList.appendChild(li);
        } else {
            var newTodoList = document.querySelector("#newTodoList");
            newTodoList.appendChild(li);
        }


    }

    // input回车框事件
    var input = document.querySelector("#inputText");
    //onkeydown onkeyup onkeypress
    input.onkeyup = function(event){ // ？？？定义了一个函数么
        console.log(event.keyCode); //keyCode C大写
        if(event.keyCode == 13) { 
            //1.将输入的内容转换为li标签，放入ul
            var inputValue = input.value;
            console.log(input.value);
            if(inputValue.trim()) {
                var todo = {title:inputValue,done:false};
                creatTodoElement(todo);
            }
            //2.清空input框
            input.value = "";
        }
    }


    // checkbox 点击事件
    document.onclick = function(event) {  //触发onclick事件后，将onclick事件的event对象当作参数传入，事件触发后要执行的函数
        // console.log(event.target);
        var ev = event.target;           //将事件的target属性，赋值给ev对象，寻找点击的动态标签
        if(ev.getAttribute("class") == "checkbox") {
           // console.log(success);
            // 1.根据当前的span（checkbox）对象获得li对象
            // 2.根据li对象获得ul对象
            var li = ev.parentNode.parentNode;
            var ul = li.parentNode;
            if(ul.getAttribute("class") == "todoList done" ) {
                newTodoList.appendChild(li); 
            } else {
                doneList.appendChild(li);
            }
        }

    } 

    //把所有checkbox标签对象  即小方块 绑定点击事件；点击然后，判断checkbox标签对象所在的ul的class属性，是todoList的时候，放入newTodoList的ul标签，是newTodoList的时候，放入todoList的ul标签

    // var checkbox = document.querySelectorAll(".checkbox");
    // for( var i = 0; i < checkbox.length; i++) {
    //     var check = checkbox[i];
    //     check.onclick = function() {
    //         console.log("123456");
    //         console.log(typeof check);//checkbox[i]类型竟然是undifined，找不到？

    //         var ul = check.parentNode.parentNode.parentNode;
    //         if(ul.getAttribute("class") == "todoList done") {
    //             newTodoList.appendChild(checkbox[i].parentNode.parentNode);
    //         } else {
    //             doneList.appendChild(checkbox[i].parentNode.parentNode);
    //         }
    //     }
    // }

    
























})();