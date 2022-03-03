# CS1822RoboticsProjectNew
## About this repository
This repository is created for Robotics Group 2 to upload and download files in order to facilitate easier collaboration between group members.
All group members have migrated from the old repository to this new repository and the old repository has since been archived.
## Organisation of the repository
Files will be subdivided into directories within the repository to facilitate better organisation and allow for files to be found more easily. When <b>creating new directories</b>,
please create a new lipsum (<i>lorem ipsum</i>) text file to populate the empty folder until files have been organised. After moving files to directories, the lipsum file can be deleted.
## Credits in files
When creating files for the final project, please add an LCD.drawString() method at 0, 1 below the version numbering which contains the name of the file and the author's name, as follows: <b>LCD.drawString("Example file name by Author", 0, 1)</b>.
## Facilitating version control
### Making changes to files
In each file uploaded to the repository, there should be a printVersion() method. The first iteration of each program will be denoted as V1 on the repository. 
When making changes to the file, please update this method by incrementing the major number if making major changes, and adding/incrementing a minor number if making minor changes.
### Making changes to SplashScreen.java
In the SplashScreen.java file (already uploaded to the repository), there is both a printVersion() method and version numbering in the main method. When updating the <b>entire</b> robot, the version number should be incremented in the same manner as aforementioned, and when editing each file individually, the version numbering in printVersion() should be updated.

