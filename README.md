# Baseball Manager

This is a mock project for CSC325 : Advanced Object Oriented Programming.

## IMPORTANT!
You *MUST* configure the Java classpath to run the `mssql-jdbc-11.2.0.jre18.jar` file. I used VSCode to create this project, and did not use Maven or Gradle, so these dependencies are not wrapped into the project. As such, you *must* configure them yourself. At the barest minimum, the `mssql-jdbc-11.2.0.jre18.jar` needs to be configured. These can be found in the `lib` folder in the source code. Furthermore, you *might* need to install the driver, and add `mssql-jdbc_auth-11.2.0.x64.dll` file to the binary section of your Java JDK.

### Adding The Necessary Driver

1) First, open the Java JDK installed on your machine. This is commonly installed under `C:\Program Files\Java`. From there, open the JDK files, and then open the `bin` folder. Paste the `mssql-jdbc_auth-11.2.0.x64.dll` file into this folder. (Note: If your JDK is not in the above specified path, you must locate it on your own.)

2) Second, open the source code for MME in your IDE of choice, and configure the Java Classpath to include the `mssql-jdbc-11.2.0.jre18.jar` found there. Optionally, you can include the `hamcrest-core-1.4.jar` and/or `junit-4.13.2.jar` files. This is not necessary, as those were for unit tests that are not included in the `master` branch of code.

## Proposal:

### Big Picture: 
An all in one management suite for baseball teams.

### Feature Accomplishment List

This project game with a list of topics from our course that I needed to cover in this project. This section will explain, in depth, how I accomplished this task.

#### Inheritance
Several of my graphics components inherit from, or extend the functionality of, several existing `Swing` components, such as `JFrame, JPanel, JDialog, JMenuBar` etc. 

#### Interfaces
Interfaces are used *extensively* throughout this project. For instance, I have a pair of custom interfaces, `ISubscriber` and `ISubscribable` that create an `Obvserver-Observable` relationship. Throughout several points in the project, several GUI components implement `ISubscriber` to receive updates from `ISubscribable` objects. Additionally, a list of `ISubscribers` is kept throughout program duration.

#### GUI Components
This is self explanatory. I made several custom GUI components, a full list of which is below.

##### GUI Components List
- `AddPlayerDialog.java`
- `FancyFrame.java`
- `FAQDialog.java`
- `InformationPanel.java`
- `Loading.java`
- `MainPanelInfoBar.java`
- `Navbar.java`
- `StartupDialog.java`

#### Graphics and Animation
The program opens with a loading bar, to allow the program to establish connection to the SQL server. Beyond this, graphics are minimal.

#### Networking
The user can click a prompt in the navbar to be directed to the GitHub page for this project, serving as some form of 'documentation.'

#### Databases
This project relies heavily on databases, and uses them to process and store all data, such as players and games.

#### Lambda Expressions
I used lambda expressions for any `ActionListener` objects. This was not stated in the requirements for the project, but I did so anyway.
