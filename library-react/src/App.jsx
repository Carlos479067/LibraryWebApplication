import './App.css'
import './index.css'
import NavBar from '../src/components/Navbar.jsx';
import Header from "./components/Header.jsx";
import Main from "./components/Main.jsx";
import {useState} from "react";

function App() {

    const[searchResults, setSearchResults] = useState([]);

    function UpdateState(data) {
        setSearchResults(data);
    }

  return (
      <>
        <Header/>
        <NavBar updateState={UpdateState}/>
          <Main searchResults={searchResults}/>
      </>
  )
}

/*
    Passed updateState as a prop to NavBar to be used.
    Navbar fetches the data from user and calls updateState(data).
    updateState updates the state and now searchResults holds the data.
    searchResults(data) will now be sent to Main as a prop to be used.
 */

export default App
