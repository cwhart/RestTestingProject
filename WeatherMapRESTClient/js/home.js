$(document).ready(function () {

  $('#get-weather').click(function (event) {
    clearWeatherData();
    $('#errorMessages').empty();
    var key = 'dc32ab2b2258835f708f855997b6544c';
    //var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));

    //if(haveValidationErrors) {
    //  return false;
    //}

    $.ajax({
      
      type: 'GET',
      url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + $('#enter-zip').val() 
      +  '&APPID=' + key + '&units=' + $('#units').val(),      
      success: function(fiveDayForecastArray) {
        $('#displayWeatherDiv').show();
        var increment = 0;
        var iteration = 1;
        $.each(fiveDayForecastArray, function(index, contact) {
          var date = fiveDayForecastArray.list[increment].dt_txt;
          var description = fiveDayForecastArray.list[increment].weather[0].description;
          var icon = fiveDayForecastArray.list[increment].weather[0].icon;
          var highTemp = fiveDayForecastArray.list[increment].main.temp_min;
          var lowTemp = fiveDayForecastArray.list[increment].main.temp_max;
          increment += 8;
                  
          //$('#description').val(currentConditions.weather[0].description);
            
            var forecastDate = '<tr>';
            forecastDate += '<td>' + date + '</td>';
            forecastDate += '</tr>';  
          
            var displayConditions = '<img src="http://openweathermap.org/img/w/';
            displayConditions += icon + '.png"></img>';
            displayConditions += '</tr>';   

            var displayDescription = '<tr>';
            displayDescription += '<td>' + description + '</td>';
            displayDescription += '</tr>';  
                
            var displayHighTemp = '<tr>';
            displayHighTemp += '<td>' + highTemp + '</td>';
            displayHighTemp += '</tr>';  

            var displayLowTemp = '<tr>';
            displayLowTemp += '<td>' + lowTemp + '</td>';
            displayLowTemp += '</tr>';
              
            $('#day-' + iteration).append(forecastDate);
            $('#day-' + iteration).append(displayConditions);
            $('#day-' + iteration).append(displayDescription);
            $('#day-' + iteration).append(displayHighTemp);
            $('#day-' + iteration).append(displayLowTemp);
            iteration += 1;    
                
                
              });
        
    },
    error: function() {
      $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
    }
      
      
      
    })

    $.ajax({
      
      type: 'GET',
      url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + $('#enter-zip').val() 
      +  '&APPID=' + key + '&units=' + $('#units').val(),      
      success: function(currentConditionsResponse) {
        $('#displayWeatherDiv').show();
        var city = currentConditionsResponse.name;
        $('#current-conditions').text("Current Conditions in " + city);

        var description = currentConditionsResponse.weather[0].description;
        var temp = currentConditionsResponse.main.temp;
        var humidity = currentConditionsResponse.main.humidity;
        var windSpeed = currentConditionsResponse.wind.speed;
        var icon = currentConditionsResponse.weather[0].icon;
        
          //$('#description').val(currentConditions.weather[0].description);
            
            var conditions = '<img src="http://openweathermap.org/img/w/';
                conditions += icon + '.png"></img>';
                conditions += '</tr>';

            var row1 = '<tr>';
                row1 += '<td>' + temp + '</td>';
                row1 += '</tr>';      

            var row2 = '<tr>';
                row2 += '<td>' + humidity + '</td>';
                row2 += '</tr>';  
                
            var row3 = '<tr>';
                row3 += '<td>' + windSpeed + '</td>';
                row3 += '</tr>';  

                $('#currentConditionsDescriptionRows').append(conditions);
                $('#currentConditionsDescriptionRows').append(description);
                $('#currentConditionsStatsRows').append(row1);
                $('#currentConditionsStatsRows').append(row2);
                $('#currentConditionsStatsRows').append(row3);
                

        
    },
    error: function() {
      $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
    }
      
      
      
    })






  });
});

function clearWeatherData() {
  $('#currentConditionsDescriptionRows').empty();
  $('#currentConditionsStatsRows').empty();
  $('#day-1').empty();
  $('#day-2').empty();
  $('#day-3').empty();
  $('#day-4').empty();
  $('#day-5').empty();
}

/*$.ajax({
    type: 'GET',
    url: 'http://localhost:8080/contacts',
    success: function(contactArray) {
        $.each(contactArray, function(index, contact) {
            var name = contact.firstName + ' ' + contact.lastName;
            var company = contact.company;
            var contactId = contact.contactId;

            var row = '<tr>';
                row += '<td>' + name + '</td>';
                row += '<td>' + company + '</td>';
                row += '<td><a onclick="showEditForm(' + contactId + ')">Edit</a></td>';
                row += '<td><a onclick="deleteContact(' + contactId + ')">Delete</a></td>';
                row += '</tr>';

                contentRows.append(row);

        });
    },
    error: function() {
      $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
    }
  });
}



function showEditForm(contactId) {
  $('#errorMessages').empty();

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/contact/' + contactId,
    success: function(data, status) {
      $('#edit-first-name').val(data.firstName);
      $('#edit-last-name').val(data.lastName);
      $('#edit-company').val(data.company);
      $('#edit-phone').val(data.phone);
      $('#edit-email').val(data.email);
      $('#edit-contact-id').val(data.contactId);
    },
    error: function() {
      $('errorMessages')
      .append($('<li>')
      .attr({class: 'list-group-item list-group-item-danger'})
      .text('Error calling web service. Please try again later.'));
    }
  })

  $('#contactTableDiv').hide();
  $('#editFormDiv').show();
}

function hideEditForm() {
  $('#errorMessages').empty();

  $('#edit-first-name').val('');
  $('#edit-last-name').val('');
  $('#edit-company').val('');
  $('#edit-phone').val('');
  $('#edit-email').val('');

  $('#contactTableDiv').show();
  $('#editFormDiv').hide();
}

function deleteContact(contactId) {
  $.ajax({
    type: 'DELETE',
    url: 'http://localhost:8080/contact/' + contactId,
    success: function() {
      loadContacts();
    }
  });
}

function checkAndDisplayValidationErrors(input) {
  $('#errorMessages').empty();

  var errorMessages = [];

  input.each(function() {
    if(!this.validity.valid) {
      var errorField = $('label[for=' + this.id + ']').text();
      errorMessages.push(errorField + ' ' + this.validationMessage);
    }
  });

  if (errorMessages.length > 0) {
    $.each(errorMessages,function(index,message) {
      $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
    });

    return true;
  } else {
    return false;
  }
}*/