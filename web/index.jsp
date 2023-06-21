<%--
  Created by IntelliJ IDEA.
  User: 赏
  Date: 2023/4/19
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>随机一题</title>
    <link rel="stylesheet" href="css/regcss.css">
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>

  </head>
  <body>

  <div id="div0">
    <div id="div_reg">
      <div id="div1_top">
        <span id="span">人面不知何处去<br>桃花依旧笑春风😅</span><br>
      </div>

      <div id="div1">
        <textarea id="area" name="comment" rows="13" cols="60" style="font-size: 16px"></textarea>
      </div>

      <div id="div_exact">
        <input type="button" value="抽取题目" id="extract">
        <input type="button" value="显示答案" id="show">
      </div>

    </div>
  </div>


  <script type="text/javascript">
    var mode = -1;
    alert("可以使用 <b>insert键</b> 来切换模式 \n 使用 <b>delete键</b> 来清空框内内容")

    $("#extract").click(function(){
      $("#area").val("")
      var url = "/selectServlet"
      var data = null;
      $.post(url, data, function (result) {
        $("#span").text(result);
      })
    })

    $("#show").click(function(){
      var url = "/selectServlet"
      var data = null;
      $.get(url, data, function (result) {
        if (mode == 1){
          alert(result)           // 弹框模式
        }else {
          $("#area").val(result) // 框内赋值模式
        }

      })
    })

    $(document).keydown(function(event) {
      if (event.keyCode == 38) {
        if (mode == -1 )
        $('#extract').click();
      }else if (event.keyCode == 40){
        if (mode == -1)
        $('#show').click();
      }else if (event.keyCode == 46){
        $("#area").val("")
      }else if (event.keyCode == 45){
        mode = -1 * mode
        if (mode == 1){
          alert("写题模式")
        }else {
          alert("快速刷题模式 \n 你可以使用\n ↑ 切换题目 \n ↓ 显示答案")
        }
      }
    });

  </script>


  </body>
</html>
