import {useState} from "react";

    function TestButton() {
        const userId = 2;
        const [bookStatus, setBookStatus] = useState('WISHLIST');
        const [books, setBooks] = useState([]);

        const fetchBooks = (status) => {
            setBookStatus(status); // Optional: to display which list is currently shown
            fetch(`http://localhost:8080/users/${userId}/books?bookStatus=${status}`)
                .then(res => res.json())
                .then(data => setBooks(data))
                .catch(err => console.error(err));
        };

        return (
            <div>
                <h2>{bookStatus ? `${bookStatus} Books` : 'Choose a collection'}</h2>
                <button onClick={() => fetchBooks('WISHLIST')}>Wishlist</button>
                <button onClick={() => fetchBooks('OWNED')}>Owned</button>
                <ul>
                    {books.map(book => (
                        <li key={book.isbn}>
                            <h3>{book.title}</h3>
                            <p><strong>Authors:</strong> {book.authors.join(', ')}</p>
                            <p><strong>Genre:</strong> {book.genre}</p>
                            <p><strong>Publishing Date:</strong> {book.publishDate}</p>
                            <p>{book.description}</p>
                            <img src={book.thumbnail} alt={`Cover of ${book.title}`} width="100"/>
                        </li>
                    ))}
                </ul>

            </div>
        );
    }

export default TestButton;
