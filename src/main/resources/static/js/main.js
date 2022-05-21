$(document).ready(function(){
    $('.table .eBtn').on('click', function(event){
        event.preventDefault();
        var houseId = $(this).attr('href');
        $('.myForm #houseId').attr('value', houseId);
        $('.myForm #exampleModalCenter').modal();
    });
});