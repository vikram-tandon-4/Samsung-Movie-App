# Samsung-Movie-App
Android Intern Mobile Experience Lab Coding challenge

Integrated Development Environment (IDE): Android Studio
Programming Language: JAVA
Platform: Android(4.2 and up)


● The project can be imported and run on android studio.
● The apk can be installed directly on an Android Device.


Screens:


SPLASH SCREEN:
● The Splash screen has the Samsung logo.
● All network API calls are done on this screen
● The data for upcoming movies and now playing movies is retrieved using discover which
is added in version 4 of themoviedb.
● Both these data are retrieved using the same API with different date parameters.
● For now playing movies the date range is 15 days prior from the current date.
● For upcoming movies the date range is 45 days ahead of the current date.
● Both these ranges can be changed from AppConstants file.
● Genres are cached in Shared Preferences once when the app is up for the first time.
HOME SCREEN:
● The Home Screen has 2 tabs.
● Both of them are populated from the data sent from the Splash screen.
● The Images are on the left.
● The movie title, popularity and Genre are there in each row item.
● A smooth animation is there when the list is scrolled.



Persistence:
1. The genre is cached once and kept in shared preferences.
2. The genere id is used to get names which is done in Adapter of the recyclerview.


NOTE:
- Please Add Authorization Token and API key in AppsConstant file(Inside Utils
Package) before running the code.
