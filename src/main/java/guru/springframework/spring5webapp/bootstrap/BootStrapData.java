package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Make this a Spring managed component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher("SFG Publishing", "St Petersburg", "FL");
        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book dodo = new Book("Domain Driven Design", "123213");
        eric.getBooks().add(dodo);
        dodo.getAuthors().add(eric);
        dodo.setPublisher(publisher);
        publisher.getBooks().add(dodo);

        // Spring data JPA is using Hibernate to save this to an in memory H2 Database
        authorRepository.save(eric);
        bookRepository.save(dodo);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book j2ee = new Book("J2EE Development", "847314734");
        rod.getBooks().add(j2ee);
        j2ee.getAuthors().add(rod);
        j2ee.setPublisher(publisher);
        publisher.getBooks().add(j2ee);

        authorRepository.save(rod);
        bookRepository.save(j2ee);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of Books: " + publisher.getBooks().size());
    }
}
