function init()
{
    alert("scripts are working");

    var testHttpRequest;

    function ajaxTestHandler()
    {
        if (testHttpRequest.readyState === XMLHttpRequest.DONE) {
            if (testHttpRequest.status === 200) {
                // alert(testHttpRequest.responseText);

                let user_details_obj = JSON.parse(testHttpRequest.responseText);

                //alert(user_details_obj.id);

                //document.getElementById("username_key").innerText = "Username :";
                document.getElementById("fullnamevalue").innerText = user_details_obj.fullname;
                document.getElementById("phonenumbervalue").innerText = user_details_obj.phonenumber;
                document.getElementById("typevalue").innerText = user_details_obj.type;
                //document.getElementById("username_key").innerText = "Username :";
                //document.getElementById("username_value").innerText = user_details_obj.username;

            } else {
                alert('There was a problem with the request.');
            }
        }
    }

    function makeTestRequest()
    {

        testHttpRequest = new XMLHttpRequest();
        if (!testHttpRequest) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        // alert("Ajax request Created");

        let username = document.getElementById("username").innerText;
        dataString = "username="+username;

        testHttpRequest.onreadystatechange = ajaxTestHandler;
        testHttpRequest.open('POST','http://localhost:8072/test-service-4-fetchuserinfo/api/1.1/userinfo',true);
        testHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        testHttpRequest.send(dataString);


    }

    function testButton()
    {
        alert("the button is working");
    }

    //document.getElementById("show_profile_button").addEventListener("click",testButton);

    document.getElementById("show_profile_button").addEventListener("click",makeTestRequest);
}