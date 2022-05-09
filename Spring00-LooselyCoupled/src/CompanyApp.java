import implementation.Mentor;
import services.FullTimeMentor;
import services.MentorAccount;
import services.PartTimeMentor;

public class CompanyApp {

    public static void main(String[] args) {
        FullTimeMentor fullTimeMentor = new FullTimeMentor();
        PartTimeMentor partTimeMentor = new PartTimeMentor();

        MentorAccount mentorAccount = new MentorAccount(fullTimeMentor);
        mentorAccount.manageAccount();
    }
}
