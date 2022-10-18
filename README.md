# Baseball Manager

This is a mock project for CSC325 : Advanced Object Oriented Programming

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
- `AddGameDialog.java`
- `FancyFrame.java`
- `FAQDialog.java`
- `InformationPanel.java`
- `Loading.java`
- `MainPanelInfoBar.java`
- `Navbar.java`
- `PastGamesDialog.java`
- `RecordGameDialog.java`
- `ScheduleDialog.java`
- `StartupDialog.java`
- `UpcomingGamesDialog.java`

#### Graphics and Animation
The program opens with a loading bar, to allow the program to establish connection to the SQL server. Beyond this, graphics are minimal.

#### Networking
The user can click a prompt in the navbar to be directed to the GitHub page for this project, serving as some form of 'documentation.'

#### Databases
This project relies heavily on databases, and uses them to process and store all data, such as players and games.

#### Lambda Expressions
I used lambda expressions for any `ActionListener` objects. This was not stated in the requirements for the project, but I did so anyway.
