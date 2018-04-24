var itemsArray=[];
var HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() { 
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open( "GET", aUrl, true );            
        anHttpRequest.send( null );
    }
}

var catItems = function(url){
    var client = new HttpClient();
    
    client.get(url, function(response) {
        //console.log(response);
        var el = document.createElement( 'html' );
        el.innerHTML = response
        //console.log(el.getElementsByTagName('a'));
        var itArray = el.getElementsByTagName('a');
        console.log(itArray);
        for(var i=1;i<itArray.length;i++){
            var clientCatUrl = url+"/"+(itArray[i].innerHTML).trim();                                            
            console.log(clientCatUrl);
            itemsArray[i-1]=clientCatUrl;
        }       
    });
    return itemsArray;
}

var url = "http://localhost:8080/PinterestUI/images/users/"+"username"
var iTems = catItems(url, function(resArr){
    console.log("results here" +resArr)
});
