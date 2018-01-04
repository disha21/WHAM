package com.neu.wham;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.neu.wham.controllers.DataSourceController;
import com.neu.wham.exceptions.LocationException;
import com.neu.wham.exceptions.LocationException.LocationExceptionType;

public class DataSourceControllerTest {
	

    DataSourceController dsController = new DataSourceController();

	@Rule
    public ExpectedException thrown = ExpectedException.none();
     
	//?lat=91&long=0&r=10  testcase4
	// get Events when Latitude is too big
    @Test
    public void getEvents_invalidBigLatTest() throws Exception {
    	String lat = "91";
    	String lon = "0";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    //?lat=-91&long=0&r=10  testcase5
    // get Events when Latitude is too small
    @Test
    public void getEvents_invalidSmallLatTest() throws Exception {
    	String lat = "-91";
    	String lon = "0";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    // get Events when Longitude is too big
    @Test
    public void getEvents_invalidBigLonTest() throws Exception {
    	String lat = "0";
    	String lon = "181";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    //?lat=0&long=-181&r=10 testcase7
    // get Events when Longitude is too small
    @Test
    public void getEvents_invalidSmallLonTest() throws Exception {
    	String lat = "0";
    	String lon = "-181";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_OUT_OF_BOUNDS).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    //?lat=foo&long=0&r=10 testcase8
    // get Events when Latitude is meaningless string
    @Test
    public void getEvents_invalidFooLatTest() throws Exception {
    	String lat = "foo";
    	String lon = "0";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_FORMAT_EXCEPTION).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    //?lat=0&long=foo&r=10 testcase9
    // get Events when Longitude is meaningless string
    @Test
    public void getEvents_invalidFooLonTest() throws Exception {
    	String lat = "foo";
    	String lon = "0";
    	String rad = "10";
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_FORMAT_EXCEPTION).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
    
    //?lat=NULL&long=NULL&r=NULL testcase12
    // get Events when latitude, longitude and radius are missing
    @Test
    public void getEvents_invalidLackLatLonRadTest() throws Exception {
    	String lat = null;
    	String lon = null;
    	String rad = null;
    	String userId = null;
    		
    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_INCOMPLETE).getMessage();
    	
        // arrange
        thrown.expect(LocationException.class);
        thrown.expectMessage(equalTo(exceptionStatement));
        // act
        dsController.firstRequest(lat, lon, rad, userId);
    }
        
	    //?lat=0 testcase 16
	    ///datasource/0/null/null
    	// get events when longitude and radius are missing
	    @Test
	    public void getEvents_invalidLackLonRadTest() throws Exception {
	    	String lat = "0";
	    	String lon = null;
	    	String rad = null;
	    	String userId = null;
	    		
	    	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_INCOMPLETE).getMessage();
	    	
	        // arrange
	        thrown.expect(LocationException.class);
	        thrown.expectMessage(equalTo(exceptionStatement));
	        // act
	        dsController.firstRequest(lat, lon, rad, userId);
	    }
    
	    //?long=0 testcase 17
	    ///datasource/null/0/null
	    // get events when latitude and radius are missing
	   @Test
	   public void getEvents_invalidLackLatRadTest() throws Exception {
	   	String lat = null;
	   	String lon = "0";
	   	String rad = null;
	   	String userId = null;
	   		
	   	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_INCOMPLETE).getMessage();
	   	
	       // arrange
	       thrown.expect(LocationException.class);
	       thrown.expectMessage(equalTo(exceptionStatement));
	       // act
	       dsController.firstRequest(lat, lon, rad, userId);
	   }
	   
	   //?r=10 testcase 18
	   ///datasource/null/null/10
	   // get events when latitude and longitude are missing
	   @Test
	   public void getEvents_invalidLackLatLonTest() throws Exception {
	   	String lat = null;
	   	String lon = null;
	   	String rad = "10";
	   	String userId = null;
	   		
	   	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_INCOMPLETE).getMessage();
	   	
	       // arrange
	       thrown.expect(LocationException.class);
	       thrown.expectMessage(equalTo(exceptionStatement));
	       // act
	       dsController.firstRequest(lat, lon, rad, userId);
	   }
	
	   
	   //?lat=45#&long=50&r=10 testcase 19
	   ///datasource/45#/50/10
	   // get events when longitude and radius are missing
	   @Test
	   public void getEvents_invalidLatTest() throws Exception {
	   	String lat = "45#";
	   	String lon = "50";
	   	String rad = "10";
	   	String userId = null;
	   		
	   	String exceptionStatement = new LocationException(LocationExceptionType.LOCATION_FORMAT_EXCEPTION).getMessage();
	   	
	       // arrange
	       thrown.expect(LocationException.class);
	       thrown.expectMessage(equalTo(exceptionStatement));
	       // act
	       dsController.firstRequest(lat, lon, rad, userId);
	   }
	   
}
