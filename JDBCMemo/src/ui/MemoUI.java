package ui;

import java.util.ArrayList;
import java.util.Scanner;

import manager.MemoManager;
import vo.UserAccount;
import vo.UserMemo;
import vo.UserMessage;

public class MemoUI {

	private MemoManager manager = new MemoManager(); 
	private Scanner sc = new Scanner(System.in);
	private Scanner scLine = new Scanner(System.in);
	private boolean accountflag = true;
	private boolean menuflag = true;
	private boolean memoflag = true;
	private boolean messageflag = true;
	private boolean adminflag = true;

	
	public MemoUI() {
		while (accountflag) {
			menuflag = true;
			adminflag = true;
			mainMenu();
			switch (sc.nextLine()) {
			case "1":
				insertAccount();
				break;
			case "2":
				deleteAccount();
				break;
			case "3":
				loginAccount();
				while (menuflag) {
					memoflag = true;
					messageflag = true;
					subMenu();
					switch (sc.nextLine()) {
					case "1":
						while (memoflag) {
							memoMenu();
							switch (sc.nextLine()) {
							case "1":
								insertMemo();
								break;
							case "2":
								searchMemo();
								break;
							case "3":
								searchAllMemo();
								break;
							case "4":
								searhOneMemo();
								break;
							case "5":
								updateMemo();
								break;
							case "6":
								deleteMemo();
								break;
							case "9":
								memoflag = false;
								break;
							default:
								System.out.println("******ERROR invalid number.");
								break;
							}
						}
						break;
					case "2":
						while (messageflag) {
							messageMenu();
							switch (sc.nextLine()) {
							case "1":
								insertMessage();
								break;
							case "2":
								deleteMessage();
								break;
							case "3":
								ownMessage();
								break;
							case "4":
								receiveMessage();
								break;
							case "5":
								sendMessage();
								break;
							case "9":
								messageflag = false;
								break;
							default:
								System.out.println("******ERROR invalid number.");
								break;
							}
						}
						break;
					case "9":
						menuflag = false;
						manager.logout();
						break;
					default:
						System.out.println("******ERROR invalid number.");
						break;
					}
				}
				break;
			case "4":
				findAccount();
				break;
			case "8":
				loginAdmin();

				while (adminflag) {
					adminMenu();
					switch (sc.nextLine()) {
					case "1":
						printAccount();
						break;
					case "2":
						countAccount();
						break;
					case "3":
						banAccount();
						break;
					case "4":
						relifeAccount();
						break;
					case "5":
						filteringMemo();
						break;
					case "9":
						adminflag = false;
						break;
					default:
						System.out.println("******ERROR invalid number.");
						break;
					}
				}
				break;
			case "9":
				System.out.println("****** Exit program.");
				accountflag = false;
				break;
			default:
				System.out.println("******ERROR invalid number.");
				break;
			}
		}
	}

	public void mainMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Main           │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.Signup					   │");
		System.out.println("│2.Withdraw					   │");
		System.out.println("│3.Signin					   │");
		System.out.println("│4.Find PW					   │");
		System.out.println("│8.Admin				   	   │");
		System.out.println("│9.Exit						   │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" Please select number => ");
	}

	public void subMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Function       │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.Memo						   │");
		System.out.println("│2.Message					   │");
		System.out.println("│9.Signout					   │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" Please select number => ");
	}

	public void memoMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Memo           │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.Write Memo				   │");
		System.out.println("│2.Search Memo				   │");
		System.out.println("│3.List Memo				   │");
		System.out.println("│4.View	Memo				   │");
		System.out.println("│5.Update Memo				   │");
		System.out.println("│6.Delete Memo				   │");
		System.out.println("│9.Home						   │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" Please select number => ");
	}

	public void messageMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Message        │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.Write Message			   │");
		System.out.println("│2.Delete Message			   │");
		System.out.println("│3.Private box				   │");
		System.out.println("│4.Inbox					   │");
		System.out.println("│5.Outbox					   │");
		System.out.println("│9.Home						   │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" Please select number => ");
	}

	public void adminMenu() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│ Soft Engineer Administrator  │");
		System.out.println("└──────────────────────────────┘");
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│1.List User				   │");
		System.out.println("│2.View User				   │");
		System.out.println("│3.Block User				   │");
		System.out.println("│4.Authorize User			   │");
		System.out.println("│5.Delete Memo				   │");
		System.out.println("│9.Home						   │");
		System.out.println("└──────────────────────────────┘");
		System.out.print(" Please select number => ");
	}

	public void loginAccount() {
		while (true) {
			System.out.println("ID => ");
			String id = scLine.nextLine();
			System.out.println("PW => ");
			String password = scLine.nextLine();
			if ((id == null || id.equals("")) || (password == null || password.equals(""))) {
				System.out.println("******ERROR Please re-enter ID/PW.");
			} else if (manager.searchAccount(id) == null) {
				System.out.println("******ERROR Invalid ID/PW.");
			} else if (manager.searchAccount(id).getPassword().equals("ban")) {
				System.out.println("****** Blocked User.");
			} else if (manager.searchAccount(id).getPassword().equals("relife")) {
				System.out.println("****** Authrized User. Please enter new PW.");
				findAccount();
			} else {
				UserAccount result = manager.loginAccount(new UserAccount(null, id, password));
				if (result != null) {
					System.out.println("****** Signin complete " + manager.getId() + "(" + manager.getName() + ") welcome.");
					break;
				} else {
					System.out.println("******ERROR Invalid ID/PW.");
				}
			}
		}
	}

	public void insertAccount() {
		while (true) {
			System.out.println("Name => ");
			String name = scLine.nextLine();
			System.out.println("ID => ");
			String id = scLine.nextLine();
			System.out.println("PW => ");
			String password = scLine.nextLine();
			if ((name == null || name.equals("")) || (id == null || id.equals(""))
					|| (password == null || password.equals(""))) {
				System.out.println("******ERROR Please re-enter Name/ID/PW.");
			} else if (password.equals("ban") || password.equals("relife")) {
				System.out.println("******ERROR PW contains prohibited words.");
			} else {
				boolean result = manager.insertAccount(new UserAccount(name, id, password));
				if (result) {
					System.out.println("****** Signup complete");
					break;
				} else {
					System.out.println("******ERROR Existing ID.");
				}
			}
		}
	}

	public void findAccount() {
		while (true) {
			System.out.println("Name => ");
			String name = scLine.nextLine();
			System.out.println("ID => ");
			String id = scLine.nextLine();
			if (manager.searchAccount(id) != null) {
				if (manager.searchAccount(id).getPassword().equals("ban")) {
					System.out.println("****** Blocked ID.");
					break;
				}
			}
			System.out.println("Please enter new PW => ");
			String password = scLine.nextLine();

			if ((name == null || name.equals("")) || (id == null || id.equals(""))) {
				System.out.println("******Please re-enter Name/ID/PW.");
				break;
			} else {
				boolean result = manager.findAccount(new UserAccount(name, id, password));
				if (result) {
					System.out.println("****** Update PW Complete");
					break;
				} else {
					System.out.println("******ERROR Invalid Name/ID.");
					break;
				}
			}
		}
	}

	public void deleteAccount() {
		while (true) {
			System.out.println("ID => ");
			String id = scLine.nextLine();
			System.out.println("PW => ");
			String password = scLine.nextLine();
			if ((id == null || id.equals("")) || (password == null || password.equals(""))) {
				System.out.println("******ERROR Please re-enter ID/PW.");
			} else {
				manager.deleteAllMemo(id);
				boolean result = manager.deleteAccount(new UserAccount(null, id, password));
				if (result) {
					System.out.println("****** Delete User Complete");
					break;
				} else {
					System.out.println("******ERROR Delete User Fail.");
				}
			}
		}
	}

	public void loginAdmin() {

		System.out.println("Please enter Administrator Code => ");
		String id = scLine.nextLine();
		if (id.equals("power overwhelming")) {
			System.out.println("****** Administator Welcome.");
		} else {
			System.out.println("****** I SEE YOU");
			adminflag = false;
		}

	}

	public void insertMemo() {

		while (true) {
			System.out.println("Title => ");
			String title = scLine.nextLine();
			System.out.println("Content => ");
			String content = scLine.nextLine();
			if ((title == null || title.equals("")) || (content == null || content.equals(""))) {
				System.out.println("******ERROR Please re-enter Title/Content.");
			} else {
				boolean result = manager.insertMemo(new UserMemo(null, title, content, null, null));
				if (result) {
					System.out.println("****** Write Memo Complete");
					break;
				} else {
					System.out.println("******ERROR Write Memo Fail.");
				}
			}
		}
	}

	public void searchMemo() {
		while (true) {
			System.out.println("Title or Content => ");
			String searchWord = scLine.nextLine();
			if ((searchWord == null || searchWord.equals(""))) {
				System.out.println("******ERROR Please re-enter Title/Content.");
			} else {
				ArrayList<UserMemo> result = manager.searchMemo(searchWord);
				if (result != null) {
					printAllMemo(result);
					break;
				} else {
					System.out.println("******ERROR Search Memo Fail.");
				}
			}
		}
	}

	public void searchAllMemo() {
		while (true) {
			ArrayList<UserMemo> result = manager.getMemoList();
			if (result != null) {
				printAllMemo(result);
				break;
			} else {
				System.out.println("******ERROR Search Memo Fail.");
			}
		}
	}

	public void searhOneMemo() {
		while (true) {
			System.out.println("Memo Number => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR Please re-enter Memo Number.");
			} else {
				UserMemo result = manager.searchOneMemo(memoseq);
				if (result != null) {
					System.out.println("Title : " + result.getTitle());
					System.out.println("Content : " + result.getContent());
					break;
				} else {
					System.out.println("******ERROR Search Memo Fail.");
				}
			}
		}
	}

	public void deleteMemo() {
		while (true) {
			System.out.println("Memo Number => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR Please re-enter Memo Number.");
			} else {
				boolean result = manager.deleteMemo(memoseq);
				if (result) {
					System.out.println("****** Delete Memo Complete.");
					break;
				} else {
					System.out.println("******ERROR Delete Memo Fail.");
				}
			}
		}
	}

	public void updateMemo() {
		while (true) {
			System.out.println("Memo Number => ");
			String memoseq = scLine.nextLine();
			if ((memoseq == null || memoseq.equals(""))) {
				System.out.println("******ERROR Please re-enter Memo Number.");
			} else {

				System.out.println("Title => ");
				String title = scLine.nextLine();
				System.out.println("Content => ");
				String content = scLine.nextLine();
				if ((title == null || title.equals("")) || (content == null || content.equals(""))) {
					System.out.println("******Please re-enter Title/Content.");
				} else {
					boolean result = manager.updateMemo(memoseq, new UserMemo(null, title, content, null, null));
					if (result) {
						System.out.println("****** Update Memo Complete");
						break;
					} else {
						System.out.println("******ERROR Update Memo Fail.");
					}
				}
			}
		}

	}

	public void insertMessage() {
		while (true) {
			System.out.println("Reciever => ");
			String toid = scLine.nextLine();
			System.out.println("Title => ");
			String title = scLine.nextLine();
			System.out.println("Content => ");
			String content = scLine.nextLine();
			if ((title == null || title.equals("")) || (content == null || content.equals(""))) {
				System.out.println("******ERROR Please re-enter Title/Content.");
			} else {
				boolean result = manager.insertMessage(new UserMessage(null, title, content, null, toid, null));
				if (result) {
					System.out.println("****** Write Message Complete");
					break;
				} else {
					System.out.println("******ERROR Write Message Error.");
				}
			}
		}
	}

	public void deleteMessage() {
		while (true) {
			System.out.println("Message Number => ");
			String messageseq = scLine.nextLine();
			if ((messageseq == null || messageseq.equals(""))) {
				System.out.println("******ERROR Please re-enter Message Number.");
			} else {
				boolean result = manager.deleteMessage(messageseq);
				if (result) {
					System.out.println("****** Delete Message Complete.");
					break;
				} else {
					System.out.println("******ERROR Delete Message Fail.");
				}
			}
		}
	}

	public void ownMessage() {
		while (true) {
			ArrayList<UserMessage> result = manager.ownMessage();
			if (result != null) {
				printAllMessage(result);
				break;
			} else {
				System.out.println("******ERROR View Private box Fail.");
			}
		}
	}

	public void receiveMessage() {
		while (true) {
			ArrayList<UserMessage> result = manager.receiveMessage();
			if (result != null) {
				printAllMessage(result);
				break;
			} else {
				System.out.println("******ERROR View Inbox Fail.");
			}
		}
	}

	public void sendMessage() {
		while (true) {
			ArrayList<UserMessage> result = manager.sendMessage();
			if (result != null) {
				printAllMessage(result);
				break;
			} else {
				System.out.println("******ERROR View Outbox Fail.");
			}
		}
	}

	public void printAccount() {
		ArrayList<UserAccount> result = manager.getUserList();
		for (UserAccount ua : result) {
			System.out.println("ID: " + ua.getId() + ", Name: " + ua.getName());
		}
	}

	public void countAccount() {
		while (true) {
			System.out.println("ID => ");
			String id = scLine.nextLine();
			if ((id == null || id.equals(""))) {
				System.out.println("******ERROR Please re-enter ID.");
			} else {
				String level = "";
				int result1 = manager.countMemo(id);
				int result2 = manager.countMessage(id);
				if (result1 + result2 >= 10) {
					level = "<Super User>";
				} else if (result1 + result2 >= 1) {
					level = "<General User>";
				} else {
					level = "<New User>";
				}
				if (manager.searchAccount(id) != null) {
					if (manager.searchAccount(id).getPassword().equals("ban")) {
						level = "<Blocked User>";
					}
				} else {
					System.out.println("******ERROR Please re-enter ID.");
					break;
				}
				System.out.println(level + " Memo: " + result1 + ", Message: " + result2 + "");
				return;
			}
		}
	}

	public void banAccount() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("ID => ");
			String id = scLine.nextLine();
			if (id == null || id.equals("")) {
				System.out.println("******ERROR Please re-enter ID.");
			} else {
				boolean result = manager.banAccount(new UserAccount(null, id, null));
				if (result) {
					System.out.println("****** " + id + " is Blocked.");
					break;
				} else {
					System.out.println("******ERROR Invalid ID.");
				}
			}
		}
	}

	public void relifeAccount() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("ID => ");
			String id = scLine.nextLine();
			if (id == null || id.equals("")) {
				System.out.println("******ERROR Please re-enter ID.");
			} else {
				boolean result = manager.relifeAccount(new UserAccount(null, id, null));
				if (result) {
					System.out.println("****** " + id + " is Authorized.");
					break;
				} else {
					System.out.println("******ERROR invalid ID.");
				}
			}
		}
	}

	public void filteringMemo() {
		while (true) {
			System.out.println("Title or Content => ");
			String searchWord = scLine.nextLine();
			if ((searchWord == null || searchWord.equals(""))) {
				System.out.println("******ERROR Please re-enter	Title/Content.");
			} else {
				ArrayList<UserMemo> result = manager.searchMemo(searchWord);
				if (result != null) {
					printFilteringMemo(result);
					break;
				}
			}
		}

	}
	
	public void printAllMemo(ArrayList<UserMemo> memos) {
		for (UserMemo memo : memos) {
			System.out.println(memo);
		}
	}

	public void printAllMessage(ArrayList<UserMessage> messages) {
		for (UserMessage message : messages) {
			System.out.println(message);
		}
	}

	public void printFilteringMemo(ArrayList<UserMemo> memos) {
		for (UserMemo memo : memos) {
			System.out.println("Memo Number: " + memo.getMemoseq() + ", Sender: " + memo.getId());
		}

		System.out.println("Are you sure Delete Memo? (y/n)");
		String choice = sc.next();
		switch (choice) {
		case "y":
			for (UserMemo memo : memos) {
				manager.deleteMemo(memo.getMemoseq());
			}
			System.out.println("****** Delete Memo Complete.");
			break;
		case "n":
			System.out.println("****** Delete Memo Canceled.");
			break;
		default:
			System.out.println("******ERROR Invalid Value.");
			break;
		}
	}
}
