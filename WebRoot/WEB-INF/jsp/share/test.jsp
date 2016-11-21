<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>分享</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
<style type="text/css">
	body{
	font-size: 1em;
	}
  .font-wartall{
    font-weight: 600;
  }
  .font25_a{
    font-size: 24px;
  }
  .padding{
  	padding:50px;
  }
  .ng-nvainfo{
    font-size: 12px;
    color: #999999;
    padding-bottom: 24px;
    border-bottom: 1px solid #999;
    margin-bottom: 20px;
    text-align: right;
  }
  .ng-newsDile{
	    font-size: 70px;
    margin: 15px 0 5px 0;
    text-align: center;
    font-weight: 900;
  }
  .ng-lpr{
    text-align: justify;
    line-height: 25px;
  }
  .ng-lpr title+p{
  	font-size: 50px;
    line-height: 70px;
    text-indent: 0em;
    text-align: center;
  }
  .ng-lpr p {
  line-height: 45px;
  text-indent: 2em;
  }
  .ng-lpr p img{
    width: 100%;
  }
  .ng-lpr img{
    width: 100%;
  }

</style>
  <body>
  <div class="news_content padding">

      <div class="ng-newsDile">${news.ftitle }</div>
      <div class="ng-nvainfo">
        <!--<em>-->
          <!--{{news.fbtime.time| dateFormatView}}-->
        <!--</em>-->
        &nbsp;&nbsp;
        <span class="">i宝</span>
      </div>
      <div class="ng-borderhr"></div>
      <div class="ng-lpr">
${news.fnrText } 
      </div>
    </div>
  </body>
</html>