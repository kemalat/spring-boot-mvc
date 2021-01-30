const SERVER_URL = 'http://localhost:8081/admin';

$(document).ready(function () {

    $('#datatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": `${SERVER_URL}/user/list`,
            data: function (data) {
                data.orderColumn = 'name';

                switch (data.order[0].column) {
                    case 0:
                        data.orderColumn = 'name';
                        break;
                }
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
            { data: 'created'},
            {
                render: function (data, type, full, meta) {

                    // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
                    return '<span class="badge badge-success">'+full.status+'</span>';
                },
                orderable: false
            },
            { "data": "email" },
            {
                render: function (data, type, full, meta) {

                    console.log(full.roles[0].role);
                    return full.roles[0].role;
                },
                orderable: false
            },
            {
                render: function (data, type, full, meta) {

                    // return '<a class="btn btn-primary text-light history" style="cursor:pointer" data-status="resume"  data-unique="' + full.destination + '">History</a> ';
                    return '<a href="edit_user?id='+full.id+'" class="btn btn-primary btn-xs"><i class="fa fa-folder"></i> Edit </a>';
                },
                orderable: false
            }
        ]
    } );

    $.ajax({
        type: 'post',
        dataType: 'json',
        url: `${SERVER_URL}/user/filtered/list`,
        data: { status: 0} ,
        headers: {
            'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
        },
        success: function (response) {
            console.log(response);
            console.log(response.pendingUsers);
            document.getElementById("badge-size").innerHTML = response.messageCount;
            var ul = document.getElementById("menu1");
            var li = document.createElement("li");
            li.innerHTML= "<a> <span class=\"image\"><img src=\"./assets/images/img.jpg\" alt=\"Profile Image\" /></span> "
                + "<span> <span>John Smith</span> <span class=\"time\">3 mins ago</span> </span> "
                + "<span class=\"message\"> "+response.message+" </span> </a> ";
            ul.appendChild(li);
        },
        error: function (response) {
            alert(response);
            console.log("home url failed");
        }

    });


	// }

});



//    if ( $.fn.dataTable.isDataTable( '#datatable' ) ) {
//     table = $('#datatable').DataTable();
//                            console.log(arr);

//     table.rows.add(arr).draw();

// }
// else {
// 	$('#datatable').DataTable( {
//          data: dataSet
//     });
