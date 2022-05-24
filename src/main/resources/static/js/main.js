$(document).ready(function(){
    $('.table .constraintBtn').on('click', function(event){
        event.preventDefault();
        var houseId = $(this).attr('href');
        $('.addConstraintForm #constraint_houseId').attr('value', houseId);
        $('.addConstraintForm #constraintModalCenter').modal();
    });
});

$(document).ready(function(){
    $('.table .imageBtn').on('click', function(event){
        event.preventDefault();
        var houseId = $(this).attr('href');
        $('.addImageForm #image_houseId').attr('value', houseId);
        $('.addImageForm #imageModalCenter').modal();
    });
});


$(document).ready(function(){
    $('.table .serviceBtn').on('click', function(event){
        event.preventDefault();
        var houseId = $(this).attr('href');
        $('.addServiceForm #service_houseId').attr('value', houseId);
        $('.addServiceForm #serviceModalCenter').modal();
    });
});

$(document).ready(function(){
    $('.table .advertiseBtn').on('click', function(event){
        event.preventDefault();
        var houseId = $(this).attr('href');
        $('.addAvailabilityForm #availability_houseId').attr('value', houseId);
        $('.addAvailabilityForm #availabilityModalCenter').modal();
    });
});