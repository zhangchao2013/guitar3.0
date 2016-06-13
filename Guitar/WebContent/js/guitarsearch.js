/**
 * 
 */
function search(){
	var builder=$("#builder").val();
	var type=$("#type").val();
	var model=$("#model").val();
	var numStrings=$("#numStrings").val();
	var topWood=$("#topWood").val();
	var backWood=$("#backWood").val();
	$.getJSON("../search",{Builder:builder,Type:type,Model:model,numStrings:numStrings,TopWood:topWood,BackWood:backWood}, function(json) {
		var html="";
			for(var i=0;i<json.length;i++){
				html+="<tr><td>"+json[i].serialNumber+"</td><td>"+json[i].builder+"</td><td>"+json[i].model+"</td><td>"+json[i].type+"</td>";
				html+="<td>"+json[i].numStrings+"</td><td>"+json[i].topwood+"</td><td>"+json[i].backwood+"</td><td>"+json[i].price+"</td></tr>";
			}
		$("#guitarsearch").append(html);
	});
}