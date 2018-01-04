/*$(document).ready(function () {

    $("#eventDate").datepicker({
        defaultDate: new Date(),
        minDate: new Date()

    });

    $("#startDate").datepicker({
        defaultDate: new Date(),
        minDate: new Date(),
        onSelect: function (dateStr) {
            $("#endDate").val(dateStr);
            $("#endDate").datepicker("option", { minDate: new Date(dateStr) })
        }
    });

    $("#endDate").datepicker({
        defaultDate: new Date(),
        minDate: new Date()
        
       
        
    });

*/


function validateName(x) {
    // Validation rule
    var re = /[A-Za-z -']$/;
    // Check input
    if (re.test(document.getElementById(x).value)) {
        // Style green
        document.getElementById(x).style.background = '#ccffcc';
        // Hide error prompt
        document.getElementById(x + 'Error').style.display = "none";
        return true;
    } else {
        // Style red
        document.getElementById(x).style.background = '#e35152';
        // Show error prompt
        document.getElementById(x + 'Error').style.display = "block";
        return false;
    }
}
function validateEmail(email) {
    var emailRegex = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@(husky\.neu\.edu|neu\.edu)$/;
    if (emailRegex.test(email)) {
        document.getElementById('email').style.background = '#ccffcc';
        document.getElementById('emailError').style.display = "none";
        return true;
    } else {
        // Style red
        document.getElementById('email').style.background = '#e35152';
        document.getElementById('emailError').style.display = "block";
        return false;
    }
}

function validatePhone(phone) {
    var stripped = phone.value.replace(/[\(\)\.\-\ ]/g, '');
    if (phone.value == "") {
        //Allow bank entries
        document.getElementById('phone').style.background = '#ccffcc';
        document.getElementById('phoneError').style.display = "none";
        return false;
    } else if (isNaN(parseInt(stripped))) {
        document.getElementById('phone').style.background = '#e35152';
        document.getElementById('phoneError').style.display = "block";
        return false;
    } else if (!(stripped.length == 10)) {
        document.getElementById('phone').style.background = '#e35152';
        document.getElementById('phoneError').style.display = "block";
        return false;
    } else {
        document.getElementById('phone').style.background = '#ccffcc';
        document.getElementById('phoneError').style.display = "none";
        return true;
    }

}
function validateNotEmpty(documentId) {
    var text = document.getElementById(documentId).value;
    var errId = documentId.concat('_Error');
    if (text == "") {
        document.getElementById(documentId).style.background = '#e35152';
        document.getElementById(errId).style.display = "block";
        return false
    } else {
        document.getElementById(documentId).style.background = '#ccffcc';
        document.getElementById(errId).style.display = "none";
        return true;
    }
}

function displaySingleDay() {
    document.getElementById('singleday').style.display = "block";
    document.getElementById('multiday').style.display = "none";
}
function displayMultiDay() {
    document.getElementById('singleday').style.display = "none";
    document.getElementById('multiday').style.display = "block";
}


function displayAsOfficialEvent(logged_user) {
    var emailRegex = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@(\.neu\.edu)$/;
    if (emailRegex.test(logged_user)) {
        document.getElementById('official').style.display = "block";
        return true;
    } else {

        document.getElementById('official').style.display = "none";
        return false;
    }
}




function validateForm() {
    // Set error catcher
    var error = 0;
    // Check name
    if (!validateName('name')) {
        document.getElementById('nameError').style.display = "block";
        error++;
    }
    // Validate email
    if (!validateEmail(document.getElementById('email').value)) {
        document.getElementById('emailError').style.display = "block";
        error++;
    }

    if (!validatePhone(document.getElementById('phone').value)) {
        document.getElementById('emailPhone').style.display = "block";
        error++;
    }

}