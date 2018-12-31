
var contactURLArray = [];
var contactArray = [];
var loadingContact = 0;

function initApplication() {
    console.log('Mustang Lite - Starting!'); 
}

function loadJSON() {
    var indexRequest1 = new XMLHttpRequest();
    indexRequest1.open('GET', 'https://z-mustang-data.azurewebsites.net/ldw6txsjg6.json');
    indexRequest1.onload = function() {
        console.log(indexRequest1.responseText);
        document.getElementById("id-01").innerHTML = indexRequest1.responseText;
    }
    indexRequest1.send();

    var indexRequest2 = new XMLHttpRequest();
    indexRequest2.open('GET', 'https://cpsc-24700-su18-lt1.azurewebsites.net/r9us3zzb88.json');
    indexRequest2.onload = function() {
        console.log(indexRequest2.responseText);
        document.getElementById("id-02").innerHTML = indexRequest2.responseText;
    }
    indexRequest2.send();

    var indexRequest3 = new XMLHttpRequest();
    indexRequest3.open('GET', 'https://mustang-index.azurewebsites.net/ldw6txsjg5.json');
    indexRequest3.onload = function() {
        console.log(indexRequest3.responseText);
        document.getElementById("id-03").innerHTML = indexRequest3.responseText;
    }
    indexRequest3.send();

    var indexRequest4 = new XMLHttpRequest();
    indexRequest4.open('GET', 'https://t-test-02.azurewebsites.net/ldw6txsjg6.json');
    indexRequest4.onload = function() {
        console.log(indexRequest4.responseText);
        var response = JSON.parse(indexRequest4.responseText);
        console.log(response);
        document.getElementById("id-04").innerHTML = indexRequest4.responseText;
    }
    indexRequest4.send();

    var indexRequest5 = new XMLHttpRequest();
    indexRequest5.open('GET', 'https://t-test-03.azurewebsites.net/ldw6txsjg6.json');
    indexRequest5.onload = function() {
        console.log(indexRequest5.responseText);
        document.getElementById("id-05").innerHTML = indexRequest5.responseText;
    }
    indexRequest5.send();   
}

function loadIndex() {
    // Load the Mustang index file.
    var indexRequest = new XMLHttpRequest();
    indexRequest.open('GET', 'https://mustang-index.azurewebsites.net/index.json');
    indexRequest.onload = function() {
        console.log("Index JSON:" + indexRequest.responseText);
        document.getElementById("indexID").innerHTML = indexRequest.responseText;
        contactIndex = JSON.parse(indexRequest.responseText);
        // Bugbug: Need to clear contactURLArray.
        for (i=0; i<contactIndex.length; i++) {
            contactURLArray.push(contactIndex[i].ContactURL);
        }
        console.log("ContactURLArray: " + JSON.stringify(contactURLArray));
    }
    indexRequest.send();
}

function logContacts() {
    console.log(contactArray);
}

function loadContacts() {
    // Clear the current contactArray.
    contactArray.length = 0;
    loadingContact = 0;

    // Note that W3C documentation and my experimentation indicate that each XMLHttpRequest callback function must be a 
    // unique instance of a function. A better implmentation would have had an array of callback functions instead of a 
    // recursive call to load
    if (contactURLArray.length > loadingContact) {
        loadNextContact(contactURLArray[loadingContact]);
    }
}

function loadNextContact(URL) {
    console.log("URL: " + URL);
    contactRequest = new XMLHttpRequest();
    contactRequest.open('GET', URL);
    contactRequest.onload = function() {
        console.log(contactRequest.responseText);
        var contact;
        contact = JSON.parse(contactRequest.responseText);
        console.log("Contact: " + contact.firstName);
        contactArray.push(contact);
        document.getElementById("contactsID").innerHTML = JSON.stringify(contactArray);

        loadingContact++;
        if (contactURLArray.length > loadingContact) {
            loadNextContact(contactURLArray[loadingContact]);
        }
    }

    contactRequest.send();
}




