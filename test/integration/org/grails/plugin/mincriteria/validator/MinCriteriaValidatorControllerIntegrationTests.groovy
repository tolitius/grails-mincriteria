package org.grails.plugin.mincriteria.validator

import grails.test.*
import org.grails.plugin.mincriteria.test.AddressController
import org.grails.plugin.mincriteria.test.Address

class MinCriteriaValidatorControllerIntegrationTests extends GrailsUnitTestCase {
	
	AddressController addressController
	
	protected void setUp() {
        super.setUp()
        addressController = new AddressController()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testShouldSaveIfMinSearchCriteriaMet() {
		
		addressController.params.zipCode = '12345'
		addressController.save()
		assertNull( addressController.modelAndView )
	}	
	
    void testShouldNotSaveIfMinSearchCriteriaNotMet() {
		
		addressController.params.street = 'Prospekt Utrenikova'
		addressController.save()
		
		assertNotNull( addressController.modelAndView )
    }
	
	void testShouldNotSaveAndReturnMinSearchCriteriaError() {
		
		addressController.params.street = 'Prospekt Utrenikova'		
		addressController.save()
		
		assertEquals( "minimum criteria is not met", 
					  addressController.modelAndView.model.addressInstance.errors.allErrors[0].defaultMessage )
	}
		
}
