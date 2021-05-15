package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.common.InteractiveService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final InteractiveService interactiveService;
    private final JobLauncher jobLauncher;
    private final Job transferJob;

    @ShellMethod(value = "Print mongo authors", key = {"ma"})
    public String printAllMongoAuthors() {
        interactiveService.printAllMongoAuthors();
        return "End of author list";
    }

    @ShellMethod(value = "Print mongo books", key = {"mb"})
    public String printAllMongoBooks() {
        interactiveService.printAllMongoBooks();
        return "End of book list";
    }

    @ShellMethod(value = "Print mongo genres", key = {"mg"})
    public String printAllMongoGenres() {
        interactiveService.printAllMongoGenres();
        return "End of genre list";
    }

    @ShellMethod(value = "Print h2 authors", key = {"ha"})
    public String printAllH2Authors() {
        interactiveService.printAllH2Authors();
        return "End of author list";
    }

    @ShellMethod(value = "Print h2 books", key = {"hb"})
    public String printAllH2Books() {
        interactiveService.printAllH2Books();
        return "End of book list";
    }

    @ShellMethod(value = "Print h2 genres", key = {"hg"})
    public String printAllH2Genres() {
        interactiveService.printAllH2Genres();
        return "End of genre list";
    }

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(transferJob, new JobParameters());
        System.out.println(execution);
    }
}
