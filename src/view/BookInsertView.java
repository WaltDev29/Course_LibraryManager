package view;

import domain.BookVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookInsertView extends JPanel {
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
    private String[] categoryNames = {
            "SF",
            "IT",
            "경제",
            "만화",
            "추리"
    };

    private JPanel panS;
    private JLabel[] lbls = new JLabel[headerList.length];
    private JTextField[] tfs = new JTextField[headerList.length - 1];
    private JComboBox<String> categoryCombo;
    private JButton submitBtn;

    // ============ 생성자 ============
    public BookInsertView() {
        setLayout(new BorderLayout());
        categoryCombo = new JComboBox(categoryNames);
        submitBtn = new JButton("도서추가");

        panS = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < headerList.length; i++) {
            lbls[i] = new JLabel(headerList[i]);
            panS.add(lbls[i]);
            if (i < headerList.length - 1) {
                tfs[i] = new JTextField();
                panS.add(tfs[i]);
            } else {
                panS.add(categoryCombo);
            }
        }
        for (int i=0; i<3; i++) panS.add(new JLabel(""));
        panS.add(submitBtn);
    }



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
        add(panS, BorderLayout.SOUTH);
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
}
