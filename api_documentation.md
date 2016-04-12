# Fat Yeti

---

Fat Yeti is web service that returns the snow depth for a user-provided zipcode.  The user enters a zipcode and the nearest station data is returned as a JSON object.

### BASE URL
http://sol.blackshard.net:11388/FatYeti

### Current API Version
1.0

### Parameters
- Zipcode (required)
    - zipCode
- API (optional)
    - API

### Example Request URL
- ZipCode only
    - http://sol.blackshard.net:11388/FatYeti/snowfall?zipCode=53719
- Zipcode and API
    - http://sol.blackshard.net:11388/FatYeti/snowfall?zipCode=53719&API=1.0

### Example JSON response
```
{
    "success":true,
    "errorMessage":"",
    "station": {
        "description":"MADISON,WI",
        "latitude":43.0603,
        "longitude":-89.4781
    },
    "lastUpdated":"2016-04-11 12",
    "snowfall":0.0
}
```

