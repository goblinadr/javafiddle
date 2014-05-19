$(function() {
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function(e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('glyphicon glyphicon-folder-open').removeClass('glyphicon glyphicon-folder-close');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('glyphicon glyphicon-folder-close').removeClass('glyphicon glyphicon-folder-open');
        }
        e.stopPropagation();
    });
});

$.ajax({
    url: 'http://localhost:8080/JavaFiddle-war/' + '/webapi/profileTree/treeElements',
    type: 'GET',
    contentType: "application/json",
    data: {},
    success: function(data) {
        alert('success: ' + data);
        var d = data;
        var root = $('#rootTree');
        var ulElem = $('<ul></ul>');
        ulElem.appendTo(root);
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
    var spanElem = $('<span id = ' + obj.id + '> </span>');
    spanElem.appendTo(liElem);
    var iElem = $('<i class="glyphicon glyphicon-list-alt"></i>');
    iElem.appendTo(spanElem);
    spanElem.append(document.createTextNode(obj.name));
    if ( ((obj.type == "Folder") || (obj.type == "Project")) && obj.child.length > 0){
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