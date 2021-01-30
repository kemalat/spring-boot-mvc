

	
const SERVER_URL = 'http://localhost:8081';

$(document).ready(function () {

    console.log($('#result').val());
    var obj = JSON.parse($('#result').val());
    var role = obj.data.user.roles[0].role;
    console.log(role);

    if(role.localeCompare("ROLE_USER") == 0) {
        URL = `${SERVER_URL}/user/client/list`;
        printClients(obj.data.user.id, URL);
        printUserNotifications(obj.data.user.id);
    }
    else {
        URL = `${SERVER_URL}/admin/client/list`;
        printClients(-1,URL);
        printAdminNotifications();
    }


    // if(role.localeCompare("ROLE_USER") == 0) {
    //     printClientsForUser(obj.data.user.id);
    //     printUserNotifications(obj.data.user.id);
    // }
    // else {
    //     printClientsForAdmin();
    //     printAdminNotifications();
    // }


	// }

});


function printClients(id,url) {

    $('#datatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": url,
            data: function (data) {
                data.orderColumn = 'name';

                switch (data.order[0].column) {
                    case 0:
                        data.orderColumn = 'name';
                        break;
                }
                data.userId = id;
            },
            type: 'POST',
            headers: {
                'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
            },
            dataSrc: function (response) {
                window.controlGroup = response.data;
                return response.data;
            }
        },
        columns: [{
            render:
                function (data, type, full, meta) {

                    if (full.name.length > 50) {
                        return full.name.slice(0, 50) + '...'
                    } else {
                        return full.name;
                    }
                }

        },
            { data: 'customerSince'},
            {
                render: function (data, type, full, meta) {

                    // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
                    return '<span class="badge badge-success">'+full.status+'</span>';
                },
                orderable: false
            },
            { "data": "totalAccounts" },
            { "data": "totalOwing" },
            { "data": "recoveryRate" },
            { "data": "lastInvoiceDate" },
            {
                render: function (data, type, full, meta) {

                    // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
                    return '<a href="profile?id='+full.id+'" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>';
                },
                orderable: false
            }
        ]
    } );
}


// function printClientsForAdmin() {
//
//     $('#datatable').DataTable( {
//         "processing": true,
//         "serverSide": true,
//         "ajax": {
//             "url": `${SERVER_URL}/admin/client/list`,
//             data: function (data) {
//                 data.orderColumn = 'name';
//
//                 switch (data.order[0].column) {
//                     case 0:
//                         data.orderColumn = 'name';
//                         break;
//                 }
//             },
//             type: 'POST',
//             headers: {
//                 'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
//             },
//             dataSrc: function (response) {
//                 window.controlGroup = response.data;
//                 return response.data;
//             }
//         },
//         columns: [{
//             render:
//                 function (data, type, full, meta) {
//
//                     if (full.name.length > 50) {
//                         return full.name.slice(0, 50) + '...'
//                     } else {
//                         return full.name;
//                     }
//                 }
//
//         },
//             { data: 'customerSince'},
//             {
//                 render: function (data, type, full, meta) {
//
//                     // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
//                     return '<span class="badge badge-success">'+full.status+'</span>';
//                 },
//                 orderable: false
//             },
//             { "data": "totalAccounts" },
//             { "data": "totalOwing" },
//             { "data": "recoveryRate" },
//             { "data": "lastInvoiceDate" },
//             {
//                 render: function (data, type, full, meta) {
//
//                     // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
//                     return '<a href="profile?id='+full.id+'" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> View </a>';
//                 },
//                 orderable: false
//             }
//         ]
//     } );
//
// }
