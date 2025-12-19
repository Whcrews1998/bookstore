try {
    const res = await fetch("/api/verify");

    if (!res.ok) {
        // Auth failure or server error
        location.replace("./login.html");
        console.log("Failure!");
    } else {
        // Auth OK → allow UI
        document.body.style.display = "block";
        console.log("Success!");
    }

} catch (err) {
    // Network / CORS / unexpected error
    location.replace("./login.html");
    console.log("Failure with exception!");
}

