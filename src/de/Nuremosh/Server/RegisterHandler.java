package de.Nuremosh.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class RegisterHandler {
    private Path path = FileSystems.getDefault().getPath("database.txt");

    public void createNewUser(String namePassword) {
        try {
            OutputStream out = Files.newOutputStream(path, CREATE, APPEND);
            out.write((namePassword + "\n").getBytes());
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isUserInDatabase(String namePassword) {
        try {
            Stream<String> in = Files.lines(path);
            //Not so safe but it's just a game
            boolean res = in.anyMatch(s -> s.contains(namePassword));
            in.close();
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
