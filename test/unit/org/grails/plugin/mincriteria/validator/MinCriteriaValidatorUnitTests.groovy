package org.grails.plugin.mincriteria.validator

import grails.test.*
import org.grails.plugin.mincriteria.test.Address;
import org.springframework.validation.BeanPropertyBindingResult

class MinCriteriaValidatorUnitTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
		
        Address.metaClass.errors = new BeanPropertyBindingResult( new Address(), 'Address' )
    }

    protected void tearDown() {
        super.tearDown()
    }
		
	void testShouldPassValidatationByZipCode() {
		
		Address address = new Address ( zipCode: '19123' )
		assertTrue ( MinCriteriaValidator.validate( address ) )
	}
	
	void testShouldPassValidatationByStreetCityAndCountry() {
		
		Address address = new Address ( street: 'Peace Prospekt',
		                         city: 'Kharkov',
		                         country: 'Ukraine'  )
		assertTrue ( MinCriteriaValidator.validate( address ) )
	}
	
	void testShouldPassValidatationByCityAndState() {
		
		Address address = new Address ( city: 'Kharkov', state: 'Ukraine'  )
		assertTrue ( MinCriteriaValidator.validate( address ) )
	}
	
	void testShouldPassValidatationByCityAndCountry() {
		
		Address address = new Address ( city: 'Kharkov', country: 'Ukraine'  )
		assertTrue ( MinCriteriaValidator.validate( address ) )
	}
	
	void testShouldPassValidatationByLatitudeAndLongitude() {
		
		Address address = new Address ( latitude: 27.686833, longitude: 62.314453  )
		MinCriteriaValidator.validate( address )
	}
		
	void testShouldFailValidatationIfMinCriteriaNotMet() {
		
		Address invalidAddress1 = new Address ( )
		Address invalidAddress2 = new Address ( latitude: 27.686833 )
		Address invalidAddress3 = new Address ( city: 'Kharkov', street: 'Peace Prospekt' )
		Address invalidAddress4 = new Address ( country: 'Ukraine', state: 'Ukraine'  )
		
		assertFalse( MinCriteriaValidator.validate( invalidAddress1 ) )
		assertFalse( MinCriteriaValidator.validate( invalidAddress2 ) )
		assertFalse( MinCriteriaValidator.validate( invalidAddress3 ) )
		assertFalse( MinCriteriaValidator.validate( invalidAddress4 ) )
	}
	
	void testShouldFailValidatationIfMinCriteriaNotMetAndRecordError() {
		
		Address address = new Address ( latitude: 27.686833 )
		Boolean isValid = MinCriteriaValidator.validate( address )

		assertFalse( isValid )
		assertEquals( "Minimum criteria for ${address.class.simpleName} is not met", address.errors.allErrors[0].defaultMessage )
	}
}
