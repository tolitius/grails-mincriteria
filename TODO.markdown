## Set global 'nullable' constraints to true  ##

Currently (since 1.2-M4) it can be done manually through Config.groovy:

	grails.gorm.default.constraints = {
        '*'(nullable:true)
    }

Figure out the way to set them for only Domain classes that have minCriteria defined

## Pass parameters to custom messages ##

Currently a default message displayed in case a minimum criteria is not met:

   "Minimum criteria is not met"

But it is configurable through 'validation.error.min.criteria' message property
Enhance it by allowing user to pass parameters ([{0}], [{1}], etc..)
