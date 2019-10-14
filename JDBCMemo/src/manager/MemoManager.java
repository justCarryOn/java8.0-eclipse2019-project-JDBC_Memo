package manager;

import java.util.ArrayList;

import dao.MemoDaoManager;
import vo.UserAccount;
import vo.UserMemo;
import vo.UserMessage;

public class MemoManager {

	private MemoDaoManager mdm = null;
	private String id;
	private String name;

	public MemoManager() {

		this.mdm = new MemoDaoManager();
	}

	public boolean insertAccount(UserAccount user) {
		boolean result = false;
		result = mdm.insertAccount(user);
		return result;
	}

	public UserAccount loginAccount(UserAccount user) {
		UserAccount result = null;
		result = mdm.loginAccount(user);
		if (result != null) {
			this.id = result.getId();
			this.name = result.getName();

		}
		return result;
	}

	public boolean deleteAccount(UserAccount user) {
		boolean result = false;
		result = mdm.deleteAccount(user);
		return result;
	}

	public boolean findAccount(UserAccount user) {
		boolean result = false;
		result = mdm.findAccount(user);
		return result;
	}

	public boolean insertMemo(UserMemo memo) {
		boolean result = false;
		memo.setId(this.id);
		result = mdm.insertMemo(memo);
		return result;
	}

	public ArrayList<UserMemo> getMemoList() {
		ArrayList<UserMemo> result = null;
		result = mdm.getMemoList(this.id);
		return result;
	}

	public ArrayList<UserMemo> searchMemo(String searchword) {
		ArrayList<UserMemo> result = null;
		result = mdm.searchMemo(searchword, this.id);
		return result;
	}

	public UserMemo searchOneMemo(String seq) {
		UserMemo result = null;
		result = mdm.searchOneMemo(seq, this.id);
		return result;
	}

	public boolean updateMemo(String seq, UserMemo memo) {
		boolean result = false;
		result = mdm.updateMemo(seq, this.id, memo);
		return result;
	}

	public boolean deleteMemo(String seq) {
		boolean result = false;
		result = mdm.deleteMemo(seq, this.id);
		return result;
	}

	public boolean deleteAllMemo(String id) {
		boolean result = false;
		result = mdm.deleteAllMemo(id);
		return result;
	}
	
	public boolean insertMessage(UserMessage message) {
		boolean result = false;
		message.setFromid(this.id);
		result = mdm.insertMessage(message);
		return result;
	}
	
	public boolean deleteMessage(String messageseq) {
		boolean result = false;
		result = mdm.deleteMessage(messageseq, this.id);
		return result;
	}

	public ArrayList<UserMessage> ownMessage() {
		ArrayList<UserMessage> result = null;
		result = mdm.ownMessage(this.id);
		return result;
	}
	
	public ArrayList<UserMessage> receiveMessage() {
		ArrayList<UserMessage> result = null;
		result = mdm.receiveMessage(this.id);
		return result;
	}
	
	public ArrayList<UserMessage> sendMessage() {
		ArrayList<UserMessage> result = null;
		result = mdm.sendMessage(this.id);
		return result;
	}
	
	public boolean logout() {

		if (this.name == null) {
			return false;
		}

		this.name = null;
		this.id = null;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<UserAccount> getUserList() {
		ArrayList<UserAccount> result = null;
		result = mdm.getUserList();
		return result;
	}

	public int countMemo(String id) {
		int result = 0;
		result = mdm.countMemo(id);
		return result;
	}
	
	public int countMessage(String id) {
		int result = 0;
		result = mdm.countMessage(id);
		return result;
	}

	public boolean banAccount(UserAccount user) {
		boolean result = false;
		result = mdm.banAccount(user);
		return result;
	}
	
	public boolean relifeAccount(UserAccount user) {
		boolean result = false;
		result = mdm.relifeAccount(user);
		return result;
	}

	public UserAccount searchAccount(String id) {
		UserAccount sa = null;
		sa = mdm.searchAccount(id);
		return sa;
	}
	
}
