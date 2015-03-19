var i = 0;
function timedCount(){
	i = i+1;
	//向 HTML 页面传回一段消息
	postMessage(i);
	setTimeout("timedCount()",500);
}

timedCount();