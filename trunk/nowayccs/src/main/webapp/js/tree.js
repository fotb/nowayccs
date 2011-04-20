function radioTable(eventFlag, url) {
//	var oTR = obj.nextSibling;
	switch(eventFlag){
		case 1:
		case 5:
		    reloadMain(url);
			if(bbTR.style.display == "block"){
				bbTR.style.display = "none";
			}
			if(ccTR.style.display == "block"){
				ccTR.style.display = "none";
			}
			if(ddTR.style.display == "block"){
				ddTR.style.display = "none";
			}
			if(ffTR.style.display == "block"){
				ffTR.style.display = "none";
			}
			break;
		case 2:
			if(bbTR.style.display == "none") {
				bbTR.style.display = "block";
			} else {
				bbTR.style.display = "none";
			}
			if(ccTR.style.display == "block"){
				ccTR.style.display = "none";
			}
			if(ddTR.style.display == "block"){
				ddTR.style.display = "none";
			}
			if(ffTR.style.display == "block"){
				ffTR.style.display = "none";
			}
			break;
		case 3:		
		    if(ccTR.style.display == "none") {
				ccTR.style.display = "block";
			} else {
				ccTR.style.display = "none";
			}
			if(bbTR.style.display == "block"){
				bbTR.style.display = "none";
			}
			if(ddTR.style.display == "block"){
				ddTR.style.display = "none";
			}
			if(ffTR.style.display == "block"){
				ffTR.style.display = "none";
			}
			break;
		case 4:
			if(ddTR.style.display == "none") {
				ddTR.style.display = "block";
			} else {
				ddTR.style.display = "none";
			}
			if(bbTR.style.display == "block"){
				bbTR.style.display = "none";
			}
			if(ccTR.style.display == "block"){
				ccTR.style.display = "none";
			}
			if(ffTR.style.display == "block"){
				ffTR.style.display = "none";
			}
			break;
		case 6:
			if(ffTR.style.display == "none") {
				ffTR.style.display = "block";
			} else {
				ffTR.style.display = "none";
			}
			if(bbTR.style.display == "block"){
				bbTR.style.display = "none";
			}
			if(ccTR.style.display == "block"){
				ccTR.style.display = "none";
			}
			if(ddTR.style.display == "block"){
				ddTR.style.display = "none";
			}
			break;
	}
	

}

			function reloadMain(url){
 		 		parent.main.location.href=url;
			}
