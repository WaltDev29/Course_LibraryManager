package view;

import domain.BookVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<BookVO> bookVOList;
    private String[] headerList = {
            "도서번호",
            "도서명",
            "출판사",
            "저자",
            "도서가격",
            "카테고리"
    };

    private JPanel panN;
    private JLabel lbl;
    private JTextField txtSearch;
    private JButton btnSearch;
    private String searchWord;
    private JComboBox<String> combo;
    private String[] comboStr = {"도서명", "출판사", "저자"};



    // 생성자
    public BookSearchView() {
        setLayout(new BorderLayout());

        combo = new JComboBox(comboStr);
        lbl = new JLabel("검색어");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("검색");

        panN = new JPanel();
        panN.add(combo);
        panN.add(lbl);
        panN.add(txtSearch);
        panN.add(btnSearch);

        add(panN, BorderLayout.NORTH);
    }



    // JTable과 DefaultTableModel을 연결하고
    // 테이블과 관련된 내용들을 초기화 시키는 메소드
    public void initView() {
        // DefaultTableModel는 기본적으로 셀을 수정할 수 있음.
        // 따라서  DefaultTableModel를 오버라이딩해서 셀을 수정 못하도록 함.
        model = new DefaultTableModel(headerList, bookVOList.size()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        
        // 테이블 컬럼 너비 설정
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        
        // 스크롤바 설정
        JScrollPane scrollPane = new JScrollPane(table);

        // 각 셀에 bookVOList의 데이터를 설정


        add(scrollPane, BorderLayout.CENTER);
    }



    // DefaultTableModel에 도서정보들을 설정하는 메서드
    public void putSearchResult() {
        // model에 행 개수 설정
        model.setRowCount(bookVOList.size());
        BookVO vo = null;

        for (int i=0; i<bookVOList.size(); i++) {
            vo = bookVOList.get(i);
            model.setValueAt(vo.getIsbn(), i , 0);
            model.setValueAt(vo.getName(), i, 1);
            model.setValueAt(vo.getPublish(), i, 2);
            model.setValueAt(vo.getAuthor(), i, 3);
            model.setValueAt(vo.getPrice(), i, 4);
            model.setValueAt(vo.getCategoryName(), i, 5);
        }
    }



    // setter
    public void setBookVOList(ArrayList<BookVO> bookVOList) {
        this.bookVOList = bookVOList;
    }



    // getter
    public String getSearchWord() {
        searchWord = txtSearch.getText();
        return searchWord;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JComboBox<String> getCombo() {
        return combo;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }
}