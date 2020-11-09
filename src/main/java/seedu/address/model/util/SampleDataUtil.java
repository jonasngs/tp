package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.contact.Contact;
// import seedu.address.model.contact.ContactName;
// import seedu.address.model.contact.Email;
// import seedu.address.model.contact.Telegram;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Telegram;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Contact[] getSampleContacts() {
        return new Contact[] {
            new Contact(new ContactName("Alex Yeoh"), new Email("alexyeoh@example.com"),
                    new Telegram("@alexyeoh"), getTagSet("friends"), false),
            new Contact(new ContactName("Bernice Yu"), new Email("berniceyu@example.com"),
                    new Telegram("@bernice"), getTagSet("colleagues", "friends"), true),
            new Contact(new ContactName("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                    new Telegram("@charlotte"), getTagSet("neighbours"), true),
            new Contact(new ContactName("David Li"), new Email("lidavid@example.com"),
                    new Telegram("@david"), getTagSet("family"), false),
            new Contact(new ContactName("Irfan Ibrahim"), new Email("irfan@example.com"),
                    new Telegram("@irfan"), getTagSet("classmates"), false),
            new Contact(new ContactName("Roy Balakrishnan"), new Email("royb@example.com"),
                    new Telegram("@roybala"), getTagSet("colleagues"), true)
        };
    }

    /**
     * Generates list of sample modules
     */
    public static Module[] getSampleModules() {
        GradeTracker gradeTrackerCompleted = new GradeTracker(5.0);
        GradeTracker gradeTrackerWithAssignments = new GradeTracker();
        gradeTrackerWithAssignments.addAssignment(new Assignment(new AssignmentName("Quiz1"),
                new AssignmentPercentage(15), new AssignmentResult(70)));
        Module cs2030 = new Module(new ModuleName("CS2030"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2030")),
                gradeTrackerCompleted, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs2101 = new Module(new ModuleName("CS2101"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs2105 = new Module(new ModuleName("CS2105"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2105")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module cs1101S = new Module(new ModuleName("CS1101S"), getZoomLinkMap(new ModuleLesson("Lecture"),
                new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        Module is1103 = new Module(new ModuleName("IS1103"),
                getZoomLinkMap(new ModuleLesson("Lecture"), new ZoomLink("https://nus-sg.zoom.us/CS2101")),
                gradeTrackerWithAssignments, getTagSet("CoreModule"), new ModularCredits(4));
        return new Module[] {cs2030, cs2101, cs2105, cs1101S, is1103
        };
    }

    /**
     * Generates list of sample archived modules
     */
    public static Module[] getSampleArchivedModules() {
        GradeTracker gradeTrackerWithAssignments = new GradeTracker();
        gradeTrackerWithAssignments.addAssignment(new Assignment(new AssignmentName("Quiz1"),
                new AssignmentPercentage(15), new AssignmentResult(70)));
        Module es2660 = new Module(new ModuleName("ES2660"),
                getTagSet("FluffModule", "Completed"), new ModularCredits(4));
        Module cs3230 = new Module(new ModuleName("CS3230"),
                getTagSet("CoreModule", "Completed"), new ModularCredits(4));
        return new Module[] {es2660, cs3230
        };
    }


    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleContactList = new ContactList();
        for (Contact sampleContact : getSampleContacts()) {
            sampleContactList.addContact(sampleContact);
        }
        return sampleContactList;
    }

    /**
     * Returns a ReadOnlyModuleList version of sample modules
     */
    public static ReadOnlyModuleList getSampleModuleList() {
        ModuleList sampleMl = new ModuleList();
        for (Module sampleModule : getSampleModules()) {
            sampleMl.addModule(sampleModule);
        }
        return sampleMl;
    }

    /**
     * Returns a ReadOnlyModuleList version of sample archived modules
     */
    public static ReadOnlyModuleList getSampleArchivedModuleList() {
        ModuleList sampleMl = new ModuleList();
        for (Module sampleModule : getSampleArchivedModules()) {
            sampleMl.addModule(sampleModule);
        }
        return sampleMl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
    /**
     * Returns a hash map containing the ModuleLesson and ZoomLink given.
     */
    public static HashMap<ModuleLesson, ZoomLink> getZoomLinkMap(ModuleLesson lesson, ZoomLink zoomLink) {
        HashMap<ModuleLesson, ZoomLink> zoomLinkMap = new HashMap<>();
        zoomLinkMap.put(lesson, zoomLink);
        return zoomLinkMap;
    }
    public static ReadOnlyEventList getSampleEventList() {
        EventList sampleEv = new EventList();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Important"));
        LocalDateTime dateTime = LocalDateTime.parse("10-9-2020 1200", DateTimeFormatter.ofPattern("d-M-uuuu HHmm"));
        Event sampleEventOne = new Event(new EventName("CS2100 Assignment 1"), new EventTime(dateTime), tags);
        sampleEv.addEvent(sampleEventOne);
        tags.add(new Tag("Urgent"));
        dateTime = LocalDateTime.parse("13-11-2020 1600", DateTimeFormatter.ofPattern("d-M-uuuu HHmm"));
        Event sampleEventTwo = new Event(new EventName("CS2103T PE"), new EventTime(dateTime), tags);
        sampleEv.addEvent(sampleEventTwo);
        return sampleEv;
    }

    public static ReadOnlyTodoList getSampleTodoList() {
        TodoList todoList = new TodoList();
        for (Task t : getSampleTasks()) {
            todoList.addTask(t);
        }
        return todoList;
    }
    public static Task[] getSampleTasks() {
        Task first = new Task(new TaskName("Finish Lab 01"));
        first = first.setTags(new HashSet<>(Arrays.asList(new Tag("Lab"), new Tag("CS2030"))));
        first = first.setPriority(Priority.valueOf("HIGH"));
        first = first.setDate(new Date(LocalDate.now().plusDays(3).toString()));
        first = first.setStatus(Status.NOT_COMPLETED);
        first = first.setDateCreated(LocalDate.now().minusDays(1));

        Task second = new Task(new TaskName("Finish Lab 02"));
        second = second.setTags(new HashSet<>(Arrays.asList(new Tag("Lab"), new Tag("CS2100"))));
        second = second.setPriority(Priority.valueOf("NORMAL"));
        second = second.setDate(new Date(LocalDate.now().plusDays(4).toString()));
        second = second.setStatus(Status.NOT_COMPLETED);
        first = first.setDateCreated(LocalDate.now().minusDays(2));

        Task third = new Task(new TaskName("Finish Assignment03"));
        third = third.setTags(new HashSet<>(Arrays.asList(new Tag("Assignment"), new Tag("CS2105"))));
        third = third.setPriority(Priority.valueOf("LOW"));
        third = third.setDate(new Date(LocalDate.now().plusDays(5).toString()));
        third = third.setStatus(Status.COMPLETED);
        first = first.setDateCreated(LocalDate.now().minusDays(1));

        return new Task[] {first, second, third};
    }
}
