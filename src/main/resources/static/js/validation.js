/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('document').ready(function(){
    var password = document.getElementById("password");
    
    var confirmPassword = document.getElementById("confirmPassword");
    
    function passwordValidation(){
        if (password.value != confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords Don't Match");
        } else {
            confirmPassword.setCustomValidity('');
        }
    }
    
    password.onchange = passwordValidation();
    confirmPassword.onkeyup = passwordValidation();
});
