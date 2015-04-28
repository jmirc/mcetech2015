
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
