package desingingWithLambdas;

import org.junit.Test;

public class FluentMailerTest {

    @Test
    public void testFluentMailer() throws Exception {
        FluentMailer.send(mailer ->
                mailer.from("build@agiledeveloper.com")
                        .to("venkats@agiledeveloper.com")
                        .subject("build notification")
                        .body("...much better..."));

    }
}