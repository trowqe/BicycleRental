function logout() {
	window.location="controller?command=logout";
}

function goBack() {
    window.history.back();
}

function createOrder(selectObject) {
	var objectId = selectObject.id;
	var bicycleId = objectId.substring(4, objectId.length);
	window.location="controller?command=prepareorder&bicycleid=" + bicycleId;
}

function returnBicycle(rentid) {
	window.location="controller?command=returnbicycle&rentid=" + rentid;
}

function openTab(evt, tabName) {
    var i, tabcontent, tablinks;

    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].className = tabcontent[i].className.replace(" active", "");
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).className += " active";
    evt.currentTarget.className += " active";
}

function cleanMessages(messageDiv) {
	messageDiv.innerHTML = "";
	messageDiv.className = "container";
}

function validateRegistrationForm() {
	cleanMessages(document.getElementById("registermsg"));
	var newpassword = document.forms["registrationForm"]["newpassword"].value;
	var repeatpassword = document.forms["registrationForm"]["repeatpassword"].value;

	if (!(newpassword == repeatpassword)) {
		var paragraph = document.createElement("p");
		var text = document
				.createTextNode("Значения, введенные в поле пароль и повтор пароля должны совпадать.");
		paragraph.appendChild(text);
		document.getElementById("registermsg").appendChild(paragraph);
		document.getElementById("registermsg").className += " wrong";
		return false;
	}
}

function validateOrderForm() {
	cleanMessages(document.getElementById("ordermsg"));
	var finishDate = new Date(document.getElementById('finishdate').value + " "
			+ document.getElementById('finishtime').value);

	var currentdate = new Date();

	if (finishDate < currentdate) {
		var paragraph = document.createElement("p");
		var text = document
				.createTextNode("Дата и время возврата должны быть позднее текущей даты и времени.");
		paragraph.appendChild(text);
		document.getElementById("ordermsg").appendChild(paragraph);
		document.getElementById("ordermsg").className += " wrong";
		return false;
	}
}

function changeLanguage() {
	document.getElementById("langForm").submit();
}

function clearBicycleFilter() {
	document.getElementById("rentalpoint").value = 0;
	document.getElementById("bicycletype").value = 0;
	document.getElementById("firm").value = "";
	document.getElementById("model").value = "";
	document.getElementById("filterbikes").submit();
}

function closeAlert() {
	document.getElementById("alert").style.display='none';
}

function closeAlertTimeout() {
	setTimeout(function(){fadeOut("alert"); closeAlert();}, 3000);	
}

function fadeOut(element) {
    var fadeTarget = document.getElementById(element);
    var fadeEffect = setInterval(function () {
        if (!fadeTarget.style.opacity) {
            fadeTarget.style.opacity = 1;
        }
        if (fadeTarget.style.opacity < 0.1) {
            clearInterval(fadeEffect);
        } else {
            fadeTarget.style.opacity -= 0.1;
        }
    }, 100);
}


function setChecked(obj, id) {
	list= document.getElementById("users").getElementsByTagName("tr");
	for (var i=0; i< list.length; ++i)  {
		list[i].classList.remove("checked");
	}
	obj.classList.add("checked");
	document.getElementById("user"+id).checked = true;
}

function blockUser() {
	var radios = document.getElementsByName('user');
	for (var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			window.location="controller?command=updateuserstatus&userid="+radios[i].value+"&status=block";
			break;
		}
	}
}

function unblockUser() {
	var radios = document.getElementsByName('user');
	for (var i = 0, length = radios.length; i < length; i++) {
		if (radios[i].checked) {
			window.location="controller?command=updateuserstatus&userid="+radios[i].value+"&status=unblock";
			break;
		}
	}
}