package com.neu.wham;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.neu.wham.controllers.DataSourceController;
import com.neu.wham.dao.EventDAO;
import com.neu.wham.dao.EventDAOImpl;
import com.neu.wham.model.Event;
import com.neu.wham.model.PreferencesStore;
import com.neu.wham.model.UserSelectedPreference;
import com.neu.wham.services.GetEventServiceImpl;
import com.neu.wham.services.PreferenceService;
import com.neu.wham.services.PreferenceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-context.xml")
public class GetEventServiceTest {

	private MockMvc mockMvc;
	
	@InjectMocks
    private GetEventServiceImpl getEventServiceMock = mock(GetEventServiceImpl.class);
	private DataSourceController dataSourceController;
	
	  
    @Autowired
    private WebApplicationContext context;

    @Mock
    private PreferenceServiceImpl prefService;

    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	dataSourceController = new DataSourceController(getEventServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(dataSourceController)
        		.build();
      }
    
    
    /**
     * 	test getEventService getEventsFromAPI() without userId
     * 
     */
    
    // testcase 1     
    // get events according to valid latitude, longtitude and radius
    @Test 
    public void getEvents_validLatLonRadTest_withoutUserId() throws Exception{
    	
		String lat = "42.338407";
		String lon = "-71.092625";
		String rad = "50";
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", rad);
		params.put("userId", userId);
		
		when(getEventServiceMock.queryEventbrite(lat, lon, rad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, rad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
    
    
    // testcase 2
    // get events according to valid latitude, longtitude and zero radius
    @Test
    public void getEvents_zeroRadTest_withoutUserId() throws Exception{
    	
		String lat = "42.3389";
		String lon = "71.0903";
		String rad = "0";
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		String afterValidateRad = "10";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", afterValidateRad);
		params.put("userId", userId);
	
		when(getEventServiceMock.queryEventbrite(lat, lon, afterValidateRad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, afterValidateRad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
    
    
    // testcase 3
    // get events according to valid latitude, longitude and negative radius
    @Test
    public void getEvents_negRadTest_withoutUserId() throws Exception{
    	
		String lat = "42.3389";
		String lon = "71.0903";
		String rad = "-10";
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		String afterValidateRad = "10";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", afterValidateRad);
		params.put("userId", userId);
		
		
		when(getEventServiceMock.queryEventbrite(lat, lon, afterValidateRad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, afterValidateRad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }

    
    // testcase 20
    // get events according to valid latitude, longitude and invalid radius string
    @Test
    public void getEvents_InvalidStringRTest_withoutUserId() throws Exception{
    	String lat = "45";
		String lon = "45";
		String rad = "w10";
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		String afterValidateRad = "10";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", afterValidateRad);
		params.put("userId", userId);
		
		
		when(getEventServiceMock.queryEventbrite(lat, lon, afterValidateRad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, afterValidateRad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.equals(new ArrayList<Event>()); 	
    }

    
    // testcase 13
    // get events according to multiple latitude, longitude and radius
    @Test
    public void getEvents_multiLatTest_withoutUserId() throws Exception{
        String lat1 = "0";
        String lon = "0";
        String rad = "10";
        String lat2 = "45";
        
        String url = "/datasource/" + lat1 + "/" + lon + "/" + rad + "/" + lat2;
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .equals("{Error: Coordinates are not valid. Please specify one latitude, one longitude, and one radius}");   
    }

    
    // testcase 14
    // get events according to valid latitude, multiple longitude and radius
    @Test
    public void getEvents_multiLonTest_withoutUserId() throws Exception{
        String lat = "0";
        String lon1 = "0";
        String rad = "10";
        String lon2 = "45";
        
        String url = "/datasource/" + lat + "/" + lon1 + "/" + rad + "/" + lon2;
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .equals("{Error: Coordinates are not valid. Please specify one latitude, one longitude, and one radius}");   
    }
    
    // testcase 15
    // get events according to valid latitude, longitude and multiple radius
    @Test
    public void getEvents_multiRadTest_withoutUserId() throws Exception{
        String lat = "0";
        String lon = "0";
        String rad1 = "10";
        String rad2 = "45";
        
        String url = "/datasource/" + lat + "/" + lon + "/" + rad1 + "/" + rad2;
        mockMvc.perform(get(url))
                .andExpect(status().isNotFound())
                .equals("{Error: Coordinates are not valid. Please specify one latitude, one longitude, and one radius}");   
    }
    

    //?lat=0&long=0&r=foo
	// testcase 10
    // get events according to valid latitude, longitude and dummy radius
    @Test
    public void getEvents_dummyRadTest_withoutUserId() throws Exception{
    	
		String lat = "42.3389";
		String lon = "71.0903";
		String rad = "foo";
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		String afterValidateRad = "10";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", afterValidateRad);
		params.put("userId", userId);
		
			
		when(getEventServiceMock.queryEventbrite(lat, lon, afterValidateRad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, afterValidateRad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
    
    //?lat=0&long=0&r=NULL
    // testcase 11
    // get events according to valid latitude, longitude and null radius
    @Test
    public void getEvents_nullRadTest_withoutUserId() throws Exception{
    	
		String lat = "42.3389";
		String lon = "71.0903";
		String rad = null;
		String userId = null;
		String[] formats = new String[0];
		String[] categories = new String[0];
		String[] subcategories = new String[0];
		
		String afterValidateRad = "10";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", afterValidateRad);
		params.put("userId", userId);
		
		
		when(getEventServiceMock.queryEventbrite(lat, lon, afterValidateRad, formats, categories, subcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, afterValidateRad, formats, categories, subcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEventsFromDB(lat, lon, rad, null)).thenReturn(new ArrayList<Event>());
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=";
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
    
    
    
  
    /**
     * 	test getEventsFromAPI() based on userId
     * 
     */
    
    // valid userId
    // get events according to valid latitude, longitude, radius and userId
    @Test
    public void getEventsFromAPI_validUserId() throws Exception{
    	String lat = "42.338407";
		String lon = "-71.092625";
		String rad = "10";
		String userId = "13";
 		String[] eFormats = new String[0];
 		String[] eCategories = {"118", "101", "110", "105"};
 		String[] eSubcategories = {"5010", "4005", "4002"};
 		
 		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", rad);
		params.put("userId", userId);
 		
 		
 		when(getEventServiceMock.queryEventbrite(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withUserId13());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenCallRealMethod();
		
 		
		assertEquals(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories).size(), 4);
		assertEquals(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories).get(0).getEventName(), "Laugh Owt Loud 2.0");
		assertEquals(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories).get(1).getEventName(), "Dan Crohn's Live Album Recording!");
		assertEquals(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories).get(2).getEventName(), "Roast Battle Boston");
		assertEquals(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories).get(3).getEventName(), "Pavement Comedy Night: Spring Has Sprung");
    }

    
    // invalid userId, the check is in controller
    // get events according to valid latitude, longitude, radius and meaningless userId
    @Test
    public void getEventsFromAPI_invalidUserId() throws Exception{
    	String lat = "42.338407";
		String lon = "-71.092625";
		String rad = "10";
		String userId = "foo";
 		String[] eFormats = new String[0];
 		String[] eCategories = new String[0];
 		String[] eSubcategories = new String[0];
 		
 		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", rad);
		params.put("userId", null);
 		
 		// if userId is invalid, we will set it to null and return events without user preference
 		when(getEventServiceMock.queryEventbrite(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=" + userId;
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
    
    // get events according to valid latitude, longitude, radius and odd userId
    @Test
    public void getEventsFromAPI_oddUserId() throws Exception{
    	String lat = "42.338407";
		String lon = "-71.092625";
		String rad = "10";
		String userId = "foo*~bar";
 		String[] eFormats = new String[0];
 		String[] eCategories = new String[0];
 		String[] eSubcategories = new String[0];
 		
 		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lat", lat);
		params.put("lon", lon);
		params.put("rad", rad);
		params.put("userId", null);
 		
 		// if userId is invalid, we will set it to null and return events without user preference
 		when(getEventServiceMock.queryEventbrite(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenReturn(GetEventServiceUtil.getCannedEventbriteResponse_withoutUserId());
		when(getEventServiceMock.getEventsFromAPI(lat, lon, rad, eFormats, eCategories, eSubcategories)).thenCallRealMethod();
		when(getEventServiceMock.getEvents(params)).thenCallRealMethod();
		
		String url = "/datasource/" + lat + "/" + lon + "/" + rad + "?userId=" + userId;
		mockMvc.perform(get(url))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].eventName", is("Boston Calling - May 27, 28, 29, 2016")))
				.andExpect(jsonPath("$[1].eventName", is("IELTS lessons taught by one of the top IELTS teachers in Uzbekistan")))
				.andExpect(jsonPath("$[2].eventName", is("Get Traction: The Virtual Growth Event [Tashkent]")));
    }
      
    
}
