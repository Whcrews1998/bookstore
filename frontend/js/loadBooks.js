const bookList = document.getElementById("bookList");

async function getBookList() {
    try {
        const response = await fetch("/api/book");

        if (!response.ok) {
            console.log("Failed to retrieve book list");
            return {};
        }

        const data = await response.json();
        return data;

    } catch (error) {
        console.error(error);
        return {};
    }
}


function createBookCard(bookData) {

    if (bookData === null) {
        console.error("Book data cannot be null!");
        return;
    }

    const bookDiv = document.createElement("div");
    bookDiv.classList.add("bookCard");

    const img = document.createElement("img");
    img.src = "/images/" + bookData.coverImageName;

    const title = document.createElement("h3");
    title.textContent = bookData.title;

    const author = document.createElement("p");
    author.textContent = bookData.author;

    const checkoutButton = document.createElement("button");
    checkoutButton.textContent = "Checkout";
    checkoutButton.addEventListener("click", async () => {
        try {
            const response = await fetch("/api/book/checkout", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    author: bookData.author,
                    title: bookData.title
                })
            })

            if (!response.ok) {
                alert("Checkout failed.");
                const text = await response.text();
                console.error(text);
                return;
            }

            alert("Checkout successful!");
        } catch (error) {
            alert("Checkout failed.");
            console.error(error);
        }
    })

    const topSection = document.createElement("div");
    topSection.classList.add("topSection");
    const bottomSection = document.createElement("div");
    bottomSection.classList.add("bottomSection");



    topSection.appendChild(img);
    topSection.appendChild(title);
    topSection.appendChild(author);
    bottomSection.appendChild(checkoutButton);

    bookDiv.appendChild(topSection);
    bookDiv.appendChild(bottomSection);

    return bookDiv;
}

function loadBookList(bookListJson) {

    bookListJson.forEach(book => {
        const data = createBookCard(book);
        bookList.appendChild(data);
    });
}

const response = await getBookList();
loadBookList(response);

// const data = await getBookList();
// console.log(data);

// const element = createBookCard(data[0]);
// bookList.appendChild(element.cloneNode(true));
// bookList.appendChild(element.cloneNode(true));
// bookList.appendChild(element.cloneNode(true));
// bookList.appendChild(element.cloneNode(true));
