/**
 * Created by uakruk on 12/25/16.
 */
 // you can also use "$(window).load(function() {"
 $(function() {
       // Slideshow 4
       $("#slider4").responsiveSlides({
             auto: true,
         pager: true,
         nav: false,
         speed: 500,
         namespace: "callbacks",
         before: function() {
           $('.events').append("<li>before event fired.</li>");
         },
         after: function() {
           $('.events').append("<li>after event fired.</li>");
         }
       });
     });