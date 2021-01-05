function initpage(){
    console.log("initpage");

    allMarkers.push(marker0);
    allMarkers.push(marker1);
    allMarkers.push(marker2);
    allMarkers.push(marker3);
    allMarkers.push(marker4);
    allMarkers.push(marker5);
    allMarkers.push(marker6);
    allMarkers.push(marker7);
    allMarkers.push(marker8);
    allMarkers.push(marker9);
    allMarkers.push(marker10);
    allMarkers.push(marker11);
    allMarkers.push(marker12);
    renderMarkers();

    findUsers();
}

//buttons
var undoBtn =  document.getElementById("undoBtn").addEventListener("click", function(){
    tempArray = crumpArray.pop();
    tempMarker = markerArray.pop();
    printArray();
    renderMarkers();
    ableToRedo = true;
    })
    
var undoBtn =  document.getElementById("redoBtn").addEventListener("click", function(){
    if(ableToRedo){
        crumpArray.push(tempArray);
        markerArray.push(tempMarker);
        printArray();
        renderMarkers();
        ableToRedo = false;
        }
    })

//markers
var marker0 = document.getElementById("marker0");
var marker1 = document.getElementById("marker1");
var marker2 = document.getElementById("marker2");
var marker3 = document.getElementById("marker3");
var marker4 = document.getElementById("marker4");
var marker5 = document.getElementById("marker5");
var marker6 = document.getElementById("marker6");
var marker7 = document.getElementById("marker7");
var marker8 = document.getElementById("marker8");
var marker9 = document.getElementById("marker9");
var marker10 = document.getElementById("marker10");
var marker11 = document.getElementById("marker11");
var marker12 = document.getElementById("marker12");

var allMarkers = new Array();
    
//vars
var crumpArray = new Array();
var markerArray = new Array();
var tempArray;
var tempMarker;
var ableToRedo = false;
    
//functions
function AddCrump(x, y, m){
    tempArray = new Array(x,y);
    crumpArray.push(tempArray);
    markerArray.push(m);
    printArray();
    renderMarkers()
}

function printArray(){
    document.getElementById("arrayOut").innerHTML = "crump array: [";
    for(i in crumpArray){
        if(i != 0){
            document.getElementById("arrayOut").innerHTML +=",[" + crumpArray[i].toString() + "]";
        }
        else{
            document.getElementById("arrayOut").innerHTML +="[" + crumpArray[i].toString() + "]";
        }
    }
    document.getElementById("arrayOut").innerHTML += "]";

    document.getElementById("marrayOut").innerHTML = markerArray;
}

var count = 1;
function renderMarkers(){
    for(i in allMarkers){
        allMarkers[i].style.display = "none";
    }

    count = 1;
    for(i in markerArray){
        var place = markerArray[i];
        switch(place) {
            case 0:
                marker0.style.display = "initial";
                marker0.src = "assets/images/marker" + count + ".png"; 
                count += 1;
                break;
            case 1:
                marker1.style.display = "initial";
                marker1.src = "assets/images/marker" + count + ".png";
                count += 1;
                break;
            case 2:
                marker2.style.display = "initial";
                marker2.src = "assets/images/marker" + count + ".png";
                count += 1;
                break;  
            case 3:
                marker3.style.display = "initial";
                marker3.src = "assets/images/marker" + count + ".png";
                count += 1;
                break; 
            case 4:
                 marker4.style.display = "initial";
                 marker4.src = "assets/images/marker" + count + ".png";
                 count += 1;
                break;  
            case 5:
                marker5.style.display = "initial";
                marker5.src = "assets/images/marker" + count + ".png";
                count += 1;
                break; 
            case 6:
                marker6.style.display = "initial";
                marker6.src = "assets/images/marker" + count + ".png";
                count += 1;
                 break; 
             case 7:
                marker7.style.display = "initial";
                marker7.src = "assets/images/marker" + count + ".png";
                count += 1;
                 break; 
            case 8:
                marker8.style.display = "initial";
                marker8.src = "assets/images/marker" + count + ".png";
                count += 1;
                break; 
            case 9:
                marker9.style.display = "initial";
                marker9.src = "assets/images/marker" + count + ".png";
                count += 1;
                break;
            case 10:
                marker10.style.display = "initial";
                marker10.src = "assets/images/marker" + count + ".png";
                count += 1;
                break;  
            case 11:
                marker11.style.display = "initial";
                marker11.src = "assets/images/marker" + count + ".png";
                count += 1;
                break; 
            case 12:
                marker12.style.display = "initial";
                marker12.src = "assets/images/marker" + count + ".png";
                count += 1;
                break; 

            default:
               console.log("yeet");
          }
    }
}

function saveRoute(){
    var route = new Object();
    route.name = document.getElementById("routeName").value;

    var crumpJsonArray = new Array();
    for (i in crumpArray){
        var crumpJson = new Object();
        crumpJson.xCo = crumpArray[i][0];
        crumpJson.yCo = crumpArray[i][1];
        crumpJsonArray.push(crumpJson);
    }

    route.route = crumpJsonArray;

    var routeJsonString = JSON.stringify(route);

    //Post de route.
    fetch("/routes/" + document.querySelector("#userList").value, { 
        method: 'POST',
        headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
        }, body: routeJsonString })
        .then(response => response.json())
        .then(function(myJson) { console.log("route: "+ myJson.id + "is gepost!");

            // //Als de route succesvol is gepost, post de crumps.
            // var crumpsJson = {
            //     [myJson.id]: crumpArray
            // };

            // var crumpJsonString = JSON.stringify(crumpsJson);

            // fetch("/breadcrumbs", { 
            //     method: 'POST',
            //     headers: {
            //             'Accept': 'application/json',
            //             'Content-Type': 'application/json',
            //     }, body: crumpJsonString })
            //     .then(response => response.json())
            //     .then(function(myJson) { 
            //         console.log(myJson);
            //     })
        })
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