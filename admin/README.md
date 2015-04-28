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





## Update the index.html file

### Add css

    <link rel="stylesheet" href="bower_components/FroalaWysiwygEditor/css/froala_editor.min.css" />
    <link rel="stylesheet" href="bower_components/FroalaWysiwygEditor/css/froala_content.min.css" />
    <link rel="stylesheet" href="bower_components/FroalaWysiwygEditor/css/froala_style.min.css" />
    <link rel="stylesheet" href="bower_components/FroalaWysiwygEditor/css/font-awesome.min.css" />

### Add libraries

    <script src="bower_components/FroalaWysiwygEditor/js/froala_editor.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/block_styles.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/colors.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/media_manager.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/tables.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/video.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/font_family.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/font_size.min.js"></script>
    <script src="bower_components/FroalaWysiwygEditor/js/plugins/char_counter.min.js"></script>
    <script src="bower_components/angular-froala/src/angular-froala.js"></script>

## Update the scripts/app/app.js
 
From
    
    angular.module('adminApp', ['LocalStorageModule', 'tmh.dynamicLocale',
        'ngResource', 'ui.router', 'ngCookies', 'pascalprecht.translate', 'ngCacheBuster', 'infinite-scroll'])

To

    angular.module('adminApp', ['LocalStorageModule', 'tmh.dynamicLocale',
        'ngResource', 'ui.router', 'ngCookies', 'pascalprecht.translate', 'ngCacheBuster', 'infinite-scroll', 'ngSanitize', 'froala'])
    
        .value('froalaConfig', {
            inlineMode: false,
            placeholder: 'Enter Text Here'
        })


## Update the sripts/app/entities/hotel/hotels.html

Replace at the line 40

    <input type="text" class="form-control" name="description"
           ng-model="hotel.description">

by

    <textarea froala ng-model="hotel.description"></textarea>
    
    
Replace at the line 162

    <td>{{hotel.description}}</td>
    
by

    <td><div ng-bind-html="hotel.description"></div></td>
    
    
    
## Create a new hotel

http://www.expedia.ca/Montreal-Hotels-Ritz-Carlton.h8767.Hotel-Information
    
    
    
 
