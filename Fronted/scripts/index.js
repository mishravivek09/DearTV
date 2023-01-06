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
let Movie = [];
document.querySelector("#sortmovie").addEventListener("change", sortFun);
let movies = () => {
  let url = "https://movie-api-production-4652.up.railway.app/vivek/movies/get";
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      Display(res);
      slideShow(res);
      getData(res);
    })
    .catch((err) => {
      console.log(err);
    });
};
window.onload = movies();

let getData = (data) => {
  data.map((elem) => {
    Movie.push(elem);
  });
};

function Display(data) {
  let container = document.querySelector("#movies");
  container.innerHTML = "";
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

function sortFun(event) {
  let selected = document.querySelector("#sortmovie").value;
  if (selected == "Ascending") {
    Movie.sort(function (a, b) {
      if (a.movieName > b.movieName) {
        return 1;
      }
      if (a.movieName < b.movieName) {
        return -1;
      }
      return 0;
    });
    Display(Movie);
  } else if (selected === "Descending") {
    Movie.sort(function (a, b) {
      if (a.movieName > b.movieName) {
        return -1;
      }
      if (a.movieName < b.movieName) {
        return 1;
      }
      return 0;
    });
    Display(Movie);
  } else if (selected == "Oldest") {
    Movie.sort(function (a, b) {
      if (a.movieYear > b.movieYear) {
        return 1;
      }
      if (a.movieYear < b.movieYear) {
        return -1;
      }
      return 0;
    });
    Display(Movie);
  } else if (selected == "Latest") {
    Movie.sort(function (a, b) {
      if (a.movieYear > b.movieYear) {
        return -1;
      }
      if (a.movieYear < b.movieYear) {
        return 1;
      }
      return 0;
    });
    Display(Movie);
  } else if (selected == "High Ratings") {
    Movie.sort(function (a, b) {
      if (a.movieRating > b.movieRating) {
        return -1;
      }
      if (a.movieRating < b.movieRating) {
        return 1;
      }
      return 0;
    });
    Display(Movie);
  } else if (selected == "Low Ratings") {
    Movie.sort(function (a, b) {
      if (a.movieRating > b.movieRating) {
        return 1;
      }
      if (a.movieRating < b.movieRating) {
        return -1;
      }
      return 0;
    });
    Display(Movie);
  }
}

function slideShow(data) {
  let container = document.querySelector("#slideshow");
  let div1 = document.createElement("div");
  let div2 = document.createElement("div");
  let div3 = document.createElement("div");
  let div4 = document.createElement("div");
  let div5 = document.createElement("div");
  let Images = data.map(function (elem) {
    return elem.movieImage;
  });
  let i = 0;
  setInterval(function () {
    if (i == Images.length) {
      i = 0;
    }
    let Img = document.createElement("img");
    Img.src = Images[i];
    div1.innerHTML = "";
    div1.append(Img);
    container.append(div1);
    i++;
  }, 3000);
  let j = 1;
  setInterval(function () {
    if (j == Images.length) {
      j = 0;
    }
    let IMG = document.createElement("img");
    IMG.src = Images[j];
    div2.innerHTML = "";
    div2.append(IMG);
    container.append(div2);
    j++;
  }, 3000);
  let k = 2;
  setInterval(function () {
    if (k == Images.length) {
      k = 0;
    }
    let IMG = document.createElement("img");
    IMG.src = Images[k];
    div3.innerHTML = "";
    div3.append(IMG);
    container.append(div3);
    k++;
  }, 3000);
  let l = 3;
  setInterval(function () {
    if (l == Images.length) {
      l = 0;
    }
    let IMG = document.createElement("img");
    IMG.src = Images[l];
    div4.innerHTML = "";
    div4.append(IMG);
    container.append(div4);
    l++;
  }, 3000);
  let m = 4;
  setInterval(function () {
    if (m == Images.length) {
      m = 0;
    }
    let IMG = document.createElement("img");
    IMG.src = Images[m];
    div5.innerHTML = "";
    div5.append(IMG);
    container.append(div5);
    m++;
  }, 3000);
}
// if(Movie.length !==0){
//   slideShow();
// }
// setTimeout((t) => {
//   slideShow();
// }, 5000);

let clicked = (elem) => {
  localStorage.setItem("clicked", JSON.stringify(elem));
  window.location.href = "Stream.html";
};
let username = localStorage.getItem("username");
document.querySelector("#names").innerHTML = username.substring(0, 20);
document.querySelector("#query").addEventListener("search", getSearchResult);
function getSearchResult() {
  let query = document.querySelector("#query").value;
  if (query.length == 0) {
    document.querySelector("#clouser").style.display = "none";
  } else {
    document.querySelector("#clouser").style.display = "block";
  }
  let url = `https://movie-api-production-4652.up.railway.app/vivek/movies/find/${query}`;
  fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((res) => {
      if(res.message==`Movies not found by name : ${query}`){
        let container = document.querySelector("#querydetails");
        container.innerHTML = "";
        let q=document.createElement("h3");
        let image=document.createElement("img");
        image.style.width="100%";
        image.style.marginBottom="-30px";
        image.src="/images/no-result.gif";
        q.innerHTML="No Results Found..";
        q.style.color="white";
        q.style.textAlign="center";
        container.append(q,image);
      }else{
        searchResult(res);
      }
    })
    .catch((err) => {
      console.log(err);
    });
}

function searchResult(res) {
  let container = document.querySelector("#querydetails");
  container.innerHTML = "";
  res.forEach((element) => {
    let maindiv = document.createElement("div");
    let div1 = document.createElement("div");
    let div2 = document.createElement("div");
    let Name = document.createElement("h4");
    let H4 = document.createElement("h4");
    let P = document.createElement("p");
    Name.innerText = element.movieName;
    H4.innerText = element.movieYear;
    P.innerText = `Rating :- ${element.movieRating}`;
    let Poster = document.createElement("img");
    Poster.src = element.movieImage;
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
