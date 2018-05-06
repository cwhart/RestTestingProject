$(document).ready(function () {

   $.ajax({
     type: 'GET',
     url: 'http://localhost:8080/contacts',
     success: function(contactArray) {
       var contactsDiv = $('#allContacts');

       $.each(contactArray, function(index, contact) {
         var contactInfo = '<p>';
         contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
         contactInfo += 'Company: ' + contact.company + '<br>';
         contactInfo += 'Email: ' + contact.email + '<br>';
         contactInfo += 'Phone: ' + contact.phone + '<br>';
         contactInfo += '</p>';
         contactInfo += '<hr>';

         contactsDiv.append(contactInfo);
       });
     },
     error: function() {
       alert("FAILURE!");
     }
   });

   $("#add-button").on("click", function() {
     console.log("WORKING");
     $.ajax({
       type: 'POST',
       url: 'http://localhost:8080/contact',
       data: JSON.stringify({
         firstName: $('#add-first-name').val(),
        lastName: $('#add-last-name').val(),
        company: $('#add-company').val(),
        phone: $('#add-phone').val(),
        email: $('#add-email').val(),
       }),
       headers: {
         'Accept' : 'application/json',
         'Content-Type': 'application/json'
       },
       'dataType': 'json',
       success: function(contact) {
         var newContactDiv = $('#newContact');


           var contactInfo = '<p>';
           contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
           contactInfo += 'Company: ' + contact.company + '<br>';
           contactInfo += 'Email: ' + contact.email + '<br>';
           contactInfo += 'Phone: ' + contact.phone + '<br>';
           contactInfo += '</p>';
           contactInfo += '<hr>';

           newContactDiv.append(contactInfo);

       },
       error: function() {
         alert('FAILURE');
       }
     });
   });

})
