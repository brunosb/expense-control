$(document).ready(function () {

    //Esconder ao carregar a tela
    function esconderAoIniciar(){
        $('.show-hide-menu').trigger("click");
    }

    var $els = $('.main-menu a, .main-menu header');
    var count = $els.length;
    var grouplength = Math.ceil(count / 3);
    var groupNumber = 0;
    var i = 1;

    $('.main-menu').css('--count', count + '');
    $els.each(function (j) {
        if (i > grouplength) {
            groupNumber++;
            i = 1;
        }
        $(this).attr('data-group', groupNumber);
        i++;
    });

    $('.show-hide-menu').on('click', function (e) {
        e.preventDefault();
        $els.each(function (j) {
            $(this).css('--top', $(this)[0].getBoundingClientRect().top + ($(this).attr('data-group') * -15) - 20);
            $(this).css('--delay-in', j * .1 + 's');
            $(this).css('--delay-out', (count - j) * .1 + 's');
        });
        $('.main-menu').toggleClass('closed');
        e.stopPropagation();
    });

    esconderAoIniciar();
});



// run animation once at beginning for demo
/*
setTimeout(function () {
    $('.main-menu footer button').click();
    setTimeout(function () {
        $('.main-menu footer button').click();
    }, (count * 100) + 500);
}, 1000);*/
