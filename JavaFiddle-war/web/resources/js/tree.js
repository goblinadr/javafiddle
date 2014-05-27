$.ajax({
    url: 'http://localhost:8080/JavaFiddle-war/' + '/webapi/profileTree/treeElements',
    type: 'GET',
    contentType: "application/json",
    data: {},
    success: function(data) {
        var d = data;
        var root = $('#rootTree');
        var ulElem = $('<ul></ul>');
        ulElem.appendTo(root);
        document.getElementById('numProjects').innerHTML = data.length;
        while (data.length > 0)
        {
            var el = data.pop();
            addChild(ulElem, el);
        }


        //var createdElem = addChild(root, obj);
    }
});

function addChild(root, obj) {
    var liElem = $('<li class="parent_li"></li>');
    liElem.appendTo(root);
    var divElem = $('<div></div>')
    divElem.appendTo(liElem)
    var spanElem = $('<span id = ' + obj.id + '> </span>');
    spanElem.click(function(){
       // alert("Click on element, ID: "+this.id); // нужный ID    
        var elementId = this.id;
        
        $.ajax({
            type: 'GET', //тип запроса: get,post либо head
            url: 'http://localhost:8080/JavaFiddle-war/webapi/profileTree/nodeId', //url адрес файла обработчика
            data: {'id':elementId}, //параметры запроса
            response: 'text',
            success: function(data) {
            }
        });
        
    });
    spanElem.appendTo(divElem);
    var iElem = $('<i class="glyphicon glyphicon-list-alt"></i>');
    iElem.appendTo(spanElem);
    spanElem.append(document.createTextNode(obj.name));
    if (((obj.type == "Folder") || (obj.type == "Project")) && obj.child.length > 0) {
        var ulElem = $('<ul></ul>');
        ulElem.appendTo(liElem);
        while (obj.child.length > 0)
        {
            var el = obj.child.pop();
            addChild(ulElem, el);
        }
    }
    return liElem;
}

