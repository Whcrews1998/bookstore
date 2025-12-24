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

    } catch(error) {
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

    bookDiv.appendChild(img);
    bookDiv.appendChild(title);
    bookDiv.appendChild(author);

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