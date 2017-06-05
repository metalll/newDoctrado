/**
 * Created by NSD on 24.05.17.
 */
$(document).ready(function () {


    $('.carousel.carousel-slider').carousel({fullWidth: true});
    // $('.button-collapse').sideNav();

    setInterval(function() {
        $('.carousel').carousel('next');
    }, 5000);



    getCurentUser();

});