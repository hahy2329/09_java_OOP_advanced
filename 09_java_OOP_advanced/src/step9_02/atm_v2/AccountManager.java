package step9_02.atm_v2;

import java.util.Random;
import java.util.Scanner;

public class AccountManager {

	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	UserManager um = UserManager.getInstance();
	
	
	void removeAcc(int identifer) {
		int accCntByUser = um.userList[identifer].accCnt;
		
		System.out.println("삭제할 계좌 번호를 입력해주세요 : ");
		String accNumber = scan.next();
		int idx = -1;
		
		if(accCntByUser == 1) { // 계좌번호가 하나일 경우 
			um.userList[identifer].acc = null;
			um.userList[identifer].accCnt--;
			return;
		}
		
		
		for (int i = 0; i < accCntByUser; i++) {
			if(um.userList[identifer].acc[i].accNumber.equals(accNumber)) {
				idx = i;
			}
			
		}
		
		if(idx == -1) { // 계좌번호가 틀릴 경우 
			System.out.println("[메세지] 계좌번호를 다시 확인해주세요.");
			return;
		}
		
		else { // 계좌가 여러 개일 경우 (여기서부터 오류 발생 55번줄)
		
			Account[] temp = um.userList[identifer].acc;
			um.userList[identifer].acc = new Account[accCntByUser-1];
			
			for (int i = 0; i < idx; i++) {
				um.userList[identifer].acc[i] = temp[i];
				
			}
			System.out.println("accCntByUser : " +  accCntByUser);
			for (int i = idx; i < accCntByUser; i++) {
				System.out.println("i : " + i);
				if(i == 2) {
					
					break;
				}
				um.userList[identifer].acc[i] = temp[i + 1]; // 0 1 2
				
			}
			temp = null;
			
			System.out.println(accNumber +"계좌가 삭제되었습니다.");
			um.userList[identifer].accCnt--;
		}
			
		
		
		
		
	}

	void createAcc(int identifier) {
		
		int accCntByUser = um.userList[identifier].accCnt;
		
		if (accCntByUser == 3) {
			System.out.println("[메세지]계좌개설은 3개까지만 가능합니다.");
			return;
		}
		
		um.userList[identifier].acc[accCntByUser] = new Account();
		
		String makeAccount = "";
		while (true) {
			makeAccount = ran.nextInt(9000000) + 1000001 + "";		
			if (!um.getCheckAcc(makeAccount)){
				break;
			}
		}
		um.userList[identifier].acc[accCntByUser].accNumber = makeAccount;
		um.userList[identifier].accCnt++;
		System.out.println("[메세지]'" + makeAccount + "'계좌가 생성되었습니다.\n");
		
	}
	
	
	void printAcc(int identifier) {
		
		User temp = um.userList[identifier];
		System.out.println("====================");
		System.out.println("ID : " + temp.id);
		System.out.println("====================");
		for (int i = 0; i < temp.accCnt; i++) {
			System.out.println("accNumber:" +temp.acc[i].accNumber + " / money: " + temp.acc[i].money);
		}
		System.out.println("=============================\n");
		
	}
	
}
