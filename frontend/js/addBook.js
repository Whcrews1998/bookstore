document.getElementById("form-id").addEventListener("submit", (e) => {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    fetch("/api/book/add", {
        method: "POST",
        body: formData
    }).then(res => {
        if (!res.ok) alert("Failed to upload book!");
        else alert("Book uploaded!");
    }).catch(err => console.error(err));
})
