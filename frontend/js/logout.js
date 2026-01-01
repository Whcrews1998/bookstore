function logout() {
    fetch("api/logout").then((res) => {
        if (!res.ok) {
            alert("Failed to logout");
            return;
        }
        window.location.href = "/html/login.html";
    });
}

document.getElementById("logout").addEventListener("click", logout);
