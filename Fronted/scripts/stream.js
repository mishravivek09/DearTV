let verifyUser = localStorage.getItem("SignIn");
if (verifyUser !== "success") {
  window.location.href = "login.html";
}
let logoutUser = () => {
  let selected = document.querySelector("#username").value;
  if (selected === "logout") {
    localStorage.setItem("SignIn", "undefined");
    window.location.href = "login.html";
  }
};
let data = JSON.parse(localStorage.getItem("clicked")) || [];
let Video = (data) => {
  let frame = document.querySelector("#iframe");
  frame.src = data.movieUrl;
};
Video(data);
let Details = (data) => {
  let image = document.createElement("img");
  image.setAttribute("id", "images");
  image.src = data.movieImage;
  document.querySelector("#details").append(image);
  document.querySelector("#Names").innerHTML=`Watching Now - ${data.movieName}`;
};
Details(data);
