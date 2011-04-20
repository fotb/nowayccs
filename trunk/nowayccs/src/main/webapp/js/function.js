function getTimeStamp(){
  d=new Date();
  return ("ts="+d.getTime());
}

/*替换字符串某位置的字符*/
function replace(str,pos,ch){
  if ((pos>=str.length) || (pos<0)){
    return str;
  }
  var s1=str.substr(0,pos);
  var s2=str.substr(pos+1,str.length-pos);
  return (s1+ch+s2);
}

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
// if there are same char in strSource and strInvalid, return false.
//For example: strSource="Abcdef'gh", strInvalid=" ';*& ", the result is false. because
// the strSource has "'"
function isValidStr(strSource,strInvalid)
{
  var isValid = true;
  for(var i=0;i<strSource.length && isValid==true;i++) {
    for(var j=0;j<strInvalid.length;j++) {
      if (strSource.charAt(i)==strInvalid.charAt(j)) {
    	isValid = false;
    	break;
      }
    }
  }
  return isValid;
}

/*正常有效的输入值应该避免的字符*/
function getInValidStr()
{
  return("'\"?,*%;#&<>");
}
/*只是为了系统缓存设置的无效字符*/
function getInValidStr2()
{
  return(",;");
}


//whether str is a  number.
function isNumber(str)
{
  var oneChar;
  var isNum = true;
  
  str=str+"";
  
  if(str.length==0)
    return false;
 
  if(str.charAt(0)=="-"){
    str=str.substr(1,str.length-1);
  }
  
  if(str.length==0)
    return false;
    
  for(var i=0; i<str.length; i++) {
	  oneChar = str.charAt(i);
		if((oneChar<"0"||oneChar>"9") && oneChar!=".") {
      isNum = false;
    }
  }
  return isNum;
}

//whether str is a integer number.
function isInteger(str)
{
  var oneChar;
  var isNum = true;
  
  str=str+"";
  
  if(str.length==0)
    return false;

  if(str.charAt(0)=="-"){
    str=str.substr(1,str.length-1);
  }
  
  if(str.length==0)
    return false;i
  
  for(var i=0; i<str.length; i++) {
	  oneChar = str.charAt(i);
		if(oneChar<"0"||oneChar>"9") {
      isNum = false;
    }
  }
  return isNum;
}

//whether str is a  number,小数点必须在几位内
function isNumberXsd(str,nxsd)
{
  var oneChar;
  var isHasXsd=false;
  var iXSD=-1;
  
  var isNumTure=false;
  
  str=trim(str);
  
  if(nxsd<0)
    return false;
  else if(nxsd==0){
    return isInteger(str);
  }
  else if(!isNumber(str))
    return false;
    
  for(var i=str.length; i>0; i--) {
	  oneChar = str.charAt(i);
    if(oneChar!=".")
      iXSD=iXSD+1;
    else{
      isHasXsd=true; 
      break;
    }
  }
  
  if (isHasXsd){
    if(iXSD<=nxsd)
      isNumTure=true;
  }
  else{
    isNumTure=true;
  }
  
  return isNumTure;
}


//whether the length of str is too big.
function isLenValid(str,strLen)
{
  if (str.length > strLen) {
    return false;
  } else {
    return true;
  }
}
//replace the space in parameter "str" with "%20" after trim "str".
function convertStr(str){
  var t="";
  var strTmp = trim(str);
  for(var i=0;i<strTmp.length;i++){
    if (strTmp.charAt(i)==" ")
      t+="%20";
    else
      t+=strTmp.charAt(i);
  }
  return t;
}

function getTimeStr()
{
  var dt=new Date();
  return "timestamp="+dt.getTime();
}



function isValidStringObj(obj,objname,must){
  var s=trim(obj.value);

  if(s==""){
    if(must){
      alert(objname+"必须填写！");
      obj.focus();
      return false;
    }
    else{
      return true;
    }
  }
  if(!isValidStr(s,getInValidStr())){
    alert(objname+"不能包含字符"+getInValidStr()+"！");
    obj.focus();
    return false;
  }
   return true;
}

function isValidIntegerObj(obj,objname,must){
  var s=trim(obj.value);
  
  if(s==""){
    if(must){
      alert(objname+"必须填写！");
      obj.focus();
      return false;
    }
    else{
      return true;
    }
  }
  if(!isInteger(s)){
    alert(objname+"必须为整数！");
    obj.focus();
    return false;
  }
   return true;
}

function isValidNumberObj(obj,objname,must){
  var s=trim(obj.value);
  
  if(s==""){
    if(must){
      alert(objname+"必须填写！");
      obj.focus();
      return false;
    }
    else{
      return true;
    }
  }
  if(!isNumber(s)){
    alert(objname+"必须为数字！");
    obj.focus();
    return false;
  }
   return true;
}

function isValidDateObj(obj,objname,must){
  var s=trim(obj.value);
  
  if(s==""){
    if(must){
      alert(objname+"必须填写！");
      obj.focus();
      return false;
    }
    else{
      return true;
    }
  }
  if(!isDate(s)){
    alert(objname+"必须为日期格式yyyy-mm-dd！");
    obj.focus();
    return false;
  }
   return true;
}

function isValidStringLength(obj,objname,len){
  var s=trim(obj.value);
  if(strlength(s)>len){
    alert(objname+"超出长度限制 "+len+" ！");
    obj.focus();
    return false;
  }
  return true;
}

function isDate(s){
  var ary=s.split("-");
  if(ary.length!=3){
    return false;
  }
  if(!(isInteger(ary[0])&&isInteger(ary[1])&&isInteger(ary[2]))){
    return false;
  }
  return true;
}

function getDateStr(dt){
  return dt.getFullYear()+"-"+(dt.getMonth()+1)+"-"+dt.getDate();
}
function getShortDateStr(dt){
  return ""+(dt.getMonth()+1)+"."+dt.getDate();
}
function getDayHourStr(dt){
  return ConvNumAsLen((dt.getMonth()+1),2)+"-"+ConvNumAsLen(dt.getDate(),2)+" "+ConvNumAsLen(dt.getHours(),2)+":"+ConvNumAsLen(dt.getMinutes(),2);
}
function getHourStr(dt){
  return ConvNumAsLen(dt.getHours(),2)+":"+ConvNumAsLen(dt.getMinutes(),2);
}

function ConvStrAsLen(str,len){
  var iLen=0;
  var sTmp="";
  str=trim(""+str);
  if(str.length>0){
	  for(var i=0;i<str.length;i++){
	    if(str.charAt(i)>="\u0080"){
	      iLen+=2;
	    }
	    else{
	      iLen+=1;
	    }
	    if(iLen<len-3){
	    	sTmp=sTmp+str.charAt(i);
		  }
		  else{
		    break;
		  }
	  }
	  if(iLen>=len-3){
	    sTmp=sTmp+"...";
	  }
	}
	
  return sTmp;
}

//设置数字定长。不够前补0
function ConvNumAsLen(num,len){
  var str=trim(""+num);
  if(str.length>=len){
    return str;
  }
  s="";
  for(var i=0;i<len-str.length;i++){
    s=s+"0";
  }
  return s+str;
}