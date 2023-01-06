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

let query = localStorage.getItem("userQuery");
document.querySelector(
  "title"
).innerText = `You searched for ${query} - DearTV`;
document.querySelector(
  "#headingName"
).innerHTML = `Search result for : ${query}`;
function getSearchResult(query) {
  let url = `https://movie-api-production-4652.up.railway.app/vivek/movies/find/${query}`;
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      DisplayData(res);
    })
    .catch((err) => {
      console.log(err);
    });
}
window.onload = getSearchResult(query);

function DisplayData(data) {
  let container = document.querySelector("#movie");
  container.innerHTML = "";
  if (data.length == 0) {
    let q = document.querySelector(".noresult");
    q.innerHTML = `Oops! No Results were Found! </br> Check Your Spelling If Incorrect. </br>
    Search The Correct Word To Get Desired Results! </br>
    Use Google To Search The Movie Correct Name. </br>
    Search The Exact Name To Get Better Filter Results!`;
  }
  data.forEach(function (element) {
    let cdiv1 = document.createElement("div");
    let Name = document.createElement("h4");
    let H4 = document.createElement("h4");
    let P = document.createElement("p");
    let Download = document.createElement("button");
    Download.innerText = "Watch Now";
    Download.addEventListener("click", function () {
      clicked(element);
    });
    let div1 = document.createElement("div");
    div1.setAttribute("id", "details");
    div1.append(Name, P, H4, Download);
    Name.innerText = element.movieName;
    let date = element.movieYear;
    let dateobj=new Date(date);
    H4.innerText=dateobj.toDateString();
    P.innerText = `Rating :- ${element.movieRating}`;
    let Poster = document.createElement("img");
    Poster.src = element.movieImage;
    cdiv1.append(Poster, div1);
    container.append(cdiv1);
  });
}
let username = localStorage.getItem("username");
document.querySelector("#names").innerHTML = username.substring(0, 20);
document.querySelector("#query").addEventListener("search", getSearchResults);
function getSearchResults() {
  let query1 = document.querySelector("#query").value;
  if (query1.length == 0) {
    document.querySelector("#clouser").style.display = "none";
  } else {
    document.querySelector("#clouser").style.display = "block";
  }
  let url = `https://movie-api-production-4652.up.railway.app/vivek/movies/find/${query1}`;
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      searchResult(res);
    })
    .catch((err) => {
      console.log(err);
    });
}

function searchResult(res) {
  let container = document.querySelector("#querydetails");
  container.innerHTML = "";
  if (res.length == 0) {
    let q = document.createElement("h3");
    let image = document.createElement("img");
    image.style.width = "100%";
    image.style.marginBottom = "-30px";
    image.src = "/images/no-result.gif";
    q.innerHTML = "No Results Found..";
    q.style.color = "white";
    q.style.textAlign = "center";
    container.append(q, image);
  }
  res.forEach((element) => {
    let maindiv = document.createElement("div");
    let div1 = document.createElement("div");
    let div2 = document.createElement("div");
    let Name = document.createElement("h4");
    let H4 = document.createElement("h4");
    let P = document.createElement("p");
    Name.innerText = element.name;
    H4.innerText = element.year;
    P.innerText = `Rating :- ${element.rating}`;
    let Poster = document.createElement("img");
    Poster.src = element.imgUrl;
    div1.append(Poster);
    div2.append(Name, P, H4);
    Poster.addEventListener("click", function () {
      clicked(element);
    });
    P.addEventListener("click", function () {
      clicked(element);
    });
    H4.addEventListener("click", function () {
      clicked(element);
    });
    Name.addEventListener("click", function () {
      clicked(element);
    });
    maindiv.append(div1, div2);
    container.append(maindiv);
  });
}
document.querySelector("#search").addEventListener("click", searchFunctions);
document.querySelector("#query").addEventListener("keypress", searchFunction);

function searchFunctions() {
  let input = document.querySelector("#query").value;
  localStorage.setItem("userQuery", input);
  window.location.href = "search.html";
}
function searchFunction(e) {
  if (e.keyCode == 13) {
    let input = document.querySelector("#query").value;
    localStorage.setItem("userQuery", input);
    window.location.href = "search.html";
  }
}
let clicked = (elem) => {
  localStorage.setItem("clicked", JSON.stringify(elem));
  window.location.href = "Stream.html";
};
