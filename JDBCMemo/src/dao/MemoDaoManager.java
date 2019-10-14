package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.UserAccount;
import vo.UserMemo;
import vo.UserMessage;

public class MemoDaoManager  {

	public ArrayList<UserAccount> getUserList() {
		ArrayList<UserAccount> gu = new ArrayList<>();
		UserAccount ua = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from userAccount";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				String id = rs.getString(2);
				ua = new UserAccount(name, id, null);
				gu.add(ua);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return gu;
	}

	public boolean insertAccount(UserAccount user) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "insert into useraccount values (?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPassword());
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public UserAccount loginAccount(UserAccount user) {
		UserAccount result = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from useraccount where id = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("id");
				String password = rs.getString("password");
				result = new UserAccount(name, id, password);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			ConnectionManager.close(con);
		}
		return result;
	}

	public boolean deleteAccount(UserAccount user) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "delete from useraccount where id = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean findAccount(UserAccount user) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "update useraccount set password = ? where name = ? and id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getId());
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean insertMemo(UserMemo memo) {
		boolean result = false;
		String seqResult = "";
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select memoseq.nextval from dual";
			PreparedStatement pstmt2 = con.prepareStatement(sql);
			PreparedStatement pstmt = con.prepareStatement("insert into UserMemo values (?, ?, ?, sysdate, ?)");
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				seqResult = rs.getString(1);
			}

			pstmt.setString(2, memo.getTitle());
			pstmt.setString(3, memo.getContent());
			pstmt.setString(4, memo.getId());
			pstmt.setString(1, seqResult);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public ArrayList<UserMemo> getMemoList(String id) {
		ArrayList<UserMemo> gm = new ArrayList<>();
		UserMemo um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from usermemo where id = ? order by indate desc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String memoseq = rs.getString(1);
				String title = rs.getString(2);
				String indate = rs.getString(4);
				um = new UserMemo(memoseq, title, null, indate, null);
				gm.add(um);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return gm;
	}

	public ArrayList<UserMemo> searchMemo(String searchword, String id) {
		ArrayList<UserMemo> sm = new ArrayList<>();
		UserMemo um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sw = "%" + searchword + "%";
			String sql = "select * from usermemo where (title like ? or content like ?) and id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sw);
			pstmt.setString(2, sw);
			pstmt.setString(3, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String memoseq = rs.getString(1);
				String title = rs.getString(2);
				String indate = rs.getString(4);
				um = new UserMemo(memoseq, title, null, indate, null);

				sm.add(um);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return sm;
	}

	public UserMemo searchOneMemo(String seq, String id) {
		UserMemo um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from usermemo where memoseq = ? and id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String memoseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				String id1 = rs.getString(5);
				um = new UserMemo(memoseq, title, content, indate, id1);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return um;
	}

	public boolean updateMemo(String seq, String id, UserMemo memo) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "update usermemo set title = ? , content = ? where memoseq = ? and id =?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memo.getTitle());
			pstmt.setString(2, memo.getContent());
			pstmt.setString(3, seq);
			pstmt.setString(4, id);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean deleteMemo(String seq, String id) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from usermemo where memoseq = ? and id = ?");
			pstmt.setString(1, seq);
			pstmt.setString(2, id);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean deleteAllMemo(String id) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement("delete from usermemo where id = ?");
			pstmt.setString(1, id);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean insertMessage(UserMessage message) {
		boolean result = false;
		String seqResult = "";
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select messageseq.nextval from dual";
			PreparedStatement pstmt2 = con.prepareStatement(sql);
			PreparedStatement pstmt = con.prepareStatement("insert into UserMessage values (?, ?, ?, sysdate, ?, ?)");
			ResultSet rs = pstmt2.executeQuery();
			if (rs.next()) {
				seqResult = rs.getString(1);
			}

			pstmt.setString(2, message.getTitle());
			pstmt.setString(3, message.getContent());
			pstmt.setString(4, message.getToid());
			pstmt.setString(5, message.getFromid());
			pstmt.setString(1, seqResult);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean deleteMessage(String messageseq, String id) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("delete from usermessage where messageseq = ? and fromid = ?");
			pstmt.setString(1, messageseq);
			pstmt.setString(2, id);
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public ArrayList<UserMessage> ownMessage(String id) {
		ArrayList<UserMessage> om = new ArrayList<>();
		UserMessage um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from usermessage where toid = ? order by indate asc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String messageseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				String toid = rs.getString(5);
				String fromid = rs.getString(6);
				um = new UserMessage(messageseq, title, content, indate, toid, fromid);
				if (um.getToid().equals(um.getFromid())) {
					om.add(um);
				}
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return om;
	}
	
	public ArrayList<UserMessage> receiveMessage(String id) {
		ArrayList<UserMessage> rm = new ArrayList<>();
		UserMessage um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from usermessage where toid = ? order by indate asc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String messageseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				String toid = rs.getString(5);
				String fromid = rs.getString(6);
				um = new UserMessage(messageseq, title, content, indate, toid, fromid);
				if (!um.getToid().equals(um.getFromid())) {
					rm.add(um);
				}
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return rm;
	}

	public ArrayList<UserMessage> sendMessage(String id) {
		ArrayList<UserMessage> sm = new ArrayList<>();
		UserMessage um = null;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from usermessage where fromid = ? order by indate asc";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String messageseq = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String indate = rs.getString(4);
				String toid = rs.getString(5);
				String fromid = rs.getString(6);
				um = new UserMessage(messageseq, title, content, indate, toid, fromid);
				sm.add(um);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return sm;
	}

	public UserAccount searchAccount(String id) {
		Connection con = null;
		UserAccount sa = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select * from useraccount where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString(1);
				String password = rs.getString(3);
				sa = new UserAccount(name, id, password);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return sa;
	}

	public int countMemo(String id) {
		int result = 0;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select count(*) from usermemo where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}
	
	public int countMessage(String id) {
		int result = 0;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "select count(*) from usermessage where fromid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean banAccount(UserAccount user) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "update useraccount set password = 'ban' where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}

	public boolean relifeAccount(UserAccount user) {
		boolean result = false;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "update useraccount set password = 'relife' where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			int temp = pstmt.executeUpdate();
			if (temp != 0) {
				result = true;
			}
		} catch (SQLException e) {
			// e.printStackTrace(); //
		} finally {
			if (con != null) {
				ConnectionManager.close(con);
			}
		}
		return result;
	}
	
}
