package resources;

import org.junit.Test;

public class FileWriterEAMTest {

    @Test
    public void testWrite() throws Exception {
        /*FileWriterEAM.use("build/eam.txt", new UseInstance<FileWriterEAM, IOException>() {
            @Override
            public void accept(FileWriterEAM writerEAM) throws IOException {
                writerEAM.writeStuff("sweet");
            }
        });*/

        FileWriterEAM.use("build/eam.txt", writerEAM -> writerEAM.writeStuff("sweet"));
    }
}