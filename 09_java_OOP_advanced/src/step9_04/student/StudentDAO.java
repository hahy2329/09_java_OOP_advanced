package step9_04.student;
//가공된(Controller <->DAO) 데이터를 DB로 보내기 전
import java.util.Map;

public class StudentDAO {
	
	public void insert(StudentVO st) {
		StudentRepository.getStDB().put(st.getId(), st);
	}
	
	public StudentVO select(String id) {
		return StudentRepository.getStDB().get(id);
	}
	
	
	public Map<String , StudentVO> getStudentDB(){
		return StudentRepository.getStDB();
	}

}
