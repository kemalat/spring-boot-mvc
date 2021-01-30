

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

    document.querySelector(".comp-name").innerHTML = response.name;
    document.getElementById(
        "comp-addr").innerHTML = "<i class=\"fa fa-map-marker user-profile-icon\"></i> "
        + response.address;
    document.getElementById("comp-contact").innerHTML = response.contact;
    document.getElementById("comp-contact-phone").innerHTML = response.phone;
    document.getElementById("comp-contact-mail").innerHTML = response.email;
    document.getElementById(
        "comp-web").innerHTML = "<i class=\"fa fa-external-link user-profile-icon\"></i><a href=\""
        + response.web + "\" target=\"_blank\">" + response.web + "</a>";

    document.getElementById("edit_client").href = `edit_client?id=${id}`;


    document.querySelector(".principal").innerHTML = response.report.principal;
    document.querySelector(".interests").innerHTML = response.report.interest;
    document.querySelector(".owingactive").innerHTML = response.report.owingActive;
    document.querySelector(".owingall").innerHTML = response.report.owingAll;
    document.querySelector(".owingclosed").innerHTML = response.report.owingClosed;
    document.querySelector(".legalfees").innerHTML = response.report.legalFees;
    document.querySelector(".fees").innerHTML = response.report.fees;
    document.querySelector(".comission").innerHTML = response.report.comission;

    Morris.Bar({
      element: "graph_bar",
      data: [
        {y: 'Total', a: response.accounts.total},
        {y: 'Active', a: response.accounts.active},
        {y: 'Closed', a: response.accounts.closed}

      ],
      xkey: "y",
      ykeys: ["a"],
      labels: ['Accounts'],
      barRatio : .4,
      horizontal: true,
      stacked: true,
      xLabelAngle: 35,
      hideHover: "auto",
      resize: !0,
      barColors: function (row, series, type) {
        console.log("--> " + row.label, series, type);
        if (row.label == "Total") return "#26B99A";
        else if (row.label == "Active") return "#34495E";
        else if (row.label == "Closed") return "#ACADAC";
      },
      yLabelFormat: function(y){return y != Math.round(y)?'':y;}
    });

    Morris.Donut({
      element: "graph_donut",
      data: [{
        label: "Total Owing",
        value: response.recoveryRate.owing
      }, {
        label: "Collected",
        value: response.recoveryRate.collected
      }],
      colors: ["#26B99A", "#34495E", "#ACADAC", "#3498DB"],
      formatter: function (a) {
        return a + "%";
      },
      resize: !0
    });
  }

});




  // "#26B99A", "#34495E", "#ACADAC", "#3498DB"

  if(role.localeCompare("ROLE_USER") == 0) {
    URL = `${SERVER_URL}/user/debtor/list/${id}`;
  }
  else {
    URL = `${SERVER_URL}/admin/debtor/list/${id}`;
  }


  $('#debtor-datatable').DataTable( {
    "processing": true,
    "serverSide": true,
    "ajax":{
      url: URL,
      data: function (data) {
        data.orderColumn = 'name';
      },
      type: 'post',
      headers: {
        'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
      },

    },
    "columns": [
      { "data": "name" },
      { "data": "accountId" },
      { "data": "principal" },
      { "data": "owing" },
      { "data": "status" }
    ]
  } );


  if(role.localeCompare("ROLE_USER") == 0) {
    printUserNotifications(obj.data.user.id);
  }
  else {
    printAdminNotifications();
  }

});



