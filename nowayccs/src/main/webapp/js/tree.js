function radioTable(eventFlag, url) {
//	var oTR = obj.nextSibling;
	switch(eventFlag){
		case 1:
		case 5:  //ÒµÎñ×ÉÑ¯
		    reloadMain(url);
		    $("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");
//			if(bbTR.style.display == "block"){
//				bbTR.style.display = "none";
//			}
//			if(ccTR.style.display == "block"){
//				ccTR.style.display = "none";
//			}
//			if(ddTR.style.display == "block"){
//				ddTR.style.display = "none";
//			}
//			if(ffTR.style.display == "block"){
//				ffTR.style.display = "none";
//			}
			break;
		case 2:
		    $("#bbTR").css("display", "block");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");
			break;
		case 3:		
		    $("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "block");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");
			break;
		case 4:
			$("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "block");
		    $("#ffTR").css("display", "none");
			break;
		case 6:
			$("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "block");
			break;
	}
}

			function reloadMain(url){
 		 		parent.main.location.href=url;
			}
