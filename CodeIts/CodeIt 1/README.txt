Arun Hari Anand
April 1, 2018
README CodeIt 1
The three edge cases were dealt with as follows:
For Edge Case 1, the issue was solved by implementing the activity lifecycle as illustrated in class. I first implemented onSaveInstanceState and then used the saved information in onCreate to "remember" the fact the user has cheated.
For Edge Case 2, the issue was resolved by simply modifying the onSaveInstanceState method to save whether the user has previously cheated on a question. This was then used in onCreate to print out the appropriate message.
For Edge Case 3, the issue was resolved by adding an instance variable to the Question class that keeps track of whether the user has previously cheated in the past. This is set to false by default and can be irreversibly changed to true if the user cheats on a question. It is then used in the onCreate method in the QuizActivity class to ensure that the appropriate message is displayed.

