package controller;

import domain.BookVO;
import repository.BookRepository;
import view.BookSearchView;
import view.CenterFrame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookController extends JFrame {
    BookSearchView searchPan;
    BookRepository bookRepo;
    ArrayList<BookVO> bookVOList;
    JComboBox<String> combo;
    JButton btnSearch;
    JTextField txtSearch;
    JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);



    public BookController() {
        searchPan = new BookSearchView();
        bookRepo = new BookRepository();

        combo = searchPan.getCombo();
        bookVOList = bookRepo.select("", combo.getSelectedIndex());

        searchPan.setBookVOList(bookVOList);
        searchPan.initView();
        searchPan.putSearchResult();

        // Enter 버튼으로 검색
        txtSearch = searchPan.getTxtSearch();
        txtSearch.addActionListener(btnLsnr);

        // 검색 버튼을 눌러 검색
        btnSearch = searchPan.getBtnSearch();
        btnSearch.addActionListener(btnLsnr);

        tab.add("도서검색", searchPan);
        add(tab);


        // JFrame Layout 설정
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        CenterFrame cf = new CenterFrame(500,500);
        cf.centerPoint();
        setBounds(cf.getX(), cf.getY(), cf.getFw(), cf.getFh());

        setVisible(true);
    }



    // 검색 버튼 클릭 이벤트 메서드
    private ActionListener btnLsnr = e -> {
        bookVOList = bookRepo.select(searchPan.getSearchWord(), combo.getSelectedIndex());

        searchPan.setBookVOList(bookVOList);
        searchPan.putSearchResult();
    };



    public static void main(String[] args) {
        new BookController();
    }
}
