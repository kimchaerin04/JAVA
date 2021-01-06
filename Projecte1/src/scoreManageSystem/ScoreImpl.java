package scoreManageSystem;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class ScoreImpl implements Score{
	private ScoreForm sf;
	private List<ScoreDTO> list = new ArrayList<ScoreDTO>();
	// list라는 부모 밑에 자식 중 하나인 Arrayist를 참조. 
	// 1 : 다 형태 부모가 ArrayList 말고 다른 것도 참조 할 수 있음 결합도를 끊어줌. 
	
	// 여기에서 받는 값들은 score 인터페이스에도 줘야됨. 
	public void insert(ScoreDTO dto) {
		// form에서 온 값 받기 
		list.add(dto); // list에 받은 dto 값 추가 
		// 등록완료 메시지 
	}
	public void print(DefaultTableModel model) {
		// model 값이 들어온다. 
		model.setRowCount(0); // 테이블을 clear해 테이블 값이 누적되지 않게 만듬. 
		
		for(ScoreDTO dto : list) {
			// list 값을 하나씩 전달한다(list에 값이 몇개 있는지 모름)
			// 벡터는 항목 수대로 만들어야한다.
			Vector<String> v = new Vector<String>();
			
			v.add(dto.getNum());
			v.add(dto.getName());
			v.add(dto.getKor() + "");
			v.add(dto.getEng() + "");
			v.add(dto.getMath() + "");
			v.add(dto.getPro()+"");
			v.add(dto.getCo()+"");
			v.add(dto.getTot() + "");
			v.add(dto.getAvg());
			
			model.addRow(v); // model에 벡터를 넣는다 > table에 값 추가됨 
			
		}
	}
	public int search(DefaultTableModel model, String num) {
		model.setRowCount(0); // 테이블을 비우고 찾는 값. 
		int sw = 0; // 찾으면 1이 된다. 
		
		// 찾고자 하는 학점 num을 model과 함께 form에서 받는다. 
		for(ScoreDTO dto : list) {
			if(num.equals(dto.getNum())) {
				// 찾은 내용을 vector > model 에다 뿌려줌. 
				Vector<String> v = new Vector<String>();
				v.add(dto.getNum());
				v.add(dto.getName());
				v.add(dto.getKor() + "");
				v.add(dto.getEng() + "");
				v.add(dto.getMath()+ "");
				v.add(dto.getPro() +"");
				v.add(dto.getCo()+"");
				v.add(dto.getTot() + "");
				v.add(dto.getAvg());
				
				model.addRow(v);
				sw = 1;
			}
		}// for
		
		// 찾고자 하는 정보가 없는 dialog는 form에다 찍어줌. 
		return sw; // 찾았는지 찾지 못했는지 알려주는 sw를 return 해줌 
		// form에 알려준다 
	}
	
	public int delete(ScoreDTO dto, String num) {
		int sw = 0; // 삭제시 1이 된다. 
		
		// 선택한 줄 번호 알아내기 
		Iterator<ScoreDTO> it = list.iterator();
		
		while(it.hasNext()) {
			dto = it.next();
				if(num.equals(dto.getNum())) {
					it.remove();
					
					sw = 1;
								
				}	
		}
		return sw;
	}
	public void sort() {
		// 내림차순
		//Collections.sort(list);
		Collections.reverse(list);
	}
	public void save() {
		// 저장 다이얼로그 
		Object o = list;
		File file = null;
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(sf);
		if(result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		// 파일 선택 없을 시 나가기 
		if(file != null){
			try{
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(o);
				oos.close();
				fos.close();
				} catch(IOException e){}
		}
		// 객체가 나감 ObjectOutputStream
		// Buffer에서 파일로 나가는 것은 FileOutputStream
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void load() {
		// 열기 다이얼로그 
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(sf);
		File file = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			System.out.println(file.getPath());
		}
		
		if(file == null) return;
		
		list.clear(); // 리스트 비우기 
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			while(true) {
				try {
					ScoreDTO dto = (ScoreDTO)ois.readObject();
					// readObject로 읽으면  Object 타입으로 읽음. 
					// DTO로 형변환 해서 넘겨줌. 
					list.add(dto); // 리스트에 불러온 값을 담음. 
					
				}catch(EOFException e) {
					break;
				}
			}
			ois.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public ScoreForm getSf() {
		return sf;
	}
	public void setSf(ScoreForm sf) {
		this.sf = sf;
	}
}