document.querySelector("#signup").addEventListener("click", myFunction);
document.querySelector("#button").addEventListener("click", verifyMail);
document.querySelector("#sendotp").addEventListener("click", getMail);
window.onload = document.querySelector("#form").reset();
let dataArr = JSON.parse(localStorage.getItem("users")) || [];
function myFunction(event) {
  event.preventDefault();
  let form = document.querySelector("#form");
  let signupData = {
    name: form.name.value,
    userName: form.email.value,
    password: form.passwd.value,
  };
  signupData = JSON.stringify(signupData);
  userSignup(signupData);
}

let userSignup = (signupData) => {
  let url = "https://regapi-production.up.railway.app/user/signup";
  fetch(url, {
    method: "POST",
    body: signupData,
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      if (res.message == "User already registered..") {
        alert(res.message);
        document.querySelector("#signup").disabled = true;
      } else {
        document.querySelector("#form").reset();
        alert("Signup Successful..")
        window.location.href = "login.html";
      }
    })
    .catch((err) => {
      console.log(err);
    });
};


function getMail(event) {
  event.preventDefault();
  let uname = document.querySelector("#email").value;
  let name = document.querySelector("#name").value;
  let pass = document.querySelector("#passwd").value;
  if (name.length < 3) {
    alert("Name should be greater than 3 digits");
  } else if (uname.length < 1) {
    alert("Please enter valid email");
  } else if (pass.length < 8) {
    alert("Password should be minimum 8 in length..");
  } else {
    let url = `https://emai-app-production.up.railway.app/generate/otp/${uname}`;
    fetch(url).then((res) => {
      return res.text();
    }).then((res) => {
      if (res != `OTP sended successfully to ${uname}`) {
        alert("Invalid email id..");
      } else {
        alert(res);
        document.querySelector("#button").disabled = false;
        document.querySelector("#button").style.cursor = "pointer";
      }
    }).catch((err) => {
      console.log(err);
    })
  }

}

function verifyMail(event) {
  event.preventDefault();
  let uname = document.querySelector("#email").value;
  let otp = document.querySelector("#emailotp").value;
  let url = `https://emai-app-production.up.railway.app/validate/otp/${uname}/${otp}`;
  fetch(url).then((res) => {
    return res.text();
  }).then((res) => {
    alert(res);
    if (res !== "Wrong OTP! Please try again..") {
      document.querySelector("#signup").disabled = false;
      document.querySelector("#signup").style.cursor = "pointer";
    }

  }).catch((err) => {
    console.log(err);
  })
}