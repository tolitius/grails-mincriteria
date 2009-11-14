import org.codehaus.groovy.grails.plugins.DomainClassPluginSupport
import org.grails.plugin.mincriteria.validator.MinCriteriaValidator
import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU
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
            "grails-app/views/error.gsp",
            "grails-app/domain/*"
    ]

    def author = "Anatoly Polinsky"
    def authorEmail = "anatoly.polinsky@gmail.com"
    def title = "Validates domain minimum criteria"
    def description = '''\\
DSL for expressing, applying and validating domain minimum criteria (example):

   Business requirements: Address is valid if and only if "it has" 
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
    def documentation = "http://grails.org/plugin/min-criteria"
	
	// to wait for 'domainClasses' to add domain dynamic validation methods
	def loadAfter = ['domainClasses']
	
    def doWithDynamicMethods = { ctx ->
		
		/** TODO: Add '*'(nullable:true) to all the constraints when the plugin loads **/
		
		//ConfigSlurper configSlurper = new ConfigSlurper( application.config )
		//def defaultNullableTrue = { '*'(nullable:true) }
		//configSlurper.setBinding( [ "ants" : defaultNullableTrue ] )
		
		application.domainClasses.each { cClass ->
			if ( GCU.isStaticProperty( cClass.clazz, "minCriteria" ) ) {
				//println "DEBUG: Domain ${cClass} has 'minCriteria' defined..."
				
				// complimenting the core method with min criteria validation
				cClass.metaClass.validate = { -> 
					
					Boolean isValid = DomainClassPluginSupport.validateInstance(delegate, ctx)
					//println "DEBUG: normal validation passed: ${isValid}"
					
					if (isValid) {
						// running minCriteria validator after the core domain validation 
						isValid = MinCriteriaValidator.validate( delegate )
						//println "DEBUG: min criteria validation passed: ${isValid}"
					}
					return isValid				    
				}				
			}
		}
    }
}
