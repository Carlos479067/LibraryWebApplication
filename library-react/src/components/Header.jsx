import headerImg from "../assets/header-img.png";

export default function Header() {
    return (
        <header className={"header"} style={{backgroundImage: `url(${headerImg})`, backgroundSize: "cover", backgroundPosition: "15% 15%"}}>
            <img src={"src/assets/books.png"} alt={"books logo"}/>
            <h1 style={{color: "#EDEADE"}}>Books</h1>
        </header>
    )
}