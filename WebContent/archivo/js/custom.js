

$(document).on("click","ul.nav li.parent > a ", function(){          
    $(this).find('i').toggleClass("fa-minus");      
}); 

$(document).on("click",".close", function(){          
    $(this).parent().hide();      
}); 

$(".sidebar span.icon").find('em:first').addClass("fa-plus");

$("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    
