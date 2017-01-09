/**
 * Created by uakruk on 12/29/16.
 */
$(document).ready(function () {
    console.log("called");
    $("#arform").submit(function () {
        var formData = new FormData($(this)[0]);

        $.ajax({
            url: "/article",
            type: "POST",
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function(data) {
            },
            }
        );
    });
});