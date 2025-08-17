
export default function Main({searchResults}) {

    let contentToRender = <></>;

    if (searchResults.length === 0) {
        contentToRender =
            <main id={"main-body"}>
                <div>
                    <p style={{color: "black"}}>Hello</p>
                </div>
                <p style={{color: "black"}}>Hello</p>
            </main>
    } else {
        contentToRender = <MapBooks/>
    }

    function MapBooks() {
        const BooksWithImg = searchResults.filter(book => book.thumbnail);

        return (
            <main>
                {BooksWithImg.length > 0 ? (
                    <>
                        <ul>
                            {BooksWithImg.map((book) => (
                                <RenderBooks bookObj={book} key={book.isbn}/>
                            ))}
                        </ul>
                    </>
                ) : (<p>No books to display</p>)
                }
            </main>
        )
    }

    /* HTML Response
    String isbn;
    String title;
    String description;
    String genre;
    String thumbNail;
    String publishDate;
    List<String> authors;
    */
    function RenderBooks({bookObj}) {
        return (
            <li>
                <div className={"container"}>
                    <img id={"thumbnail-img"} src={bookObj.thumbnail} alt={bookObj.title}/>
                    <div>
                        <h2>{bookObj.title}</h2>
                        <h4>By: {bookObj.authors.join(", ")}</h4>
                        {/*<h4>Genre: {bookObj.genre}</h4>*/}
                        {/*<h4>Published: {bookObj.publishDate}</h4>*/}
                        {/*<p>{bookObj.description}</p>*/}
                    </div>
                    <div className={"btn-collection"}>
                        <button id={"wishlist-btn"}>
                            <span>Add to Wishlist</span>
                            <img src={"src/assets/star.png"} alt={"Star"} id={"wishlist-img"}/>
                        </button>
                        <button id={"owned-btn"}>
                            <span>Add to Owned Collection</span>
                            <img src={"src/assets/Owned.png"} alt={"Checkmark"} id={"owned-img"}/>
                        </button>
                    </div>
                </div>
            </li>
        )
    }

    return (
        <main id={"main-body"}>
            {contentToRender}
        </main>

    )


}