package org.grails.plugin.mincriteria.validator;

class MinCriteriaValidator {
	
	static Boolean validate( domain ) {
		
		Boolean isValid = false
		
		// there is no way to break from the closure - using a for loop
		for ( constraint in domain.minCriteria ) {
			if ( constraint.every { domain."${it}" } ) {
				isValid = true
				break
			}
		}
		
		if( !isValid ) {
			/** TODO: Incorporate Grails messaging resolution **/
			domain.errors.reject("validation.error.min.criteria", 
			                     "minimum criteria is not met")
		}
		
		return !domain.errors.hasErrors()
	}
}