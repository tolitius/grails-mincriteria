// this for now will need to be set by the client 
grails.gorm.default.constraints = {
		'*'(nullable:true)
}

log4j = {
	// Example of changing the log pattern for the default console
	// appender:
	//
	appenders {
		console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	}
	
	error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
			'org.codehaus.groovy.grails.web.pages', //  GSP
			'org.codehaus.groovy.grails.web.sitemesh', //  layouts
			'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
			'org.codehaus.groovy.grails.web.mapping', // URL mapping
			'org.codehaus.groovy.grails.commons', // core / classloading
			'org.codehaus.groovy.grails.plugins', // plugins
			'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
			'org.springframework',
			'org.hibernate',
			'org.grails.plugin.mincriteria.validator'
	
	warn    'org.grails.plugin.mincriteria.validator'
			//'org.codehaus.groovy.grails.plugins'
	
	debug   'org.grails.plugin.mincriteria.validator.MinCriteriaValidator'
			//'org.codehaus.groovy.grails.plugins'
}