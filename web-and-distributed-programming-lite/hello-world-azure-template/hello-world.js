function initApplication() {
    console.log('Hello World - Starting!'); 
}

function executeAjaxAndPhp() {
    var request = new XMLHttpRequest();
    request.open('GET', 'hello-world.php');
    request.onload = function() {
        console.log("Response:" + request.responseText);

        var responseObject = JSON.parse(request.responseText);
        if (responseObject.firstWord != "Hello") {
            document.getElementById("id-ajaxandphp").innerHTML = "Error: " + request.responseText;
        }
        else {
            document.getElementById("id-ajaxandphp").innerHTML = "<em>Success:</em> " + responseObject.firstWord + " " + responseObject.secondWord + "<br><br>Data:" + request.responseText;
        }
    }
    request.send();
}





