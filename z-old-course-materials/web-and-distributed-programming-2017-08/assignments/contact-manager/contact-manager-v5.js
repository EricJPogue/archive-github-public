
// Starting array of contacts.
var contacts = [
    {name:'Eric Pogue v2', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Ted', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Sanjay', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Edward', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
]; 

currentContactIndex = 0; 
currentContact = contacts[currentContactIndex];

function viewCurrentContact() {
    console.log('viewCurrentContact()');
    document.getElementById("name").value = currentContact.name;   
    document.getElementById("address").value = currentContact.address;
    document.getElementById("city").value = currentContact.city;   
    document.getElementById("state").value = currentContact.state;
    document.getElementById("zip").value = currentContact.zip;  
}

function previous() {
    if (currentContactIndex > 0) {
        currentContactIndex--;
    }
    currentContact = contacts[currentContactIndex];
    viewCurrentContact();

    // Todo: Disable previous button when currentContactIndex equal to 0.
}

function next() {
    if (currentContactIndex < (contacts.length-1)) {
        currentContactIndex++;
    }
    currentContact = contacts[currentContactIndex];
    viewCurrentContact();
    console.log('next()'); 
    
    // Todo: Disable next button when there is no next item.
}

function add() {
    console.log('add()');
}

function remove() {
    console.log('remove()');
}

function zipFocusFunction() {
    console.log('focusFunction()');
}
function zipBlurFunction() {
    console.log('blurFunction');

    // When zip loses focus, set appropriate city and state if they are empty.
    currentState = document.getElementById("state").value;
    console.log('currentState='+currentState);
    if (currentState == "") {
        currentContact.state = "IL";
        document.getElementById("state").value = "IL";
    }
    console.log('**getPlace1');
    getPlace(currentContact.zip);
}

function myFunction() {
    console.log('myFunction()');
}

function getPlace(zip) {
    console.log("v2.... function getPlace(zip) { ... }");
    var xhr = new XMLHttpRequest();

// Register the embedded handler function
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var result = xhr.responseText;
      var place = result.split(', ');
      if (document.getElementById("city").value == "")
        document.getElementById("city").value = place[0];
      if (document.getElementById("state").value == "")
        document.getElementById("state").value = place[1];
    }
  }
  //xhr.open("GET", "contact-manager-v5.php?zip=" + zip);
  xhr.open("GET", "getCityState.php?zip=" + zip);
  xhr.send(null);
}

