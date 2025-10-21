import React, {useState} from "react";
import {NavLink} from "react-router-dom";

export default function NavBar({updateState, resetState}) {

    const [keyStroke, setKeyStroke] = useState("");

    function handleInputValue(e) {
        setKeyStroke(e.target.value);
    }

    async function handleFetch() {
        const getUrl = "http://localhost:8080/books/search?query=" + encodeURIComponent(keyStroke);
        const objMethod = {
            method: "GET"
        }
        try {
            const response = await fetch(getUrl, objMethod);

            if(!response.ok) {
                throw new Error(`Network response error: ${response.status}`);
            }

            const data = await response.json();
            updateState(data);
        } catch(error) {
            console.error(`There was a problem with fetch request: ${error.message}`);
        }
    }

    return (
        <nav className={"navbar"}>
            <div className={"navContent"}>
                <div className={"navbarDiv"}>
                    <ul>
                        <li><a href={"/"}><h2>Home</h2></a></li>
                        <li><a><h2>Collection</h2></a></li>
                        <li><a><h2>Browse</h2></a></li>
                    </ul>
                </div>
                <div className={"searchBar"}>
                    <form onSubmit={(e) => {e.preventDefault(); handleFetch();}}>
                        <input type={"text"} value={keyStroke} onChange={handleInputValue} placeholder={"Search Books..."}></input>
                        <button type={"submit"}>Go</button>
                    </form>
                </div>
            </div>
        </nav>
    )
}