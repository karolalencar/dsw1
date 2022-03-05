var xmlHttp;

function areaSelecionada(str) {
    
    if (typeof XMLHttpRequest !== "undefined") {
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlHttp === null) {
        alert("Browser does not support XMLHTTP Request");
        return;
    }
   
    var url = "profissionais/buscaPorAreaJS";
    url += "?area=" + str;
    xmlHttp.onreadystatechange = atualizaExpertise;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}

function atualizaExpertise() {
    if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {
        document.getElementById("expertise").innerHTML = xmlHttp.responseText;
    }
}