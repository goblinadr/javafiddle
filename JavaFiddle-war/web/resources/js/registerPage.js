function registerListener(data){
    var status = data.status;
    switch (status){
         case "begin": // Before the ajax request is sent.
            // ...
            break;

        case "complete": // After the ajax response is arrived.
            // ...
            break;

        case "success": // After update of HTML DOM based on ajax response..
            updateModal();
            break;        
    }
    
}

function updateModal(){
    if(jQuery('#registerErrors>li').size() > 0){ 
        //custom processing of errors
    }
    else{
        jQuery('#reg').modal('hide');
    }    
}