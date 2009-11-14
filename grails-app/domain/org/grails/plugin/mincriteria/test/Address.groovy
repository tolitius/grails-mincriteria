package org.grails.plugin.mincriteria.test

class Address {
	
	String flatNumber
	String houseNumber
	String street
	String city
	String state
	String zipCode
	String country
	
	Double latitude
	Double longitude
	
	static constraints = {
		zipCode(size:5..6)
	}
	
	/**
	 *  <p>Defines a minimum criteria that should be met
	 *   to create / save an Address instance.</p>
	 **/
	static minCriteria = [
	
	[ 'zipCode' ],
	[ 'street', 'city', 'country' ],
	[ 'city', 'state' ],
	[ 'city', 'country' ],
	[ 'latitude', 'longitude' ]
	
	]
}
