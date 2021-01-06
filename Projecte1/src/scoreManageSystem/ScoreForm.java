package scoreManageSystem;

import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ScoreForm extends JFrame implements ActionListener{
	
	private ArrayList<ScoreDTO> list;
	private Score score;
	private JButton insertBtn, printBtn, searchBtn, deleteBtn, sortBtn, saveBtn, loadBtn;
	private JTextField numT, nameT, korT, engT, mathT,proT,coT;
	private JTable table;
	private DefaultTableModel model;
	// 모델이 벡터를 받고 테이블이 모델을 받는다 
	// 벡터가 데이터를 한 번더 담아주고 모델이 데이터를 관리해준다(삭제, 추가)
	// 테이블은 view의 기능을 한다. 
	public ScoreForm() {
		
		setTitle("학생 점수 관리 시스템");
		
		// 왼쪽 - 입력
		
		JPanel numP = new JPanel();
		JLabel numL = new JLabel("학번");
		numT = new JTextField(20);
		numP.add(numL);
		numP.add(numT);
		JPanel nameP = new JPanel();
		JLabel nameL = new JLabel("이름");
		nameT = new JTextField(20);
		nameP.add(nameL);
		nameP.add(nameT);
		JPanel korP = new JPanel();
		JLabel korL = new JLabel("국어");
		korT = new JTextField(20);
		korP.add(korL);
		korP.add(korT);
		JPanel engP = new JPanel();
		JLabel engL = new JLabel("영어");
		engT = new JTextField(20);
		engP.add(engL);
		engP.add(engT);
		JPanel mathP = new JPanel();
		JLabel mathL = new JLabel("수학");
		mathT = new JTextField(20);
		mathP.add(mathL);
		mathP.add(mathT);
		JPanel proP = new JPanel();
		JLabel proL = new JLabel("플밍");
		proT = new JTextField(20);
		proP.add(proL);
		proP.add(proT);
		JPanel coP = new JPanel();
		JLabel coL = new JLabel("컴일");
		coT = new JTextField(20);
		coP.add(coL);
		coP.add(coT);
		
		JPanel rightP = new JPanel();
		rightP.setLayout(new GridLayout(5, 1, 3, 5));
		rightP.add(numP);
		rightP.add(nameP);
		rightP.add(korP);
		rightP.add(engP);
		rightP.add(mathP);
		rightP.add(proP);
		rightP.add(coP);		
		// 오른쪽 테이블 
		Vector<String> v = new Vector<String>();
		v.add("학번");
		v.add("이름");
		v.add("국어");
		v.add("영어");
		v.add("수학");
		v.add("플밍");
		v.add("컴일");
		v.add("총합");
		v.add("평균");
		model = new DefaultTableModel(v, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		
		JPanel liftP = new JPanel();
		liftP.setLayout(new GridLayout(2, 2));
		liftP.add(scroll);
		
		
		// 왼쪽 + 오른쪽 
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(1, 2));
		centerP.add(liftP);
		centerP.add(rightP);
		
		score = new ScoreImpl();
		// 생성은 자식으로 잡는다. 
		// 부모는 자식에 있는 것들을 참조할 수 있다(다형성)
		
		
		// 버튼
		insertBtn = new JButton("입력");
		printBtn = new JButton("출력");
		deleteBtn = new JButton("삭제");
		sortBtn = new JButton("순위");
		searchBtn = new JButton("검색");
		saveBtn = new JButton("저장");
		loadBtn = new JButton("열기");
		
		
		
		JPanel btnP = new JPanel();
		btnP.setBackground(new Color(238,130,238));
		btnP.add(insertBtn);
		btnP.add(printBtn);
		btnP.add(deleteBtn);
		btnP.add(sortBtn);
		btnP.add(searchBtn);
		btnP.add(saveBtn);
		btnP.add(loadBtn);
		
		
		Container c = getContentPane();
		c.add("Center", centerP);
		c.add("South", btnP);
		

		setBounds(800, 600, 990, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
	}// ScoreForm
	public void event() {
		insertBtn.addActionListener(this);
		printBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		sortBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		loadBtn.addActionListener(this);
		
	}
	
	// 값 받아서 impl로 보냄
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			// TextField 값을 가져온다. 
			ScoreDTO dto = new ScoreDTO();
			
			dto.setNum(numT.getText());
			dto.setName(nameT.getText());
			dto.setKor(Integer.parseInt(korT.getText()));
			dto.setEng(Integer.parseInt(engT.getText())); 
			dto.setMath(Integer.parseInt(mathT.getText()));
			dto.setPro(Integer.parseInt(proT.getText()));
			dto.setCo(Integer.parseInt(coT.getText()));
			dto.calc();
			// impl에 값을 가져간다. 
			score.insert(dto);
			
			JOptionPane.showMessageDialog(this, "등록 완료");
			numT.setText("");
			nameT.setText("");
			korT.setText("");
			engT.setText("");
			mathT.setText("");
			proT.setText("");
			coT.setText("");

		}else if(e.getSource() == printBtn) {
			// table에 데이터를 넣으려면 model에다 데이터를 넣어준다. 
			// table은 직접 데이터를 수정할 수 없고 수정 및 추가는 model에서 이루어지기 때문
			score.print(model);
			
		}else if(e.getSource() == searchBtn) {
			// impl에서 물어보면 위와 같이 또 dialog가 frame 밖에 생성되기 때문에 여기서 물어본다. 
			 String num = JOptionPane.showInputDialog(this, "검색할 학번을 입력하세요");
			 if(num == null || num.equals("")) return; // 없으면 나가기 
			 
			 int sw = score.search(model, num);
			 // 찾고자 하는 num을 model과 함께 넘겨전다 
			 // 결과를 table에 뿌릴 것이기 때문에 model 값을 가지고 간다
			 if(sw == 0) JOptionPane.showMessageDialog(this, "찾는 정보가 없습니다");
			 
		}else if(e.getSource() == deleteBtn) {
			// 삭제하고자 하는 값을 찾는다 
			ScoreDTO dto = new ScoreDTO();
			String num = JOptionPane.showInputDialog(this, "삭제할 학번을 입력하세요 ");
			if(num == null || num.equals("")) return;
		
			int sw = score.delete(dto, num);
			if(sw == 1) JOptionPane.showMessageDialog(this, "삭제 완료");
			if(sw == 0) JOptionPane.showMessageDialog(this, "삭제할 정보가 없습니다.");
		}else if(e.getSource() == sortBtn) {
			// 총점으로 내림차순
			score.sort();
			score.print(model); // 정렬된 데이터 출력 
		}else if(e.getSource() == saveBtn) {
			score.save();
		}else if(e.getSource() == loadBtn) {
			score.load();
			score.print(model);
			// 불러온 파일 내용을 print()로 뿌려준다. 
			
		}
	}
}