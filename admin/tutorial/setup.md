TUTORIAL FOR THE ADMIN
==========================

# Create the project

    mkdir admin
    cd admin
    yo jhipster

1. 1/13 - admin
1. 2/13 - Default
1. 3/13 - session based
1. 4/13 - Default
1. 5/13 - Default
1. 6/13 - Default
1. 7/13 - H2
1. 8/13 - Default
1. 9/13 - Default
1. 10/13 - Default
1. 11/13 - Default
1. 12/13 - Default
1. 13/13 - Default

# Entities creation

## Hotel Entity

    yo jhipster:entity hotel

1. field: name String required
1. field: description String
1. field: address String 
1. field: city String 
1. field: postalCode String 
1. field: province String 
1. field: country String 
1. relationship: one-to-many room

## Increase the size of the hotel's description

The size of the description field is too short. Let's increase this field to 2048

    mcetech/admin/src/main/resources/config/liquibase/changelog/[NEW DATE]_added_entity_Hotel.xml

## Room Entity

    yo jhipster:entity room
    
1. field: name String required
1. relationship: many-to-one hotel
    
# Change the description field to support HTML

## Install angular froala library

    bower install angular-froala --save

Select option 2. 

## Open the bower.json

Check if the new library has been added. 

    "angular-froala": "~1.2.6"

You can remove the ~ to force the version. If not the latest version will be used.

