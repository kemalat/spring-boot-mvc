function printUserNotifications(id) {

    $.ajax({
        type: 'post',
        dataType: 'json',
        url: `${SERVER_URL}/user/notifications`,
        data: { userId : id } ,
        headers: {
            'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
        },
        success: function (response) {
            console.log(response);
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

}

function printAdminNotifications() {

    $.ajax({
        type: 'post',
        dataType: 'json',
        url: `${SERVER_URL}/admin/notifications`,
        data: { userId : 1 } ,
        headers: {
            'X-Csrf-Token': $('input[name="_csrf"]').attr('value')
        },
        success: function (response) {
            console.log(response);
            console.log(response.pendingUsers);
            document.getElementById("badge-size").innerHTML = 1;
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

}
