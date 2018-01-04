package com.neu.wham.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.springframework.stereotype.Repository;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.neu.wham.dao.EventDAO;
import com.neu.wham.model.Event;
import com.neu.wham.model.SelectedPreference;
import com.neu.wham.model.UserSelectedPreference;

/**
 * EventDAO implementaion
 * @author Vijet Badigannavar, Surbhi, Ryan, Ashwin
 */
@Repository
public class EventDAOImpl implements EventDAO {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://ec2-52-87-159-69.compute-1.amazonaws.com:3306/whamDB";

	//  Database credentials
	static final String USER = "wham";
	static final String PASS = "wham@123";

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addNewEvent(Event event) throws SQLException {

		java.text.SimpleDateFormat sdf1 = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*
			String currentStartTime = sdf1.format(event.getStartTime());
			String currentEndTime = sdf1.format(event.getEndTime());
			
		*/
		String creationTime = sdf1.format(event.getCreationTime());
		String updatedTime = sdf1.format(event.getLastUpdateTime());
		String startDate = sdf1.format(event.getStartDateAndTime());
		String endDate = sdf1.format(event.getEndDateAndTime());

		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		String sql_statement = "insert into EVENT(name,description,is_official,phone,email,"
				+ "address,latitude,longitude,create_datetime,last_update_datetime,org_name,org_desc,start_date_and_time,end_date_and_time,file_path,event_type,event_topic,event_subtopic)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		
		PreparedStatement stmt =conn.prepareStatement(sql_statement);
		stmt.setString(1, event.getEventName());
		stmt.setString(2, event.getEventDesc());
		stmt.setBoolean(3, event.isOfficialEvent());
		stmt.setString(4, event.getPhoneNumber());
		stmt.setString(5, event.getEmailId());
		stmt.setString(6, event.getEventLocation());
		stmt.setDouble(7, event.getLatitude());
		stmt.setDouble(8, event.getLongitude());
		stmt.setString(9, creationTime);
		stmt.setString(10, updatedTime);
		stmt.setString(11, event.getOrganiserName());
		stmt.setString(12, event.getOrganiserDesc());
		stmt.setString(13, startDate);
		stmt.setString(14, endDate);
		stmt.setString(15, event.getFilePath());
		stmt.setInt(16, event.getEventType());
		stmt.setInt(17, event.getEventTopic());
		stmt.setInt(18, event.getEventSubtopic());


		try{
		int val = stmt.executeUpdate();
		if(val!=0){
			return true;
		}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		throw new SQLException();
	}
	
	@Override
	public List<Event> getEventsData(String lat, String lon, String radius, UserSelectedPreference userPrefs) throws SQLException, JSONException, UnirestException
	{ 
		if(null != userPrefs){
			try {
				return getEventsFromDBWithPref(lat, lon, radius, userPrefs);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getEventsFromDBWithoutPref(lat, lon, radius);
	}
	
	
	public List<Event> getEventsFromDBWithPref(String lat, String lon, String radius, UserSelectedPreference userPrefs) throws SQLException, URISyntaxException, UnirestException
	{
		
		List<SelectedPreference> prefs = userPrefs.getSelectedPreference();
		List<Event> DBEvents = new ArrayList<Event>();
		String tableName = null;
		
		for(SelectedPreference pref : prefs){
			
			switch(pref.getEventCategory()) {
				case 1:
					tableName = "event_type";
					break;
				case 2:
					tableName = "event_topic";
					break;
				case 3:
					tableName = "event_subtopic";
					break;
				default:
					System.out.println("Unknown preference type! " + pref.getEventCategory());
			}
		
			double lat_rad_sin = Math.sin(((double)Double.parseDouble(lat) * 3.14) / 180);
			double lat_rad_cos = Math.cos(((double)Double.parseDouble(lat) * 3.14) / 180);
			double lon_rad = ((double)Double.parseDouble(lon) * 3.14) / 180;
			
			String query = "SELECT * FROM EVENT WHERE "
					+ "acos(? * sin(latitude * 3.14 / 180) + ? * cos(latitude * 3.14 / 180) * cos(longitude * 3.14 / 180 - ?)) * 6371 <= ? AND " + tableName + " = ?";
			
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, lat_rad_sin);
			pstmt.setDouble(2, lat_rad_cos);
			pstmt.setDouble(3, lon_rad);
			pstmt.setDouble(4, (double)Double.parseDouble(radius) / 0.62137);
			pstmt.setInt(5, pref.getEventId());
			
			ResultSet rs = null;

			try {
				rs = pstmt.executeQuery();
				while(rs.next()) { 
					Event e = new Event();
					e.setEventName(rs.getString("name"));
					e.setEventDesc(rs.getString("description"));
					e.setEventLocation(rs.getString("address"));
					e.setPhoneNumber(rs.getString("phone"));
					e.setEmailId(rs.getString("email"));

					e.setStartDateAndTime(rs.getDate("start_date_and_time"));
					e.setEndDateAndTime(rs.getDate("end_date_and_time"));

					e.setLatitude(rs.getDouble("latitude"));
					e.setLongitude(rs.getDouble("longitude"));
					e.setFilePath(rs.getString("file_path"));			
					e.setOrganiserName(rs.getString("org_name"));
					e.setOrganiserDesc(rs.getString("org_desc"));
					e.setOfficialEvent(rs.getBoolean("is_official"));
					e.setCreationTime(rs.getDate("create_datetime"));
					e.setLastUpdateTime(rs.getDate("last_update_datetime"));
					DBEvents.add(e);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			    throw e;
			}
			finally {
				conn.close();
			}
			System.out.println(DBEvents.size());
		}
		
		Set<Event> filterDupEvents = new HashSet<Event>(DBEvents);
		List<Event> filteredEvents = new ArrayList<Event>(filterDupEvents);
		return filteredEvents;
	}

	public List<Event> getEventsFromDBWithoutPref(String lat, String lon, String radius) throws SQLException
	{
		double lat_rad_sin = Math.sin(((double)Double.parseDouble(lat) * 3.14) / 180);
		double lat_rad_cos = Math.cos(((double)Double.parseDouble(lat) * 3.14) / 180);
		double lon_rad = ((double)Double.parseDouble(lon) * 3.14) / 180;
		
		String query = "SELECT * FROM EVENT WHERE "
				+ "acos(? * sin(latitude * 3.14 / 180) + ? * cos(latitude * 3.14 / 180) * cos(longitude * 3.14 / 180 - ?)) * 6371 <= ?";
		
		Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setDouble(1, lat_rad_sin);
		pstmt.setDouble(2, lat_rad_cos);
		pstmt.setDouble(3, lon_rad);
		pstmt.setDouble(4, (double)Double.parseDouble(radius) / 0.62137);
		
		ResultSet rs = null;
		List<Event> DBEvents = new ArrayList<Event>();
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) { 
				Event e = new Event();
				e.setEventName(rs.getString("name"));
				e.setEventDesc(rs.getString("description"));
				e.setEventLocation(rs.getString("address"));
				e.setPhoneNumber(rs.getString("phone"));
				e.setEmailId(rs.getString("email"));

				e.setStartDateAndTime(rs.getDate("start_date_and_time"));
				e.setEndDateAndTime(rs.getDate("end_date_and_time"));

				e.setLatitude(rs.getDouble("latitude"));
				e.setLongitude(rs.getDouble("longitude"));
				e.setFilePath(rs.getString("file_path"));			
				e.setOrganiserName(rs.getString("org_name"));
				e.setOrganiserDesc(rs.getString("org_desc"));
				e.setOfficialEvent(rs.getBoolean("is_official"));
				e.setCreationTime(rs.getDate("create_datetime"));
				e.setLastUpdateTime(rs.getDate("last_update_datetime"));
				DBEvents.add(e);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		    throw e;
		}
		finally {
			conn.close();
		}
		System.out.println(DBEvents.size());
		return DBEvents;
	}
}
