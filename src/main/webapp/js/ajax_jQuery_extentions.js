/**
 * Created by NSD on 03.06.17.
 */
jQuery.each( [ "put", "delete", "post" , "get" ], function( i, method ) {
    jQuery[ method ] = function( url, data, callback, type ) {
        if ( jQuery.isFunction( data ) ) {
            type = type || callback;
            callback = data;
            data = undefined;
        }

        return jQuery.ajax({
            url: url,
            type: method,
            dataType: type,
            data: data,
            success: callback
        });
    };
});


//how it work ?
/*
$.put('http://stackoverflow.com/posts/22786755/edit', {text:'new text'}, function(result){
    console.log(result);
});

*/