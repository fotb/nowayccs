function radioTable(eventFlag, url) {
//	var oTR = obj.nextSibling;
	switch(eventFlag){
		case 1:
		    reloadMain(url);
/*			if($("#aaTR").css("display") == "none"){
				$("#aaTR").css("display", "block");
			} else {
				$("#aaTR").css("display", "none");
			}
		    $("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");*/
		case 12:
			reloadMain(url);
		case 5:  //ҵ����ѯ
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
			if($("#bbTR").css("display") == "none"){
				$("#bbTR").css("display", "block");
			} else {
				$("#bbTR").css("display", "none");
			}
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");
			break;
		case 3:		
		    $("#bbTR").css("display", "none");
			if($("#ccTR").css("display") == "none"){
				$("#ccTR").css("display", "block");
			} else {
				$("#ccTR").css("display", "none");
			}
		    //$("#ccTR").css("display", "block");
		    $("#ddTR").css("display", "none");
		    $("#ffTR").css("display", "none");
			break;
		case 4:
			$("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    //$("#ddTR").css("display", "block");
		    
			if($("#ddTR").css("display") == "none"){
				$("#ddTR").css("display", "block");
			} else {
				$("#ddTR").css("display", "none");
			}
		    $("#ffTR").css("display", "none");
			break;
		case 6:
			$("#bbTR").css("display", "none");
		    $("#ccTR").css("display", "none");
		    $("#ddTR").css("display", "none");
		    //$("#ffTR").css("display", "block");
			if($("#ffTR").css("display") == "none"){
				$("#ffTR").css("display", "block");
			} else {
				$("#ffTR").css("display", "none");
			}
		    
			break;
	}
}

			function reloadMain(url){
 		 		parent.main.location.href=url;
			}
