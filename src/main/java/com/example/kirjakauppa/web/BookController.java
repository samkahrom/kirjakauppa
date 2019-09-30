package com.example.kirjakauppa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kirjakauppa.domain.Book;
import com.example.kirjakauppa.domain.BookRepository;

@Controller

public class BookController {

	@Autowired
	private BookRepository bookrepo;

	@RequestMapping(value = "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", bookrepo.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookrepo.save(book);
		return "redirect:/booklist";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookrepo.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/editbook/{id}")
	public String editbook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookrepo.findById(bookId));
		return "/editbook";
	}

	@RequestMapping(value = "editbook/save", method = RequestMethod.POST)
	public String saveEdit(Book book) {
		bookrepo.save(book);
		return "redirect:/booklist";
	}

}
