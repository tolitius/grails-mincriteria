## What is Grails minCriteria plugin? ##

DSL (Domain Specific Language) for expressing, applying and validating domain minimum criteria in Grails

## Can you give me an example? ##

Business requirement: Address is valid if and only if "it has" 
one of the following combinations (of properties) set:

        'zipCode'
     OR 'street, city and country'
     OR 'city and state'
     OR 'city and country'
     OR 'latitude and longitude'

With minCriteria plugin this can be expressed in the Address domain object as:    	

     static minCriteria = [

          [ 'zipCode' ],
          [ 'street', 'city', 'country' ],
          [ 'city', 'state' ],
          [ 'city', 'country' ],
          [ 'latitude', 'longitude' ]
     ]

Every time "Address.validate()" is called, whether it is automatically on ".save()" or 
explicitly in the controller, the plugin will ensure that Address minimum criteria (Business requirement) is met

## What error do I get in case a minimum criteria was not met?  ##

Currently a default message returned/displayed in case a minimum criteria is not met:

    "Minimum criteria for [Address] is not met" 

"Address" here is the domain object name discussed above. By default the plugin would pick up a domain name and would use it within the error message (e.g. if your domain name is "Car", the default error message would be "Minimum criteria for [Car] is not met").

But this message is configurable through 'validation.error.min.criteria' message property. And since v0.1.1, the plugin supports message parameters (something that is called "errorArgs" in Spring). For example, if you put the following property under "grails-app/i18n/messages.properties":

    "validation.error.min.criteria=Minimum search criteria for {0} is not met."

and your domain name is "Car", the message your end-user would get is:

    "Minimum search criteria for Car is not met." 

<br>*(see '[TODO.markdown](http://github.com/trickster/grails-mincriteria/blob/master/TODO.markdown "TODO.markdown")' document to read about future enhancements)*

## So, is it like Grails native constraints? ##

The idea is to make sure that Domain objects have a set of different criterias that should be met once they are validated.
It is similar to Grails "native" constraints, however this plugin allows to set a minimum criteria as *different combinations* of Domain properties

## Sounds pretty 'like any other plugin' to me, so how can I use it? ##

Besides the obvious (*validating Domain objects*), this plugin can be used to specify/validate the *minimum search criteria*, as well as any *minimum other criterias*
