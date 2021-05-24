# ShoeStore

The app contains single Activity and 5 fragments:
Login
Welcome
Instructions screen
Listing screen
Detail screens

-it's the first Project in the nano degree program,it's designed to ensure that the student can impelement Android Navigation and uses Navigation
Components correctly as navigation drawer,handling the up button in addition to animations for fragment transitions

-also data binding used in all project layout binding the layouts with its corresponding data binding classes,avoiding using findViewById removing
unnecessary over head

-listing here done using addView function to add views(shoe items) vertically to the linear layout, this was the tricky part as i used to implement recycler But here

the requirements doesnt include using recycler(as i got it its a more simple way to list small and known size lists of items)

- the project follows MVVM  Architecture Pattern

-Getting Started : 

-the login screen is a dummy one simulating login(Firebase Authentication implemented in a later project),just click on login or register 

will carry you to the next welcome fragment with a greeting message including your email(if you entered it in the first fragment) or a default message will be deployed if 

no email is entered

-click on the instructions button and follow it

- there is an overflow menu in the listing fragment will log the user out poping all the backstack till the login fragment

-Toasts are implemented to handle errors and edge cases
