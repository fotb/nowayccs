function sl_check(field, name, maxlength, minlength)
{
	var sMsg="";
	if(minlength != 0 && field.value == "")
	{
    sMsg=name+"\t不能为空，请您填写"+name+"！\n\n";
		field.focus();
    return sMsg;
	}
	if(!no_deviant_char(field,name)) {
    sMsg+=name+"\t中不能包含单引号等非法字符！\n\n";
    return sMsg;
  }

	if(minlength != 0 && ansiLength(field.value) < minlength)
	{
		if(minlength == maxlength)
      sMsg+=name+"\t的长度必须是"+minlength+"位，请您重新填写！\n\n";
		else
      sMsg+=name+"\t的长度至少需要"+minlength+"位以上，请您重新填写！\n\n";
		field.focus();
    return sMsg;
	}
	if(maxlength != 0 && ansiLength(field.value) > maxlength)
	{
    sMsg+=name+"\t的长度不能超过"+maxlength+"个字符，请您重新填写！\n\n";
		field.focus();
    return sMsg;
	}
  return sMsg;
}

function sl_alert(errinfo, url)
{
	alert("系统提示：　　　　\n\n" + errinfo + "\n\n");
	if (url != null)
		location.replace(url);
}

function sl_checkChoice(field, name)
{
  var sMsg="";
	if (field.value == null || field.value == "")
	{
    sMsg="请选择"+name+"！\n\n";
		field.focus();
	}
  return sMsg;
}


function sl_confirm(confirm_info, vbVersion)
{
	if (vbVersion == null){
        return confirm("系统确认：\n\n您确认要" + confirm_info + "吗？");
    }
	else
		return sl_vb_comfirm("系统确认：\n\n您确认要" + confirm_info + "吗？");
}

function sl_check_update(doNew)
{
	if (doNew == "null")
		return sl_confirm("保存");
	else
		return sl_confirm("保存修改");
}

function no_deviant_char(s, alertwords)
{
	if (s.value.indexOf("'", 0) >= 0)
	{
		s.focus();
		return false;
	}
	return true;

}

function ansiLength(str)
{
	var len = 0;
	for (i = 0; i < str.length; i++)
	{
		if (str.charCodeAt(i) > 127)
			len = len + 2;
		else
			len++;
	}
	return len;
}

function sl_checkNum(field, name, maxlength, minlength)
{
	var i, str, sMsg;
	sMsg=sl_check(field, name, maxlength, minlength);
	str =""+Math.abs(field.value);

  if(sMsg==""){
       if(!str.match("^[0-9]+$")){
          sMsg+=name+"\t必须是整数数字，请您重新填写！\n\n";
          field.focus();
          return sMsg;
      }
  }

	return sMsg;
}

/*
intLength:全部数字限制
floatLength：小数位数字限制
minlength：最小长度
*/
function sl_checkFloat(field, name, intLength,floatLength, minlength)
{
	var str, sMsg;
	sMsg=sl_check(field, name, intLength+floatLength+1, minlength);
	str =""+Math.abs(field.value);

  if(sMsg!=""){
  	return sMsg;
  }
  var s1 = str.replace(/\./g,"");
  if(str.length-s1.length>1){
  	sMsg+=name+"\t格式不正确，只能存在一个小数点，请您重新填写！\n\n";
	  field.focus();
	  return sMsg;
  }
	if(!s1.match("^[0-9]+$")){
	  sMsg+=name+"\t必须是数字，请您重新填写！\n\n";
	  field.focus();
	  return sMsg;
	}
  var pos = str.indexOf(".");
  if(pos!=-1){
  	if(str.length-pos>floatLength+1){
      sMsg += name+"\t小数部分不能超过"+floatLength+"位，请调整\n\n";
		  return sMsg;
    }
  }
  return sMsg;
}


function confirmRemov(element)
{
	if (element == null)
	{
		sl_alert("未选定任何记录！");
		return false;
	}
	if(checkedCount(element) == 0)
	{
		sl_alert("请选定要删除的记录！");
		return false;
	}
	return sl_confirm('对删除选定的记录');
}


function checkedCount(element)
{
	var iCount = 0;
	var i;

	if(element == null)
		return 0;

	if(element.length == null)
	{
		if(element.checked)
			return 1;
		else
			return 0;
	}

	for(i = 0; i < element.length; i++)
		if(element[i].checked)
			iCount++;

	return iCount;
}


function sl_checkEmail(s, name)
{
  var sMsg;
	var len=trim(s.value).length;
	sMsg=sl_check(s,name,40,0);
	if(len !=0)
  {
		pos1 = trim(s.value).indexOf("@");
		pos2 = trim(s.value).indexOf(".");
		pos3 = trim(s.value).lastIndexOf("@");
		pos4 = trim(s.value).lastIndexOf(".");
		//check '@' and '.' is not first or last character
		if ((pos1 <= 0)||(pos1 == len)||(pos2 <= 0)||(pos2 == len))
		{
			sMsg+="请您输入有效的"+ name +"！\n\n";
			s.focus();
			return sMsg;
		}
		else
		{
			//check @. or .@
			if( (pos1 == pos2 - 1) || (pos1 == pos2 + 1)
			  || ( pos1 != pos3 )  //find two @
			  || ( pos4 < pos3 )  //. should behind the '@'
			  || (pos4 == len - 1) ) //. should not in the last letter
			{
				sMsg+="请您输入有效的"+ name +"！\n\n";
				s.focus();
				return sMsg;
			}
		}
		if (!isCharsInBag( trim(s.value), "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_@"))
		{
			sMsg+=name + "中只能包含字符ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_@\n" + "请您重新输入"+ name+"\n\n";
			s.focus();
			return sMsg;
		}
	}
	return sMsg;
}

function isCharsInBag(s, bag)
{
	var i;
	// Search through string's characters one by one.
	// If character is in bag, append to returnString.
	for (i = 0; i < s.length; i++)
	{
		// Check that current character isn't whitespace.
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1) return false;
	}
	return true;
}

function isNull(str)
{
	var flag = false;
	for (i = 0; i < str.length; i++)
	{
		if (str.charAt(i) != ' ')
			flag = true;
	}
	return !flag;
}


//trim str.
function trim(str)
{
  var strTmp;
  if ((str=="")||(str==null))
    return "";
  for(var i = 0 ; i<str.length && str.charAt(i)==" " ; i++ ) ;
  strTmp = str.substring(i,str.length);

  for(var i = strTmp.length - 1; i > 0 && strTmp.charAt(i)==" "; i--) ;
  strTmp = strTmp.substring(0,i+1);

  return strTmp;
}

function selectAll(checkname)
{
	if(checkname == null)
		return;

	var iCount = checkedCount(checkname);
	var i;

	if(checkname.length == null)
	{
		checkname.checked = iCount < 1;
		return;
	}

	for(i = 0; i < checkname.length; i++)
		checkname[i].checked = iCount < checkname.length;
}


function checkedTextCount(element,element_txt)
{
	var iValid = true;
	var i;

	if(element_txt.length == null)
	{
		if(element.checked && element_txt.value=="")
			iValid=false;
	}

	for(i = 0; i < element.length; i++){
		if(element[i].checked && element_txt[i].value=="")
    {
			iValid=false;
      break;
    }
  }
  return iValid;
}

function sl_checkPhone(field, name, maxlength, minlength){
  var sMsg=sl_check(field, name, maxlength, minlength);
  var s=trim(field.value);
  if(sMsg==""){
    if(!s.match("^[0-9\- ]+$")){
      sMsg+=name+"不是有效电话号码！\n\n";
      field.focus();
      return sMsg;
    }
  }
  return sMsg;
}

function sl_checkDate(field, name){
  var sMsg=sl_check(field, name, 10,0);
  var str=trim(field.value);
  if(str!=""){
    var ary=str.split("-");
    if(ary.length!=3){
      sMsg+=name+"必须为日期格式yyyy-mm-dd！\n\n";
      return sMsg;
    }
    if(!(isInt(ary[0])&&isInt(ary[1])&&isInt(ary[2]))){
      sMsg+=name+"必须为日期格式yyyy-mm-dd！\n\n";
      return sMsg;
    }
  }
  return sMsg;
}
function isInt(s){
	if (s.match("^[0-9]+$"))
	{
      return true;
    }
	else
	{
	  return false;
  }
}

//比较sTime1与sTime2。
function compareDate(sTime1,sTime2) {
	t1 = Date.parse(sTime1);
	t2 = Date.parse(sTime2);
	return t1 - t2;
}
//如果Date是以"-"分隔(如2004-11-15),则先转化"-"为"/",再比较
//如果前面的日期大于后面的日期则返回结果>0, 否则<0
function newCompareDate(Date1, Date2) {
	return compareDate(Date1.replace("-","/"),Date2.replace("-","/"));
}

//函数名：fucPWDchk
//功能介绍：检查是否含有字母
//参数说明：要检查的字符串
//返回值：false：含有 true：全部为字母
function fucPWDchk(str)
{
	var strSource ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var ch;
	var i;
	var temp;

	for(i=0;i<=(str.length-1);i++)
	{
		ch = str.charAt(i);
		temp = strSource.indexOf(ch);
		if (temp==-1)
		{
			return false;
		}
	}
	if(strSource.indexOf(ch)==-1)
	{
		return false;
	}
	else
	{
		return true;
	}
}

//函数名：getRadioValue
//功能介绍：取单选框的值
//参数说明：单选框对象
//返回值：单选框的值
function getRadioValue(obj){
    var ary = obj;
    var rtn="";
    if(ary.value==null){
        for(i=0;i<ary.length;i++){
            if(ary[i].checked){
                rtn=ary[i].value;
                break;
            }
        }
    }
    else {
        if(obj.checked){
             rtn=obj.value;
        }
    }
    return rtn;
}
