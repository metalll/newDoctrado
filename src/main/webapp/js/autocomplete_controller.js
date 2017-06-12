/**
 * Created by NSD on 12.06.17.
 */



function initAutocompete(autocompleteData) {


    var formatedAutocomplete = {};

    for (var i = 0; i < autocompleteData.length; i++) {
        //console.log(countryArray[i].name);
        formatedAutocomplete[autocompleteData[i].header] = autocompleteData[i].image; //countryArray[i].flag or null
    }
    $('input.autocomplete').autocomplete({
        data: formatedAutocomplete,
        limit: 8, // The max amount of results that can be shown at once. Default: Infinity.
    });

}
