import './App.css'
import './index.css'
import NavBar from '../src/components/Navbar.jsx';
import Header from "./components/Header.jsx";
import Home from "./pages/Home.jsx";
import {useState} from "react";
import {Route, Router, Routes} from "react-router-dom";

function App() {

    const[searchResults, setSearchResults] = useState([]);
    const[randomBooks, setRandomBooks] = useState([]);

    function UpdateState(data) {
        setSearchResults(data);
    }

    function resetState() {
        setSearchResults([]);
    }

    function setRandomState(data) {
        setRandomBooks(data);
    }

  return (
          <>
            <Header/>
            <NavBar updateState={UpdateState} resetState={resetState}/>
            <Routes>
              <Route path={"/"} element={<Home searchResults={searchResults} setRandomState={setRandomState} randomBooks={randomBooks}/>}/>
            </Routes>
          </>
  )
}

/*
    Passed updateState as a prop to NavBar to be used.
    Navbar fetches the data from user and calls updateState(data).
    updateState updates the state and now searchResults holds the data.
    searchResults(data) will now be sent to Home as a prop to be used.
 */

export default App
