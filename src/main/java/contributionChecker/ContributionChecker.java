package contributionChecker;

import cmd.CommandPrompt;
import parser.Parser;
import projectFiles.FileInfo;
import storage.Storage;
import ui.Ui;
import user.User;
import user.UserManagement;

import java.util.ArrayList;

public class ContributionChecker {
    private FileInfo fileInfo;
    private CommandPrompt commandPrompt;
    private Parser parser;
    private UserManagement userManagement;
    private Ui ui;
    private Storage storage;

    private Process process;
    private ArrayList<String> infoFromGitBlame = null;


    public ContributionChecker(FileInfo fileInfo) {
        this.fileInfo = fileInfo;

        commandPrompt = new CommandPrompt();
        parser = new Parser();
        userManagement = new UserManagement();
        ui = new Ui();
        storage = new Storage();
    }

    public void run() {
        process = null;

        callCommandPrompt_GitBlame();
        callParser_ReadAndParseProcess();
        // infoFromGitBlame = ksw95 1 AAAAA ksw95 2 BBBBB ...
        // todo add the data to who and what?
        callFileInfo_AddLines();
        callUserManagement_addUser();

        // todo contribution check add mostLineContributor mostCharContributor

        callStorage_doSomething();
        callUi_getContributionReport();
    }

    private void callCommandPrompt_GitBlame() {
        try {

            process = commandPrompt.gitBlame(fileInfo.getDrive(), fileInfo.getPath(), fileInfo.getName());
            System.out.println("Command Prompt Git Blame complete\n");
        } catch (Exception e) {
            // todo improve error handling later
            System.out.println("Command Prompt has error");
            System.out.println("replace this line with something more useful later");
            e.printStackTrace();
        }
    }

    private void callParser_ReadAndParseProcess() {
        try {
            infoFromGitBlame = parser.readAndParseLine(process);
            System.out.println("Parser complete\n");
        } catch (Exception e) {
            // todo improve error handling later
            System.out.println("Parser has error");
            System.out.println("replace this line with something more useful later");
            e.printStackTrace();
        }
    }

    private void callFileInfo_AddLines(){
        //fileInfo.addFileLines(infoFromGitBlame);
    }

    private void callUserManagement_addUser() {
        userManagement.addUser(infoFromGitBlame);
        // todo add contribution
    }

    private void callStorage_doSomething() {
        // todo call Storage to output
        storage.run();
    }

    private void callUi_getContributionReport() {
        // todo call Ui to print useful info
        ui.getContributionReport();
    }
}
