function initpage(){
    console.log("initpage");

    getUsers();
}

function getUsers(){
    fetch("/users", {method: "GET"})
        .then(function (response) {
            return response.json();
        }).then(function (users) {

            console.log(users);

            for(let user of users){
                //CREATE HTML ELEMENTS
                var tr = document.createElement("tr");

                var tdSurName = document.createElement("td");
                var bSurName = document.createElement("b");

                var tdName = document.createElement("td");
                var bName = document.createElement("b");

                var tdEmail = document.createElement("td");
                var bEmail = document.createElement("b");



                //    SET ELEMENT ATTRIBUTES
                tdSurName.setAttribute("scope", "row");


                console.log("USER: " + user.toString());
                console.log(user.lastName);
                console.log(user.firstName);
                console.log(user.email);


                bSurName.innerHTML = user.lastName;
                bName.innerHTML = user.firstName;
                bEmail.innerHTML = user.email;


                //   APPEND INTO ELEMENT
                tdSurName.appendChild(bSurName);
                tdName.appendChild(bName);
                tdEmail.appendChild(bEmail);

                //    APPEND INTO HTML
                tr.appendChild(tdSurName);
                tr.appendChild(tdName);
                tr.appendChild(tdEmail);


                document.querySelector("#userTable").appendChild(tr);
            }



    });

}






function createEditButton(id){
    var btnEdit = document.createElement('button');
    btnEdit.setAttribute('name', 'Edit');
    btnEdit.setAttribute('type', 'button');
    btnEdit.setAttribute('id', 'edit' + id);
    btnEdit.innerHTML = "Edit";

    return btnEdit;
}