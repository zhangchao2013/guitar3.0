/**
 * 
 */
$(document).ready(function(){
	$.getJSON("../guitarJson",function(json){
		var html="";
		for(var i=0;i<json.length;i++){
			var serialNumber=json[i].serialNumber;
			var price=json[i].price;
			var builder=json[i].builder;
			var model=json[i].model;
			var type=json[i].type;
			var backwood=json[i].backwood;
			var topwood=json[i].topwood;
			var numStrings=json[i].numStrings;
			html+="<tr><td>"+serialNumber+"</td><td>"+builder+"</td><td>"+model+"</td><td>"+type+"</td>";
			html+="<td>"+numStrings+"</td><td>"+topwood+"</td><td>"+backwood+"</td><td>"+price+"</td><td><button id='deleteGuitar' class='btn btn-default' onclick='deleteGuitar("+serialNumber+");'>删除</button></td></tr>";
		}
		$("#showguitar").append(html);
	});
});
function deleteGuitar(serialNumber){
	if(confirm("操作不可恢复，确认要删除选中的试吉他？")==true){
		window.location.href="../delete?serialNumber="+serialNumber; 
	}
}