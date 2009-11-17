import org.springframework.context .ApplicationContext
import org.grails.plugin.mincriteria.validator.MinCriteriaValidator
import org.codehaus.groovy.grails.commons .GrailsDomainClass

import org.hibernate.SessionFactory
import org.codehaus.groovy.grails.orm.hibernate.metaclass.ValidatePersistentMethod
import org.codehaus.groovy.grails.orm.hibernate.metaclass.SavePersistentMethod
import org.codehaus.groovy.grails.plugins.DomainClassPluginSupport
import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU
import org.springframework.validation.Validator
import grails.util.GrailsUtil

class MinCriteriaGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2-M4 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
		"grails-app/views/*.gsp",
		"grails-app/views/**/*.gsp",
		"grails-app/domain/**/*.groovy",
		"grails-app/controllers/**/*.groovy",
	]

    def author = "Anatoly Polinsky"
    def authorEmail = "anatoly.polinsky@gmail.com"
    def title = "Validates domain minimum criteria"
    def description = '''\\
   DSL for expressing, applying and validating domain minimum criteria (example):

   Business requirement: Address is valid if and only if "it has" 
   one of the following combinations (of properties) set:

        'zipCode'
     OR 'street, city and country'
     OR 'city and state'
     OR 'city and country'
     OR 'latitude and longitude'

     With 'minCriteria' plugin this can be expressed in the Address domain object as:    	
    	
     static minCriteria = [

          [ 'zipCode' ],
          [ 'street', 'city', 'country' ],
          [ 'city', 'state' ],
          [ 'city', 'country' ],
          [ 'latitude', 'longitude' ]
     ]

     Every time "Address.validate()" is called, whether it is automatically on ".save()" or 
     explicitly in the controller, the plugin will ensure that Address minimum criteria (Business requirement) is met.
'''

    // URL to the plugin's documentation
    def documentation = "http://github.com/trickster/grails-mincriteria/blob/master/README.markdown"
	
	// to wait for 'domainClasses' to add domain dynamic validation methods
	def loadAfter = ['core', 'domainClasses', 'hibernate']
	
	def doWithSpring = {

		/** go through all domain classes, 
            pick the ones with minCriteria defined,
            register a MinCriteriaValidator for them **/		

		for(GrailsDomainClass dc in application.domainClasses) {
			if ( GCU.isStaticProperty( dc.clazz, "minCriteria" ) ) {

				//println "\nDEBUG: registering ${dc.fullName}\n"
				"${dc.fullName}Validator"(MinCriteriaValidator) {
					messageSource = ref("messageSource")
					domainClass = ref("${dc.fullName}DomainClass")
					grailsApplication = ref("grailsApplication", true)                
				}
			}
		}
			
	}		
}
