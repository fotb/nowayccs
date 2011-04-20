function sl_check(field, name, maxlength, minlength)
{
	var sMsg="";
	if(minlength != 0 && field.value == "")
	{
    sMsg=name+"\t����Ϊ�գ�������д"+name+"��\n\n";
		field.focus();
    return sMsg;
	}
	if(!no_deviant_char(field,name)) {
    sMsg+=name+"\t�в��ܰ��������ŵȷǷ��ַ���\n\n";
    return sMsg;
  }

	if(minlength != 0 && ansiLength(field.value) < minlength)
	{
		if(minlength == maxlength)
      sMsg+=name+"\t�ĳ��ȱ�����"+minlength+"λ������������д��\n\n";
		else
      sMsg+=name+"\t�ĳ���������Ҫ"+minlength+"λ���ϣ�����������д��\n\n";
		field.focus();
    return sMsg;
	}
	if(maxlength != 0 && ansiLength(field.value) > maxlength)
	{
    sMsg+=name+"\t�ĳ��Ȳ��ܳ���"+maxlength+"���ַ�������������д��\n\n";
		field.focus();
    return sMsg;
	}
  return sMsg;
}

function sl_alert(errinfo, url)
{
	alert("ϵͳ��ʾ����������\n\n" + errinfo + "\n\n");
	if (url != null)
		location.replace(url);
}

function sl_checkChoice(field, name)
{
  var sMsg="";
	if (field.value == null || field.value == "")
	{
    sMsg="��ѡ��"+name+"��\n\n";
		field.focus();
	}
  return sMsg;
}


function sl_confirm(confirm_info, vbVersion)
{
	if (vbVersion == null){
        return confirm("ϵͳȷ�ϣ�\n\n��ȷ��Ҫ" + confirm_info + "��");
    }
	else
		return sl_vb_comfirm("ϵͳȷ�ϣ�\n\n��ȷ��Ҫ" + confirm_info + "��");
}

function sl_check_update(doNew)
{
	if (doNew == "null")
		return sl_confirm("����");
	else
		return sl_confirm("�����޸�");
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
          sMsg+=name+"\t�������������֣�����������д��\n\n";
          field.focus();
          return sMsg;
      }
  }

	return sMsg;
}

/*
intLength:ȫ����������
floatLength��С��λ��������
minlength����С����
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
  	sMsg+=name+"\t��ʽ����ȷ��ֻ�ܴ���һ��С���㣬����������д��\n\n";
	  field.focus();
	  return sMsg;
  }
	if(!s1.match("^[0-9]+$")){
	  sMsg+=name+"\t���������֣�����������д��\n\n";
	  field.focus();
	  return sMsg;
	}
  var pos = str.indexOf(".");
  if(pos!=-1){
  	if(str.length-pos>floatLength+1){
      sMsg += name+"\tС�����ֲ��ܳ���"+floatLength+"λ�������\n\n";
		  return sMsg;
    }
  }
  return sMsg;
}


function confirmRemov(element)
{
	if (element == null)
	{
		sl_alert("δѡ���κμ�¼��");
		return false;
	}
	if(checkedCount(element) == 0)
	{
		sl_alert("��ѡ��Ҫɾ���ļ�¼��");
		return false;
	}
	return sl_confirm('��ɾ��ѡ���ļ�¼');
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
			sMsg+="����������Ч��"+ name +"��\n\n";
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
				sMsg+="����������Ч��"+ name +"��\n\n";
				s.focus();
				return sMsg;
			}
		}
		if (!isCharsInBag( trim(s.value), "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_@"))
		{
			sMsg+=name + "��ֻ�ܰ����ַ�ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.-_@\n" + "������������"+ name+"\n\n";
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
      sMsg+=name+"������Ч�绰���룡\n\n";
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
      sMsg+=name+"����Ϊ���ڸ�ʽyyyy-mm-dd��\n\n";
      return sMsg;
    }
    if(!(isInt(ary[0])&&isInt(ary[1])&&isInt(ary[2]))){
      sMsg+=name+"����Ϊ���ڸ�ʽyyyy-mm-dd��\n\n";
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

//�Ƚ�sTime1��sTime2��
function compareDate(sTime1,sTime2) {
	t1 = Date.parse(sTime1);
	t2 = Date.parse(sTime2);
	return t1 - t2;
}
//���Date����"-"�ָ�(��2004-11-15),����ת��"-"Ϊ"/",�ٱȽ�
//���ǰ������ڴ��ں���������򷵻ؽ��>0, ����<0
function newCompareDate(Date1, Date2) {
	return compareDate(Date1.replace("-","/"),Date2.replace("-","/"));
}

//��������fucPWDchk
//���ܽ��ܣ�����Ƿ�����ĸ
//����˵����Ҫ�����ַ���
//����ֵ��false������ true��ȫ��Ϊ��ĸ
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

//��������getRadioValue
//���ܽ��ܣ�ȡ��ѡ���ֵ
//����˵������ѡ�����
//����ֵ����ѡ���ֵ
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
