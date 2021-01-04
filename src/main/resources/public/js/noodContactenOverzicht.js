let globalUserNoodcontactId;
let globalElementArray = [];
let globalBooleanSelection = 0;


function initpage() {
    console.log("Init page");

    findUsers();
}


function overallSave(){
    globalElementArray = [];
    console.log("IN UPDATE");

    alert("IN UPDATE");

    globalElementArray.push(globalUserNoodcontactId);

    for(let element of document.getElementById("editForm").elements){

        globalElementArray.push(element.value);

        console.log(globalElementArray);
    }
    console.log(globalElementArray.toString());
    alert("hjdsbfg jhbdfhjbdfgjhdfbghjdfbgkjhfd");


    globalElementArray[6] = globalBooleanSelection;


    console.log(globalElementArray.toString());

    alert("GLOBAL");

    saveNoodcontact();

}

function saveNoodcontact(){

let jsonFormat =

    "{\"userid\" : " + globalElementArray[0] + ", " +
    "\"firstName\" : \"" + globalElementArray[1] + "\", " +
    "\"lastName\" : \"" + globalElementArray[2] + "\", " +
    "\"email\" : \"" + globalElementArray[3] + "\", " +
    "\"phoneNumber\" : " + globalElementArray[4] + ", " +
    "\"available\" : " + globalElementArray[5] + "}"+
    "\"orderBy\" : " + globalElementArray[6] + "}";


console.log(jsonFormat);

alert("djfnhbgjhdfbhgj");


//POPUP TO CONFIRM
    if (confirm("Weet u zeker dat u verder wilt?")) {


        //PUT USER BY ID WITH JSON
        fetch("/emergencycontacts", {
            method: "POST", body: JSON.stringify(jsonFormat), headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {

                alert("IN THE FETCH");
                return response.json();


            }).then(function (response) {
            // response-status = 200 OK

            console.log(response);
        });
    }



}

function getNoodcontacten(value) {

    fetch("/emergencycontacts/users/" + value, {method: "GET"}
    ).then(function (response) {
        clearTables();
        return response.json();


    }).then(function(noodcontacten) {

        for (let noodcontact of noodcontacten) {

            //CREATE HTML ELEMENTS
            var tr = document.createElement("tr");

            var tdFirstName = document.createElement("td");
            var bFirstName = document.createElement("b");

            var tdLastName = document.createElement("td");
            var bLastName = document.createElement("b");

            var tdEmail = document.createElement("td");
            var bEmail = document.createElement("b");

            var tdPhoneNumber = document.createElement("td");
            var bPhoneNumber = document.createElement("b");

            var tdAvailability = document.createElement("td");
            var bAvailability = document.createElement("b");

            var tdOrderBy = document.createElement("td");
            var bOrderBy = document.createElement("b");


            //    SET ELEMENT ATTRIBUTES
            tdFirstName.setAttribute("scope", "row");

            //    INSERT TEXT INTO ELEMENT
            bFirstName.innerHTML = noodcontact.firstName;
            bLastName.innerHTML = noodcontact.lastName;
            bEmail.innerHTML = noodcontact.email;
            bPhoneNumber.innerHTML = noodcontact.phoneNumber;
            bAvailability.innerHTML = noodcontact.available;
            bOrderBy.innerHTML = noodcontact.orderBy;


            //   APPEND INTO ELEMENT
            tdFirstName.appendChild(bFirstName);
            tdLastName.appendChild(bLastName);
            tdEmail.appendChild(bEmail);
            tdPhoneNumber.appendChild(bPhoneNumber);
            tdAvailability.appendChild(bAvailability);
            tdOrderBy.appendChild(bOrderBy);

            //    APPEND INTO HTML
            tr.appendChild(tdFirstName);
            tr.appendChild(tdLastName);
            tr.appendChild(tdEmail);
            tr.appendChild(tdPhoneNumber);
            tr.appendChild(tdAvailability);
            tr.appendChild(tdOrderBy);

        document.querySelector("#noodcontactenTable").appendChild(tr);
        }
    });
}

function findUsers() {
    //var value = document.getElementById("noodcontactenList").value;

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

            document.querySelector("#noodcontactenList").appendChild(option);
        }
    })
}

function getSelectedValue() {
    var value = document.getElementById("noodcontactenList").value;

    // return value;

    globalUserNoodcontactId = value;

    getNoodcontacten(value);

    console.log("GLOBAL USER ID: " + globalUserNoodcontactId);

}

function clearTables() {
    var datalist = document.querySelector("#noodcontactenTable");
    var child = datalist.lastElementChild;
    while (child) {
        datalist.removeChild(child);
        child = datalist.lastElementChild;
    }
}

function getSelectedUserNoodcontactValue() {
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

function openForm() {
    document.getElementById('id01').style.display = 'block';
    document.getElementById("id01").style.width = "auto";
}

document.querySelector("#editButtonForm").addEventListener("click", overallSave);

function check(obj){
    if(obj.checked){
        globalBooleanSelection = 1;
    }
    else{
        globalBooleanSelection = 0;
    }
}
