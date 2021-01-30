

const SERVER_URL = 'http://localhost:8081';

$(document).ready(function () {

  var URL;
  var arr = document.URL.match(/id=([0-9]+)/)
  var id = arr[1];

  console.log($('#result').val());
  var obj = JSON.parse($('#result').val());
  var role = obj.data.user.roles[0].role;
  console.log(role);

  if(role.localeCompare("ROLE_USER") == 0) {
    URL = `${SERVER_URL}/user/client/detail/${id}`;
  }
  else {
    URL = `${SERVER_URL}/admin/client/detail/${id}`;
  }

  $.ajax({
    "async": true,
    "crossDomain": true,
    "url": URL,
    "method": "POST",

    beforeSend: function (xhr) {
      var token =  $('input[name="_csrf"]').attr('value');
      xhr.setRequestHeader('X-Csrf-Token', token);

    },

    success: function (response) {

      response.statutes.forEach(function (item, index) {
        $("select#status").append( $("<option>")
            .val(item)
            .html(item)
        );
      });

      document.querySelector('#status').value = response.status;

      response.agents.forEach(function (item, index) {
        $("select#assignee").append( $("<option>")
            .val(item)
            .html(item)
        );
      });
      document.querySelector('#assignee').value = response.agents[0];

      response.actionPlans.forEach(function (item, index) {
        $("select#actions").append( $("<option>")
            .val(item)
            .html(item)
        );
      });

      document.querySelector('#actions').value = response.actionPlan;
      $('#client-name').val(response.name);



    }
  });


  var radioNofee = document.getElementById("radio-nofee");
  var radioDebtor = document.getElementById("radio-debtor");
  var radioClient = document.getElementById("radio-client");
  var buttonAdd = document.getElementById("button-add");

  var value  = "12";

  if(value) {
    $("#fee").val("hee");
    $( "#radio-debtor" ).prop( "checked", true );
  }

  var y = document.getElementById("fee");

  radioNofee.addEventListener('click', function() {
    if(radioNofee.checked) {
      document.getElementById('fee').style.display = 'none';

    }
  }, false);

  radioDebtor.addEventListener('click', function() {

    if(radioDebtor.checked) {
      document.getElementById('fee').style.display = '';
      document.getElementById('fee').focus();

    }
  }, false);

  radioClient.addEventListener('click', function() {
    if(radioClient.checked) {
      document.getElementById('fee').style.display = '';
      document.getElementById('fee').focus();

    }
  }, false);

  buttonAdd.addEventListener('click', function() {
    console.log("clicked");
    var newRow=document.getElementById('table-percents').insertRow();
    newRow.innerHTML = "<td class=\"principal\"><input type=\"number\" step=\"any\" id=\"rate1\" name=\"varrate1*\" maxlength=\"15\" size=\"10\" value=\"35.000\"></td>\n"
        + "                              <td class=\"principal\"><input type=\"checkbox\" value=\"\"></td>\n"
        + "                              <td><input type=\"checkbox\" value=\"\"></td>\n"
        + "                              <td><input type=\"checkbox\" value=\"\"></td>\n"
        + "                              <td><input type=\"checkbox\" value=\"\"></td>\n"
        + "                              <td></td>\n";


  }, false);

  if(role.localeCompare("ROLE_USER") == 0) {
    printUserNotifications(obj.data.user.id);
  }
  else {
    printAdminNotifications();
  }

});

function cancelForm(){
  var arr = document.URL.match(/id=([0-9]+)/)
  var id = arr[1];
  window.location.href = `http://localhost:8081/profile?id=${id}`;
}


