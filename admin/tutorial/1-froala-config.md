
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

name: Ritz-Carlton
address: 1228 Sherbrooke Street West 
city: Montreal 
postalCode: H3G 1H6 
province: Quebec
country: Canada
description:

    <h3>Luxury Montreal hotel in Downtown Montreal, near Notre Dame Basilica</h3>
    <article data-type="read-more"><section>
    <h4>Location</h4>
    
    <p>Located in Downtown Montreal, this luxury hotel is within a 15-minute walk of Reid Wilson House, Bell Centre, and Christ Church Cathedral. Percival Molson Memorial Stadium and Mount Royal Park are also within 1 mi (2 km).</p>
    
    <h4>Hotel Features</h4>
    
    <p>This smoke-free hotel features a restaurant, an indoor pool, and a 24-hour fitness center. WiFi in public areas is free. Additionally, a bar/lounge, a rooftop terrace, and a 24-hour business center are onsite.</p>
    
    <h4>Room Amenities</h4>
    
    <p>All 129 rooms boast deep soaking tubs and offer free WiFi and 24-hour room service. Guests will also find LCD TVs, minibars, and premium bedding.</p>
    </section></article>
