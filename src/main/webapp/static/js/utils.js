function fetchRecords(url, callback, Method) {
	
  var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(data) {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          var data = xhr.responseText;
		  data = JSON.parse(data);
		  callback(data);
        } else {
		  
          // TODO remove log msg
          console.log('response was not 200', xhr.status)
          callback(null);
        }
      }
    }
    // Note that any URL fetched here must be matched by a permission in
    // the manifest.json file!
    xhr.open('GET', url, true);
    xhr.send();
}	

function ajaxCall(action, jsonData, onSuccess, onError, dataType){
	var defaultDataType = "json";
	if(dataType == null || dataType == "" || dataType == "undefined"){
		defaultDataType = "json";	
	}else {
		defaultDataType = dataType;
	}
	jQuery.ajax({
		url: action,
		data: jsonData,
		type: 'post',
		async: false,
		dataType: defaultDataType,
		success: function (data){
			
			if(onSuccess != null){
				onSuccess(data);
			}				
		},
		error: function(){
		
			if(onError != null){
				onError();
			}	
		}
	}); 
}



