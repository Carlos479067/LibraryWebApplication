import {NavLink} from "react-router-dom";
import React, {useState} from "react";

export default function SignUp() {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstNameError, setFirstNameError] = useState("");
    const [lastNameError, setLastNameError] = useState("");

    function handleFirstName(event) {
        setFirstName(event.target.value);
        // Clear error if user types in any values
        setFirstNameError("");
    }

    function handleLastName(event) {
        setLastName(event.target.value);
        // Clear error if user types in any values
        setLastNameError("");
    }

    function handleOnSubmit(event) {
        event.preventDefault();

        const userData = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
        }

        if(firstName === "") {
            setFirstNameError("Field empty");
            return;
        }
        else if(lastName === "") {
            setLastNameError("Field empty");
            return;
        }

    }

    return (

        <div className={"signUp"}>
            <h2>Sign Up</h2>
            <form className={"signUpForm"} onSubmit={handleOnSubmit}>
                <label>First name:
                    <input type={"text"} onChange={handleFirstName} value={firstName}/>
                    {firstNameError && <p>{firstNameError}</p>}
                </label>
                <label>Last name:
                    <input type={"text"} onChange={handleLastName} value={lastName}/>
                    {lastNameError && <p>{lastNameError}</p>}
                </label>
                <button type={"submit"}>Submit</button>
            </form>
        </div>
    )
}