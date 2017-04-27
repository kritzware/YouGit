# DOWNLOADS
- **Windows:** [YouGit-win32-x64](https://www.dropbox.com/s/vl6pjua33dyer1r/YouGit-win32-x64.rar?dl=0)
- **MacOS:** Built not working currently

# INSTALLATION INSTRUCTIONS 
#### (If compiled versions don't work)
1. [Install Node.js v6.9.0 or less (cannot be higher version as nodegit is incompatible](https://nodejs.org/dist/v6.9.0/)
2. Clone the repository and cd to `yougit/yougit-js/`
3. Run the command `npm install` to download the required dependencies
4. Run the command `npm rebuild-mac` or `npm rebuild` for Windows (compiles packages, this may take some time)
5. Finally, run the command `npm run start` to launch the application!

Optional: `npm run test` to run the unit tests

## Java Instructions (deprecated)

### Setup
1. Download and Install IntelliJ Java IDE: [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)
2. Clone the repository with ```git clone https://github.com/kritzware/software-project.git```
3. Launch IntelliJ and open the downloaded project

### Notes
Before starting work make sure you pull the latest version from Github. Navigate to the ```software-project/``` folder in your command line and use the command ```git pull``` (If that doesn't work, try ```git pull origin master```)

### Pushing to the repository
1. Implement/add your feature
2. Write a unit test for your newly added code (If applicable)
3. Navigate to the ```software-project/``` folder and use the following commands
4. ```git add .``` --> Adds all your changes
5. ```git commit -m "Your commit message"``` --> Commit the changes with a message
6. ```git push``` --> Push your commits to GitHub

### Adding Libraries
1. Open the project in IntelliJ IDE
2. Right click on software-project (root folder) on the left sidebar
3. Click 'Open Module Settings' > 'Modules' (Project Settings) > 'Dependencies'
4. Click the green plus button > 'JARs or directories'
5. Navigate to software-project/.idea/libraries and select all 6 .jar files
```
- hamcrest-core-1.3.jar
- jsch-0.1.54.jar
- junit-4.12.jar
- org.eclipse.jgit-4.6.0.201612231935-r.jar
- slf4j-api-1.7.22.jar
- slf4j-simple-1.7.22.jar
```
6. Once you've added all the jars, click Apply and then OK to close
7. The libraries should be correctly imported now and the program should build succesfully
