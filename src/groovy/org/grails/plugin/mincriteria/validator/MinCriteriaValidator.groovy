package org.grails.plugin.mincriteria.validator;

import org.codehaus.groovy.grails.commons.GrailsDomainClass
import org.codehaus.groovy.grails.validation.GrailsDomainClassValidator
import org.apache.log4j.Logger

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

class MinCriteriaValidator extends GrailsDomainClassValidator {
	
	private static final Log LOG = LogFactory.getLog(MinCriteriaValidator.class)
	
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
			/** TODO: Incorporate i18n messaging resolution **/
			domain.errors.reject("validation.error.min.criteria",
								 [ domain.class.simpleName ] as Object[],
			                     "Minimum criteria for ${domain.class.simpleName} is not met")
			
			LOG.debug "VDEBUG: minCriteria validation (!)FAILED(!)"
		}
		
		return !domain.errors.hasErrors()
	}
	
	public void validate(Object obj, Errors errors, boolean cascade) {
		//LOG.debug "DEBUG: validate(Object obj, Errors errors, boolean cascade) is called"
		this.validate(obj)
		super.validate ( obj, errors, cascade )
		
	}
	public void validate(Object obj, Errors errors) {
		//LOG.debug "DEBUG: validate(Object obj, Errors errors) is called"
		this.validate(obj)
		super.validate ( obj, errors )
		
	}	
}