
console.log('Initialising of websocket');
wsocket = new WebSocket("ws://localhost:8080/JavaFiddle-war/endpoint");
wsocket.onopen = function() {
    console.log('Connection established');
    console.log('User ID: '+userId);
    var message = {
        type: "init",
        data: userId
    };
    wsocket.send(JSON.stringify(message));
};
wsocket.onclose = function(event){
    console.log('Connection closed');    
}
wsocket.onerror = function(event){
    console.log('Error: '+event.message);
    
}
wsocket.onmessage = function(event){
    console.log('input message: '+event.data);    
}

jQuery('#editor').bind("keydown keypress", function(event) {
  console.log("Current position: " +$(this).caret());
  var caret = $(this).caret();
  
});


