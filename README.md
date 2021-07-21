# demo

Demo project built by Bedrock.


## Getting Started
The following instructions should be used whenever you first start with a fresh download of the project.

- Use `git` to clone the repository on your local machine.  
- With _IntelliJ_, select "File" > "Open..." from the menu, navigate to your local clone directory, then click "Open."
- Open the *"Gradle projects"* tool window, then click "Refresh all Gradle projects."
- Refreshing projects may take a little while to complete your initial build, however once it is done, you're all set!

Your IDE is now setup.  You should be able to execute any available Run/Debug Configurations.


## Build
Builds are performed using [Gradle](https://gradle.org/getting-started-gradle/#toggle-id-1).  From Terminal, execute 
`./gradlew`; by default, this will build (imagine that!) all available modules, creating artifacts for deploying/running 
your applications.

As is the norm with _Gradle_, use `./gradlew tasks` to list all available tasks.  The following describes the more common
tasks:
- **build** - compiles and tests all modules
- **dockerBuildImage** - creates/updates the deployable _Docker_ image for all modules


## Sample Workflow Type
```json
PUT /workflow
{
  "settings": {
    "analysis": {
      "normalizer": {
        "lowercase_normalizer": {
          "type": "custom",
          "char_filter": [],
          "filter": ["lowercase"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "domainUuid": { "type": "text" },
      "sourceType": { "type": "text" },
      "serviceOrderDetails": { 
        "properties": {
          "type":  { "type": "text" },
          "sourceNumber": { "type": "text" },
          "generalComments": { "type": "text" },
          "serviceComments": { "type": "text" },
          "customerName": { "type": "text" },
          "customerNameKeyword": { "type": "keyword", "normalizer": "lowercase_normalizer" },
          "contactName": { "type": "text" },
          "phoneNumber": { "type": "text" },
          "address": { 
            "properties": {
              "street": { "type": "text" },
              "city":  { "type": "text" },
              "state": { "type": "text" },
              "zipcode": { "type": "text" }
            }
          }
        }
      },
      "tasks": { 
        "properties": {
          "id":  { "type": "text" },
          "description": { "type": "text" },
          "status": { "type": "text" },
          "critical": { "type": "boolean" },
          "estimatedHours": { "type": "float" },
          "neededDate": { "type": "date" },
          "priority": { "type": "text" },
          "workGroup": { "type": "text" },
          "locked": { "type": "boolean"},
          "remarks": { "type": "text" },
          "assignments": { 
            "properties": {
              "id": { "type": "text" },
              "assigneeName": { "type": "text" },
              "scheduledStartDateTime": { "type": "date" },
              "scheduledHours": { "type": "float" },
              "actualStartDateTime": { "type": "date" },
              "actualEndDateTime": { "type": "date" },
              "assigneeKey": {
                "properties": {
                  "id": { "type": "text" },
                  "type": { "type": "text" }
                }
              }
            }
          }
        }
      }
    }
  }
}
```

## Sample Documents to be used with POST workflow/_doc
```json
{
  "domainUuid" : "bead5950-7c3f-46cf-b271-cf2dbdbce2bf",
  "sourceType" : "SO",
  "serviceOrderDetails" : [
	{
	  "type" : "SO",
	  "sourceNumber" : "999888777",
	  "generalComments" : "generally a comment",
	  "customerName" : "Pam Customerman",
	  "customerNameKeyword" : "Pam Customerman",
	  "contactName" : "Jim Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "road path",
		"city" : "town village",
		"state" : "MO"
	  }
	}
  ]
}

{
  "domainUuid" : "bead5950-7c3f-46cf-b271-cf2dbdbce2bf",
  "sourceType" : "SO",
  "serviceOrderDetails" : [
	{
	  "type" : "SO",
	  "sourceNumber" : "999888777",
	  "generalComments" : "generally a comment",
	  "customerName" : "Jim Customerman",
	  "customerNameKeyword" : "Jim Customerman",
	  "contactName" : "Pam Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "road path",
		"city" : "town village",
		"state" : "MO"
	  }
	},
	{
	  "type" : "SO",
	  "sourceNumber" : "999888778",
	  "generalComments" : "generally a comment",
	  "serviceComments" : "serves as a service comment",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "alley line",
		"state" : "MO",
		"zipcode" : "77777"
	  }
	},
	{
	  "type" : "SO",
	  "sourceNumber" : "999888779",
	  "generalComments" : "im for source 999888779",
	  "serviceComments" : "im for service 999888779",
	  "customerName" : "999888779 Customerman",
	  "customerNameKeyword" : "999888779 Customerman",
	  "contactName" : "999888779 Contactman",
	  "phoneNumber" : "999-888-7790",
	  "address" : {
		"street" : "999888779 line",
		"city" : "castle 999888779",
		"state" : "MO",
		"zipcode" : "999888779"
	  }
	}
  ]
}

{
  "domainUuid" : "bead5950-7c3f-46cf-b271-cf2dbdbce2bf",
  "sourceType" : "SO",
  "serviceOrderDetails" : [
	{
	  "type" : "SO",
	  "sourceNumber" : "999888777",
	  "generalComments" : "generally a comment",
	  "serviceComments" : "serves as a service comment",
	  "customerName" : "Jim Customerman",
	  "customerNameKeyword" : "Jim Customerman",
	  "contactName" : "Pam Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "road path",
		"city" : "town village",
		"state" : "MO",
		"zipcode" : "67890"
	  }
	},
	{
	  "type" : "SO",
	  "sourceNumber" : "999888778",
	  "generalComments" : "generally a comment",
	  "serviceComments" : "serves as a service comment",
	  "customerName" : "Dwight Customerman",
	  "customerNameKeyword" : "Dwight Customerman",
	  "contactName" : "Micheal Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "alley line",
		"city" : "castle burg",
		"state" : "MO",
		"zipcode" : "77777"
	  }
	}
  ]
}

{
  "domainUuid" : "bead5950-7c3f-46cf-b271-cf2dbdbce2bf",
  "sourceType" : "TT",
  "serviceOrderDetails" : [
	{
	  "type" : "TT",
	  "sourceNumber" : "999888777",
	  "generalComments" : "generally a comment",
	  "customerName" : "Daryl Customerman",
	  "customerNameKeyword" : "Daryl Customerman",
	  "contactName" : "Angela Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "road path",
		"city" : "town village",
		"state" : "MO"
	  }
	}
  ],
  "tasks" : [
	{
	  "id" : "123446",
	  "description" : "Meter Install Follow Up",
	  "status" : "CLOSED",
	  "eventReasonCode" : "COST",
	  "eventReasonDescription" : "JOB TOO EXPENSIVE",
	  "critical" : false,
	  "estimatedHours" : 2,
	  "neededDate" : 1919592000000,
	  "priority" : "LOW",
	  "workGroup" : "METER_TECHS",
	  "locked" : false,
	  "remarks" : "Perform follow up within 24 hours",
	  "assignments" : [ ]
	}
  ]
}

{
  "domainUuid" : "bead5950-7c3f-46cf-b271-cf2dbdbce2bf",
  "sourceType" : "SO",
  "serviceOrderDetails" : [
	{
	  "type" : "SO",
	  "sourceNumber" : "999888777",
	  "generalComments" : "generally a comment",
	  "customerName" : "Ryan Customerman",
	  "customerNameKeyword" : "Ryan Customerman",
	  "contactName" : "Tobi Contactman",
	  "phoneNumber" : "123-456-7890",
	  "address" : {
		"street" : "road path",
		"city" : "town village",
		"state" : "MO"
	  }
	}
  ],
  "tasks" : [
	{
	  "id" : "123445",
	  "description" : "Meter Install Follow Up",
	  "status" : "DELAY",
	  "eventReasonCode" : "COST",
	  "eventReasonDescription" : "JOB TOO EXPENSIVE",
	  "critical" : false,
	  "estimatedHours" : 2,
	  "neededDate" : 1604059200000,
	  "priority" : "HIGH",
	  "workGroup" : "METER_TECHS",
	  "locked" : false,
	  "remarks" : "Perform follow up within 24 hours",
	  "assignments" : [ ]
	}
  ]
}
}
```

