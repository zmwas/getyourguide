# getyourguide

Problem Solving

- To decide on how to implement the solution I broke the problem statements into separate tasks.
- The tasks were:
  - fetching the list of reviews
  - designing a recyclerview to display the reviews,
  - implementing logic to enable continuous fetching of reviews
  - designing a view to display a single review.
- With the tasks fleshed out I decided on what the best technologies to use were given the tasks.


Language:

- Kotlin

Libraries used:

- RxJava2 for handling streams of data and multithreading

- Android architecture components (Viewmodel, Livedata, Paging)

- Dagger2 for dependency injection

- Picasso to display images

- Retrofit for fetching api results

Architecture:

- MVVM is used to separate concerns and enable testing of implementation details in the view model.
- The activity calls the viewmodel to fetch a livedata containing the  reviews.
- To enable fetching of paginated results I used the jetpack paging library to fetched paged results
and pass them through to the livedata in the viewmodel.



Testing
Since the activities are free of any implementation details, I wrote tests for the viewmodel using junit and mockito.
To cater for the RxJava Single, I used a rule to mock schedulers so that I would be able to subscribe on ```Trampoline.io```
I used Mockito to mock the api service to return a Single with the test data.
To facilitate waiting I used a test util with a latch count down timer when observing. This facilitates mocking a pagedlist livedata
and getting values.

