package com.neu.wham;

import static org.junit.Assert.*;

import java.util.Arrays;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import com.neu.wham.model.PreferencesStore;
import com.neu.wham.services.PreferenceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-context.xml")
public class PreferenceServiceTest {
	
	@Autowired 
	private PreferenceServiceImpl prefServiceImpl; 
	
	// positive test, valid userId
	// check PreferenceStore object according to valid userId
	@Test
	public void buildPreferencesStoreTest() {
		String userId = "13";
		PreferencesStore result = prefServiceImpl.buildPreferencesStore(userId);
		
		String[] formats = new String[0];
		String[] categories = { "Academic Events", "Auto, Boat And Air", "Business And Professional", "Food And Drink", "Performing And Visual Arts"};
 		String[] subcategories = {"", "Comedy", "Comics", "Film"};
 		String[] eFormats = new String[0];
 		String[] eCategories = {"118", "101", "110", "105"};
 		String[] eSubcategories = {"5010", "4005", "4002"};
		
		assertEquals(formats.length, 0);
		assertEquals(eFormats.length, 0);
		assertTrue(Arrays.equals(result.getCategories(), categories));
		assertTrue(Arrays.equals(result.getSubcategories(), subcategories));
		assertTrue(Arrays.equals(result.getCategoriesAsEventbrite(), eCategories));
		assertTrue(Arrays.equals(result.getSubcategoriesAsEventbrite(), eSubcategories));
	
	}
	
	// negative test, invalid userId
	// check PreferenceStore object according to invalid userId
	@Test(expected=NullPointerException.class)
	public void buildPreferencesStoreNegTest_foo(){
		String userId = "foo";
		prefServiceImpl.buildPreferencesStore(userId);
	}
	
	// negative test, invalid userId
	// check PreferenceStore object according to odd userId
	@Test(expected=NullPointerException.class)
	public void buildPreferencesStoreNegTest_oddChars(){
		String userId = "foo*~bar";
		prefServiceImpl.buildPreferencesStore(userId);
	}
	
}
