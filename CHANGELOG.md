# Release Version 1.10
Version 1.10 is not released yet.

**New Features**
* Actuation support. By default the actuation entities are hidden from the index,
  and from navigationLinks. By turning on the setting `enableActuation` the entities
  and navigationLinks are shown. Validation of the taskingParameters is not
  implemented yet.
* MultiDatastreams can be hidden from the index page and from navigationLinks by
  setting enableMultiDatastream to false. By default MultiDatastreams are enabled.
* Added experimental support for a serverSettings element to the index page, as
  discussed on the SensorThings API GitHub page: https://github.com/opengeospatial/sensorthings/issues/4
* Added database persistence manager implementations using JOOQ instead of QueryDSL.


# Release Version 1.9
Version 1.9 was released on 2019-01-18.

**New Features**
* Added experimental DELETE on Collections, with filters. Allows easier data cleanup.
  See https://github.com/opengeospatial/sensorthings/issues/44
* Added experimental way to change the location of a Thing, without generating a
  HistoricalLocation with a time of now(). See #66 and https://github.com/opengeospatial/sensorthings/issues/30
* Added authentication support with two backends:
  * Basic: Using an internal user database and Basic authentication for HTTP.
  * Keycloak: Authenticating against an external Keycloak server.
* Added support for JSON-Patch[RFC6902] updates. This allows users to specify
  specific changes to be made to the properties object, without replacing the
  entire object.


# Release Version 1.8
Version 1.8 was released on 2018-08-24.

**New Features**
* Upgraded moquette to v0.11.
* Allow setting of the moquette persistent store path and storage class.
* Enabling the tomcat CorsFilter to allow cross-site-scripting can be done from environment variables.
* Added option to automatically run the liquibase database upgrade.

**Bugfixes**
* Fixed #59, incorrect nextLink when filtering on unitOfMeasurement/name.
* Fixed `MultiDatastream.observationType` being required even though we set it automatically.
* Prioritise `persistence_db_url` over `persistence_db_jndi_datasource`. This way there is no longer the need to add an empty environment variable `persistence_db_jndi_datasource` for the HTTP and MQTTP component when configuring using environment variables.
* Fixed string ids in next- and selfLink not being urlEncoded.


# Release Version 1.7
Version 1.7 was released on 2018-07-02.

**New Features**
* Observation.result can be explicitly set to null. This is useful in cases where
  an observation did not produce a value, but the fact that an observation was attempted
  must still be recorded.
* Exposed database connection options `persistence.db.conn.max`, `persistence.db.conn.idle.max`, `persistence.db.conn.idle.min`

**Bugfixes**
* Fixed #53: Query parser not Unicode aware.
* Fixed #52: Generating FeatureOfInterest did not work for Things with multiple Location entities when some of these entities were not geoJSON.
* Fixed the 'year' function not working on interval properties.


# Release Version 1.6
Version 1.6 was released on 2018-05-09.

**New Features**
* User-defined-ids. FROST-Server can not be configured to allow the user to specify the id of created enitites.
  The new setting `persistence.idGenerationMode` has three allowed values:
  * `ServerGeneratedOnly`: No client defined ids allowed, database generates ids.
  * `ServerAndClientGenerated`: Both, server and client generated ids, are allowed.
  * `ClientGeneratedOnly`: Client has to provide @iot.id to create entities.

  Thanks to Marcel Köpke for the patch.
* Improved time handling in queries. FROST-Server can now calculate with times:

    ```/Observations?$filter=phenomenonTime gt now() sub duration'P1D' mul Datastream/properties/days```

* Separated the MQTT and HTTP parts of the server.
  The MQTT and HTTP parts of the server are now separated in to stand-alone programs:
  * FROST-Server.HTTP: contains a web-app handling the HTTP part of the server.
  * FROST-Server.MQTT: contains a java application handling the MQTT part of the server.
  * FROST-Server.MQTTP: contains a web-app combining HTTP and MQTT, like it was before.

  There can be multiple MQTT and HTTP instances using the same database, to allow for horizontal
  scaling on a cloud infrastructure. The instances communicate over a pluggable message bus.
* There are now three docker images:
  * The stand-alone HTTP package: fraunhoferiosb/frost-server-http.
  * The stand-alone MQTT package: fraunhoferiosb/frost-server-mqtt.
  * The all-in-one package: fraunhoferiosb/frost-server.

  An example configuration for docker-compose can be found as docker-compose-separated.yaml,
  that shows how the HTTP and MQTT packages can be started separately, with an MQTT message bus
  for communication between the HTTP and MQTT instances.
* All configuration parameters can now be overridden using environment variables.

**Bugfixes**
* Fixed service prefix in default config file.
* Fixed Tomcat breaking selfLinks for ids that are URLs.
* Fixed $select not working for @iot.id, in MQTT.
* Fixed #48: creation in Observations in MultiDatastreams using DataArray formatting fails.


# Release Version 1.5
Version 1.5 was released on 2018-02-15.

**New Features**
* We have a name: FROST-Server
* Implemented the Batch-processing extension.

**Bugfixes**
* Fixed that the Docker image was built every time. Build it using `mvn dockerfile:build -pl SensorThingsServer`


# Release Version 1.4
Version 1.4 was released on 2018-02-07.

**New Features**
* Available through dockerhub: `docker pull fraunhoferiosb/sensorthingsserver`

**Bugfixes**
* Fixed deep insert of MultiDatastreams.
* Fixed building with Java 9.
* Fixed EntityType initialisation sometimes failing.


# Release Version 1.3
Version 1.3 was released on 2018-01-22.

**New Features**
* Added new backends, using PostgreSQL with String and UUID columns for entity ids.
* Improved support for Wildfly.

**Bugfixes**
* Improved memory use when fetching large Observations with a high $top.


# Release Version 1.2
Version 1.2 was released on 2017-12-04.

**New Features**
* Added support for ISO8601 Interval formats in the form of [instant]/[duration] and [duration]/[instant]. For example: 2015-10-14T01:01:01.000+02:00/P1D.
* Added a json properties field to (Multi)Datastream, FoI, Location, ObservedProperty and Sensor.
* Added setting to limit data site of responses. In case of Observations with large results, or Things with large properties, this will reduce the $top when the max size is reached.

**Bugfixes**
* Fixed only application/vnd.geo+json being recognised as GeoJSON, but not application/geo+json.
* Fixed GIS-filters on FeatureOfInterest/feature not working.


# Release Version 1.1
Version 1.1 was released on 2017-10-05.

**New Features**
* Implemented MultiDatastream.
* Added full support for filtering and odering on json fields.
* Added Docker support.
* Explicitly set the SRIDs from 0 to 4326 for PostgreSQL databases.
* Added support for direct filtering on boolean fields.
* Separated options for MQTT address to bind and internal address to connect to.

**Bugfixes**
* Fixed result not always being correctly saved when updating observations.
* Fixed MQTT not working on MultiDatastream subscriptions.
* Fixed single quotes in string literals.
* Fixed filtering on json Observation/result values.
* Fixed crs of inserted geoJson.
* Fixed update on MultiDatastream.
* Fixed #8: Only keep one subscription per topic, with a count of how many clients use it.
* Fixed inserting Observations using MultiDatastreams(x)/Observations
* Fixed #5: MultiDatastream(x)/Observations did not work.
* Fixed MQTT subscriptions on navigation links returning too much.
* Fixed incorrect /$value response for time instance and time interval properties.



# Release Version 1.0
Version 1.0 was released on 2016-11-03

