---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## **Introduction**

CAP5Buddy helps NUS SoC students to keep track of their module details efficiently. It helps them centralize key 
module details and follows their study progress through a Command Line Interface (CLI) that allows efficient management 
of module details. CAP5Buddy also functions as a scheduling system, todo list and contact list.

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

**How the architecture components interact with each other**
![Structure of the Overall Product](images/ArchitectureDiagram.png)

### Overall components

This is the overall design of our product. As we are using **GUI to help to display the information** and mainly focuses on
using **CLI to take in the required commands**, thus the product consists of **6 main major components**. The product starts
from the Launcher classes, that initiates based on our pre-set settings and then activates the MainApp class
the will run the GUI with these settings. MainApp will also start the _brain_ and -muscles_ of the program, which are the Logic, Storage,
Model and Ui components.

The role of the **Logic** component is to act as the _brain_ of the program, where all the parsing of information will be done, and the
execution of the commands will be carried out.

The role of the **Storage** component is to represent the _memory_ of the program, where the storing and tracking of the different items happens.
These items are saving locally in a json file, which can be imported and exported easily.

The role of the **Model** component is to represent all the items and their behaviours. Contains all the item classes and their support classes.

The role of the **Ui** component is to handle all the User interface related instructions, which includes the loading of GUI components, the updating
of these components and displaying the changes.

## Module Tracker

### UI component
![Structure of the UserInterface Component](images/UiClassDiagram.png)

The job of the UI component is to be the _face_ of the product, which the user directly interacts with.
It is in charge of containing the logic that **breaks down and executes the user input**, and displaying the **GUI** of the
product.

It composes of a few main classes, that serves as the focal point of this component. Such classes are **UiPart**,
**MainWindow**, **UiManager** and the respective panel displays, **(XYZListPanel)**. The rest of the classes are supporting
classes to help make the GUI.

The MainWindow is what the user actually sees, which has a **CommandBox**, **XYZListPanel**, **ResultDisplay** and **StatusBar**. These
components are stacking on top of one another using **stackPane** to ensure a smooth looking GUI. The order of the components
are as follows, **CommandBox**, **ResultDisplay**, **XYZListPanel** and **StatusBar**.

The **CommandBox** is just a textField component where the user can enter the commands. Upon pressing *Enter*, extracting of the
text occurs and is sent to the logic to be parsed and executed.

Next, after the executing is completed, a **CommandResult** object returns and is then passed to the **ResultDisplay** for the
relevant information to be shown in this component. This is being displayed in a TextArea component.

Lastly, the **XYZListPanel** is in charge of displaying all the modules, contacts, etc that is the product is tracking.
Each of these items are being displayed in a *cell* under their respective **XYZCard**, which will be displayed in the *ListCell*
of the **XYZListPanel**.


**API** :

### Logic component

![LogicClassDiagram](images/LogicClassDiagram.png)

**API** : `Logic.java`

1. `Logic` uses the `ParserManager` class to create the respective classes: `ModuleListParser`, `ContactListParser`, `TodoListParser`,
   `GradeTrackerParser` and `SchedulerParser` which will parse the user command.
2. This results in a `Command` object which is executed by `LogicManager`.
3. The command execution can affect the Model (e.g. adding a module).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
help to the user.

![Structure of the Storage Component](images/ModelClassDiagram.png)

### Model component

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the data for these 3 types of list:
  * module tracker
  * contact list
  * todo list
* exposes an unmodifiable `ObservableList<T>` for all types of list as mentioned above which can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component facilitates the storage of CAP5BUDDY data in the hard drive. When the program attempts to save
data, the `Storage` component converts java data objects such as `ModuleList` and `ContactList` into a json format to store
at a specified file location. When the program is started, it will attempt to read existing user data and the `Storage`
component will be converting data in json format into java objects.

* can save `UserPref` objects in json format and read it back.
* can save the module list data in json format and read it back.
* can save the contact list data in json format and read it back.
* can save the todo list data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.
### Common classes

**API** : 

## Module List
![Structure of the Module List Component](images/ModuleListDiagram.png)

**Module package** : [`seedu.address.model.module`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/module)

* Module is a container class that stores :
  * Name of a module
  * Zoom link of a module
  * GradeTracker of a module
* GradeTracker is a container class that stores:
  * Grade for a module
  * Assignments for a module
  
#### ModuleList class
**ModuleList class** : [`ModuleList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/ModuleList.java)

* Wraps all data i.e. Modules at the module list level
* Stores Modules in memory
* Stores a UniqueModuleList
* Duplicate Modules are not allowed

## CAP Calculator

## Scheduler

## Contact List

![Structure of the Contact List Component](images/ContactListDiagram.png)

#### Contact class

**Contact package** : [`seedu.address.model.contact`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/contact)

* Contact is a container class that stores :
  * Name of a contact
  * Email of a contact
  * Telegram of a contact
#### ContactList class
**ContactList class** : [`ContactList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/ContactList.java)

* Wraps all data i.e. Contacts at the contact list level
* Stores Contacts in memory
* Stores a UniqueContactList
* Duplicate Contacts are not allowed

## Todo List

![Structure of the Todo List Component](images/TodoList/TodoListClassDiagram.png)

#### Task class

**Task package** : [`seedu.address.model.task`](https://github.com/AY2021S1-CS2103T-F12-3/tp/tree/master/src/main/java/seedu/address/model/task)

* Task is a container class that stores :
  * Name of a task
  * Tags of a task
  * Priority of a task
  * Date or deadline of a task
  * Status of a task<br/>
  Only name is compulsory when creating a new Task.

#### TodoList class

**TodoList class** : [`TodoList.java`](https://github.com/AY2021S1-CS2103T-F12-3/tp/blob/master/src/main/java/seedu/address/model/TodoList.java)

* Wraps all data i.e. Tasks at the Todo List level
* Stores Tasks in memory
* Stores a UniqueTodoList
* Duplicate Task objects are now allowed

TodoList will be explained more comprehensively in the [TodoList feature](#todolist-feature) Section

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Add Event feature
![Structure of the Add Event command](images/AddEventSequenceDiagram.png)
#### Proposed Implementation
The idea of this feature is to be able to allow the user to keep track of his/her current events that
will be happening. Events can be either a one time event like an exam for a particular module, or a recurring
event like a weekly tutorial class.

How we are currently implementing this feature is by following the same implementation as the AB3. We have an event
object under the Model package. Two classes called EventName and EventTime act as information containers to store
the respective data and help support the Event class.

We also make sure in the Logic package, there are personal sub-parsers for each of the existing Event
related commands, and an overall Parser known as SchedulerParser that is in charge of managing all of the
sub-parsers of the Scheduler. 

Each of the commands of the Scheduler will always return a CommandResult class, that is basically an information
container that stores all the relevant data of the results. This CommandResult object is then passes back up to the
UiManager, where it is then passed to the GUI components for it to be displayed.

#### Design consideration:

##### Aspect: Whether to create a new Parser for Scheduler.
Option 1 **(Current implementation)**: A custom Parser in charge of all **Scheduler** related commands **only**.
Pros: 
- More OOP orientated.
- More defensive programming.
Cons:
- More Parsers to handle by the ParserManager

Option 2: Place the Scheduler related parser together with the rest of the other parsers for other features, like module list, etc.
Pros:
- Faster to implement.
- Less effort needed, simply add on to the existing Parser.
Cons:
- Mess and less readible, hard to distinguish between differnt commands.
- Higher chance of errors, as we are mixing all the different parsers for every feature into a single Parser.
- LONG methods.


### \[Proposed\] Data archiving



### Contact List Management

As a module tracking system, Cap 5 Buddy allows users to manage a list of module-related contacts with ease.
This is especially important since being enrolled in numerous modules results in the need to keep track of
numerous contacts, each with different contact details.

The section below provides details of the implementation of each Contact List function and design considerations
of the contact list features.

#### Contact List Commands

Below is a list of all `Contact` related features:

1. Add a contact: Adds a new contact into the contact list
2. Delete a contact: Deletes a pre-existing contact from the contact list
3. Edit a contact: Edits a pre-existing contact in the contact list
4. Find a contact: Search for contacts using different search parameters


Given below is the class diagram of the `Contact` class:

![ContactClassDiagram](images/Contact/ContactClassDiagram.png)

Figure ?.? Class Diagram for Contact class

#### Details of implementation

##### Add contact feature

The add contact feature creates and adds a new `Contact`, using contact details provided by the users, into the contact list if the contact does not already exist. 
This feature is facilitated by the following classes:
   
 * `AddContactParser`:
   * It implements `AddContactParser#parse()` to parse and validate the user arguments to create a new `Contact`.
   
 * `AddContactCommand`:
   * It implements `AddContactCommand#execute()` which executes the addition of the new contact into `Model`.

Given below is an example usage scenario and how the mechanism for adding contact behaves at each step:
Step 1. `LogicManager` receives the user input `addcontact n/John e/john@gmail.com te/@johndoe` from `Ui`
Step 2. `LogicManager` calls `ContactListParser#parseCommand()` to create an `AddContactParser`
Step 3. Additionally, `ContactListParser` will call the `AddContactParser#parse()` method to parse the command arguments
Step 4. This creates an `AddContactCommand` and `AddContactCommand#execute()` will be invoked by `LogicManager` to add the new contact
Step 5. The `Model#addContact()` operation exposed in the `Model` interface is invoked to add the new contact
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to add a contact works:
![AddContactSequenceDiagram](images/Contact/AddContactSequenceDiagram.png)
Figure ?.? Sequence diagram for the execution of `AddContactCommand`

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddContactCommand` and `AddContactParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the `AddContactCommand`:
![AddContactCommandActivityDiagram](images/Contact/AddContactCommandActivityDiagram.png)
Figure ?.? Activity diagram representing the execution of `AddContactCommand`

##### Design consideration:

##### Aspect: Require users to provide all contact fields when adding a new contact

* **Alternative 1 (current choice):** Require `ContactName` and `Email` to be mandatory fields that must be provided, while leaving `Telegram` as an optional field
  * Pros: This caters to certain contacts who do not have a `Telegram` field, providing more flexibility for users when creating contacts.
  * Cons: This can complicate the process of checking if 2 contacts are the same since we need to consider if the `Telegram` field of a contact 
          is present before the comparison is performed. 
  
* **Alternative 2:** Require `ContactName`, `Email` and `Telegram` to be mandatory fields
  * Pros: The process of checking if 2 contacts are the same by comparing all 3 contact fields will be simpler.
  * Cons: This can create problems for users who want to add a contact that does not have a suitable `Telegram` field

Alternative 1 was chosen since it provides users with greater freedom when creating contacts. Enforcing all contact fields to be 
mandatory can restrict users when adding contacts, hindering user experience.


#### Delete Contact Feature

The delete contact feature deletes a pre-existing `Contact` using the index of the contact on the displayed contact list.
This feature is facilitated by the following classes: 

  * `DeleteContactParser`:
    * It implements `DeleteContactParser#parse()` to parse and validate the contact ID

  * `DeleteContactCommand`:
    * It implements `DeleteContactCommand#execute()` to delete the `Contact` from `Model`

After the user input has been parsed by `DeleteContactParser`, `LogicManager` will execute the delete operation by invoking
`DeleteContactCommand#execute()`. This deletes the target contact by invoking the `Model#deleteContact()` method exposed in the `Model` interface.

Given below is the sequence diagram of how the operation to delete a contact works:
![DeleteContactSequenceDiagram](images/Contact/DeleteContactCommandSequenceDiagram.png)

#### Design consideration:

##### Aspect: Method to delete contact

* **Alternative 1 (current choice):** Delete a contact based on its index in the displayed contact list
  * Pros: Using the contact index allows us to uniquely identify the target contact to delete, reducing the room for possible error
  * Cons: The target contact to be deleted might not be displayed on the contact list and hence the contact index might not be
          readily available. This can inconvenience users who have to search for the contact to retrieve the contact index.
  
* **Alternative 2:** Delete a contact based on the contact name
  * Pros: It can make the deletion process simpler for **users** who can provide the name of the contact without having to execute more commands.
  * Cons: This can complicate the deletion process since contacts with the same name is a possibility. If there are multiple
          contacts with the same provided name, more information of the target contact has to be provided by the user,
          creating more inconvenience for the user as well.
          
Alternative 1 was chosen since it guarantees a unique contact would be provided in every case. This ensures the
contact deletion process is safe and the correct contact is deleted, minimising the room for potential errors.  
          


#### Edit Contact Feature

The edit contact feature edits a pre-existing contact in the contact list using contact details provided by the users.
This feature is facilitated by the following classes:

  * `EditContactParser`: 
    * It implements `EditContactParser#parse()` to parse and validate the provided contact details and contact index
    
  * `EditContactDescriptor`:
    * It stores the contact details which will be used to edit the target contact
    
  * `EditContactCommand`:
    * It implements `EditContactCommand#execute()` to edit the contact in `Model`
    
Given below is an example usage scenario and how the mechanism for editing contact behaves at each step:
Step 1. `LogicManager` receives the user input `editcontact 1 n/John te/@johndoe` from `Ui`
Step 2. `LogicManager` calls `ContactListParser#parseCommand()` to create an `EditContactParser`
Step 3. Additionally, `ContactListParser` will call the `EditContactParser#parse()` method to parse the command arguments
Step 4. This creates an `EditContactCommand` and `EditContactCommand#execute()` will be invoked by `LogicManager` to edit the target contact
Step 5. The `Model#setContact()` operation exposed in the `Model` interface is invoked to replace the target contact with the edited contact
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to edit a contact works:
![EditContactSequenceDiagram](images/Contact/EditContactCommandSequenceDiagram.png)


#### Design consideration:

##### Aspect: How to encapsulate the edited contact details and relay this information to EditContactCommand

* **Alternative 1 (current choice):** Encapsulate all edited contact details in a class `EditContactDescriptor`
  * Pros: Reduces the complexity of the `EditContactCommand` class constructor as the number of parameters for the constructor
          is constant. It also reduces the level of coupling between `EditContactCommand` and `EditContactParser`
  * Cons: Additional method calls are require to store the edited contact details in `EditContactDescriptor` class.
  
* **Alternative 2:** Provide the edited contact details as arguments for the `EditContactCommand` constructor
  * Pros: Fewer method calls are required since `EditContactParser` can directly pass the edited contact arguments to the `EditContactCommand` constructor
  * Cons: This can increase data coupling between `EditContactParser` and `EditContactCommand` which can cause unnecessary changes in
          either class if the other class were to change.

Alternative 1 was chosen as it would make future changes to any class easier and less error-prone, since there
was less coupling between the 2 classes.

##### Implementation of `EditContactCommand`

* **Alternative 1 (current choice):** 
  * Pros: Reduces coupling between the command classes and `EditContactCommand` can be implemented without restrictions,
          or a need to consider how it might affect the other command classes
  * Cons: Additional methods have to be implemented to replace the target contact with the edited contact

* **Alternative 2:** Reuse `DeleteContactCommand` to delete the target contact and `AddContactCommand` to add the edited contact
  * Pros: Reusing other commands would make the implementation of `EditContactCommand` simpler and easier
  * Cons: It increases coupling between the 3 commands and this can cause issues in `EditContactCommand` if either 
          `DeleteContactCommand` or `AddContactCommand` developed bugs or errors. Also, it might affect performance since 
          executing `EditContactCommand` will execute 2 other commands.

Alternative 1 was chosen since it gave more freedom with regard to the implementation of `EditContactCommand` since
we were not restricted to reusing other commands. Less coupling between the classes meant that changes in one class would 
less likely require changes to other classes.


#### Find Contact Feature

The find contact feature is important since sieving through all contacts to search for a specific contact can be 
tedious and not user-friendly. Finding contacts using one search parameter is not meaningful 
as the search results might not be refined and accurate.

The find contact feature searches for contacts using 2 parameters: contact name and/or tag. 
For each search parameter, contacts have to match at least one keyword to fulfil that search criteria.
If multiple parameters are provided, e.g both name and tag keywords, only contacts that fulfil both the name and tag criteria are returned. 

This feature is facilitated by the following classes:

  * `FindContactParser`:
    * It implements `FindContactParser#parse()` to parse and validate the user input
    * It creates predicate objects using the command arguments and adds them to the list of predicates in
      `FindContactCriteria`
    
  * `FindContactCriteria`:
    * It stores all the predicates which will be used to test for matching contacts
    * It implements the following operations:
      * `FindContactCriteria#addPredicate():` Adds a new predicate into the list of predicates 
         to test for matching contacts
      * `FindContactCriteria#getFindContactPredicate():` To compose all predicates into a 
         single predicate
         
    * Predicate objects that can be stored in `FindContactCriteria`:
      * `ContactNameContainsKeywordsPredicate`:
        * Tests if a given contact matches at least one of the name keywords provided (case-insensitive)
      * `ContactContainsTagsPredicate`:
        * Tests if a given contact contains at least one of the search tags provided (case-insensitive)
        
  * `FindContactCommand`:
    * It implements `FindContactCommand#execute()` to find all matching contacts by updating the 
      filtered contact list in `Model` using the predicate from `FindContactCriteria`
       
Given below is an example usage scenario and how the mechanism for finding contact behaves at each step:
Step 1. `LogicManager` receives the user input `findcontact n/John t/friend` from `Ui`
Step 2. `LogicManager` calls `ContactListParser#parseCommand()` to create a `FindContactParser`
Step 3. Additionally, `ContactListParser` will call the `FindContactParser#parse()` method to parse the command arguments
Step 4. This creates a `FindContactCriteria` that stores the created `Predicate` objects to test for matching contacts
Step 4. Additionally, a `FindContactCommand` is created and `FindContactCommand#execute()` will be invoked by `LogicManager` to find matching contacts
Step 5. The `Model#updateFilteredContactList()` operation exposed in the `Model` interface is invoked to update the displayed contact list 
        using the predicate from `FindContactCriteria`
Step 6. A `CommandResult` from the command execution is returned to `LogicManager`

Given below is the sequence diagram of how the operation to find contact works:
![FindContactCommandSequenceDiagram](images/Contact/FindContactCommandSequenceDiagram.png)
Fig ??

Given below is the sequence diagram showing the interaction between `FindContactParser` and `FindContactCriteria`:
![FindContactCriteriaSequenceDiagram](images/Contact/FindContactCriteriaSequenceDiagram.png)


#### Design consideration:

##### Aspect: Storing of predicates in `FindContactCriteria`

* **Alternative 1 (current choice):** Store the predicates as a list of predicates
  * Pros: Composing the predicates into a single predicate is easier as we can simply iterate through the list
          and compose all the predicates.
  * Cons: Using a list means that we will not know the exact predicate objects in the list. This can make testing and
          debugging more complicated. Moreover, we need to enforce checks on the predicate ensure that
          null objects which can cause `NullPointerException` to be thrown are not added.
  
* **Alternative 2:** Store each predicate object as an individual field of `FindContactCriteria`
  * Pros: 
  * Cons: It is tedious to compose the predicates into a single predicate as we have to check each individual field and 
          determine if it is null of if the predicate exists. 

##### Aspect: To implement `FindContactCommand` as a single command or as separate commands for different search parameters

* **Alternative 1 (current choice):**


### \[Proposed\] Calculate CAP feature

#### Proposed Implementation

The proposed calculate CAP function is facilitated by `CalculateCapCommand`. It extends Command with a counter for total
grade points and modular credits, both stored internally `gradePoints` and `modularCredits` respectively. Additionally, it implements the following operations:

* `CalculateCapCommand#accumulate(ModuleList)` - Loops through a given `ModuleList` and updates the grade points and
modular credits count accordingly.

* `CalculateCapCommand#calculateCap()` - Calculates CAP based the grade points and modular credits counter.

The following sequence diagram shows how the calculate cap operation works:
![CalculateCapSequenceDiagram](images/CalculateCapSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `CalculateCapCommand`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Design consideration:

##### Aspect: Information used to calculate cap
* Alternative 1 (current choice): Calculates based on academic information on mods tagged as completed.
    * Pros : Easy to implement
    * Cons : User has to manually input every module taken
    
* Alternative 2 : Prompts user for academic information used for last calculated cap and stores it.
    * Pros : 
     * User does not need to input unnecessary modules.
     * Will use less memory.(e.g Modules that the user is not currently taking does not need to be added by user). 
    * Cons : Will require additional storage.

### TodoList feature

#### Implementation

The TodoList feature has two main component :

* **Containee component** (Task-related classes)
  * `Class Task` - container class to store information about a task
  * `Class TaskName` - wrapper class to store the name of a task
  * `Class Date` - wrapper class to store the date/deadline of a task
  * `Enum Priority` - enum class to represent priority of a task
  * `Enum Status` - enum class to represent the progress status of a task

* **Container component** (List-like classes)
  * Class `UniqueTodoList` - container class for storing tasks
  * Class `TodoList` - wrapper class for UniqueTodoList
  * Interface `ReadOnlyTodoList` - interface for displaying the list on the GUI

##### Containee Component

The Task class mainly functions as a class to store all the informations related to a task i.e. name, tag, priority,
date, and status. It does not have any subclasses.

The Task class supports the following operations :

* Setters for all the field
* Getters for all the field
* `Task#isSameTask()` - checks if two tasks are the same i.e. have the same name 
(weaker than Task#equals() which requires all the fields to be the same)
* `Task#hasSameTag()` - checks if the task has the specified tag
* `Task#hasSamePriority()` - checks if the task has the specified priority
* `Task#hasSameDate()` - checks if the task has the specified date

##### Container Component

The TodoList class is facilitated by UniqueTodoList. The UniqueTodoList is stored internally inside
the TodoList class which act like a wrapper class. 

The TodoList class supports the following operations :

* `TodoList#resetData()` - replaces all data in TodoList with new data.
* `TodoList#hasTask()` - checks if the specified task exist in the list.
* `TodoList#addTask()` - adds a task to the list.
* `TodoList#setTask()` - replaces a task with the specified task.
* `TodoList#removeTask()` - removes the specified task from the list.

The operations above are exposed in the Model interface as :

* `Model#hasTask()`
* `Model#addTask()`
* `Model#setTask()`
* `Model#deleteTask()`

TodoList implements ReadOnlyTodoList which require the following operation :

* `ReadOnlyTodoList#getTodoList()` - returns an ObservableList with type Task that is immutable, and we cannot
  modify the elements.

#### Design Consideration

##### Aspect: Task type

* Alternative 1 (current): <br/>
  Use one concrete class i.e. Task without inheritance involved. The type of the task
  is represented by the Tag field instead.
  
  Pros :
  * Easier to implement
  * Types are not pre-defined i.e. can simply add a different tag to represent different type of task
  
  Cons :
  * All type of task have the same pre-defined field

* Alternative 2 : <br/>
  Use one abstract class i.e. Task with inheritance. Each subclasses represent a type of a Task.
  
  Pros :
  * Difference between type are clear and standardized
  * Can be considered more OOP
  
  Cons :
  * Types must be pre-defined i.e. cannot add new type of classes without adding codes
  
  Alternative 1 is chosen since we prioritize user freedom to create custom type for the task.
  
    
### \[Proposed\] GradeTracker feature

#### Proposed Implementation

The proposed grade tracker feature is an association class used to store additional information for the module. 
The `Assignments` each store their own `assignment name`, `percentage of final grade` and `result`. 

![Structure of the Module List Component](images/GradeTrackerDiagram.png)

When an `assignment` is added, it follows the sequence diagram as shown below. The sequence flows similarly 
to the rest of the project as the command is parsed and then executed.

![Sequence Diagram of the Add Assignment Command](images/AddAssignmentSequenceDiagram.png)

#### Design consideration:

##### Aspect: Format to store the grade for a module
* Alternative 1 : Grade stores CAP.
    * Pros : Easier to integrate with Cap Calculator
    * Cons : User has to manually input CAP and does not know the average from the assignments accumulated
    
* Alternative 2 (current choice): Grade stores the raw score calculated from assignment
    * Pros : Grade can be automatically calculated from the assignment overall percentage for user to view
    * Cons : Requires separate CAP to be stored for Cap Calculator to access

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a number of classes
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* Users can keep track of all module details efficiently.
* Centralize key module details.
* Keep track of study progress effectively.
* Everything can be done in a single app.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ------------------------------------------------------ |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App |
| `* * *`  | user                                       | add a new module               | keep track of the module information easily            |
| `* * *`  | user                                       | delete a module                | remove modules that are completed                      |
| `* *`    | user                                       | find a module by name          | locate details of a module without having to go through the entire list |
| `* *`    | user                                       | add a zoom link to a module    | keep track and retrieve it easily                      |
| `* *`    | user                                       | calculate my cumulative average point   | plan my academic progress for the future      |
| `* *`    | user                                       | add graded assignments       | add the information of the assignments that contributed to my grade      |
| `* *`    | user                                       | edit my graded assignments     | update the information of the assignments I have completed     |
| `* *`    | user                                       | delete graded assignments      | remove the assignments that are do not contribute to my grade anymore|
| `*`      | user who is overloading                    | sort modules by name           | locate a module easily                                 |
| `* * *`  | user                                       | add a task                     | keep track of the tasks that I must complete           |
| `* * *`  | user                                       | delete a task                  | remove a task that has been done                       |
| `* * *`  | user                                       | edit a task                    | make necessary changes to a task                       |
| `* *`    | user                                       | label a task as completed      |                                                        |
| `* *`    | user                                       | find a task                    | find a task easily without looking at the entire list  |
| `* *`    | user                                       | sort tasks based on criteria   | easily manage the tasks by order                       |
| `* *`    | user                                       | filter tasks based on criteria | easily manage the tasks by group                       |
| `*`      | user                                       | reset the status of a task     | change a task from labeled as completed to not completed |
| `*`      | user                                       | archive a task                 | hide irrelevant tasks that might still be useful for future purposes |               

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `CAP5BUDDY` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a new Module**

**MSS**

1. User wants to add a new module to track.
2. User enters the command for adding a module: `add [module name/code]`
3. CAP5BUDDY adds the module into storage and display the success message.

*{More to be added}*

**Use case: Delete a module**

**MSS**

1. User requests to show all the existing modules.
2. CAP5BUDDY displays a list of the modules.
3. User wants to delete a specific module from the list.
4. User enters the command for module deletion: `delete [module name/code]`
4. CAP5BUDDY deletes that module from the list.

**Extensions**
* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or does not exist.

  * 3a1. CAP5BUDDY displays an error message.

    Use case resumes at step 2.

*{More to be added}*

**Use case: Edit a module**

**MSS**

1. User requests to show all the existing modules.
2. CAP5BUDDY displays all the modules in a list.
3. User wants to edit a specific module from the list.
4. User enters the new information for that module.
5. CAP5BUDDY overwrites the existing information of that module.

**Extensions**
* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or does not exist.

  * 3a1. CAP5BUDDY displays an error message.

    Use case resumes at step 2.

* 4a. The new information is empty

  * 4a1. CAP5BUDDY display an error message.

  Use case resumes at step 2.

  *{More to be added}*

**Use case: View a module**

**MSS**

1. User requests to view the list of modules.
2. CAP5BUDDY displays all the modules in a list.
3. User requests to view the information of a specific module from the list.
4. CAP5BUDDY displays all information related to the specified module.

**Extensions**

* 2a. The list is empty.

  Use case ends here.

* 3a. The given index is invalid or null.

  * 3a1. CAP5BUDDY displays an error message.

  Use case resumes at step 2.

**Use case: Input module Cumulative Average Point(CAP) details**

**MSS**

1. User requests to input CAP details(Grade point and Credit) for a module.
2. CAP5BUDDY saves CAP details under the specified module.

Use case ends.

**Extensions**

* 2a. Input contains invalid CAP details.

  * 2a1. CAP5BUDDY displays an error message.
  * 2a2. User enters the correct data.
  Use case resumes at step 1.

**Use case: Calculate Cumulative Average Point(CAP)**

**MSS**

1. User requests to calculate CAP
2. CAP5BUDDY calculates and displays CAP to user.

Use case ends.

**Extensions**

* 2a. One or more modules do not contain details of grade point or credits.

  * 2a1. CAP5BUDDY displays an error message.

  * 2a2. User enters required data.

  Steps 2a1-2a2 are repeated until the data requirements are fulfilled.<br>
  Use case resumes at step 2.

* 3a. One or more modules contain invalid details of grade point or credits.

  * 3a1. CAP5BUDDY displays an error message.

  * 3a2. User enters valid data.

  Steps 2a1-2a2 are repeated until the data requirements are fulfilled.<br>
  Use case resumes at step 2.

  *{More to be added}*

**Use case: Add a task to todo list**

**MSS**

1. User wants to add a task to the todo list.
2. User chooses the type of task to be added.
3. User enters the information regarding the task.
4. CAP5BUDDY saves the given task in the todo list.

Use case ends.

**Extensions**

* 3a. Format for information about the task is invalid.

  * 3a1. CAP5BUDDY displays an error message and ask the user to use the correct format.
  * 3a2. User enters the information with the correct format.

  Use case resumes at step 4

  *{More to be added}*

**Use case: Delete a task in the todo list**

**MSS**

1. User wants to delete a task in the todo list.
2. User chooses the task to be deleted.
3. CAP5BUDDY shows a prompt message asking if the user really wants to delete the task.
4. User clicks the "YES" button.
5. CAP5BUDDY deletes the task from the todo list.

Use case ends.

**Extensions**

* 4a. User accidentally clicked the "NO" button.

  * 4a1. The prompt message disappears and CAP5BUDDY does not delete the task.

  Use case ends.

  *{More to be added}*

**Use case: Sort task in the todo list**

**MSS**

1. User wants to sort the tasks in the todo list.
2. User chooses the basis for sorting the tasks.
3. CAP5BUDDY displays the sorted tasks based on the chosen basis.

Use case ends.

  *{More to be added}*

**Use case: Find a task in the todo list**

**MSS**

1. User wants to find a task in the todo list.
2. User chooses whether to find task based on date or keyword.
3. User enters the date or keyword.
4. CAP5BUDDY displays all task based on the user input from step 3.

Use case ends.

**Extensions**

* 3a. User inputs the date with an incorrect format.

  * 3a1. CAP5BUDDY displays an error message and ask the user to use the correct format.
  * 3a2. User enters the date with the correct format.

  Use case resumes at step 4

  *{More to be added}*

**Use case: Archive a task in the todo list**

**MSS**

1. User wants to archive a task in the todo list.
2. CAP5BUDDY shows a prompt message asking if the user is sure to archive the task.
3. User clicks the "YES" button.
4. CAP5BUDDY archives the task.

Use case ends.

**Extensions**

* 3a. User accidentally clicked the "NO" button.

  * 3a1. The prompt message disappears and CAP5BUDDY does not archive the task.

  Use case ends.

  *{More to be added}*

**Use Case: View all contact details of a lecturer**

  **MSS**
   1. User requests to view all contact details of a lecturer.
   2. User provides the name of the lecturer.
   3. CAP5BUDDY searches for the specified lecturer from storage.
   4. CAP5BUDDY retrieves all contact details of the lecturer from storage.
   5. CAP5BUDDY displays the desired contact details.

  **Extensions**

   * 3a. The specified lecturer name does not exist.

     * CAP5BUDDY displays an error message.

     Use case ends.

  **Use Case: View the email of a Lecturer**

  **MSS**
  1. User requests to view the email of a lecturer.
  2. User provides the name of the lecturer.
  3. CAP5BUDDY searches for the specified lecturer from storage.
  4. CAP5BUDDY retrieves the email of the lecturer from storage.
  4. CAP5BUDDY displays the desired email address.

  **Extensions**

  * 3a. The specified lecturer name does not exist.

    * CAP5BUDDY displays an error message.

    Use case ends.

  **Use Case: View the hand phone contact of a peer**

  **MSS**
  1. User requests to view the hand phone number of a peer.
  2. User provides the name of the peer.
  3. CAP5BUDDY searches for the specified peer from storage.
  4. CAP5BUDDY retrieves the hand phone contact of the peer from storage.
  4. CAP5BUDDY displays the desired hand phone contact.

  **Extensions**

* 3a. The specified peer name does not exist.

  * CAP5BUDDY displays an error message.

  Use case ends.

* 4a. The specified peer does not have a hand phone contact saved.

  * CAP5BUDDY displays an error message.

  Use case ends.

**Use Case: Edit the email of a TA**

  **MSS**
  1. User requests to edit the email of a TA.
  2. User provides the name of the TA.
  3. CAP5BUDDY searches for the specified TA from storage.
  4. User provides the new email to replace the existing email.
  5. CAP5BUDDY replaces the email of the TA with the user provided email.
  6. CAP5BUDDY displays the success message.

  **Extensions**

  * 3a. The specified TA name does not exist.

    * CAP5BUDDY displays an error message.

    Use case ends.

  * 4a. The provided email address is empty or null.

    * CAP5BUDDY displays an error message.

    Use case ends.

    *{More to be added}*

**Use Case: Add assignment to CAP5BUDDY**

  **MSS**
   1. User requests to add an assignment to a module in CAP5BUDDY.
   2. CAP5BUDDY retrieves module from module list.
   3. CAP5BUDDY creates and adds assignment to the gradetracker in the module retrieved.
   4. CAP5BUDDY updates module in module list.
   5. CAP5BUDDY displays success message.

  **Extensions**

 * 2a. The module to add to is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

 * 3a. The given grade is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

**Use Case: View grades for a module**

  **MSS**
  1. User requests to view grades for a module.
  2. CAP5BUDDY retrieves current grades.
  3. CAP5BUDDY displays current grades.

  **Extensions**

  * 3a. The current list of grades is empty.

    * CAP5BUDDY displays an error message.

      Use case ends.


**Use Case: Edit assignment in CAP5BUDDY**

  **MSS**
  1. User requests to edit an assignment in a module in CAP5BUDDY.
  2. CAP5BUDDY retrieves the module.
  3. CAP5BUDDY retrieves the assignment requested from the grade tracker in the module.
  4. User requests to edit the assignment retrieved.
  5. CAP5BUDDY edits the assignment.
  6. CAP5BUDDY saves the edited assignment in the module.
  7. CAP5BUDDY displays success message.

  **Extensions**

  * 2a. The given module is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

  * 3a. The given assignment is invalid.

    * CAP5BUDDY displays an error message.

      Use case ends.

  *{More to be added}*

**Use case: Delete an assignment**

   **MSS**
   1. User requests to delete an assignment in a module in CAP5BUDDY.
   2. CAP5BUDDY retrieves the module.
   3. CAP5BUDDY retrieves the assignment requested from the grade tracker in the module.
   4. CAP5BUDDY deletes the assignment.
   5. CAP5BUDDY updates the grade tracker in the module.
   4. CAP5BUDDY displays success message.

   **Extensions**

   * 3a. The provided assignment is invalid.

      * CAP5BUDDY displays an error message.

        Use case ends.

   *{More to be added}*

**Use case: Add an event**

  **MSS**
  1. User request to create and add a new event into the tracker.
  2. CAP5BUDDY creates and add the event into the list.

  **Extensions**
  * 1a. The provide event information is invalid, missing date and time.

       * CAP5BUDDY displays an error message.

          Use case resumes at step 1.

**Use case: Edit an event**

  **MSS**
  1. User requests to view all existing events.
  2. CAP5BUDDY displays the list of all events.
  3. User requests to modify an existing event.
  4. CAP5BUDDY displays the newly modified event.

  **Extensions**
  * 3a. The newly inputted information is invalid format or empty.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

  * 3b. The requested event does not exist.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

**Use case: Delete an event**

  **MSS**
  1. User requests to view all existing events in the list.
  2. CAP5BUDDY displays all the events.
  3. User requests to remove a specific event from the list.
  4. CAP5BUDDY shows a success message and shows the new list.

  **Extensions**
  * 3a. The event to be removed does not exist.

    * CAP5BUDDY displays an error message.

      Use case resumes at step 2.

**Use case: Mark an event as completed**

  **MSS**
  1. User requests to show all events.
  2. CAP5BUDDY displays all tracked events.
  3. User requests to mark a specific event as completed.
  4. CAP5BUDDY shows the event as completed.

  **Extensions**

  * 3a. The event requested does not exist.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

  * 3b. The event requested is already marked as completed/

    * CAP5BUDDY does nothing.

      Use case resumes at step 2.

**Use case: Push the event back**

  **MSS**
  1. User requests to show all events.
  2. CAP5BUDDY displays all currently tracked events.
  3. User requests to push the event back.
  4. CAP5BUDDY shows the new date and time of the event.

  **Extensions**

  * 3a. The requested event does not exist.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

  * 3b. The new date and time is invalid.

    * CAP5BUDDY shows an error message.

      Use case resumes at step 2.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3.  A user with not be able to access and modify other users' data files.
4.  Should be usable by any novice that has never used this program before.
5.  The name of the modules should not exceed 40 characters.
6.  Should not contain more than 100 modules at 1 time.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Module List**: A list of all modules currently being tracked and stored in the CAP5BUDDY.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
