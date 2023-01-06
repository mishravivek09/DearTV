document.querySelector("form").addEventListener("submit", verifyUser);

function verifyUser(event) {
  event.preventDefault();
  let form = document.querySelector("#form");
  let loginData = {
    userName: form.email.value,
    password: form.passwd.value,
  };
  loginData = JSON.stringify(loginData);
  userLogin(loginData);
}
let userLogin = (loginData) => {
  let url = "https://regapi-production.up.railway.app/user/login";
  fetch(url, {
    method: "POST",
    body: loginData,
    headers: {
      "content-type": "application/json",
    },
  })
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      if (res.message == undefined) {
        alert("Login Successful..");
        window.location.href="index.html";
        localStorage.setItem("SignIn", "success");
        localStorage.setItem("username",res.name);
      } else {
        alert(res.message);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};
