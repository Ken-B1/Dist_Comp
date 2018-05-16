function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function showBoards() {
    document.getElementById("boards").style.display = "block";
}

function validateForm() {
    var email = document.forms["signUp"]["email"].value;
    var uname = document.forms["signUp"]["username"].value;
    var pwd = document.forms["signUp"]["password"].value;
    var fname = document.forms["signUp"]["fname"].value;
    var lname = document.forms["signUp"]["lname"].value;
    var gender = document.forms["signUp"]["gender"].value;
    //console.log("email id is got");
    if(!email.toString().includes("@")){
        document.getElementById("emailError").innerHTML = "Please provide proper email";
        return false;
    }
    if(uname.length > 45){
        document.getElementById("unameError").innerHTML = "Username should contain 45 charecters";
        return false;
    }
    if(pwd.length > 45){
        document.getElementById("pwdError").innerHTML = "Password should contain 45 charecters";
        return false;
    }
    if(fname.length > 45){
        document.getElementById("fnameError").innerHTML = "Firstname should contain 45 charecters";
        return false;
    }
    if(lname.length > 45){
        document.getElementById("lnameError").innerHTML = "Lastname should contain 45 charecters";
        return false;
    }
     if(gender.toString().includes("Gender")){
        document.getElementById("gError").innerHTML = "Select Gender";
        return false;
    }
}