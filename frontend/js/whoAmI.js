const addNewBook = document.getElementById("addNewBook");

async function whoAmI() {
    try {
        const res = await fetch("/api/whoami");

        if (!res.ok) {
            console.error("Failed to fetch whoami.");
            return;
        }

        const data = await res.json();
        if (data.userRoles.includes("ROLE_ADMIN")) {
            alert("User is an admin");
            addNewBook.classList.remove("hidden");
        } else {
            alert("User is not an admin");
        }
    } catch (error) {
        console.error(error);
    }
};

whoAmI();
