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
            addNewBook.classList.remove("hidden");
        }

    } catch (error) {
        console.error(error);
    }
};

whoAmI();
