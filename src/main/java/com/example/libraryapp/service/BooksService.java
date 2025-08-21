package com.example.libraryapp.service;
import com.example.libraryapp.dto.BookDto;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;
    private final RestTemplate restTemplate;
    @Value("${google.books.api.key}")
    private String apiKey;

    public BooksService(BooksRepository booksRepository, RestTemplate restTemplate) {

        this.booksRepository = booksRepository;
        this.restTemplate = restTemplate;
    }

    public List<BookDto> getAllBooks() {
        return fetchFromGoogleBooks("Fantasy");
    }

    public List<BookDto> searchBooks(String query) {
        return fetchFromGoogleBooks(query);
    }

    public Optional<BookDto> searchIsbn(String isbn) {
        String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
        Map<String, Object> response = restTemplate.getForObject(baseUrl, Map.class);

        if(response.containsKey("items")) {
            Object itemObj = response.get("items");
            if (itemObj instanceof List) {
                List<?> items = (List<?>) itemObj;

                if (items.get(0) instanceof Map) {
                    Map<String, Object> mapItem = (Map<String, Object>) items.get(0);
                    Object volInfoObject = mapItem.get("volumeInfo");
                    if (volInfoObject != null && volInfoObject instanceof Map) {
                        Map<String, Object> volumeInfo = (Map<String, Object>) volInfoObject;
                        BookDto book = mapFromGoogleBookItem(volumeInfo);
                        return Optional.of(book);
                    }
                }
            }
        }
        return Optional.empty();
    }

    List<BookDto> fetchFromGoogleBooks(String query) {
        String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=10&key=" + apiKey;
        Map<String, Object> response = restTemplate.getForObject(baseUrl, Map.class);
        List<BookDto> bookList = new ArrayList<>();

        if(response.containsKey("items")) {
            Object itemObj = response.get("items");
            if(itemObj instanceof List) {
                List<?> items = (List<?>) itemObj;

                for(int i=0; i < items.size(); i++) {
                    if(items.get(i) instanceof Map) {
                        Map<String, Object> mapItem = (Map<String, Object>) items.get(i);
                        Object volInfoObject = mapItem.get("volumeInfo");
                        if(volInfoObject != null && volInfoObject instanceof Map) {
                            Map<String, Object> volumeInfo = (Map<String, Object>) volInfoObject;
                            BookDto book = mapFromGoogleBookItem(volumeInfo);
                            bookList.add(book);
                        }
                    }
                }
            }
        }
        return bookList;
    }

    private BookDto mapFromGoogleBookItem(Map<String, Object> volumeInfo) {
        BookDto dto = new BookDto();
        dto.setTitle((String) volumeInfo.get("title"));
        dto.setPublishDate((String) volumeInfo.get("publishedDate"));
        dto.setDescription((String) volumeInfo.get("description"));
        Object catObj = volumeInfo.get("categories");
        if(catObj != null && catObj instanceof List) {
            List<?> categories = (List<?>) catObj;
            if(!categories.isEmpty()) {
                if(categories.get(0) instanceof String) {
                    dto.setGenre((String) categories.get(0));
                }
            }
        }

        Object imageObj = volumeInfo.get("imageLinks");
        if(imageObj != null && imageObj instanceof Map) {
            Map<String, Object> imgObj = (Map<String, Object>) imageObj;
            Object thumbNailObj =  imgObj.get("thumbnail");
            if(thumbNailObj != null && thumbNailObj instanceof String) {
                dto.setThumbnail((String) thumbNailObj);
            }
        }

        Object authObject = volumeInfo.get("authors");
        if(authObject != null && authObject instanceof List) {
            dto.setAuthors((List<String>) volumeInfo.get("authors"));
        }
        else {
            dto.setAuthors(List.of("Unknown"));
        }

        Object isbnObj = volumeInfo.get("industryIdentifiers");
        if(isbnObj != null && isbnObj instanceof List) {
            List<?> identifiers = (List<?>) isbnObj;
            for(int i = 0; i < identifiers.size(); i++) {
                if(identifiers.get(i) instanceof Map) {
                    Map<String, Object> identifierMap = (Map<String, Object>) identifiers.get(i);
                    String type = (String) identifierMap.get("type");
                    String identifierStr = (String) identifierMap.get("identifier");
                    if(type.equals("ISBN_13")) {
                        dto.setIsbn(identifierStr);
                        break;
                    }
                }
            }
        }

        return dto;

    }

    private BookDto mapToBook(Book book) {
        BookDto dto = new BookDto();

        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setThumbnail(book.getThumbNail());
        dto.setDescription(book.getDescription());
        dto.setGenre(book.getGenre());
        dto.setAuthors(book.getAuthors());
        dto.setPublishDate(book.getPublishDate());
        return dto;
    }
}
