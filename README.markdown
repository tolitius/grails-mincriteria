## What is Grails minCriteria plugin? ##

DSL (Domain Specific Language) for expressing, applying and validating domain minimum criteria in Grails

## Can you give me an example? ##

Business requirements: Address is valid if and only if "it has" 
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

## So, is it like Grails native constraints? ##

The idea is to make sure that Domain objects have a set of different criterias that should be met once they are validated.
It is similar to Grails "native" constraints, however this plugin allows to set a minimum criteria as *different combinations* of Domain properties

## Sounds pretty 'like any other plugin' to me, so how can I use it? ##

Besides the obvious (*validating Domain objects*), this plugin can be used to specify/validate the *minimum search criteria*, as well as any *minimum other criterias*
