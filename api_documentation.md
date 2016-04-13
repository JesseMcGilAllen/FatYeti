# Fat Yeti

---

Fat Yeti is web service that returns the snow depth for a user-provided zipcode.  The user enters a zipcode and the nearest station data is returned as a JSON object.  The web service is provided in two forms - a standard HTML query string and a RESTful implementation.

Distance between two latitude and longitude coordinate pairs is calculated using Pythagoras' theorm.  The results are returned in meters.

### BASE URL 
http://sol.blackshard.net:11388/FatYeti

### Current API Version
1.0

### Parameters (standard HTML query string)
- Zipcode (required)
    - zipCode
- API (optional)
    - API

### Parameters (RESTful service)
- /API/zipCode

### Example Request URL for Standard HTML Query String
- ZipCode only
    - http://sol.blackshard.net:11388/FatYeti/snowfall?zipCode=53719
- Zipcode and API
    - http://sol.blackshard.net:11388/FatYeti/snowfall?zipCode=53719&API=1.0

### Example Request URL for RESTful Implementation
- Zipcode and API
    - http://sol.blackshard.net:11388/FatYeti/snowfallREST/1.0/54814

### Example JSON response
```
{
    "success":true,
    "errorMessage":"",
    "station": {
        "location": {
            "lat":43.0603,
            "lng":-89.4781,
            "additionalProperties":""
        }
        "description":"MADISON,WI",
        "lastUpdated":"2016-04-11 12",
        "snowfallDepth":0.0,
        "stationElevation":267
    },
    "distanceToStation":174146.72237568902
}
```

