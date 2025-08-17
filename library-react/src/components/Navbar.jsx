import {useState} from "react";

export default function NavBar({updateState}) {

    const [bookSearch, setBookSearch] = useState("");

    function HandleQuery(event) {
        setBookSearch(event.target.value);
    }

    function HandleSearchButton(event) {
        event.preventDefault();

        console.log(bookSearch);
        //Build the full url with the search term appended at the end
        const getURL = "http://localhost:8080/books/search?query=" + encodeURIComponent(bookSearch);

        // Create request Object
        const requestObj = {
            method: "GET",
        }

        // 3. Send the request using fetch
        fetch(getURL, requestObj)
            .then(response => {
                //Check if response is ok
                if(!response.ok) {
                    throw new Error(`Network response error: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                console.log(data[0]);
                //Handle the data
                updateState(data)
            })
            .catch(error => {
                //Handle errors
                console.error(`There was a problem with fetch request: ${error.message}`);
            })

    }

    return (
            <nav>
                <ul>
                    <li><a href={"#home"}><h3>Homepage</h3></a></li>
                    <li className={"dropdown"}>
                        <a href={"#collection"}><h3>Collections</h3></a>
                        <ul className={"dropdown-content"}>
                            <li><a href={"#wishlist"}>Wish List</a></li>
                            <li><a href={"#wishlist"}>Owned List</a></li>
                        </ul>
                    </li>
                    <li><a href={"#browse"}><h3>Browse Books</h3></a></li>
                </ul>
                <form className={"search-bar"} onSubmit={HandleSearchButton}>
                    <input className={"search"} type={"text"} placeholder={"Search books..."} onChange={HandleQuery}></input>
                    <button className={"btn-search"} type={"submit"}>Go</button>
                </form>
            </nav>
    )
}