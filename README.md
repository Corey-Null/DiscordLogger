DiscordLogger
=====
This is a self bot which logs all discord DM messages into a log folder with each DM having their own text file.
<br>
These text files are ordered by ID which you can get by running the command `$id` in the direct message.
<br>
To run this you just need to run `java -jar <filename>.jar` in console and it should just run through the commandline.
<br>
This program uses <a href="https://github.com/DV8FromTheWorld/JDA">JDA</a> and <a href="https://www.slf4j.org/">SL4J</a> to run, these are all shaded with maven.

Getting started
------
For the bot to run you need a text file named "info.txt" in the folder with the .jar file.
<br>
The first line should contain the token, which I'll explain how to find.
<br>
The second line should contain `true` or `false` stating wether you want the console to display your token.

Getting your token
-------
In browser or desktop Discord, type Ctrl-Shift-I. Go to the Application section, and and go to Storage > LocalStorage > discordapp.com. 
<br>
Find the token row, and your token will be the value in quotes.
<br>
!!!Do not share this token with anyone!!!
<br>
This token provides complete access to your Discord account, so never share it!