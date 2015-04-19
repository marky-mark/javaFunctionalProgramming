package desingingWithLambdas;

import org.junit.Test;

public class MailerTest {

    @Test
    public void testMailer() throws Exception {
        Mailer mailer = new Mailer();
        mailer.from("build@agiledeveloper.com");
        mailer.to("venkats@agiledeveloper.com");
        mailer.subject("build notification");
        mailer.body("...your code sucks...");
        mailer.send();

        new MailBuilder()
                .from("build@agiledeveloper.com")
                .to("venkats@agiledeveloper.com")
                .subject("build notification")
                .body("...it sucks less...")
                .send();
    }
}