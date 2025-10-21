import {useEffect} from "react";
import {NavLink} from "react-router-dom";

export default function Home({searchResults, setRandomState, randomBooks}) {

    let contentToRender = <></>;

    if (searchResults.length === 0) {
        //Make a copy of randomBooks array
        const copyRandomBooks = [...randomBooks];
        //Randomize books and render first 5
        const slicedShuffledBooks = copyRandomBooks.sort(() => Math.random() - 0.5).slice(0, 4);

        contentToRender =
            <div className={"mainContent"}>
                <section id={"renderRandomBooks"}>
                    <h2 style={{fontWeight: "bold"}}>Explore new books</h2>
                    <MapBooks books={slicedShuffledBooks}/>
                </section>
                <section className={"card"}>
                    <h2>Sign Up</h2>
                    <img src={"src/assets/accountBackground.png"} alt={"Account Sign In"}/>
                    <div>
                        <NavLink to={"/signup"}>
                            <button>Sign Up with email</button>
                        </NavLink>
                    </div>
                    <p>Already a member?<a href={"Sign"}> Sign In</a></p>
                </section>
            </div>
    } else {
        contentToRender =
            <div id={"main-body"}>
                <MapBooks books={searchResults}/>
            </div>
    }

    useEffect(() => {
            // Build the full url
            const getURL = "http://localhost:8080/books";
            // Create request object
            const requestObj = {
                method: "GET",
            }
        async function fetchRandomBooks() {
            try {
                const response = await fetch(getURL, requestObj);
                if(!response.ok) {
                    throw new Error(`Network response error: ${response.status}`);
                }
                const data = await response.json();
                setRandomState(data);
            } catch(error) {
                console.error(`There was a problem with fetch request: ${error.message}`);
            }
        }
        // Call the function
        fetchRandomBooks();
    },[]); // Empty array runs once


    // function getRandomBooks() {
    //     //Build the full url
    //     const getURL = "http://localhost:8080/books";
    //     // Create request object
    //     const requestObj = {
    //         method: "GET",
    //     }
    //     // Send request using fetch
    //     fetch(getURL, requestObj)
    //         .then(response => {
    //             //Check if response is ok
    //             if(!response.ok) {
    //                 throw new Error(`Network response error: ${response.status}`);
    //             }
    //             return response.json();
    //         })
    //         .then(data => {
    //             //Handle the data
    //             setRandomState(data);
    //         })
    //         .catch(error => {
    //             //Handle errors
    //             console.error(`There was a problem with fetch request: ${error.message}`);
    //         })
    // }

    function MapBooks({books}) {
        const BooksWithImg = books.filter(book => book.thumbnail);
        return (
            <>
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
            </>
        )
    }

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