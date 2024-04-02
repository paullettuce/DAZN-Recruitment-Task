# DAZN-Recruitment-Task
The "DAZN Recruitment Task" is an Android app designed with a bottom navigation interface, offering users access to event listings and a tomorrow's schedule that auto-refreshes every 30 seconds. Emphasizing clean architecture it enables users to seamlessly watch associated videos.

## Demo
https://github.com/paullettuce/DAZN-Recruitment-Task/assets/64195546/7d1375bb-4b39-4b81-8e56-7fbfae878114



## Quality Assesment
### 1. Requirements and design:
* General Requirements:
    * The app must have two tabs implemented using bottom navigation, one for events and the other for the schedule.
    * The Events tab should display a list of events available to be watched.
    * The Schedule tab should display a schedule for tomorrow only and auto-refresh every 30 seconds.
    * The app should fetch data from the provided APIs: https://us-central1-dazn-sandbox.cloudfunctions.net/getEvents for events and https://us-central1-dazn-sandbox.cloudfunctions.net/getSchedule for the schedule.
    * Events and schedule items should be ordered by date in ascending order.
    * Thumbnail images for schedule items should be retrieved from the listings feed.
* Events Screen:
    * Display a list of events ordered by date in ascending order.
    * When an event item is tapped, the app should navigate to the Playback Screen and play the appropriate video.
* Schedule Screen:
    * Display a schedule for tomorrow only.
    * Auto-refresh the schedule every 30 seconds.
    * Update the state of the schedule list without losing its scroll position and without flickering.
* Playback Screen:
    * Implement a Playback Screen to play the video in the default player.
    * Play the video provided in the selected event.
* Unit Tests:
    * Write unit tests to cover relevant parts of the application.
* Code Quality and Architecture:
    * Ensure clean code following best practices.
    * Apply object-oriented programming principles and SOLID principles.
    * Consider basic memory management practices.
    * Build with app growth in mind, predicting which features may be developed.
* Testing Practices:
    * Implement QA practices such as requirement analysis, test planning, test case design, test execution, defect reporting, and regression testing.
* REST APIs Usage Practices:
    * Follow best practices for consuming REST APIs, including error handling, proper use of HTTP methods, and data parsing.
* Handling Activity Lifecycle:
    * Properly handle the Android Activity lifecycle to ensure efficient resource management and smooth user experience.
* Build Requirements:
    * Ensure the project can be checked out and compiled out of the box on both Mac and Windows machines with relevant default IDEs installed.
    * Utilize any libraries or frameworks desired for production, ensuring they are included and properly integrated into the project.
### 2. Test Planning: 
Develop a comprehensive test plan using various aspects of testing, including functional, performance, compatibility, UI/UX, UAT (kind of).
### 3. Setup Testing Environment / Compatibility Tests:
Overall I've concluded tests on emulators with Android 5, 6, 10. On real device Pixel 7 Android 14. I've also had my friends test it on few Samsungs and Pixels running Android 11-14. No problems so far.\
For professional app though - this would be far from complete compatibility tests. One must check all Android versions and devices. \
Also taking into consideration the character of real DAZN app, tests would have to include various device types, such as tablets and TVs.
### 4. Functional tests results:
The app meets all functional requirements as follows: The bottom navigation incorporates two tabs, one for events and the other for the schedule, with event listings displayed in ascending order.\
The Schedule tab updates tomorrow's schedule every 30 seconds and pulls data from the specified APIs. The Schedule Screen refreshes without disrupting scroll position or causing flickering.\
Thumbnails for schedule items are sourced correctly and backed with a placeholder.\
Tapping an event item navigates to the Playback Screen, playing the associated video.\
### 5. Performance tests results:
Tests reveal that the app functions smoothly and without any noticeable lag, ensuring a seamless user experience. Every aspect of the app, from loading times to screen transitions, operates efficiently.\
However, for a professional app, it is a must to dig deeper into performance metrics. Utilizing IDE profiling tools, such as Android Profiler, enables a comprehensive analysis of network, GPU, and CPU performance aspects.\
By scrutinizing these key components, any potential bottlenecks or inefficiencies can be identified and addressed proactively, further enhancing the app's overall performance and reliability.
### 6. UI/UX tests results:
ISSUES: On Android API 21 and 23 it doesn't apply color change to selected navigation tab.
Apart from that the app operates smoothly and boasts a well-designed interface. User needs are effectively addressed, and the app maintains a user-friendly approach throughout. Error messages are informative and straightforward, aiding users in resolving issues. Additionally, animations are tastefully utilized to enhance user engagement. Overall, the UI/UX testing indicates that the app offers a polished and user-centric experience.
### 7. UAT (kind of) tests results:
I have shared the APK with a handful of friends, and they found it visually appealing and very smooth. They even said they would embrace it as a part of their digital repertoire if it were real and more finely attuned to their specific sporting preferences:)
### 8. Summary and comment:
In summary, the UI/UX of the app is smooth and graphically appealing. All functional requirements are met. In addition I took the initiative to implement features such as automatic refresh on every screen visit and a new items button after data updates.\
While I'm aware of minor UI/UX issues, time constraints prevent me from addressing them. There may be additional issues, but it would require thorough testing and a significant amount of time.\
I dedicated considerable amount of energy into the events tabs and lists and the player functionality may appear undercooked as a result. From a time perspective, I slightly regret that. The imbalance is my mistake done in development planning phase.\
While one week is sufficient to create a solid, good-looking app with all functionalities, building a cutting-edge app would require much more time and resources.\
Ultimately, I really enjoyed doing that task, can't really remember when was the last time I got so hooked on doing something like that. There are reasons behind that, which I will disclose on the interview.
