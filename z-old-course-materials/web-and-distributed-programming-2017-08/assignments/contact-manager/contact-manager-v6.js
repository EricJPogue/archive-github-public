// Global Variables

// Starting array of contacts.
var contacts = [
    {name:'Eric Pogue v2', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Ted', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Sanjay', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
    {name:'Edward', address:'2760 South Highland Ave', city:'Lemont', state:'IL', zip:'60148'},
]; 

currentContactIndex = 0; 
currentContact = contacts[currentContactIndex];

// Functions
function viewCurrentContact() {
    console.log('V3... viewCurrentContact()');
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

    // Todo: Implement add functionality by inserting new element into array.
}

function remove() {
    console.log('remove()');

    // Todo: Implement delete functionality by deleting element from array.
}

function zipFocusFunction() {
    console.log('focusFunction()');

    // Todo: Remove the function as it is not needed.
}

function zipBlurFunction() {
    getPlace();
}

function keyPressed() {
    console.log('keyPressed()');

    // This type of function should be useful in search as it implements keyPressed.
}

function getPlace() {
    var zip = document.getElementById("zip").value
    console.log("zip:"+zip);

    console.log("function getPlace(zip) { ... }");
    var xhr = new XMLHttpRequest();

    // Register the embedded handler function
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.responseText;
            console.log("result:"+result);
            var place = result.split(', ');
            if (document.getElementById("city").value == "")
                document.getElementById("city").value = place[0];
            if (document.getElementById("state").value == "")
                document.getElementById("state").value = place[1];
        }
    }
    xhr.open("GET", "contact-manager-v6.php?zip=" + zip);
    xhr.send(null);
}


