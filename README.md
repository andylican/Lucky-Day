# Lucky-Day
A mobile app made using android studio that challenges the user to coin toss as many heads as possible in a row. 
The user's score is inserted into a MySQL database and the top 10 scores are queried to be displayed as the leaderboard.

How it works
The app consists of 2 activities, with the main one being the home page and the other one being the coin toss simulator.
The main activity allows the user to type in their name and start coin tossing and it also has a button that displays the current leaderboard.
This is done using HttpClient, which sends a GET request to a php file hosted on a local WAMP server, which then queries the database, sorting the results, and returning the top 10.
The coin toss simulat keeps on running until the user flips tails, where HttpClient is used to send a POST request to another php file on the local WAMP server, which then inserts the score and username into the MySQL database.

How to run it
Set up a local WAMP server and a MySQL database using phpMyAdmin, and then put the 2 .php files into the server's root directory. Then open the app in android studio and run it on an emulator or connect your mobile device to it.
