## Implement "only number of the following" criteria ##

	static onlyOneOfCriteria = [
		[zipCode],
		[latitude, longitude]
    ]

"zipCode" is valid, "latitude + longitude" is valid, however "zipCode + latitude + longitude" (or nothing, or just latitude) is not valid.

challenges here:

* naming for criterias
* multiple number of "only allowed" e.g. "only two out of these are allowed" - first element of the list..? different names..?
* it is still *kind* of minimum criteria, but deviates from it a bit

## Allow for custom error messages per Domain ##

	[domainName].error.min.criteria'

where "domainName" is the actual Domain that failed validation. This would allow for custom messages for each domain e.g.

	"Address.error.min.criteria=Do not have enough data to find your Address. Please enter one of the following: Zip Code OR City and State OR City and Country"

## Pass parameters to custom messages ##

Currently a default message displayed in case a minimum criteria is not met:

    "Minimum criteria [Address] is not met"

But it is configurable through 'validation.error.min.criteria' message property
Enhance it by allowing user to *pass* message property parameters for {0}, {1}, etc..
