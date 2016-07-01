# binary-duplicates
This program takes in a directory and checks whether that directory has files that contain duplicate binary data.The objective of the program is to report these duplicate files in a log file.

<strong>INFORMATION:</strong><br>
Certain files containing binary data are stored in the following manner:

file_0001.dat<br>
file_0002.dat<br>
file_0003.dat<br>
....<br>
file_000(i).dat where i > 0 

The directory holding these files contains duplicates resulting in disk space issues.
In other words there exist file_000(i) such that file_000(i) contains the same binary data as, say for example, some file_000(i+1) in the same directory.

This program analyses such a directory of files, detects these duplicates and writes them to a log file (log.txt), which can be found in the working directory after execution.


<strong>BUILD:</strong><br>
Code in Java8 completed in Netbeans IDE.
Project may be imported into IDE manually or via Git<br>
Information on using Git support in Netbeans IDE: <a href="https://netbeans.org/kb/docs/ide/git.html" target="_blank">Documentation</a> and <a href="https://www.youtube.com/watch?v=mzzAUEFS4vs" target="_blank">Demonstration</a><br>
Alternatively use typical Java compilation, that is:<br>

<strong>Navigate to required directory.</strong><br>
<strong>javac BinaryAnalyzer.java main.java</strong><br>
<strong>java main</strong>

<strong>IMPROVEMENTS:</strong><br>
<i>1. File naming pattern should be irrelevant concerning construction of log file.</i> i.e The program should be able to print out the names of the files even if the files have miscellaneous names with no associated pattern<br>
<i>2. Delete one of the duplicates out of each pair.</i><br>
<i>3. Extend the Formatter class and @overide methods to remove class name from log file.</i> i.e Create a custom Formatter. Note: Currently this is required if you want to prevent the package name from being displayed in the log file as SimpleFormatter(Java 8) persists the package and method name in the log file. Ofcourse assuming you are using java.util.logging.Logger for logging purposes.<br>

Fork to contribute to suggested improvements.

