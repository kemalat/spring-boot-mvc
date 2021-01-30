

const SERVER_URL = 'http://localhost:8081/admin';
$(document).ready(function () {

  var arr = document.URL.match(/id=([0-9]+)/)
  id = arr[1];

  $.ajax({
    "async": true,
    "crossDomain": true,
    "url": `${SERVER_URL}/user/detail/${id}`,
    "method": "POST",

    beforeSend: function (xhr) {
      var token =  $('input[name="_csrf"]').attr('value');
      xhr.setRequestHeader('X-Csrf-Token', token);

    },

    success: function (response) {

      response.userStatutes.forEach(function (item, index) {
        $("select#status").append( $("<option>")
            .val(item)
            .html(item)
        );
      });

      response.roles.forEach(function (item, index) {
        $("select#role").append( $("<option>")
            .val(item)
            .html(item)
        );
      });

      response.actionPlans.forEach(function (item, index) {
        $("select#plan").append( $("<option>")
            .val(item)
            .html(item)
        );
      });

      console.log(">>>"+response.roleDto[0].role)
      document.querySelector('#status').value = response.status;
      document.querySelector('#role').value = response.roleDto[0].role.substring(5);;


      // response.agents.forEach(function (item, index) {
      //   $("select#assignee").append( $("<option>")
      //       .val(item)
      //       .html(item)
      //   );
      // });
      // document.querySelector('#assignee').value = response.assignee;

      $('#user-name').val(response.name);
      $('#user-email').val(response.email);

    }
  });



  $('#update-user').ajaxForm({
    async: true,
    dataType: 'json',
    type: 'post',
    beforeSubmit:  function () {
      status = document.querySelector('#status').value;
      role = document.querySelector('#role').value;
      actionPlan = document.querySelector('#plan').value;
      userName = document.querySelector('#user-name').value;
      userEmail = document.querySelector('#user-email').value;
      $('#user-id').attr("value", id);
      userId = document.querySelector('#user-id').value;
      // console.log("user id"+userId);
      return true;
    },
    success: function (res) {
      console.log(res.code);
      console.log(res.message);
      if(res.code == 200)
        window.location.href = "http://localhost:8081/users";
      else
        console.log(res.code);

    }
  });

});

function cancelForm(){
  window.location.href = "http://localhost:8081/users";
}
