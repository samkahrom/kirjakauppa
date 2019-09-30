package com.example.kirjakauppa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.kirjakauppa.domain.Book;
import com.example.kirjakauppa.domain.BookRepository;

@SpringBootApplication
public class KirjakauppaApplication {

	private static final Logger log = LoggerFactory.getLogger(KirjakauppaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppaApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(BookRepository bookrepo) {
		return (args) -> {
			log.info("save a couple of books");
			bookrepo.save(new Book((long) 1111, "A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 0.00));
			bookrepo.save(new Book((long) 1111, "Animal Farm", "George Orwell", 1994, "2212343-5", 0.00));

			log.info("fetch all books");
			for (Book book : bookrepo.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
