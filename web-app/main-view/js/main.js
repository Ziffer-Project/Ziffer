

$(document).ready(function(){

    $.stellar();


    $('.flexslider').flexslider({
        animation : "slide",
        slideshowSpeed : 3000,
        animationSpeed : 600
    });

    TweenMax.from(".logo",4,{opacity:0});
    TweenMax.from(".logo",4,{left:-250,ease:Elastic.easeOut});
    var controller = $.superscrollorama();
    controller.addTween("#aboutTitle",TweenMax.from("#aboutTitle",1,{left:50,opacity:0}));
    controller.addTween("#imgabout",TweenMax.from(".aleft",2,{left:-50,opacity:0}));
    controller.addTween("#imgabout", TweenMax.from(".arigth",2,{left:50,opacity:0}));
    controller.addTween("#plataforma",  TweenMax.to("#plataformaTitle h1",2,{ fontSize:'80px',immediateRender:true, ease:Quad.easeInOut}));
    controller.addTween("#dispimg",    TweenMax.from("#dispimg",1,{ opacity:0}));
    controller.addTween("#labelIcon",TweenMax.from(".icon ",0.5,{ opacity:0,y:300,scale:2,rotation:360},0.5));
    controller.addTween("#nosotrosTitle h1",TweenMax.from("#nosotrosTitle h1",1,{left:-50,opacity:0}));


});



