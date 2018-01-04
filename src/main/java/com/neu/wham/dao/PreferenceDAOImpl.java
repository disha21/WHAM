package com.neu.wham.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;
import com.neu.wham.keys.DBConstants;
import com.neu.wham.model.EventTopic;
import com.neu.wham.model.EventType;
import com.neu.wham.model.SelectedPreference;
import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.model.UserPreference;

/**
 * Implemenation of PreferenceDAO
 * @author Vijet,Vaibhav,Harsh,Mavez,Yue, Ryan, Surbhi, Ashwin
 */
@Repository
public class PreferenceDAOImpl implements PreferenceDAO {

	private Connection conn = null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public UserPreference getAllPreferences() throws Exception {
		
		conn = DriverManager.getConnection(DBConstants.DB_URL,DBConstants.USER,DBConstants.PASS);
		
		UserPreference userPref = new UserPreference();
		userPref.setEventType(getAllEventTypes());
		userPref.setEventTopic(getAllEventTopics());
		
		return userPref;
	}
	
	private List<EventTopic> getAllEventTopics() throws SQLException{
		// TODO Auto-generated method stub
		List<EventTopic> eventTopicList = new ArrayList<EventTopic>();
		
		
			//List<EventTopic> eventTopicList = new ArrayList<EventTopic>();
			StringBuilder sqlStmt1 = new StringBuilder();
			sqlStmt1.append("Select * from EVENT_SUB_TOPIC_MASTER where event_topic_id=?;");
			
			StringBuilder  sqlStmt = new StringBuilder();
			sqlStmt.append("Select * from EVENT_TOPIC_MASTER order by event_topic_id;");
			
			
			PreparedStatement ppdStmt = (PreparedStatement) conn.prepareStatement(sqlStmt.toString());
			ResultSet rsTopc = ppdStmt.executeQuery();
			
			while(rsTopc.next()){
				EventTopic etopic = new EventTopic();
				int topicId= rsTopc.getInt("event_topic_id");
				etopic.setTopicId(topicId);
				etopic.setTopicName(rsTopc.getString("event_topic"));
				List<EventType> eTypeList = new ArrayList<EventType>();
				PreparedStatement ppdStmtSubQuery = (PreparedStatement) conn.prepareStatement(sqlStmt1.toString());
				ppdStmtSubQuery.setInt(1, topicId);
				ResultSet rsSubTopc = ppdStmtSubQuery.executeQuery();
				while(rsSubTopc.next()){
					EventType tempEventType = new EventType();
					tempEventType.setEventId(rsSubTopc.getInt("event_sub_topic_id"));
					tempEventType.setEventName(rsSubTopc.getString("event_sub_topic"));
					eTypeList.add(tempEventType);
				}
				etopic.setSubTopicsList(eTypeList);
				
				eventTopicList.add(etopic);
			}
			return eventTopicList;
			
			
			
	}

	// GetallEventTYpes;
	private List<EventType> getAllEventTypes() throws SQLException{
		List<EventType> eventTypeList = new ArrayList<EventType>();
		String sqlStmt = "Select * from EVENT_TYPE_MASTER;";
		PreparedStatement ppdStmt = (PreparedStatement) conn.prepareStatement(sqlStmt);
		
		ResultSet rs = ppdStmt.executeQuery();
		while(rs.next()){
			EventType et = new EventType();
			et.setEventId(rs.getInt("event_type_id"));
			et.setEventName(rs.getString("event_type"));
			eventTypeList.add(et);
		}
		return eventTypeList;
	}

	@Override
	public UserSelectedPreference updatePreference(int userId, UserSelectedPreference userPref) throws Exception {
		conn = DriverManager.getConnection(DBConstants.DB_URL,DBConstants.USER,DBConstants.PASS);
		deleteUserPreference(userId);
		updateUserPreference(userId,userPref);
		System.out.println("Preference saved!");
		return userPref;
	}
	


	private void updateUserPreference(int userId, UserSelectedPreference userPref) throws SQLException {
		StringBuilder updateUserPreference = new StringBuilder();
		updateUserPreference.append("insert into USER_PREFERENCES values(?,?,?);");
		
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(updateUserPreference.toString());
		Iterator<SelectedPreference> iter = userPref.getSelectedPreference().iterator();
		while(iter.hasNext()){
			SelectedPreference tempPref = iter.next();
			stmt.setInt(1, userId);
			stmt.setInt(2, tempPref.getEventCategory());
			stmt.setInt(3, tempPref.getEventId());
			int value = stmt.executeUpdate();
			if(value == 0){
				throw new SQLException();
			}
		}
		stmt.close();
	}

	private boolean deleteUserPreference(int uesrId) throws SQLException{
		StringBuilder deleteUserBuilder = new StringBuilder();
		deleteUserBuilder.append("delete from USER_PREFERENCES where user_id=?;");
		
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(deleteUserBuilder.toString());
		stmt.setInt(1, uesrId);
		int result = stmt.executeUpdate();
		if(result!=0){
			stmt.close();
			return true;
		}
		stmt.close();
		return false;
	}

	@Override
	public UserSelectedPreference getUserPreferences(int userId) throws Exception {
		System.out.println("in getUserPreferences, userId: " + userId);
		UserSelectedPreference userPref = new UserSelectedPreference();
		List<SelectedPreference> prefList = new ArrayList<SelectedPreference>();
		
		conn = DriverManager.getConnection(DBConstants.DB_URL,DBConstants.USER,DBConstants.PASS);
		
		StringBuilder stmtBuilder = new StringBuilder();
		stmtBuilder.append("select * from USER_PREFERENCES where user_id=?;");
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(stmtBuilder.toString());
		stmt.setInt(1, userId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			prefList.add(new SelectedPreference(rs.getInt("event_pref_id"), rs.getInt("event_pref_type")));
		}
		System.out.println("prefList size: " + prefList.size());
		userPref.setSelectedPreference(prefList);
		return userPref;
	}

	@Override
	public String getPreferenceString(int id, int tableId) throws Exception {
		conn = DriverManager.getConnection(DBConstants.DB_URL,DBConstants.USER,DBConstants.PASS);
		
		String prefString = "";
		String columnKey;
		StringBuilder stmtBuilder = new StringBuilder();
		
		switch(tableId) {
			case 0:
				stmtBuilder.append("select * from EVENT_TYPE_MASTER where event_type_id=?;");
				columnKey = "event_type";
				break;
			case 1:
				stmtBuilder.append("select * from EVENT_TOPIC_MASTER where event_topic_id=?;");
				columnKey = "event_topic";
				break;
			case 2:
				stmtBuilder.append("select * from EVENT_SUB_TOPIC_MASTER where event_sub_topic_id=?;");
				columnKey = "event_sub_topic";
				break;
			default:
				columnKey = "";
		}
		
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(stmtBuilder.toString());
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			prefString = rs.getString(columnKey);
		}
		return prefString;
	}
}
