package org.grails.plugin.mincriteria.validator

import grails.test.*
import org.grails.plugin.mincriteria.test.Address;

class MinCriteriaValidatorIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testShouldPassValidatationByZipCode() {
		
		Address address = new Address ( zipCode: '19123' )
		Boolean isValid = address.validate()
		
		assertTrue ( "$address.errors" , isValid )
	}
	
	void testShouldPassValidatationByStreetCityAndCountry() {
		
		assertTrue ( new Address ( street: 'Peace Prospekt',
		city: 'Kharkov',
		country: 'Ukraine'  ).validate() )
	}
	
	void testShouldPassValidatationByCityAndState() {
		
		assertTrue ( new Address ( city: 'Kharkov',
		state: 'Ukraine'  ).validate() )
	}
	
	void testShouldPassValidatationByCityAndCountry() {
		
		assertTrue ( new Address ( city: 'Kharkov',
		country: 'Ukraine'  ).validate() )
	}
	
	void testShouldPassValidatationByLatitudeAndLongitude() {
		
		assertTrue ( new Address ( latitude: '27.686833',
		longitude: '62.314453'  ).validate() )
	}
	
	void testShouldFailValidatationIfMinCriteriaNotMet() {
		
		assertFalse ( new Address ( latitude: '27.686833').validate() )
		
		assertFalse ( new Address ( city: 'Kharkov',
		street: 'Peace Prospekt'  ).validate() )
		
		assertFalse ( new Address ( country: 'Ukraine',
		state: 'Ukraine'  ).validate() )
		
	}
	
	void testShouldFailValidatationIfMinCriteriaNotMetAndRecordError() {

		Address address = new Address ( latitude: '27.686833')
		Boolean isValid = address.validate()

		assertEquals( "minimum criteria is not met", address.errors.allErrors[0].defaultMessage )
	}	
	
	// this ensures that Grails core validator is called first
	// and if it fails minCriteria validator is not called
	void testShouldFailCoreValidatation() {		
		assertFalse ( new Address ( zipCode: '19' ).validate() )
	}
	
}
