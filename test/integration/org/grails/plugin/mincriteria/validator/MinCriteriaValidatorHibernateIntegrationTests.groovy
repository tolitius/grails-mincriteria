package org.grails.plugin.mincriteria.validator

import grails.test.*
import org.grails.plugin.mincriteria.test.Address

class MinCriteriaValidatorHibernateIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testShouldSaveByZipCode() { 
		assertNotNull ( new Address ( zipCode: '19123' ).save() )
	}
	
	void testShouldSaveByStreetCityAndCountry() {
		
		assertNotNull ( new Address ( street: 'Peace Prospekt',
		city: 'Kharkov',
		country: 'Ukraine'  ).save() )
	}
	
	void testShouldSaveByCityAndState() {
		
		assertNotNull ( new Address ( city: 'Kharkov',
		state: 'Ukraine'  ).save() )
	}
	
	void testShouldSaveByCityAndCountry() {
		
		assertNotNull ( new Address ( city: 'Kharkov',
		country: 'Ukraine'  ).save() )
	}
	
	void testShouldSaveByLatitudeAndLongitude() {
		
		assertNotNull ( new Address ( latitude: '27.686833',
		longitude: '62.314453'  ).save() )
	}
	
	void testShouldNotSaveIfMinCriteriaNotMet() {
		
		assertNull ( new Address ( latitude: '27.686833').save() )
		assertNull ( new Address ( city: 'Kharkov',street: 'Peace Prospekt'  ).save() )
		assertNull ( new Address ( country: 'Ukraine', state: 'Ukraine'  ).save() )
		
	}
	
	// this ensures that Grails core validator is called during Hibernate save as well
	void testShouldNotSaveIfCoreValidationFails() {
		assertNull ( new Address ( zipCode: '19' ).save() )
		
	}		
}
