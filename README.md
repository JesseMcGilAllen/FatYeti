# Team Project

## Problem Statement

There are a number of web services that will return snow depth for popular locations like ski resorts.  However, there does not seem to be a web service that will give you snow depth at any particular location.  Our project aims to remedy that situation.  Fat Yeti will be realized as a web service that takes in a ZIP code and will return information regarding snow depth for that location based on the report from the nearest measurement station.  Data elements returned will include the most recently reported snow depth and the station that is reporting the snow depth information.

NOAA has a compilation of snow reports from various stations across the US.  We will be attempting to consume this data, which is searchable but not readily accessible, and return the information via our web service.

We may add additional features to this web service such as reporting minimum, average and maximum snow depths across multiple stations if there are multiple reports for a given ZIP code.  Initially we will just be reporting the first station's results (order is determined by stations distance from the center of the given ZIP code)

## References
- [NOAA.gov](http://www.nohrsc.noaa.gov/nsa/reports.html?region=National&var=snowfall&dy=2016&dm=3&dd=5&units=e&gui=1)
- [Web services for computing geographical distance](https://www.geosvc.com/docs/Ref)
