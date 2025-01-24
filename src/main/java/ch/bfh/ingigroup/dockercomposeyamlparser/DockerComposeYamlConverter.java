package ch.bfh.ingigroup.dockercomposeyamlparser;

public class DockerComposeYamlConverter {

    public static DockerComposeFile parse(String yaml) {

        return new DockerComposeFile(yaml);
    }

    public static String dump(DockerComposeFile dockerComposeFile) {

        return dockerComposeFile.getContent();
    }
}
