let globalRollatorArray = [];
let globalRollatorid;
let globalUserArray = [];
let globalUserId;
let globalRollatorInUse = [];

function initpage() {
    console.log("initpage");

    //FETCH ROLLATORS AND INSERT HTML
    getRollators();

    //LOAD ROLLATOR AND USERS FOR EDIT DROPDOWN
    loadRollators();
    loadUsers();

}

function overallUpdate() {

    // CHECK IF ROLLATOR IS IN USE
    if (globalRollatorInUse.includes(globalRollatorid)) {
        alert("De rollator is al in gebruik, selecteer een andere rollator!");
        window.location.href = "rollatorsOverzicht.html";
    }

    // CALL THE METHOD TO UPDATE USER
    var update = findById();
    updateRollator(update);
}

function getRollators() {

    fetch("/rollators", {method: "GET"})
        .then(function (response) {
            return response.json();
        }).then(function (rollators) {

            // FOR LOOP FOR DYNAMIC HTML
            for (let rollator of rollators) {

                //FOR LOOP TO READ OUT ROUTE
                for (var key in rollator.route) {
                    if (rollator.route.hasOwnProperty(key)) {
                        bRoute.innerHTML = rollator.route[key].name;
                    }
                }
                //FIND USERS BY ID
                getUsers(rollator.id, rollator.name);

            }
        }
    )
}

function getUsers(rollatorid, rollatorName) {
    //FETCH USER
    fetch("/users", {method: "GET"})
        .then(function (response) {
            return response.json();
        }).then(function (users) {

        //CREATE HTML ELEMENTS
        var tr = document.createElement("tr");

        var tdId = document.createElement("td");
        var bId = document.createElement("b");

        var tdName = document.createElement("td");
        var bName = document.createElement("b");

        var tdUser = document.createElement("td");
        var bUser = document.createElement("b");

        //    SET ELEMENT ATTRIBUTES
        tdId.setAttribute("scope", "row");

        //    INSERT TEXT INTO ELEMENT
        bId.innerHTML = rollatorid;
        bName.innerHTML = rollatorName;


        //EXAMPLE CODE
        // var userArray = Object.values(users);
        //
        //
        // for (var i = 0; i < userArray.length; i++) {
        //     if (userArray[i]["rollators"] != null) {
        //         console.log(userArray[i]["rollators"]["id"]);
        //     }
        // }

        //FOR LOOP FOR USERS
        for (let user of users) {

            if (user.rollators != null && user.rollators.id === rollatorid) {

                bUser.innerHTML = user.fullName;
            }

        }
        //   APPEND INTO ELEMENT
        tdId.appendChild(bId);
        tdName.appendChild(bName);
        tdUser.appendChild(bUser);

        //    APPEND INTO HTML
        tr.appendChild(tdId);
        tr.appendChild(tdName);
        tr.appendChild(tdUser);

        document.querySelector("#rollatorTable").appendChild(tr);
    });
}


function loadRollators() {

    //FETCH ALL ROLLATORS
    fetch("/rollators", {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (rollators) {

        //    FOR LOOP TO CREATE HTML ELEMENTS PER ROLLATOR
        for (let rollator of rollators) {

            //    CREATE HTML ELEMENTS
            var option = document.createElement("option");
            option.setAttribute("value", rollator.id);
            option.setAttribute("id", rollator.id);

            //    INSERT TEXT INTO ELEMENT
            option.innerHTML = rollator.id + " | " + rollator.name;

            document.querySelector("#rollatorList").appendChild(option);
        }
    })
}

function loadUsers() {
    fetch("/users", {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (users) {

        for (let user of users) {


            //IF TO CHECK ROLLATOR ID != NULL AND INSERT INTO GLOBAL ARRAY
            if (user.rollators != null) {
                globalRollatorInUse.push(user.rollators.id);

            }

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

function getSelectedUserValue() {
    globalUserArray = [];
    var value = document.getElementById("userList").value;


    // GET USER BY ID
    fetch("/users/" + value, {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (user) {

        //INSERT INTO GLOBAL VAR
        globalUserArray.push("\"id\" : " + user.id, "\"lastName\" : " + "\"" + user.lastName + "\"",
            "\"firstName\" : " + "\"" + user.firstName + "\"", "\"fullName\" : " + "\"" + user.fullName + "\"",
            "\"email\" : " + "\"" + user.email + "\"", "\"enabled\" : " + user.enabled);
        globalUserId = value;
    });
}

function getSelectedRollatorValue() {
    globalRollatorArray = [];
    var value = document.getElementById("rollatorList").value;

    //GET ROLLATOR BY ID
    fetch("/rollators/" + value, {method: "GET"})
        .then(function (response) {
            return response.json();

        }).then(function (rollator) {

        //    INSERT INTO GLOBAL VAR
        globalRollatorArray.push("\"id\" : " + rollator.id, "\"name\" : " + "\"" + rollator.name + "\"", "\"route\" : " + rollator.route);
        globalRollatorid = rollator.id;

        console.log("COMPLETE ARRAY ROLLATORS: " + globalRollatorArray);
    });

}


function findById() {

    // INSERT GLOBAL VAR INTO STRING
    var jsonStringRollator = ("\"rollators\" : {" +
        globalRollatorArray[0] + ", " +
        globalRollatorArray[1] + ", " +
        globalRollatorArray[2] + "}\n}");

    var jsonStringUser = "{" +
        globalUserArray[0] + ", " +
        globalUserArray[1] + ", " +
        globalUserArray[2] + ", " +
        globalUserArray[3] + ", " +
        globalUserArray[4] + ", " +
        globalUserArray[5] + ", ";


    //COMBINE STRING
    var totalJsonString = jsonStringUser + jsonStringRollator

    //PARSE STRING TO JSON AND RETURN RESULT
    return JSON.parse(totalJsonString);
}

function updateRollator(obj) {

    //POPUP TO CONFIRM
    if (confirm("Weet u zeker dat u verder wilt?")) {


        //PUT USER BY ID WITH JSON
        fetch("/users/" + globalUserId, {
            method: "PUT", body: JSON.stringify(obj), headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {
                return response.json();


            }).then(function (response) {
            // response-status = 200 OK

            console.log(response);
        });
    }
}


document.querySelector("#editButtonForm").addEventListener("click", overallUpdate);

document.getElementById("editButton").addEventListener("click", function () {
    document.getElementById('id01').style.display = 'block';
    document.getElementById("id01").style.width = "auto";
});

function openForm() {
    document.getElementById('id01').style.display = 'block';
    document.getElementById("id01").style.width = "auto";
}




