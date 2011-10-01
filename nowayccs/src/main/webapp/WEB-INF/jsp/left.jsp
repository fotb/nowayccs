<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/includes.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Untitled Document</title>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/tree.js"></script>
</head>

<body style="margin: auto">
<table width="150" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr valign="top">
    <td width="150" height="460" background="images/bg_left.gif"><table width="135" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="6"></td>
      </tr>
      <tr onclick="radioTable(1,'bizaccept.do')" style="cursor: hand;">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">业务受理</td>
      </tr>
      <tr onclick="radioTable(2)" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">业务处理</td>
      </tr>
      <tr id="bbTR" style="display: none" class="green">
        <td><table width="100%"  border="0" cellpadding="1" cellspacing="0">
          <tr>
            <td height="5"></td>
          </tr>
          <tr>
            <td class="leftmenu_02"><a href="bizlife.do" target="main"><img src="images/leftpic_10.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">生活服务类</font></a></td>
          </tr>
          <tr>
            <td class="leftmenu_02"><a href="bizaffair.do" target="main"><img src="images/leftpic_11.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">事务服务类</font></a></td>
          </tr>
          <tr>
            <td height="5"></td>
          </tr>
        </table></td>
      </tr>
      <tr onclick="radioTable(3)" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">客户回访</td>
      </tr>
	  <tr id="ccTR" style="display: none" class="green">
        <td><table width="100%"  border="0" cellpadding="1" cellspacing="0">
          <tr>
            <td height="5"></td>
          </tr>
          <tr>
            <td class="leftmenu_02"><a href="bizlifebackvst.do" target="main"><img src="images/leftpic_10.gif" width="70" height="30" border="0"><br>
                  <font color="#FFFFFF">生活服务类</font></a></td>
          </tr>
          <tr>
            <td class="leftmenu_02"><a href="bizaffairbackvst.do" target="main"><img src="images/leftpic_11.gif" width="70" height="30" border="0"><br>
                  <font color="#FFFFFF">事务服务类</font></a></td>
          </tr>
          <tr>
            <td height="5"></td>
          </tr>
        </table></td>
      </tr>
      <tr onclick="radioTable(4)" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">信息查询</td>
      </tr>
      <tr id="ddTR" style="display: none" class="green">
        <td><table width="100%"  border="0" cellpadding="1" cellspacing="0">
            <tr>
              <td height="5"></td>
            </tr>
            <tr>
              <td class="leftmenu_02"><a href="QryReport_infolist.do" target="main"><img src="images/leftpic_01.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">报表查询</font></a></td>
            </tr>
            <tr>
              <td class="leftmenu_02"><a href="QryInfoAccept_list.do" target="main"><img src="images/leftpic_02.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">求助信息查询</font></a></td>
            </tr>

            <tr>
              <td class="leftmenu_02"><a href="delivercount.do" target="main"><img src="images/leftpic_03.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">派单量查询</font></a></td>
            </tr>

            <tr>
              <td class="leftmenu_02"><a href="volunteer.do" target="main"><img src="images/leftpic_03.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">一技之长服务者查询</font></a></td>
            </tr>
             <tr>
              <td class="leftmenu_02"><a href="entprise.do" target="main"><img src="images/leftpic_03.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">企业查询</font></a></td>
            </tr>
                         <tr>
              <td class="leftmenu_02"><a href="opr_message_list.do" target="main"><img src="images/leftpic_03.gif" width="70" height="30" border="0"><br>
                <font color="#FFFFFF">信息栏</font></a></td>
            </tr>
			<tr>
              <td height="5"></td>
            </tr>
        </table></td>
      </tr>
      <tr onclick="radioTable(5, 'refer.do')" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">业务咨询</td>
      </tr>
<!--
        <tr onclick="radioTable(5, '/opr_infocommon_list.do')" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">业务咨询</td>

      </tr>-->

      <tr onclick="radioTable(6)" style="cursor: hand">
        <td height="24" background="images/bg_leftmenu.gif" class="leftmenu_01">系统设置</td>
      </tr>
      <tr id="ffTR" style="display: none" class="green">
        <td>
        <table width="100%"  border="0" cellpadding="1" cellspacing="0" bgcolor="#3F9A91">
  <tr>
   <td height="5"></td>
  </tr>
    <tr>
    <td class="leftmenu_02"><a href="sys_icdsetting_list.do" target="main"><img src="images/leftpic_12.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">坐席连接设置</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="user.do" target="main"><img src="images/leftpic_12.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">用户管理</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="role.do" target="main"><img src="images/leftpic_04.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">角色权限维护</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="area.do" target="main"><img src="images/leftpic_05.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">街道社区维护</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="entcategory.do" target="main"><img src="images/leftpic_06.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">服务企业类别维护</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="entprise.do" target="main"><img src="images/leftpic_07.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">服务企业维护</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="volunteer.do" target="main"><img src="images/leftpic_08.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">一技之长服务者维护</font></a></td>
  </tr>
  <tr>
    <td class="leftmenu_02"><a href="dict.do" target="main"><img src="images/leftpic_09.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">数据字典维护</font></a></td>
  </tr>
  <!--
   <tr>
    <td class="leftmenu_02"><a href="/opr_infocommon_whlist.do" target="main"><img src="images/leftpic_09.gif" width="70" height="30" border="0"><br>
    <font color="#FFFFFF">业务咨询维护</font></a></td>
  </tr>
  -->
</table>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
