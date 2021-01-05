function initpage() {
    console.log("Init page");

    findUsers();
}

function getRoutes(value) {

    fetch("/routesbyuser/" +  value , {method: "GET"}
    ).then(function (response) {
        clearTables();
        return response.json();


    }).then(function (routes) {

        console.log(routes);
        for (var key in routes) {
            if (routes.hasOwnProperty(key)) {

                 console.log(key);

                //CREATE HTML ELEMENTS
                var tr = document.createElement("tr");

                var tdName = document.createElement("td");
                var bName = document.createElement("b");

                var tdLengte = document.createElement("td");
                var bLengte = document.createElement("b");

                var tdDelete = document.createElement("td");


                //    SET ELEMENT ATTRIBUTES
                tdName.setAttribute("scope", "row");

                //    INSERT TEXT INTO ELEMENT
                bName.innerHTML = routes[key].name;


                // if block for star amount
                var routeLength = routes[key].route.length;
                if (routeLength < 5) {
                    for (var i = 0; i < 1; i++) {
                        var star = document.createElement("i");
                        star.setAttribute("class", "fa fa-star");
                        bLengte.appendChild(star);
                    }
                } else if (routeLength >= 5 && routeLength < 8) {
                    for (var i = 0; i < 2; i++) {
                        var star = document.createElement("i");
                        star.setAttribute("class", "fa fa-star");
                        bLengte.appendChild(star);
                    }
                } else if (routeLength >= 8 && routeLength < 10) {
                    for (var i = 0; i < 3; i++) {
                        var star = document.createElement("i");
                        star.setAttribute("class", "fa fa-star");
                        bLengte.appendChild(star);
                    }
                } else if (routeLength >= 10 && routeLength < 12) {
                    for (var i = 0; i < 4; i++) {
                        var star = document.createElement("i");
                        star.setAttribute("class", "fa fa-star");
                        bLengte.appendChild(star);
                    }
                } else {
                    for (var i = 0; i < 5; i++) {
                        var star = document.createElement("i");
                        star.setAttribute("class", "fa fa-star");
                        bLengte.appendChild(star);
                    }
                }
            }

            //   APPEND INTO ELEMENT
            tdName.appendChild(bName);
            tdLengte.appendChild(bLengte);
            tdDelete.appendChild(createDeleteButton(routes[key].id, tr));

            //    APPEND INTO HTML
            tr.appendChild(tdName);
            tr.appendChild(tdLengte);
            tr.appendChild(tdDelete);

            document.querySelector("#routeTable").appendChild(tr);

        }
    })
}
function createDeleteButton(id, tr) {
    var btnDelete = document.createElement('button');
    btnDelete.setAttribute('name', 'Edit');
    btnDelete.setAttribute('type', 'button');
    btnDelete.setAttribute('id', 'delete' + id);
    btnDelete.onclick = function() { deleteRoute(id, tr) };
    btnDelete.innerHTML = "Delete";

    return btnDelete;
}

function deleteRoute(id, tr) {
    if (confirm("Weet u zeker dat u deze route wilt verwijderen?")) {
        console.log("delete");
        fetch("routes/" + id, { method: 'DELETE' })
            .then(function (info) {
                document.querySelector("#routeTable").removeChild(tr);
            });
    }
}

function findUsers() {
    var value = document.getElementById("userList").value;

    fetch("/users", {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (users) {

        for (let user of users) {
            //    CREATE HTML ELEMENTS

            var option = document.createElement("option");
            option.setAttribute("value", user.id);
            option.setAttribute("id", user.id);

            //    INSERT TEXT INTO ELEMENT

            option.innerHTML = user.fullName;

            document.querySelector("#userList").appendChild(option);
        }
    })
}

function getSelectedValue() {
    var value = document.getElementById("userList").value;

    console.log(value);
    // return value;

    getRoutes(value);
}

function clearTables() {
    var datalist = document.querySelector("#routeTable");
    var child = datalist.lastElementChild;
    while (child) {
        datalist.removeChild(child);
        child = datalist.lastElementChild;
    }
}
