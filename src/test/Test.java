package test;

import domain.BookVO;
import repository.BookRepository;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        BookRepository bookRepo = new BookRepository();
        ArrayList<BookVO> bookVOList = bookRepo.select("", 0);
        for (BookVO book : bookVOList) {
            System.out.println("도서번호 : " + book.getIsbn());
            System.out.println("도서명 : " + book.getName());
            System.out.println("출판사 : " + book.getPublish());
            System.out.println("저자 : " + book.getAuthor());
            System.out.printf("가격 : %,d원\n", book.getPrice());
            System.out.println("분류 : " + book.getCategoryName());
            System.out.println();
        }
    }
}
